public class Fou extends Piece {

    public Fou (Joueur J,char couleur,int x,int y) {
        super('F',couleur,J,x,y);
    }

    @Override
    public boolean deplacement_possible(int x, int y, int xf, int yf) {
    	if(Math.abs(xf-x) == Math.abs(yf-y)) {
                return true ;
        }
            return false;
    }

    public boolean obstacle(int x, int y, int xf, int yf) {
		int i,j,k = 1,l =1;
			if(x>xf) k = - k ;
			if(y>yf) l = - l ;
			for(i = x + k , j = y + l ; i != xf && j != yf ; i += k,j += l) {
				if((getJ().getE().getMat()[i][j] != null)) return true ;
			}
		return false;
		
   }
}