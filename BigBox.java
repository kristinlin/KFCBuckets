public class BigBox {
    
    //~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
    private Box[][] board;
    ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public BigBox(){
	board = new Box[9][9];
    }
        
    public void set(int r, int c, Box b){
	board[r][c] = b;
    }

    public int[] remove(int val){
	int[] ret = new int[18];
	int cPos = 0;
	for (int i= 0; i < 18; i ++){
	    ret[i] = -1;
	}
	for (int r = 0; r < 3; r ++){
	    for (int c = 0; c < 3; c ++){
		if (board[r][c].remove(val)){
		    remove(board[r][c].getGuess());
		    ret[cPos] = r;
		    ret[cPos + 1] = c;
		    cPos += 2;
		}   
	    }
	}
	return ret;
	
    }

}
