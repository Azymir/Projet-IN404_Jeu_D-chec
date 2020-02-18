import java.util.ArrayList;
/**
 */
public class Joueur {
	private Echiquier E;
	// Historique des commandes réalisées : enregistre tous les mouvements du joueur
	private ArrayList<String> moves = new ArrayList<String>();

	// Historique des commande annulées : enregistre les actions annulées
	private ArrayList<String> cancels = new ArrayList<String>();


	public Joueur(Echiquier E) {
		this.E = E ;
	}



	/**
	 * Annule la dernière commande exécutée
	 */
	public void undo(){
		if (!moves.isEmpty()) {
			cancels.add(moves.remove(moves.size()-1));
			int x = Integer.valueOf(cancels.get(cancels.size()-1).charAt(0)) - 48;
			int y = Integer.valueOf(cancels.get(cancels.size()-1).charAt(1)) - 48;
			//mets la piece sur la nouvelle case
			int xf = Integer.valueOf(cancels.get(cancels.size()-1).charAt(2)) - 48;
			int yf = Integer.valueOf(cancels.get(cancels.size()-1).charAt(3)) - 48;
			E.getMat()[x][y] = E.getMat()[xf][yf];
			//mets NULL à l'ancienne position
			E.getMat()[xf][yf] = null;
		}
		else {
			System.out.println("No command to undo");
		}
	}

	/**
	 * Execute la dernière commande annulée
	 */	
	public void redo(){
		if(!cancels.isEmpty()){
			moves.add(cancels.remove(cancels.size() - 1));
			int x = Integer.valueOf(moves.get(moves.size() - 1).charAt(2)) - 48;
			int y = Integer.valueOf(moves.get(moves.size() - 1).charAt(3)) - 48;
			int xf = Integer.valueOf(moves.get(moves.size()-1).charAt(0)) - 48;
			int yf = Integer.valueOf(moves.get(moves.size()-1).charAt(1)) - 48;
			//mets la pièce dans sa position avant l'undo (avec x et y)
			//mets NULL dans (l'ancienne/nouvelle) position
			E.getMat()[x][y] = E.getMat()[xf][yf];
			E.getMat()[xf][yf] = null;
		} else {
			System.out.println("No command to redo");
		}   
	}
	/**
	 * 
	 * @param x	position de départ en abscisse
	 * @param y	position de départ en ordonnée
	 * @param xf position d'arrivée en  abcsisse
	 * @param yf position d'arrivée en ordonnée
	 * @return true si case d'arrivée vide ou si les pièces situés au départ et à l'arrivée sont de joueurs différents false sinon
	 */
	public boolean anti_allié(int x,int y ,int xf,int yf) {
		Piece p1 = E.getMat()[x][y] ;
		Piece p2 = E.getMat()[xf][yf];
		
		if(p1 != null && p2 != null) {
			if(p1.getCouleur() == p2.getCouleur()) return false ;
		}
		
		return true ;
	}
/**
 * déplace une pièce situé en x y en xf yf
 * @param x	position de départ en abscisse
 * @param y	position de départ en ordonnée
 * @param xf position d'arrivée en  abcsisse
 * @param yf position d'arrivée en ordonnée
 */
	public void deplacer(int x,int y ,int xf,int yf) {
		if(E.getMat()[x][y] != null) {			
			if(E.getMat()[x][y].deplacement_possible(x,y,xf,yf) 
					&&(E.getMat()[x][y].getJ() == this) 
					&&(!E.getMat()[x][y].obstacle(x, y, xf, yf))
					&& anti_allié(x,y,xf,yf)) {

				System.out.println("Joueur:" +" "+ x +" "+ y +" "+xf+" "+yf);

			    E.getMat()[xf][yf] = E.getMat()[x][y];
                E.getMat()[x][y] = null;
                String move = Integer.toString(x) + Integer.toString(y) + Integer.toString(xf) + Integer.toString(yf);
                moves.add(move);
                System.out.println(moves);
			}    

			else {
				System.out.println("aucune piece");	
			}
		}	
	}


/**
 * 
 * @return echiquier du joueur
 */
	public Echiquier getE() {
		return E;
	}

	/**
	 * Cette fonction génère des valeurs aléaoires, les teste, puis déplace la pièce 
	 */
	public void IA_deplacer() 
	{
		int i=0;
		while (i == 0)
		{
			int x = (int)(Math.random()*8);
			int y = (int)(Math.random()*8);
			int xf = (int)(Math.random()*8);
			int yf = (int)(Math.random()*8);
			if(E.getMat()[x][y] != null) 
			{
				if ((E.getMat()[x][y].deplacement_possible(x,y,xf,yf)) &&
						(E.getMat()[x][y].getJ() == this)
						&&	(!E.getMat()[x][y].obstacle(x, y, xf, yf))
						&& anti_allié(x,y,xf,yf))
				{
					System.out.println("IA:" +" "+ x +" "+ y +" "+xf+" "+yf);
					E.getMat()[xf][yf] = E.getMat()[x][y];
					E.getMat()[x][y] = null;
					i=1;
				}
				else
				{
					//System.out.println("aucune piece");	
					i=0;
				}
			}


		}
	}

	public ArrayList<String> getMoves() {
		return moves;
	}


	public void setMoves(ArrayList<String> moves) {
		this.moves = moves;
	}


	public ArrayList<String> getCancels() {
		return cancels;
	}
}