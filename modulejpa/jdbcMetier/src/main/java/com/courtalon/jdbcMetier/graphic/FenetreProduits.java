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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.courtalon.jdbcMetier.metier.Produit;
import com.courtalon.jdbcMetier.utils.ProduitDAO;

public class FenetreProduits extends JFrame 
							implements ActionListener, ListSelectionListener
{
	public static final String ACTION_LOAD = "load";
	public static final String ACTION_CLEAR = "clear";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_CREATE = "create";
	
	private JButton boutonLoad;
	private JButton boutonClear;
	private JButton boutonEdit;
	private JButton boutonCreate;
	
	private JList<Produit> listeProduits;
	private DefaultListModel<Produit> dataProduits;
	
	private ProduitDAO produitDAO;
	private FenetreEdit fenetreEdit;
	
	
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
		
		// bouton d'edition et creation de produits
		boutonEdit = new JButton("editer produit");
		boutonCreate = new JButton("creer produit");
		boutonEdit.setActionCommand(ACTION_EDIT);
		boutonCreate.setActionCommand(ACTION_CREATE);
		boutonEdit.addActionListener(this);
		boutonCreate.addActionListener(this);
		
		panelHaut.add(boutonEdit);
		panelHaut.add(boutonCreate);
		
		fenetreEdit = new FenetreEdit(this);
		
		boutonEdit.setEnabled(false); // desactive le bouton
		
		// prevenir la fenetre qunad la selection change
		listeProduits.addListSelectionListener(this); 
	}
	
	public void saveProduit(Produit p) {
		// sauver le produit
		System.out.println("sauvegarde de " + p);
		produitDAO.save(p);
		refreshFromBase();
	}

	// cette méthode sera appelée par tout composant ayant notre fenetre 
	// en ActionListener (ici, nos 2 boutons)
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case ACTION_LOAD:
				refreshFromBase();
				break;
			case ACTION_CLEAR:
				dataProduits.clear();
				break;
			case ACTION_EDIT:
				// je recupere le produit a editer
				fenetreEdit.setProduit( listeProduits.getSelectedValue());
				// j'affiche la fenetre d'edition
				fenetreEdit.setVisible(true);
				break;
			case ACTION_CREATE:
				fenetreEdit.setProduit(new Produit(0, "", 0.0, 0.0));
				fenetreEdit.setVisible(true);
				break;
		}
		
	}

	private void refreshFromBase() {
		dataProduits.clear();
		// je récupere la liste des produits depuis la base
		List<Produit> produits = produitDAO.findAll();
		// et je copie tous les produit dans ma JList
		for (Produit p : produits) {
			dataProduits.addElement(p);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// activer/desactiver le bouton edit suivant l'etat de la selection da la liste
		boutonEdit.setEnabled(!listeProduits.isSelectionEmpty());
	}
	

}
