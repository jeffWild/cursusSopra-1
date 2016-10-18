package com.courtalon.firstSpringMvcForm.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.firstSpringMvcForm.metier.Message;

public class MessageDAO implements IMessageDAO {

	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}
	
	
	/* (non-Javadoc)
	 * @see com.courtalon.firstSpringMvcForm.repositories.IMessageDAO#findAll()
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Message> findAll() {
		return em.createQuery("select m from Message as m", Message.class)
				 .getResultList();
	}
	// readOnly permettra a hibernate d'optimiser un peu mieux ses requettes
	// attention, a utiliser uniquement si vous etes SUR que la requete ne
	// modifie pas la base
	/* (non-Javadoc)
	 * @see com.courtalon.firstSpringMvcForm.repositories.IMessageDAO#findByID(int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Message findByID(int id) {
		return em.find(Message.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.courtalon.firstSpringMvcForm.repositories.IMessageDAO#save(com.courtalon.firstSpringMvcForm.metier.Message)
	 */
	@Override
	@Transactional
	public Message save(Message m) {
		Message old = em.find(Message.class, m.getId());
		if (old == null) {
			em.persist(m);
			return m;
		}
		else {
			return em.merge(m);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.courtalon.firstSpringMvcForm.repositories.IMessageDAO#remove(int)
	 */
	@Override
	@Transactional
	public boolean remove(int id) {
		Message m = em.find(Message.class, id);
		if (m != null) {
			em.remove(m);
			return true;
		}
		return false;
	}
	
	
}
