/**Jessica Feng P3
 */

import java.util.*;

public class check {

    public static void main(String[] args){
        int s = 8;
        Scanner sc = new Scanner(System.in);
        String[][] board = new String[s][s];

        //creating pieces
        pieces[] p = new pieces[24];
        p[0] = new pieces (0,0,false,"b");
        p[1] = new pieces (0,2,false,"b");
        p[2] = new pieces (0,4,false,"b");
        p[3] = new pieces (0,6,false,"b");
        p[4] = new pieces (1,1,false,"b");
        p[5] = new pieces (1,3,false,"b");
        p[6] = new pieces (1,5,false,"b");
        p[7] = new pieces (1,7,false,"b");
        p[8] = new pieces (2,0,false,"b");
        p[9] = new pieces (2,2,false,"b");
        p[10] = new pieces (2,4,false,"b");
        p[11] = new pieces (2,6,false,"b");
        p[12] = new pieces (5,1,false,"w");
        p[13] = new pieces (5,3,false,"w");
        p[14] = new pieces (5,5,false,"w");
        p[15] = new pieces (5,7,false,"w");
        p[16] = new pieces (6,0,false,"w");
        p[17] = new pieces (6,2,false,"w");
        p[18] = new pieces (6,4,false,"w");
        p[19] = new pieces (6,6,false,"w");
        p[20] = new pieces (7,1,false,"w");
        p[21] = new pieces (7,3,false,"w");
        p[22] = new pieces (7,5,false,"w");
        p[23] = new pieces (7,7,false,"w");

        //initializing empty board
        for (int i = 0; i < s; i++){
            if (i % 2 == 0){
                for (int j = 0; j < s; j++){
                    if (j % 2 == 1){
                        board[i][j] = "-";
                    }
                    else {
                        board[i][j] = "O";
                    }
                }
            }
            else{
                for (int j = 0; j < s; j++){
                    if (j % 2 == 0){
                        board[i][j] = "-";
                    }
                    else {
                        board[i][j] = "O";
                    }
                }
            }
        }
        System.out.println("WELCOME TO 2-PLAYER CHECKERS\n\nBelow is the checkerboard: \n'ROWS' is the Y-AXIS \n'COLUMNS' is the X-AXIS.\n'O' are where you may place your pieces.\n'-' are where you cannot place your pieces.\nIf a piece becomes KING, it will be capitalized.\nINPUT FORMAT: row col (ie. 0 0)\n\nWITHOUT FURTHER ADO, LET THE GAMES BEGIN!\n" );
        for (int i = 0; i < 24; i++){
            board[p[i].getX()][p[i].getY()] = p[i].getLetter();
        }
        printBoard(board);

        //********************PLAYTIME************************************************************************

        int play = 0;
        while (!over(board)){
            //for B
            if (play % 2 == 0){
                //selecting piece
                System.out.print("Player B: Which piece would you like to move?");
                int a = sc.nextInt();
                int b = sc.nextInt();

                //moving piece
                System.out.print("Player B: Where would you like to put it?");
                int rowmove = sc.nextInt();
                int colmove = sc.nextInt();
                int cm = 4;
                cm = canMove(board,a,b,rowmove,colmove,"b", p);

                //validation
                while (cm == -1 || ((board[a][b].equals("w") || board[a][b].equals("W")))){
                    System.out.println("Invalid move");
                    System.out.print("Player B: Which piece would you like to move?");
                    a = sc.nextInt();
                    b = sc.nextInt();
                    System.out.print("Player B: Where would you like to put it?");
                    rowmove = sc.nextInt();
                    colmove = sc.nextInt();
                }

                for (int i = 0; i < 12; i++){
                    if (a == p[i].getX() && b == p[i].getY()){
                        becomesK("b", p[i]);
                        System.out.println();
                        while (rowmove - a == -1 && !canUp (board, p[i], p[i].getLetter())){
                            //check if can up
                            System.out.println("Cannot move up");
                            System.out.println("Player B: Which piece would you like to move?");
                            a = sc.nextInt();
                            b = sc.nextInt();
                            System.out.println("Player B: Where would you like to put it?");
                            rowmove = sc.nextInt();
                            colmove = sc.nextInt();
                        }
                        //can Down?
                        while (rowmove - a == 1 && !canDown(board, p[i], p[i].getLetter())){
                            System.out.println("Cannot move down");
                            System.out.println("Player B: Which piece would you like to move? ");
                            a = sc.nextInt();
                            b = sc.nextInt();
                            System.out.println("Player B: Where would you like to put it?");
                            rowmove = sc.nextInt();
                            colmove = sc.nextInt();
                        }
                        p[i].setX(rowmove);
                        p[i].setY(colmove);
                        board[rowmove][colmove] = p[i].getLetter();
                        board[a][b] = "O";
                        //multiple kills
                        if (rowmove >= 2 && rowmove <= 5 && colmove >= 2 && colmove <= 5 && cm == 1) {
                            if ((canMove(board, rowmove, colmove, rowmove + 2, colmove - 2, "b", p) == 1) && canDown(board, p[i], p[i].getLetter())) { //move sw - works
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove + 2);
                                p[i].setY(colmove - 2);
                                board[rowmove + 2][colmove - 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";
                            }
                            else if ((canMove(board, rowmove, colmove, rowmove + 2, colmove + 2, "b", p) == 1) && canDown(board, p[i], p[i].getLetter())) { //move se - works!
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove+2);
                                p[i].setY(colmove+2);
                                board[rowmove + 2][colmove + 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";

                            }
                            else if (canMove(board, rowmove, colmove, rowmove - 2, colmove + 2, "b", p) == 1 && canUp(board, p[i], p[i].getLetter())){ //move ne
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove-2);
                                p[i].setY(colmove+2);
                                board[rowmove - 2][colmove + 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";
                            }
                            else if (canMove(board, rowmove, colmove, rowmove - 2, colmove - 2, "b", p) == 1 && canUp(board, p[i], p[i].getLetter())){ //move nw
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove-2);
                                p[i].setY(colmove-2);
                                board[rowmove - 2][colmove - 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";
                            }
                        }
                    }
                }
            }

            //for W
            else{
                //selecting piece
                System.out.print("Player W: Which piece would you like to move?");
                int a = sc.nextInt();
                int b = sc.nextInt();

                //moving piece
                System.out.print("Player W: Where would you like to put it?");
                int rowmove = sc.nextInt();
                int colmove = sc.nextInt();
                int cm = 3;
                cm = canMove(board,a,b,rowmove,colmove,"w", p);

                //validation
                while (cm == -1 || ((board[a][b].equals("b") || board[a][b].equals("B")))){
                    System.out.println("Invalid move");
                    System.out.print("Player W: Which piece would you like to move?\n");
                    a = sc.nextInt();
                    b = sc.nextInt();
                    System.out.print("Player W: Where would you like to put it? \n");
                    rowmove = sc.nextInt();
                    colmove = sc.nextInt();
                }

                for (int i = 12; i < 24; i++){
                    if (a == p[i].getX() && b == p[i].getY()){
                        becomesK("w", p[i]);
                        System.out.println();
                        while (rowmove - a == -1 && !canUp (board, p[i], p[i].getLetter())){
                            //check if can up
                            System.out.println("Cannot move up");
                            System.out.println("Player W: Which piece would you like to move?");
                            a = sc.nextInt();
                            b = sc.nextInt();
                            System.out.println("Player W: Where would you like to put it?");
                            rowmove = sc.nextInt();
                            colmove = sc.nextInt();
                        }
                        //can Down?
                        while (rowmove - a == 1 && !canDown(board, p[i], p[i].getLetter())){
                            System.out.println("Cannot move down");
                            System.out.println("Player W: Which piece would you like to move?");
                            a = sc.nextInt();
                            b = sc.nextInt();
                            System.out.println("Player W: Where would you like to put it?");
                            rowmove = sc.nextInt();
                            colmove = sc.nextInt();
                        }
                        p[i].setX(rowmove);
                        p[i].setY(colmove);
                        board[rowmove][colmove] = p[i].getLetter();
                        board[a][b] = "O";

                        if (rowmove >= 2 && rowmove <= 5 && colmove >= 2 && colmove <= 5 && cm == 1) {
                            if ((canMove(board, rowmove, colmove, rowmove + 2, colmove - 2, "w", p) == 1) && canDown(board, p[i], p[i].getLetter())) { //move sw
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove + 2);
                                p[i].setY(colmove - 2);
                                board[rowmove + 2][colmove - 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";
                            }
                            else if ((canMove(board, rowmove, colmove, rowmove + 2, colmove + 2, "w", p) == 1) && canDown(board, p[i], p[i].getLetter())) { //move se
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove + 2);
                                p[i].setY(colmove + 2);
                                board[rowmove + 2][colmove + 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";

                            }
                            else if (canMove(board, rowmove, colmove, rowmove - 2, colmove + 2, "w", p) == 1 && canUp(board, p[i], p[i].getLetter())){ //move ne
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove-2);
                                p[i].setY(colmove+2);
                                board[rowmove - 2][colmove + 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";
                            }
                            else if (canMove(board, rowmove, colmove, rowmove - 2, colmove - 2, "w", p) == 1 && canUp(board, p[i], p[i].getLetter())){ //move nw
                                System.out.println("You can make multiple kills!");
                                p[i].setX(rowmove-2);
                                p[i].setY(colmove-2);
                                board[rowmove - 2][colmove - 2] = p[i].getLetter();
                                board[rowmove][colmove] = "O";
                            }
                        }
                    }
                }
            }
            printBoard(board);
            play++;
        }
    }


