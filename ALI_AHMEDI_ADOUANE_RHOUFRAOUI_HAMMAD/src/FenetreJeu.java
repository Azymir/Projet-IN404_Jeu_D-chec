import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class FenetreJeu extends JFrame {
	/**
	 * Echiquier du jeu, contient le tableau de case.
	 */
	
	private JLabel[][] tab; // tableau de JLabels

	private JPanel panelControle = new JPanel(); // panel du haut
	private JPanel panelGrille = new JPanel(); // panel du bas ( grille )
	private JPanel TimerPanel = new JPanel();
	GridLayout gridLayout1 = new GridLayout();

	private JButton start = new JButton();
	private JTextField champTexte = new JTextField();
	private JButton Reset = new JButton();
	private JPanel panelblanc = new JPanel();
	private JPanel panelnoir = new JPanel();
	// declaration de variable
	private static int heure=0,minute=0,seconde=0;
	private int delais=1000;
	private JLabel label=new JLabel(""+heure+":"+minute+":"+seconde);
	private ActionListener tache_timer;
	private Timer timer1;
	private Echiquier ec ;
	
	/**
	 * Constructeur, appelle mÃ©thode JBInit
	 */
	public FenetreJeu(Echiquier ec) 
	{
		try {
			jbInit();
			this.ec = ec;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void chrono(){
			tache_timer= new ActionListener()  {
				public void actionPerformed(ActionEvent e1)  {
					seconde++;
					
					if(seconde==60)  {
						seconde=0;
							minute++;}
							
					          if(minute==60) {
					        	  minute=0;
					        	  	heure++;}
		  //Afficher le chrono dans un JLabel
		  label.setText(heure+":"+minute+":"+seconde);
		  
 
		 }
     };
     //Action et temps execution de la tache
     timer1=new Timer(delais,tache_timer);
     //Demarrer le chrono
     timer1.start();
	}
	
	/**
	 * initialise la surface de jeu. CrÅ½Å½ tout les Å½lements et initialise leur position leur couleur.. etc
	 */
	private void jbInit() throws Exception {

		tab = new JLabel[8][8]; // crÃ©ation du tableau de JLabel
		
		

		TimerPanel.setBounds(new Rectangle(5, 65, 550, 70));
		TimerPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		TimerPanel.setLayout(gridLayout1);
		
		this.getContentPane().add(TimerPanel,null);
		this.getContentPane().add(label);
		chrono();
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(784, 700));
		this.setTitle("Jeu d'Echecs");
		panelControle.setBounds(new Rectangle(5, 10, 550, 45));
		panelControle.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panelControle.setLayout(null);
		panelGrille.setBounds(new Rectangle(5, 165, 550, 465));
		panelGrille.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panelGrille.setLayout(gridLayout1);
		gridLayout1.setColumns(8);
		gridLayout1.setRows(8);
		this.getContentPane().add(panelnoir, null);
		this.getContentPane().add(panelblanc, null);
		this.getContentPane().add(panelGrille, null);
		panelControle.add(Reset, null);
		panelControle.add(champTexte, null);
		panelControle.add(start, null);
		this.getContentPane().add(panelControle, null);
		start.setBounds(new Rectangle(15, 10, 130, 25));
		start.setText("Commencer");
		champTexte.setBounds(new Rectangle(160, 10, 215, 25));

		// les Ã©couteurs
		Reset.setText("Reset");
		Reset.setBounds(new Rectangle(390, 10, 130, 25));
		GestionnaireEvenement gest = new GestionnaireEvenement();
		start.addMouseListener(gest);
		Reset.addMouseListener(gest);
		
		//crÅ½ation des labels
		panelblanc.setBounds(new Rectangle(570, 65, 75, 480));
		panelblanc.setBackground(new Color(255, 255, 255));
		panelblanc.setLayout(new FlowLayout());
		panelnoir.setBounds(new Rectangle(655, 65, 75, 475));
		panelnoir.setBackground(new Color(100, 100, 100));
		panelnoir.setLayout(new FlowLayout());
		
		//J'attribue la couleur aux JLabels
		int a = 1;
		for (int ligne = 0; ligne < 8; ligne++) {
			a = a == 1 ? 0 : 1;
			for (int colonne = 0; colonne < 8; colonne++) {
				tab[colonne][ligne] = new JLabel(); // crÃŒÃ¶ation du JLabel
				tab[colonne][ligne].setOpaque(true);
				panelGrille.add(tab[colonne][ligne]); // ajouter au Panel
				tab[colonne][ligne].setOpaque(true);
				tab[colonne][ligne].setHorizontalAlignment(SwingConstants.CENTER); // pour
				tab[colonne][ligne].addMouseListener(gest); // ajouter l'ÃŒÃ¶couteur aux
				if ((colonne + 1) % 2 == a)
					tab[colonne][ligne].setBackground(new Color(255, 255, 255));
				else
					tab[colonne][ligne].setBackground(new Color(100, 100, 100));

			}
		}

	}

	
	private class GestionnaireEvenement extends MouseAdapter {


		int ligneClic;
		int colonneClic;
		
		String couleurControle = "blanc";
		



		/** methode s'excutant si l'on clique sur la surface de jeu. La methode determine ensuite ou est-ce que l'on cliquer
		 * et fait les action en consequence
		 *
		 */
		public void mouseClicked(MouseEvent eve) {
			Echiquier a = ec;
			// si on clique sur le bouton dÃ©buter
			if (eve.getSource() == start) {
				//initialise le champ texte, apelle la mÅ½thode dÅ½buter, et initialise toute les variables 
				champTexte.setText("C'est le tour aux blanc");
				start.setEnabled(false);
				String dossierIcone = "Icone/";
			
				
				for(int i = 0 ; i < 8 ; i++) {
                    for(int j = 0 ; j < 8 ; j++) {
                        if(a.getMat()[i][j] != null) {                            
                        tab[i][j].setIcon(new ImageIcon(dossierIcone + a.getMat()[i][j].getC()+  a.getMat()[i][j].getCouleur() + ".gif"));
                        }
                    }
                }
			}
			// si on clique sur le bouton reset
			else if (eve.getSource() == Reset) {
				RAZ();

			}

			else if (eve.getSource() instanceof JLabel) // donc on a cliquÃ© sur un Label
			{
				for (int i = 0; i < 8; i++)
					//je determine sur quelle Jlabel on a cliquÅ½
					for (int j = 0; j < 8; j++) 
						if (eve.getSource() == tab[j][i]) {
							ligneClic = i;
							colonneClic = j;
						}
				}
		}
	}

	//Je remet tout les attributs de la classe a 0
	public void RAZ()
	{
		for (int ligne = 0; ligne < 8; ligne++) 
			for (int colonne = 0; colonne < 8; colonne++) {
				tab[colonne][ligne].setIcon(null);
				//e.getCase(colonne, ligne).setPiece(null);
				
			}
		champTexte.setText("");
		start.setEnabled(true);
		//e.debuter();
		String couleurControle = "noir";

		panelblanc.removeAll();
		panelblanc.repaint();
		panelnoir.removeAll();
		panelnoir.repaint();

	}
}