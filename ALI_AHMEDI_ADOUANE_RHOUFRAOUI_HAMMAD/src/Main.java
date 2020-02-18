import java.util.Scanner;

import javax.swing.JFrame;
public class Main {
	private static final int EXIT = 0;
	Echiquier Chess;
	private static Scanner sc;

	public void affichefin() {
		System.out.println("----------------------------------------");
		System.out.println("|		Fin du jeu		|");
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args) {
		Joueur J1 = null,J2 = null;
		sc = new Scanner(System.in);
		Echiquier Chess = new Echiquier(J1,J2);
		J1 = new Joueur(Chess);
		J2 = new Joueur(Chess);
		Chess.init_echiquier(J1,J2);
		System.out.println("----------------------------------------");
		System.out.println("|		Jeu d'échec		|");
		System.out.println("----------------------------------------");
		FenetreJeu j = new FenetreJeu(Chess);
		j.setVisible(true);
		j.setLocation(100, 130);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ferme le processus associÃ©

		//Chess.evolution(Chess,J1);
		String saisie;
		int i =1;
		while (i<10) {
			System.out.println("----------------------------------------");
			System.out.println("|		Tour:"+i+"			|");
			System.out.println("----------------------------------------");


			saisie = new String("");
			saisie = sc.nextLine();
			if(saisie.equals("undo")) {
				J2.undo();
				J1.undo();
			}
			else if (saisie.equals("redo")) {
				J2.redo();
				J1.redo();
			}
			else {
				if (Chess.echec('N')) {
					Chess.afficheFin();
				}
				if(Chess.echec('B')) Chess.afficheEchec();
				int a = Chess.conv(saisie.charAt(0));
				int b = Chess.conv(saisie.charAt(1));
				int c = Chess.conv(saisie.charAt(2));
				int d = Chess.conv(saisie.charAt(3));
				J2.deplacer(a,b,c,d);
				J1.IA_deplacer();
			}
			i++;

			//Chess.afficherMap(); 

		}
	}
}