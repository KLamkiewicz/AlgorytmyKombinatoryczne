package gomoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Gomoku {

    private static final int BOARD_SIZE = 15;
    private static final List<Cell> movesList = new ArrayList<>();
    private static final Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];  
    private static final KnuthMorris km = new KnuthMorris();
    private static final String patternBlackWin = "xxxxx";
    private static final String patternWhiteWin = "ooooo";
    
    public static void main(String[] args) {
        init();
        System.out.println(" Starting board : ");
        print();
        gomokuProblem(14, 9);
        System.out.println(" Finished game board in : " + movesList.size() + " moves ");
        print();       
    }
    public static boolean gomokuProblem(int a, int b) {
        if ( gomoku( a, b )  ) { //Check if won 
            System.out.println( "VICTORY AT " + a + " " + b);
            return true;
        }
        
        List<Cell> possibleMoves = getPossibleMoves(a, b);
        long seed = System.nanoTime();
        Collections.shuffle(possibleMoves, new Random(seed));
        
        for( Cell cell : possibleMoves ){
            if( canBeSet( cell ) ){
                addMove(cell);
                if( gomokuProblem( cell.getI(), cell.getJ())){
                    return true;
                }
                deleteLast();
            }
        }
        return false;
    }
    
    private static List<Cell> getPossibleMoves(int a, int b){
        List<Cell> possibleMoves = new ArrayList<>();
        for( int i=a-1; i<=a+1; i++){
            for( int j=b-1; j<=b+1; j++){
                if( ( i!=a || j!=b ) && i>=0 & j>=0 && i<BOARD_SIZE && j<BOARD_SIZE ){
                    possibleMoves.add(board[i][j]);
                }
            }
        }
        
        return possibleMoves;
    }
        
    //TODO Return string from the checkers, reassign, check with pattern only
    private static boolean gomoku(int a, int b) {

        //Check row
        if (rowCheck(a, b, patternBlackWin)) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (rowCheck(a, b, patternWhiteWin)) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }

        //Check column
        if (columnCheck(a, b, patternBlackWin)) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (columnCheck(a, b, patternWhiteWin)) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }
        
        //Check diagonals
        if (diagonalCheck(a, b, patternBlackWin)) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (diagonalCheck(a, b, patternWhiteWin)) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }
        //Check diagonals
        if ( diagonalCheckUp(a, b, patternBlackWin) ) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (diagonalCheckUp(a, b, patternWhiteWin) ) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }

        return false;
    }
    
    private static boolean diagonalCheck( int a, int b, String pattern ){
        String diagonal = "";
        for( int j=b-4; j<=b+4; j++){
            if(j>=0 && j<BOARD_SIZE){
                diagonal+=board[j][j];
            }
        }
        return km.kmpMatcher(diagonal, pattern);
    }
    
    private static boolean diagonalCheckUp( int a, int b, String pattern ){
        String diagonal = "";
        System.out.println("");
        System.out.println(" A : " + a + " B : " + b);
        System.out.println("");
        for( int j=b-4; j<=b+4; j++){
            if(j>=0 && j<BOARD_SIZE){
                if (j > b) {
                    if (a - (j-b) >= 0) {
                        diagonal += board[a - (j -b)][j];
                        System.out.println( "B I : " + (a-(j-b)) + " J : " + j);
                    }
                } else {
                    if ( (a + (b-j)) <  BOARD_SIZE ) {
                        diagonal += board[a + (b-j)][j];
                        System.out.println( "S I : " + (a+(b-j)) + " J : " + j );
                    }
                }
            }
        }
        
        return km.kmpMatcher(diagonal, pattern);
    }
    
    private static boolean rowCheck( int a, int b, String pattern ){
        String row = "";
        for( int j=b-4; j<=b+4; j++){
            if( j>=0 && j<BOARD_SIZE ){
                row+=board[a][j].getColor();
            }
        }
        return km.kmpMatcher(row, pattern);
    }
    
    private static boolean columnCheck( int a, int b, String pattern ){
        String column = "";
        for( int j=a-4; j<=a+4; j++){
            if( j>=0 && j<BOARD_SIZE ){
                column+=board[j][b].getColor();
            }
        }
        return km.kmpMatcher(column, pattern);
    }
    
    private static void addMove( Cell cell ){
        Color color = movesList.get(movesList.size()-1).getColor();
        cell.setMove( movesList.size()+1);
        if( Color.BLACK.equals(color) ){
            cell.setColor(Color.WHITE);
        }else{
            cell.setColor(Color.BLACK);
        }
        movesList.add(cell);
    }
    
    private static boolean canBeSet( Cell cell ){
        return cell.getColor()==Color.NONE;
    }
    
    private static void deleteLast(){
        Cell cell = movesList.get(movesList.size()-1);
        cell.setColor(Color.NONE);
        cell.setMove(0);
        movesList.remove(movesList.size()-1);
    }
    
    
    private static void init() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell( i, j );
            }
        }
        movesList.add(board[7][7].doAll(Color.BLACK, 1));
        movesList.add(board[8][7].doAll(Color.WHITE, 2));
        movesList.add(board[7][8].doAll(Color.BLACK, 3));
        movesList.add(board[0][5].doAll(Color.BLACK, 4));
//        //movesList.add(board[0][1].doAll(Color.BLACK, 4));
//        movesList.add(board[3][1].doAll(Color.BLACK, 5));
//        movesList.add(board[4][1].doAll(Color.BLACK, 6));
//        movesList.add(board[5][1].doAll(Color.BLACK, 7));
//        movesList.add(board[6][1].doAll(Color.BLACK, 8));
        //movesList.add(board[][1].doAll(Color.BLACK, 8));
        
    }

    public static void print() {
        for( int i=0; i<BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(String.format("%3s", board[i][j]));
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
