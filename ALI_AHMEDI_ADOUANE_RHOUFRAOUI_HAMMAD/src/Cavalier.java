
public class Cavalier extends Piece {
	public Cavalier(Joueur J,char couleur,int x,int y) {
        super('C',couleur,J,x,y);
    }
 
	
	@Override
	public boolean deplacement_possible(int x,int y, int xf, int yf) {
		if( (	(Math.abs(xf-x) == 2 && Math.abs(yf-y) == 1) 
			|| 	(Math.abs(xf-x) == 1 && Math.abs(yf-y) == 2) ))
				return true ;		
		return false;
	}
	
	

	@Override
	public boolean obstacle(int x, int y, int xf, int yf) {
		// TODO Auto-generated method stub
		return false;
	}
}

	