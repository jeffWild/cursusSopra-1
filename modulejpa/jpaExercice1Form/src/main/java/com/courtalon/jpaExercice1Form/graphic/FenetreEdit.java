package com.courtalon.jpaExercice1Form.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.courtalon.jpaExercice1Form.metier.Tache;

public class FenetreEdit extends JFrame {

	private Tache tache;
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
		fillFromTache();
	}

	
	private FenetreTaches parent;
	private JTextField champDescription;
	private JTextField champPriorite;
	private JTextField champContexte;
	private JButton boutonSave;
	
	private void fillFromTache() {
		champDescription.setText(tache.getDescription());
		champPriorite.setText("" + tache.getPriorite());
		champContexte.setText(tache.getContexte());
	}
	
	
	private void fillFromFields() {
		tache.setDescription(champDescription.getText());
		tache.setPriorite(Integer.parseInt(champPriorite.getText()));
		tache.setContexte(champContexte.getText());
	}
	
	public FenetreEdit(FenetreTaches parent) {
		super("edition tache");
		
		this.parent = parent;
		setSize(300, 150);
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
		ligne.add(new JLabel("description tache"));
		champDescription = new JTextField(30);
		ligne.add(champDescription);
		add(ligne);

		ligne = new JPanel();
		blLigne = new BoxLayout(ligne, BoxLayout.X_AXIS);
		ligne.setLayout(blLigne);
		ligne.add(new JLabel("priorite tache"));
		champPriorite = new JTextField(30);
		ligne.add(champPriorite);
		add(ligne);
		
		ligne = new JPanel();
		blLigne = new BoxLayout(ligne, BoxLayout.X_AXIS);
		ligne.setLayout(blLigne);
		ligne.add(new JLabel("contexte tache"));
		champContexte = new JTextField(30);
		ligne.add(champContexte);
		add(ligne);
		
		boutonSave = new JButton("sauvegarder");
		add(boutonSave);
		
		// ici, on fournis une classe anonyme interne qui implement ActionListener
		// sous la forme d'un lambda (introduit avec java 8)
		boutonSave.addActionListener((e) -> {
			fillFromFields();
			parent.saveTache(tache);
			this.setVisible(false);
		});
			
			
	}
	
}