    //continue game?
    public static boolean over(String[][] board){
        boolean over = false;
        int wc = 0;
        int bc = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board[i][j].equals("b")){
                    bc++;
                }
                else if (board[i][j].equals("w")){
                    wc++;
                }
            }
        }
        if (wc == 0 && bc == 0){
            over = true;
            System.out.println("IT'S A TIE!");
        }
        else if (wc == 0){
            over = true;
            System.out.println("PLAYER B WINS!");
        }
        else if (bc == 0){
            over = true;
            System.out.println("PLAYER W WINS!");
        }
        return over;
    }

    //can move up?
    public static boolean canUp(String[][] board, pieces x, String letter){
        boolean canUp = false;
        if (x.getKing() || letter.equals("W") ||letter.equals("w") || letter.equals("B")){
            canUp = true;
        }
        return canUp;
    }

    //can move down?
    public static boolean canDown(String[][] board, pieces x, String letter){
        boolean canDown = false;
        if (x.getKing() || letter.equals("b") || letter.equals("B")|| letter.equals("W")){
            canDown = true;
        }
        return canDown;
    }

    //validation
    public static int canMove(String[][]board,int x, int y, int x1, int y1, String letter, pieces[] p){
        int canMove = 2;
        if (x < 0 || x > 7 || y < 0 || y > 7 || x1 < 0 || x1 > 7 ||y1 < 0 || y1 > 7) {
            System.out.println("Out of bounds");
            canMove = -1;
        }
        else if (!board[x1][y1].equals("O") || (board[x][y].equals("-") || board[x][y].equals("O") ||(Math.abs(x1 - x) != Math.abs(y1 - y)) || (letter.equals("w") && board[x][y].equals("b")) || (letter.equals("b") && board[x][y].equals("w")))){
            canMove = -1;
        }
        else if (Math.abs(x1 - x) > 1 && Math.abs(x1-x) == Math.abs(y1-y) && (board[(x+x1)/2][(y + y1)/2].equals("O"))){
            canMove = -1; //if more than 1 square but not jumping
        }
        else {
            if ((letter.equals("w") ||letter.equals("W")) && (board[(x+x1)/2][(y + y1)/2].equals("b") || board[(x+x1)/2][(y + y1)/2].equals("B")) && board[x1][y1].equals("O")){
                canMove = 1;
                board[(x+x1)/2][(y + y1)/2] = "O";
            }
            else if ((letter.equals("b") || letter.equals("B")) && (board[(x+x1)/2][(y + y1)/2].equals("w") || board[(x+x1)/2][(y + y1)/2].equals("W")) && board[x1][y1].equals("O")){
                canMove = 1;
                board[(x+x1)/2][(y + y1)/2] = "O";
            }
            else{
                canMove = 0;
            }
        }
        return canMove;
    }

    //can become king?
    public static void becomesK(String letter, pieces a){
        if (letter.equals("w") && a.getX() == 0){
            a.setKing(true);
            a.setLetter("W");
        }
        else if (letter.equals("b") && a.getX() == 7){
            a.setKing(true);
            a.setLetter("B");
        }
    }
    //print board
    public static void printBoard(String [][] n){
        System.out.println("  0 1 2 3 4 5 6 7 c");
        for (int i = 0; i < 8; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++){
                System.out.print(n[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("r");
        System.out.println();
    }
}