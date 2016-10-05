package com.courtalon.jdbcMetier.graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.courtalon.jdbcMetier.metier.Produit;
import com.courtalon.jdbcMetier.utils.ProduitDAO;

public class FenetreProduits extends JFrame implements ActionListener
{
	public static final String ACTION_LOAD = "load";
	public static final String ACTION_CLEAR = "clear";
	
	private JButton boutonLoad;
	private JButton boutonClear;
	
	private JList<Produit> listeProduits;
	private DefaultListModel<Produit> dataProduits;
	
	private ProduitDAO produitDAO;
	
	public FenetreProduits(ProduitDAO produitDAO) {
		super("super produit bio");
		
		this.produitDAO = produitDAO;
		
		setSize(500, 400);
		// centrer la fenetre
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// decoupe la fenetre en 5 zones, NORTH, SOUTH, EAST, WEST, CENTER
		setLayout(new BorderLayout());
		
		dataProduits = new DefaultListModel<>();
		// si j'ajoute des produits dans dataProduit
		// cela rafraichira la JList listeProduits
		listeProduits = new JList<>(dataProduits);
		
		// j'ajoute ma liste dans un paneau défilant, au centre de la fenetre
		add(new JScrollPane(listeProduits), BorderLayout.CENTER);
		
		// ce panel contiendra mes boutons de commande
		JPanel panelHaut = new JPanel();
		// ce layout aligne les composant horizontalement
		BoxLayout bl = new BoxLayout(panelHaut, BoxLayout.X_AXIS);
		panelHaut.setLayout(bl);
		
		// je place ce panel au nord de la fenetre
		add(panelHaut, BorderLayout.NORTH);
		
		boutonLoad = new JButton("charger produits");
		panelHaut.add(boutonLoad);
		
		boutonClear = new JButton("vider liste");
		panelHaut.add(boutonClear);
		
		boutonLoad.setActionCommand(ACTION_LOAD);
		boutonClear.setActionCommand(ACTION_CLEAR);
		// ma fenetre ecoute a l'action par defaut de mon bouton
		boutonLoad.addActionListener(this);
		boutonClear.addActionListener(this);
	}

	// cette méthode sera appelée par tout composant ayant notre fenetre 
	// en ActionListener (ici, nos 2 boutons)
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case ACTION_LOAD:
				dataProduits.clear();
				// je récupere la liste des produits depuis la base
				List<Produit> produits = produitDAO.findAll();
				// et je copie tous les produit dans ma JList
				for (Produit p : produits) {
					dataProduits.addElement(p);
				}
				break;
			case ACTION_CLEAR:
				dataProduits.clear();
				break;
		}
		
	}
	

}
