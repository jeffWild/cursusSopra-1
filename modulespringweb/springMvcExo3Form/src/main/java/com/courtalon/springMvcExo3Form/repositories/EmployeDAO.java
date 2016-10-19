package com.courtalon.springMvcExo3Form.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springMvcExo3Form.metier.Departement;
import com.courtalon.springMvcExo3Form.metier.Employe;

public class EmployeDAO implements IEmployeDAO {
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}
	

	/* (non-Javadoc)
	 * @see com.courtalon.springMvcExo3Form.repositories.IEmployeDAO#findAll()
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Employe> findAll() {
		TypedQuery<Employe> q = em.createQuery("select e from Employe as e", Employe.class);
		/*q.setFirstResult(0);
		q.setMaxResults(3);*/
		return q.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Employe> findByDepartement(int did) {
		TypedQuery<Employe> q = em.createQuery(
						"select e from Employe as e where e.departement.id=:did",
						Employe.class);
		q.setParameter("did", did);
		return q.getResultList();
	}

	
	/* (non-Javadoc)
	 * @see com.courtalon.springMvcExo3Form.repositories.IEmployeDAO#findByID(int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Employe findByID(int id) {
		return em.find(Employe.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.courtalon.springMvcExo3Form.repositories.IEmployeDAO#save(com.courtalon.springMvcExo3Form.metier.Employe)
	 */
	@Override
	@Transactional
	public Employe save(Employe e) {
		Employe old = em.find(Employe.class, e.getId());
		
		// reassocie le departement avant de sauvegarder l'employe
		if (e.getDepartementID() != 0)
			e.setDepartement(em.find(Departement.class, e.getDepartementID()));
		else
			e.setDepartement(null);
			
		if (old == null) {
			em.persist(e);
			return e;
		}
		else {
			return em.merge(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.courtalon.springMvcExo3Form.repositories.IEmployeDAO#remove(int)
	 */
	@Override
	@Transactional
	public boolean remove(int id) {
		Employe old = em.find(Employe.class, id);
		if (old != null) {
			em.remove(old);
			return true;
		}
		return false;
	}
	
}
