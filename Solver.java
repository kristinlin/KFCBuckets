import java.io.*;
import java.util.*;

public class Solver {
    
    //~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
    private Box[][] board;
    //private BigBox[][] collections;
    private int numDefinite;
    private final int max = 81;
    ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public Solver(){
	board = new Box[9][9];
	numDefinite = 0;
    }
    
    public Solver(String inputFile){
	this();
	String[][] puzzle = readPuzzle(inputFile);
	
	for (int r = 0; r < puzzle.length; r++){
	    for (int c = 0; c < puzzle[0].length; c++){
		board[r][c] = new Box();
	    }
	}

	for (int r = 0; r < puzzle.length; r++){
	    for (int c = 0; c < puzzle[0].length; c++){
	        if (!(puzzle[c][r].equals("_"))){
		    int given = Integer.parseInt(puzzle[c][r]);
		    assign(given, r, c);
		}
	    }
	}
    }

    public String[][] readPuzzle(String inputFile){
	String[][] puzzle = new String[9][9];
	int h = 0;
	int w = 0;

	//transcribe maze from file into memory

	try {
	    Scanner sc = new Scanner( new File(inputFile) );
	    System.out.println( "reading in file..." );
	    int row = 0;
	    while( sc.hasNext() ) {
		String line = sc.nextLine();
		if ( w < line.length() ) 
		    w = line.length();
		for( int i=0; i<line.length(); i++ )
		    puzzle[i][row] = line.substring( i, i + 1 );
		h++;
		row++;
	    } 
	} catch( Exception e ) { System.out.println( "Error reading file" ); }
	return puzzle;

    }

    //for givens and definites
    public void assign(int newVal, int r, int c) {
	board[r][c].setGuess(newVal, true);
	removeFromRow(newVal, r);
	removeFromCol(newVal, c);
	removeFromCollection(newVal, r, c);
        //remove from the collection
    }
    
    public void removeFromRow(int value, int row){
	//for each element in this row
	for (int c = 0; c < 9; c ++){
	    if (board[row][c].remove(value) == true) {
		assign (board[row][c].getGuess(), row, c);
	    }
	} 
    }

    public void removeFromCol(int value, int col){
	//for each element in this column
	for(int r = 0; r < 9; r++){
	    if (board[r][col].remove(value) == true) {
		assign (board[r][col].getGuess(), r, col);
	    }
	}
    }

    public void removeFromCollection(int value, int r, int c) {
	r = r - (r%3); //corner box of collection
	c = c - (c%3); //corner box of collection
	for (int row = r; row < r+3; row++) {
	    for (int col = c; col < c+3; col++) {
		if (board[row][col].remove(value) == true) {
		    assign(board[row][col].getGuess(), row, col);
		}
	    }
	}
    }

    //find Box with least number of possibles
    public int findLeast() {
	int cor = 0;
	int x = 9;
	for (int r = 0; r < 9; r++) {
	    for (int c = 0; c < 9; c++) {
		if (!board[r][c].getIsDef()) {
		    if (board[r][c].getNumPossible() == 2) {
			return r*10 + c;
		    }
		    if (board[r][c].getNumPossible() < x) {
			cor = r*10 + c;
		    }
		}
	    }
	}
	return cor;
    }
    
    /*

    public void sRemoveFromRow(int value, int row) {
	//for each element in this row
	for (int c = 0; c < 9; c++) {
	    boolean isFinished = board[row][c].remove(value);
	    if (isFinished) {
		int takenVal = board[row][c].getGuess();
		//sRemoveFromRow(takenVal, row);
		//sRemoveFromCol(takenVal, c);
	    }
	}
    }
    
    //assign for tentatives
    public void assignT(int newGuess, int r, int c) {
	//setGuess
    }

    //check row to look for any missing values
    public int checkC(int col) {
	int[] checker = {1,2,3,4,5,6,7,8,9};
	for (int r = 0; r < 9; r ++){
	    checker[board[r][col].getGuess() - 1] = 0;
	}
	for (int x = 0; x < 9; x++){
	    if (checker[x] != 0){
		return checker[x];
	    }
	}
	return 0;
    }
    
    ========================
     *pre: givens and definites determined from givens already assigned; 
     *function: Time to guess! Checks and finds box with least num possibles,
     *then assigns the least one tentatively. If there is a contradiction, 
     *backtrack and assign the next least one.
      =======================
    public void solve() {

    }


    */

    public String toString() {
	String retstr = "";
	for (Box[] r : board){
	    retstr += "\n";
	    for (Box col : r){
	        retstr += "'" + col.getNumPossible() + "'" + col.getGuess() + " ";
	    }
	}
	return retstr;
    }
    
    public static void main(String[] args){
	try {
	    String inputFile = args[0];
	    Solver s = new Solver(inputFile);
	    System.out.println("[2J");
	    System.out.println(s);
	    System.out.println(s.findLeast());
	} catch (Exception e) {
	}
	    
    }
    
}
