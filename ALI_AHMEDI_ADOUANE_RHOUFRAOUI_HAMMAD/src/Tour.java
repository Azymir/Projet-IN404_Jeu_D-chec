
public class Tour extends Piece {

	public Tour(Joueur J,char couleur,int x,int y) {
        super('T',couleur,J,x,y);
    }
	
	public boolean deplacement_possible(int x,int y, int xf, int yf) {
		if ( ( ((x == xf) && (y != yf)) 		// deplacement en vertical
				|| ((y == yf) && (x != xf)) )	// deplacement en horizontal
				) return true ;
		else return false;
	}
	
	

	@Override
	public boolean obstacle(int x, int y, int xf, int yf) {
		int i,k = 1;
		
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
		return false;	
	}			
}
