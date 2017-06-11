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

    //constructor
    //places givens and definites from givens
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


    //~~~~~~~~~~~~~~~~~GIVENS AND DEFINITES~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //for givens and definites
    public void assign(int newVal, int r, int c) {
	board[r][c].setGuess(newVal, true);
	max--;
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

    //~~~~~~~~~~~~~~~~~~~~~GUESSING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    /* ========================
     *pre: givens and definites determined from givens already assigned; 
     *function: Time to guess! Checks and finds box with least num possibles,
     *then assigns the least one tentatively. If there is a contradiction, 
     *backtrack and assign the next least one.
     ======================= */
    public boolean solve() {

	int nextBox = findLeast();
	//coors for the least box
	int c = nextBox % 10;
	int r = nextbox/10;
	

	//look for least
	//for the numbers in the least box
	//assignT == true 
	//check the max; if max is 100; well done! return true
	//if its not then if (solve() == true)  again
	//if 
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

    //assign for tentatives
    public boolean assignT(int newGuess, int r, int c) {
	//check for existing contradictions
	if (check(r, c) == false) {
	    return false;
	}
	//is this your last number?
	if (max == 1) {
	    board[r][c].setGuess(newGuess, true);
	    max--;
	    return true;
	}
	else {
	    int coors = 0;
	    board[r][c].setGuess(newGuess, false);
	    //do separately because i'm scared of repeats
	    coors = sRemoveFromRow(r);
	    

	    //coors += sRemoveFromCol(c);
	    //coors += sRemoveFromCollection(r,c);
	}
	//if max is hit; then return true
	//setsGuess; but makes sure that numPossible is not reduced to 1
	//sremoveFromRow (which makes sure that it doesn't go on a tangent)
	//sremoveFromCol (but returns the coors of the new "definite")
	//sremoveFromCollection
	//if there are no coors return true; solve will see it's not max
	//if (assignT with new coors is true) 
	//if false; backtrack it
	return true;
    }

    

    //~~~~~~~~~~~~~~~~CHECKS FOR CONTRADICTION~~~~~~~~~~~~~~~~~~

    //check if this number already exists in row/col/collection
    //true == you're good to go
    //false == bad, it exists already
    public boolean check(int r, int c) {
	return checkR(r) && checkC(c) && checkCollect(r, c);
    }
    
    //checks row
    public boolean checkR(int r) {
	boolean[] list = new boolean[9];
	for (int x = 0; x < list.size(); x++) {
	    boolean[x] = false;
	}
	for (int col = 0; col < 9; col++) {
	    if (list[board[r][col].getGuess()-1] == true;) {
		return false;
	    }
	    else (list[board[r][col].getGuess()-1] = true;)
	}
	return true;
    }

    //checks column
    public boolean checkC(int c) {
	boolean[] list = new boolean[9];
	for (int x = 0; x < list.size(); x++) {
	    boolean[x] = false;
	}
	for (int row = 0; row < 9; row++) {
	    if (list[board[row][c].getGuess()] == true) {
		return false;
	    }
	    else {
		list[board[row][c].getGuess()] = true;
	    }
	}
	return true;
    }

    //check collection
    public boolean checkCollection(int r, int c) {
	//top left corner of collection
	r = r - (r%3);
	c = c - (c%3);
	boolean[] list = new boolean[9];
	for (int x = 0; x < list.size(); x++) {
	    boolean[x] = false;
	}
	for (int row = r; row < r+3; row++) {
	    for (int col = c; col < c+3; col++) {
		if (list[board[row][col].getGuess()] == true) {
		    return false;
		}
		else {list[board[row][col].getGuess()] = false;}
	    }
	}
	return true;
    }

    //~~~~~~~~~~~~~~~~~~~~~BACKTRACKING~~~~~~~~~~~~~~~~~~~~~~~~
        
    public void backTrack(int val, int r, int c) {
	board[r][c].setGuess(0, false);
	max++;
	returnToRow(val, r);
	returnToCol(val, c);
	returnToCollection(val, r, c);
    }

    public void returnToRow(int val, int r) {
	for (int col = 0; col < 9; col++) {
	    if (!board[r][col].getIsDef() && board[r][col].getPNum(val-1) < 0) {
		board[r][col].setPNum(val-1);
	    }
	}
    }

    
    public void returnToCol(int val, int c) {
	for (int row = 0; row < 9; row++) {
	    if (!board[row][c].getIsDef() && board[row][c].getPNum(val-1) < 0) {
		board[row][c].setPNum(val-1);
	    }
	}
    }

    public void returnToCollection(int val, int r, int c) {
	r = r - (r%3);
	c = c - (c%3);
	for (int row = r; row < r+3; row++) {
	    for (int col = c; col < c+3; col++) {
		if (!board[r][c].getIsDef() &&
		    board[r][c].getPNum(val-1) < 0) {
		    board[r][c].setPNum(val-1);
		}
	    }
	}
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
    */

    




    public String toString() {
	String retstr = "";
	for (Box[] r : board){
	    retstr += "\n";
	    for (Box col : r){
	        //retstr += "'" + col.getNumPossible() + "'"
		retstr += col.getGuess() + " ";
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
