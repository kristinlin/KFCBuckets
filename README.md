# KFCBuckets
Carol, Fabiha, &amp; Kristin

## Sudoku Solver
Solves a sudoku puzzle in terminal

### Tasks:
- [ ] First Stage: load sudoku puzzle
- [x] Second Stage: load givens; determine definites from givens
- [ ] Third Stage: guess from a box with least number of possibles; determine "definite" from it until contradiction; backtrack if needed

### Algo

For each number added, all slots in row, col, and box will remove that possible slot number
Then cehcks if any box has only one possible slot number, 
Then adds, and repeat. 

And then, find slot with least number of possibles, 
choose first possibility, 
go through step above until 
contradiction - two same numbers in row, col, or collection

### Current State

There are issues with the assign tentatively--specifically with setGuess of Box. Setting it with false will allow it to have numbers removed later on from its possibleNums. 
