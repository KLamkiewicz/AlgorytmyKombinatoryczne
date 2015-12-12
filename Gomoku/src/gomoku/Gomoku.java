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
        gomokuProblem( 7, 7 );
        System.out.println(" Finished game in : " + movesList.size() + " moves ");
        print();       
    }
    public static boolean gomokuProblem(int a, int b) {
        if ( gomoku( a, b )  ) { //Check if won 
            System.out.println( "VICTORY AT " + a + " " + b);
            return true;
        }
        
        List<Cell> possibleMoves = getPossibleMoves(a, b);
        Collections.shuffle(possibleMoves, new Random(System.nanoTime()));
        
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
    
    private static boolean gomoku(int a, int b) {
        
        String brdCheck = "";
      
        brdCheck = rowCheck(a, b);
        //Check row
        if (km.kmpMatcher(brdCheck, patternBlackWin)) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (km.kmpMatcher(brdCheck, patternWhiteWin)) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }

        brdCheck = columnCheck(a, b);
        //Check column
        if (km.kmpMatcher(brdCheck, patternBlackWin)) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (km.kmpMatcher(brdCheck, patternWhiteWin)) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }

        brdCheck = diagonalCheck(a, b);
        //Check diagonals
        if (km.kmpMatcher(brdCheck, patternBlackWin)) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (km.kmpMatcher(brdCheck, patternWhiteWin)) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }

        brdCheck = diagonalCheckUp(a, b);
        //Check diagonals
        if (km.kmpMatcher(brdCheck, patternBlackWin)) {
            board[a][b].setColor(Color.BLACK_V);
            return true;
        } else if (km.kmpMatcher(brdCheck, patternWhiteWin)) {
            board[a][b].setColor(Color.WHITE_V);
            return true;
        }

        return false;
    }
    private static String diagonalCheck( int a, int b){
        String diagonal = "";
        for( int j=b-4; j<=b+4; j++){
            if(j>=0 && j<BOARD_SIZE){
                diagonal+=board[j][j];
            }
        }
        return diagonal;
    }
 
    private static String diagonalCheckUp( int a, int b ){
        String diagonal = "";

        for( int j=b-4; j<=b+4; j++){
            if(j>=0 && j<BOARD_SIZE){
                if (j > b) {
                    if (a - (j-b) >= 0) {
                        diagonal += board[a - (j -b)][j];
                    }
                } else {
                    if ( (a + (b-j)) <  BOARD_SIZE ) {
                        diagonal += board[a + (b-j)][j];
                    }
                }
            }
        }
        return diagonal;
    }
    
    private static String rowCheck( int a, int b ){
        String row = "";
        for( int j=b-4; j<=b+4; j++){
            if( j>=0 && j<BOARD_SIZE ){
                row+=board[a][j].getColor();
            }
        }
        return row;
    }
    
    private static String columnCheck( int a, int b ){
        String column = "";
        for( int j=a-4; j<=a+4; j++){
            if( j>=0 && j<BOARD_SIZE ){
                column+=board[j][b].getColor();
            }
        }
        return column;
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
