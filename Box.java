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

    //returns true if the box has turned definite
    public boolean remove(int takenValue){
	if (!isDefinite && possibleNums[takenValue-1] > 0) {
	    possibleNums[takenValue - 1] = 0; 
	    numPossible -= 1;
	    if (numPossible == 1) {
		int index = 0;
		while (possibleNums[index] <= 0) {index++;}
		setGuess(possibleNums[index], true);
		return true;
	    }
	}
	return false;
    }

    /*
    public boolean sRemove(int takenValue) {
	possibleNums[takenValue - 1] = possibleNums[takenValue-1] * -1;
	numPossible -=1;

    } 
    */

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
	if (defo == true) {
	    numPossible = 1;
	}
	return true;
    }


    public int getPNum(int val) {
	return possibleNums[val];
    }

    public void setPNum(int val) {
	if (possibleNums[val] < 0) {
	    possibleNums[val] = possibleNums[val] * -1;
	}
    }
    
}
