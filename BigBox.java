public class BigBox {
    
    //~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
    private Box[][] board;
    ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public BigBox(){
	board = new Box[3][3];
    }
        
    public void set(int r, int c, Box b){
	board[r][c] = b;
    }

    public int[] remove(int val){
	int[] ret = new int[16];
	int cPos = 0;
	for (int i= 0; i < 16; i ++){
	    ret[i] = -1;
	}
	for (int r = 0; r < 3; r ++){
	    for (int c = 0; c < 3; c ++){
		board[r][c].remove(val);
		//remove(board[r][c].getGuess());
		//ret[cPos] = r;
		//ret[cPos + 1] = c;
		//cPos += 2;
	       
	    }
	}
	return ret;
	
    }

}
