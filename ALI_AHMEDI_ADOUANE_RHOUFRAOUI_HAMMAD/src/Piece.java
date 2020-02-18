
public abstract class Piece  {
	private char c;
	private Joueur J;
	private char couleur;
	private int x;
	private int y;
	public Piece (char c,char couleur,Joueur J,int x,int y) {
		this.c = c;
		this.couleur = couleur;
		this.J = J;
		this.x = x;
		this.y = y;
	}
/**
 *
 * @param x	position de départ en abscisse
 * @param y	position de départ en ordonnée
 * @param xf position d'arrivée en  abcsice
 * @param yf position d'arrivée en ordonnée
 * @return true si la pièce peut se déplacer false si elle ne peut pas se déplacer
 */
	public abstract boolean deplacement_possible(int x,int y, int xf, int yf);
	
	/**
	 *
	 * @param x	position de départ en abscisse
	 * @param y	position de départ en ordonnée
	 * @param xf position d'arrivée en  abcsice
	 * @param yf position d'arrivée en ordonnée
	 * @return true si il ya une piece entre la case de départ et d'arrivée false sinon
	 */
	public abstract boolean obstacle(int x,int y, int xf, int yf);
	/**
	 * 
	 * @return le joueur de la pièce
	 */
	public Joueur getJ() {
		return J;
	}
/**
 * 
 * @return retourne le symbole de la pièce
 */
	public char getC() {
		return c;
	}
/**
 * 
 * @return l'abscisse de la pièce
 */
	public int getX() {
		return x;
	}
/**
 * 
 * @param x modifie le parametre xde la piece
 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return l'ordonnée de la pièce
	 */
	public int getY() {
		return y;
	}
	/**
	 *
	 *@param y modifie le parametre y de la piece
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 * @return la couleur de la pièce
	 */
	public char getCouleur() {
		return couleur;
	}
	

	
}
