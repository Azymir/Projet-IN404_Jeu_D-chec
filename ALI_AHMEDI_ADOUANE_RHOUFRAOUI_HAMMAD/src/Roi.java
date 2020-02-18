
public class Roi extends Piece {

	public Roi(Joueur J,char couleur,int x,int y) {
        super('R',couleur,J,x,y);
    }

	@Override
	public boolean deplacement_possible(int x,int y, int xf, int yf) {
		if ((Math.abs(xf - x) == 0) && (Math.abs(yf - y) == 0)) return false;
		else if( ((Math.abs(xf - x) <= 1) && (Math.abs(yf - y)<= 1) )) return true;
		else return false;
	}


	@Override
	public boolean obstacle(int x, int y, int xf, int yf) {
		// TODO Auto-generated method stub
		return false;
	}
}
