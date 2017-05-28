public class Box {

  private int guess;
  private int[] possibleNums;
  private boolean isDefinite;
  private int numPossible;

  public Box(){
    guess = 0;
    isDefinite = false;
    possibleNums = new int[9];
    for (int x = 1; x < 10; x ++){
	possibleNums[x - 1] = x;
    }
    numPossible = 9;
  }

    public boolean remove(int takenValue){

	possibleNums[takenValue - 1] = 0; 
	numPossible -= 1;
	//System.out.println();
	//for (int x : possibleNums) {
	//    System.out.print(x);
	//}
	if (numPossible == 1) {
	    int index = 0;
	    while (possibleNums[index] == 0) {
		index++;  
	    }
	    setGuess(possibleNums[index], true); 
	    return true;
	}
	return false;
    }

    public boolean sRemove(int takenValue) {
	possibleNums[takenValue - 1] = possibleNums[takenValue-1] * -1;
	numPossible -=1;

    }

    public boolean check(){
	if (numPossible == 1) {
	    //search for that last remaining number
	    int index = 0;
	    while (possibleNums[index] <= 0) {
		index++;
	    }
	    setGuess(index+1, false);
	    return true;
	}
	return false;
    }
    
    public boolean getIsDef() {
	return isDefinite;
    }
    
    public int getGuess(){
	return guess;
    }
    
    public int getNumPossible(){
	return numPossible;
    }
    
    
    // possible guess number, defo if we know it's definite or not
    public boolean setGuess(int possible, boolean defo) {
	if (isDefinite) {
	    return false; //you must backtrack b/c contradiction
	}
	guess = possible;
	isDefinite = defo;
	return true;
    }
    
}
