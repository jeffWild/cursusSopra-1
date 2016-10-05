package com.courtalon.jdbcMetier.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.courtalon.jdbcMetier.metier.Produit;

public class FenetreEdit extends JFrame {

	private Produit produit;
	public Produit getProduit() {return produit;}
	public void setProduit(Produit produit) {
		this.produit = produit;
		fillFromProduit();
	}
	
	private FenetreProduits parent;
	private JTextField champNom;
	private JTextField champPrix;
	private JTextField champPoids;
	private JButton boutonSave;
	
	// cette fonction rempli les champs du formulaire depuis l'objet produit
	private void fillFromProduit() {
		champNom.setText(produit.getNom());
		champPrix.setText("" + produit.getPrix());
		champPoids.setText("" + produit.getPoids());
	}
	
	// cette fonction rempli l'objet produit a partir des champs du formulaire
	private void fillFromFields() {
		produit.setNom(champNom.getText());
		produit.setPrix(Double.parseDouble(champPrix.getText()));
		produit.setPoids(Double.parseDouble(champPoids.getText()));
	}
	
	public FenetreEdit(FenetreProduits parent) {
		super("edition produit");
		
		this.parent = parent;
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		setLayout(bl);
		
		JPanel ligne;
		BoxLayout blLigne;
		
		// ma premiere ligne de "formulaire"
		ligne = new JPanel();
		blLigne = new BoxLayout(ligne, BoxLayout.X_AXIS);
		ligne.setLayout(blLigne);
		ligne.add(new JLabel("nom produit"));
		champNom = new JTextField(30);
		ligne.add(champNom);
		add(ligne);

		ligne = new JPanel();
		blLigne = new BoxLayout(ligne, BoxLayout.X_AXIS);
		ligne.setLayout(blLigne);
		ligne.add(new JLabel("prix produit"));
		champPrix = new JTextField(30);
		ligne.add(champPrix);
		add(ligne);
		
		ligne = new JPanel();
		blLigne = new BoxLayout(ligne, BoxLayout.X_AXIS);
		ligne.setLayout(blLigne);
		ligne.add(new JLabel("poids produit"));
		champPoids = new JTextField(30);
		ligne.add(champPoids);
		add(ligne);
		
		boutonSave = new JButton("sauvegarder");
		add(boutonSave);
		
		// ici, on fournis une classe anonyme interne qui implement ActionListener
		// sous la forme d'un lambda (intrudit avec java 8)
		boutonSave.addActionListener((e) -> {
			fillFromFields();
			parent.saveProduit(produit);
			this.setVisible(false);
		});
			
			
	}
	
}
