
public class Pion extends Piece {

	
	public Pion(Joueur J,char couleur,int x,int y) {
        super('P',couleur,J,x,y);
    }
	@Override
	public boolean deplacement_possible(int x,int y, int xf, int yf) {
		int i = 1;
		if(getJ().getE().getMat()[x][y].getCouleur() == 'B') i = -i;
		
		if(( (Math.abs(xf - x) == 1) && (yf - y == i))
				&& (getJ().getE().getMat()[xf][yf] != null))
			return true ;
		else {
			
			
			if((xf == x) && (yf - y == i)) {

				return true ;
			}	
		}
		return false;
	}
	
	
	@Override
	public boolean obstacle(int x, int y, int xf, int yf) {
		// TODO Auto-generated method stub
		return false;
	}
}
