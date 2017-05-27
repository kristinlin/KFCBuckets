public class Box {

  private int guess;
  private int[] possibleNums;
  private boolean isDefinite;
  private int numPossible;

  public Box(){
    guess = 0;
    isDefinite = false;
    possibleNums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    numPossible = 9;
  }

  public boolean remove(int takenValue){
   	possibleNums[takenValue - 1] = 0; 
    numPossible -= 1;
    if (numPossible == 1) {isDefinite = true;}
  }
    
  public boolean getIsDef() {
    return isDefinite;
  }

  // possible guess number, defo if we know it's definite or not
  public boolean setGuess(int possible; boolean defo) {
    if (isDefinite) {
     return false; //you must backtrack b/c contradiction
    }
    guess = possible;
    isDefinite = defo;
    return true;
  }
  
}
