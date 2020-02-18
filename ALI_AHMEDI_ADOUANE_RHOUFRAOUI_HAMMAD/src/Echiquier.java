
public class Echiquier{
	private Piece matrice[][];
	private Joueur J1;
	private Joueur J2;
	/**
	 * 
	 * @param J1
	 * @param J2
	 */
	public Echiquier (Joueur J1,Joueur J2) {
		this.matrice = new Piece[8][8];
		this.J1 = J1;
		this.J2 = J2;
	}

	public void init_echiquier(Joueur J1,Joueur J2) {
		int i,j;
		for(i = 0; i < 8 ; i++) {
			for(j = 0; j < 8 ; j++) {
				matrice[i][j] = null;
			}
		}

		matrice [0][0] = new Tour(J1,'N',0,0);
		matrice [1][0] = new Cavalier(J1,'N',1,0); 
		matrice [2][0] = new Fou(J1,'N',2,0); 
		matrice [3][0] = new Reine(J1,'N',3,0); 
		matrice [4][0] = new Roi(J1,'N',4,0); 
		matrice [5][0] = new Fou(J1,'N',5,0); 
		matrice [6][0] = new Cavalier(J1,'N',6,0); 
		matrice [7][0] = new Tour(J1,'N',7,0); 

		j=1;
		for(i = 0; i < 8 ; i++) {
			matrice[i][j] = new Pion(J1,'N',i,j);
		}

		j=6;
		for(i = 0; i < 8 ; i++) {
			matrice[i][j] = new Pion(J2,'B',i,j);
		}
		//	matrice[7][7] = new Pion(J1,'N',i,j);


		matrice [0][7] = new Tour(J2,'B',0,7);
		matrice [1][7] = new Cavalier(J2,'B',1,7); 
		matrice [2][7] = new Fou(J2,'B',2,7); 
		matrice [3][7] = new Reine(J2,'B',3,7); 
		matrice [4][7] = new Roi(J2,'B',4,7); 
		matrice [5][7] = new Fou(J2,'B',5,7); 
		matrice [6][7] = new Cavalier(J2,'B',6,7); 
		matrice [7][7] = new Tour(J2,'B',7,7);
		/**/
	}

	public Piece[][] getMat(){
		return matrice;
	}

	public void afficherMap() {
		int i,j;
		for(j = 0; j < 8;j++) {
			for(i = 0; i < 8;i++) {
				System.out.print(matrice[i][j]+"	");   // affichage des caracteres sur la ligne
			}
			System.out.println();                  // saut de ligne
		}
		// 

	}	

	public int conv(char c) {
		if (c == 'a'|| c == '1') return 0;
		else if (c == 'b'|| c == '2') return 1;
		else if (c == 'c'|| c == '3') return 2;
		else if (c == 'd'|| c == '4') return 3;
		else if (c == 'e'|| c == '5') return 4;
		else if (c == 'f'|| c == '6') return 5;
		else if (c == 'g'|| c == '7') return 6;
		else if (c == 'h'|| c == '8') return 7;
		else return -1;

	}

	public Joueur getJoueur1() {
		return J1;
	}
	public Joueur getJoueur2() {
		return J2;
	}

	public int posRoiX(char C){
		for(int i = 0 ; i < 8 ; i++) {
			for(int j = 0; j< 8 ;j++) {
				if(matrice[i][j]!= null) {
					if(matrice[i][j].getCouleur()== C
							&& matrice[i][j].getC() == 'R')
						return i;
				}

			}
		}
		return -1;
	}

	public int posRoiY(char C){
		for(int i = 0 ; i < 8 ; i++) {
			for(int j = 0; j< 8 ;j++) {
				if(matrice[i][j]!= null) {
					if(matrice[i][j].getCouleur()== C
							&& matrice[i][j].getC() == 'R')
						return j;
				}

			}
		}
		return -1;
	}

	public boolean echec(char coul) {

		int i,j;
		Piece p ;
		for(i = 0 ; i < 8 ; i++) {
			for(j = 0 ; j < 8 ; j++) {
				p = matrice[i][j];
				if(p != null) {
					if(p.getCouleur() != coul ) {
						if(p.deplacement_possible(i,j,posRoiX(coul),posRoiY(coul)))//faire getX getY
							return true ;
					}
				}
			}
		}

		return false ;
	}
	public void evolution(Echiquier e,Joueur J) {
		int i,j;
		j = 0;
		for(i = 0;i<8;i++) {
			if (e.getMat()[i][j].getC() == 'P' && e.getMat()[i][j].getCouleur() == 'B'  ) {
				matrice[i][j] = new Reine(J2,'B',i,j);
				evolution(e,J);
			}
		}
		j=7;
		for(i = 0;i<8;i++) {
			if (e.getMat()[i][j].getC() == 'P' && e.getMat()[i][j].getCouleur() == 'N'  ) {
				matrice[i][j] = new Reine(J1,'N',i,j);
				evolution(e,J);
			}
		}
	}
	public void afficheFin() {
		System.out.println("----------------------------------------");
		System.out.println("|		Fin du jeu		|");
		System.out.println("----------------------------------------");
		System.exit(0);
	}
	
	public void afficheEchec() {
		System.out.println("Vous êtes en echec !!");
	}


}

