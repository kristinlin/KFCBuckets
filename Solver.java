public class Solver {

  //~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
  private Box[][] board;
  private int numDefinite;
  private final int = 81;
  ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  
  public Solver(){
    board = new Board[9][9];
    numDefinite = 0;
  }
  
  public Solver(String[][] puzzle){
    this();
    for (int r = 0; int r < puzzle.length; r++){
      for (int c = 0; c < puzzle[0].length; c++){
        if (puzzle[r][c].equals("_")){
          board[r][c] = new Board();
        }
        else {}
      }
    }
  }
}
