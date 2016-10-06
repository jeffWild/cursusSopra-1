package com.courtalon.jpaExercice1Form.graphic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.courtalon.jpaExercice1Form.metier.Tache;
import com.courtalon.jpaExercice1Form.utils.TacheDAO;

public class FenetreTaches extends JFrame 
						   implements ActionListener, ListSelectionListener, DocumentListener
{
	public static final String ACTION_LOAD = "load";
	public static final String ACTION_CLEAR = "clear";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_CREATE = "create";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_TRI = "tri";
	
	private JButton boutonLoad;
	private JButton boutonClear;
	private JButton boutonEdit;
	private JButton boutonCreate;
	private JButton boutonDelete;
	
	private DefaultListModel<Tache> dataTaches;
	private JList<Tache> listeTaches;
	
	private FenetreEdit fenetreEdit;
	
	private TacheDAO tacheDAO;
	
	/*
	 * BONUS, tri et filtrage
	 * 
	 */
	private JCheckBox triPriorite;
	private JTextField filtreContexte;
	// la liste des taches récupérées depuis la base
	private List<Tache> taches;
	
	
	
	public FenetreTaches(TacheDAO tacheDAO) {
		super("todolist Manager");
		this.tacheDAO = tacheDAO;
		setSize(550, 400);
		// centrer la fenetre
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// decoupe la fenetre en 5 zones, NORTH, SOUTH, EAST, WEST, CENTER
		setLayout(new BorderLayout());
		
		dataTaches = new DefaultListModel<>();
		listeTaches = new JList<>(dataTaches);
		
		add(new JScrollPane(listeTaches), BorderLayout.CENTER);
		
			
		// ce panel contiendra mes boutons de commande
		JPanel panelHaut = new JPanel();
		// ce layout aligne les composant horizontalement
		BoxLayout bl = new BoxLayout(panelHaut, BoxLayout.X_AXIS);
		panelHaut.setLayout(bl);
		
		// je place ce panel au nord de la fenetre
		add(panelHaut, BorderLayout.NORTH);
		
		boutonLoad = new JButton("charger taches");
		panelHaut.add(boutonLoad);
		
		boutonClear = new JButton("vider liste");
		panelHaut.add(boutonClear);
		
		boutonLoad.setActionCommand(ACTION_LOAD);
		boutonClear.setActionCommand(ACTION_CLEAR);
		// ma fenetre ecoute a l'action par defaut de mon bouton
		boutonLoad.addActionListener(this);
		boutonClear.addActionListener(this);
		
		// bouton d'edition et creation de produits
		boutonEdit = new JButton("editer tache");
		boutonCreate = new JButton("creer tache");
		boutonDelete = new JButton("effacer tache");
		boutonEdit.setActionCommand(ACTION_EDIT);
		boutonCreate.setActionCommand(ACTION_CREATE);
		boutonDelete.setActionCommand(ACTION_DELETE);
		boutonEdit.addActionListener(this);
		boutonCreate.addActionListener(this);
		boutonDelete.addActionListener(this);
		
		
		panelHaut.add(boutonEdit);
		panelHaut.add(boutonCreate);
		panelHaut.add(boutonDelete);
		
		
		//fenetreEdit = new FenetreEdit(this);
		
		boutonEdit.setEnabled(false); // desactive le bouton		
		boutonDelete.setEnabled(false); // desactive le bouton
		
		listeTaches.addListSelectionListener(this);
		
		fenetreEdit = new FenetreEdit(this);
		
		
		
		// panel en bas pour les nouveaux controle (bonus)
		JPanel panelBas = new JPanel();
		bl =new BoxLayout(panelBas, BoxLayout.X_AXIS);
		panelBas.setLayout(bl);
		
		add(panelBas, BorderLayout.SOUTH);
		
		
		JLabel labelFiltre = new JLabel("filtre contexte");
		panelBas.add(labelFiltre);
		
		filtreContexte = new JTextField(50);
		panelBas.add(filtreContexte);

		triPriorite = new JCheckBox("trier par priorite");
		triPriorite.setSelected(false);
		panelBas.add(triPriorite);

		triPriorite.setActionCommand(ACTION_TRI);
		triPriorite.addActionListener(this);
		
		// liste de taches vide
		taches = new ArrayList<>();
		
		//
		// pour le JTextField, si on veu etre prevenu d'un changement dans son contenu
		// il ne faut pas ecouter le JTextField lui même, mais le Document
		// qu'il utilise pour gérer son contenu
		// le JtextField ne s'occupe que de la partie "interface"
		// le Document s'occupe du contenu, y compris les evenements associés
		//
		filtreContexte.getDocument().addDocumentListener(this);
		
	}

	// selection dans la liste
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// activer/desactiver le bouton edit suivant l'etat de la selection da la liste
		boutonEdit.setEnabled(!listeTaches.isSelectionEmpty());
		boutonDelete.setEnabled(!listeTaches.isSelectionEmpty());
		
		
	}
	
	public void saveTache(Tache t) {
		System.out.println("sauvegarde de " + t);
		tacheDAO.save(t);
		refreshFromBase();
	}

	// action d'un composant/bouton
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case ACTION_TRI:
			case ACTION_LOAD:
				refreshFromBase();
				break;
			case ACTION_CLEAR:
				dataTaches.clear();
				taches.clear();
				break;
			case ACTION_EDIT:
				fenetreEdit.setTache( listeTaches.getSelectedValue());
				// j'affiche la fenetre d'edition
				fenetreEdit.setVisible(true);
				break;
			case ACTION_CREATE:
				fenetreEdit.setTache(new Tache());
				fenetreEdit.setVisible(true);
				break;
			case ACTION_DELETE:
				tacheDAO.deleteOne(listeTaches.getSelectedValue().getId());
				refreshFromBase();
				break;
				
		}
	}
	
	private void refreshFromBase() {
		// je récupere la liste des produits depuis la base
		taches = tacheDAO.findAll(triPriorite.isSelected());
		// et je copie tous les produit dans ma JList
		filtreTache();
	}

	private void filtreTache() {
		dataTaches.clear();
		String filtre = filtreContexte.getText();
		for (Tache t : taches) {
			// si rien de saisie dans le filtre, on ne filtre rien
			if (filtre == null || filtre.isEmpty())
				dataTaches.addElement(t);
			// si le filtre est présent dans le contexte, ajouter la tache
			else if (t.getContexte().indexOf(filtre) != -1)
				dataTaches.addElement(t);
		}
	}
	
	/*
	 * les methodes du documentListener 
	 * 
	 * 
	 */

	@Override
	public void insertUpdate(DocumentEvent e) { filtreTache(); }
	@Override
	public void removeUpdate(DocumentEvent e) { filtreTache(); }
	@Override
	public void changedUpdate(DocumentEvent e) { filtreTache(); }

}
