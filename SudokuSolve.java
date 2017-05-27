
/*---------------------------
class SudokuSolve

Description of Algo: 
2D array to hold Sukoku board
81 arrays to hold possible numbers
2D array rows, holds references to certain rows of slots
2D array columns, holds references to certain columns of slots
2D array boxes, holds references to certain boxes of slots

for each number added, all slots will remove a possible slot number
then cehcks if any box has only one possible slot number, 
then adds, and etc. 

otherwise, will find slot with least length, 
choose first possibility, 
go through step above until 
contradiction - no possible numbers in one box
---------------------------*/

public class SudokuSolve {
    
    //instance variables
    int[][] board;
    int[] a1; a2; a3; a4; a5; a6; a7; a8; a9; b1; b2; b3; b4; b5; b6; b7; b8; b9; c1; c2; c3; c4; c5; c6; c7; c8; c9; d1; d2; d3; d4; d5; d6; d7; d8; d9; e1; e2; e3; e4; e5; e6; e7; e8; e9; f1; f2; f3; f4; f5; f6; f7; f8; f9; g1; g2; g3; g4; g5; g6; g7; g8; g9; h1; h2; h3; h4; h5; h6; h7; h8; h9; i1; i2; i3; i4; i5; i6; i7; i8; i9;
    int[][] rows1; row2; row3; row4; row5; row6; row7; row8; row9;
    int[][] colA; colB; colC; colD; colE; colF; colG; colH; colI;
    int[][] boxes1; boxes2; boxes3; boxes4; boxes5; boxes6; boxes7; boxes8; boxes9;
    
    //constructor 
    public SudokuSolve() {
        a1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; a2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; a3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};  a4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; a5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; a6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; a7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; a8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; a9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        b1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; b9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        c1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; c9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        d1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; d9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        e1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; e9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        f1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; f9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        g1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; g9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        h1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; h9 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        i1 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i2 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i3 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i4 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i5 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i6 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i7 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i8 = {1, 2, 3, 4, 5, 6, 7, 8, 9}; i9= {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
        rows1 = {a1, b1, c1, d1, e1, f1, g1, h1, i1};
        rows2 = {a2, b2, c2, d2, e2, f2, g2, h2, i2};
        rows3 = {a3, b3, c3, d3, e3, f3, g3, h3, i3};
        rows4 = {a4, b4, c4, d4, e4, f4, g4, h4, i4};
        rows5 = {a5, b5, c5, d5, e5, f5, g5, h5, i5};
        rows6 = {a6, b6, c6, d6, e6, f6, g6, h6, i6};
        rows7 = {a7, b7, c7, d7, e7, f7, g7, h7, i7};    
        rows8 = {a8, b8, c8, d8, e8, f8, g8, h8, i8};
        rows9 = {a9, b9, c9, d9, e9, f9, g9, h9, i9};
        colA = {a1, a2, a3, a4, a5, a6, a7, a8, a9};
        colB = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        colC = {c1, c2, c3, c4, c5, c6, c7, c8, c9};
        colD = {d1, d2, d3, d4, d5, d6, d7, d8, d9};
        colE = {e1, e2, e3, e4, e5, e6. e7, e8. e9};
        colF = {f1, f2, f3, f4, f5, f6, f7, f8, f9};
        colG = {g1, g2, g3, g4, g5, g6, g7, g8, g9};
        colH = {h1, h2, h3, h4, h5, h6, h7, h8, h9};
        colI = {i1, i2, i3, i4, i5, i6, i7, i8, i9};
        boxes1 = {a1, a2, a3, b1, b2, b3, c1, c2, c3};
        boxes2 = {d1, d2, d3, e1, e2, e3, f1, f2, f3};
        boxes3 = {g1, g2, g3, h1, h2, h3, i1, i2, i3};
        boxes4 = {a4, a5, a6, b4, b5, b6, c4, c5, c6};
        boxes5 = {d4, d5, d6, e4, e5, e6, f4, f5, f6};
        boxes6 = {g4, g5, g6, h4, h5, h6, i4, i5, i6};
        boxes7 = {a7, a8, a9, b7, b8, b9, c7, c8, c9};
        boxes8 = {d7, d8, d9, e7, e8, e9, f7, f8, f9};
        boxes9 = {g7, g8, g9, h7, h8, h9, i7, i8, i9};
    }
        
    public void add(int col, int row, int box, int newNum) {
        board[row-1][col-1] = newNum;
        if (col == 1) {remove(colA, newNum);}
        else if (col == 2) {remove(colB, newNum);}
        else if (col == 3) {remove(colC, newNUm);}
        else if (col == 4) {remove(colD, newNum);}
        else if (col == 5) {remove(colE, newNum);}
        else if (col == 6) {remove(colF, newNum);}
        else if (col == 7) {remove(colG, newNum);}
        else if (col == 8) {remove(colH, newNum);}
        else {remove(colI, newNum);}
        if (row == 1) {remove(row1, newNum);}
        else if (row == 2) {remove(row2, newNum);}
        else if (row == 3) {remove(row3, newNum);}
        else if (row == 4) {remove(row4, newNum);}
        else if (row == 5) {remove(row5, newNum);}
        else if (row == 6) {remove(row6, newNum);}
        else if (row == 7) {remove(row7, newNum);}
        else if (row == 8) {remove(row8, newNum);}
        else {remove(row9, newNum);}
        if (box == 1) {remove(box1, newNum);}
        else if (box == 2) {remove(box2, newNum);}
        else if (box == 3) {remove(box3, newNum);}
        else if (box == 4) {remove(box4, newNum);}
        else if (box == 5) {remove(box5, newNum);}
        else if (box == 6) {remove(box6, newNum);}
        else if (box == 7) {remove(box7, newNum);}
        else if (box == 8) {remove(box8, newNum);}
        else {remove(box9);}
    }
    
    public void remove(int[][] possibles, int not) {
        for (int[])
    }
    

}