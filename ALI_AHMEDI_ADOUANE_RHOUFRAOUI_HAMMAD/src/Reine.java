
public class Reine extends Piece {
	Echiquier Chess;
	public Reine(Joueur J,char couleur,int x,int y) {
		super('D',couleur,J,x,y);
	}

	@Override
	public boolean deplacement_possible(int x,int y, int xf, int yf) {
		if(	(Math.abs(xf-x) == Math.abs(yf-y)) //if(Tour.deplacementpossible ou fou.deplacement)
				|| (((x == xf) && (y != yf)) || ((y == yf) && (x != xf))) ) {
			return true ;}
		return false;
	}

	public boolean obstacle(int x, int y, int xf, int yf) {
		int i,j,k=1,l=1;

		if(Math.abs(x-xf)== Math.abs(y-yf)) {
			if(x>xf) k = - k ;
			if(y>yf) l = - l ;
			for(i = x + k , j = y + l ; i != xf && j != yf ; i += k,j += l) {
				if((getJ().getE().getMat()[i][j] != null)) return true ;
			}
		}

		else {
			if(yf - y == 0) {
				if(xf - x < 0 )k = - k;
				for(i = x+k ; i != xf ; i+= k){
					if(getJ().getE().getMat()[i][yf] != null) return true ;
				}
			}

			if(xf - x == 0) {
				if(yf - y < 0 ) k = -k;
				for(i = y+k ; i != yf ; i+=k) {
					if(getJ().getE().getMat()[xf][i] != null) return true ;
				}
			}	
		}

		return false;
	}
}
