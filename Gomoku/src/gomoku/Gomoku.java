package gomoku;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krzysiek
 */
public class Gomoku {

    private static final int BOARD_SIZE = 15;
    private static final List<Cell> movesList = new ArrayList<>();
    private static final Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
    private static final int FINAL_MOVE = 21;
    public static  int z = 0;

    public static void main(String[] args) {
        
        init();
       
        print();
//        for(int i=0; i<movesList.size();i++){
//            System.out.println(movesList.get(i).getMove());
//        }
        gomokuProblem(8, 8);
        
        print();       
        
//        for(int i=0; i<movesList.size();i++){
//            System.out.println(movesList.get(i).getMove());
//        }

    }
    static int i =0;
    static int smallest = 1000;
    public static boolean gomokuProblem(int a, int b) {
        if ( gomoku()  ) { //Check if won
//            if( movesList.size()< smallest){
//                smallest = movesList.size();
//                System.out.println(smallest);
//            }
            
            //if( movesList.size()<=FINAL_MOVE){
                
                return true;
        }
        List<Cell> possibleMoves = getPossibleMoves(a, b);
        
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
    
            
    private static final KnuthMorris km = new KnuthMorris();
    private static final String pattern = "XXXXX";
        
    
    private static boolean gomoku(){

        
        for( int i=0; i<BOARD_SIZE; i++){
            if(km.kmpMatcher( getRow(i), pattern)){
                return true;
            }
            if(km.kmpMatcher(getColumn(i), pattern)){
                return true;
            }
            if (km.kmpMatcher(getDiagonalLeft(i), pattern)) {
                return true;
            }
        }    
        return false;
    }
    
    private static String getRow(int i){
        String row = "";
        for(int j=0; j<BOARD_SIZE; j++){
            row+=board[i][j].getColor().toString();
        }
        
        return row;
    }
    
    private static String getColumn(int i){
        String column = "";
        for(int j=0; j<BOARD_SIZE; j++){
            column+=board[j][i].getColor().toString();
        }
        return column;
    }
    
    private static String getDiagonalLeft(int i){
        String diagonal = "";
        int z = 0;
        for( int k=BOARD_SIZE-i; k<BOARD_SIZE; k++){
                diagonal += board[k][z].getColor().toString();
                //System.out.println(k + " " + z);
          
            z++;
        }
        
        return diagonal;
    }
    
    private static String getDiagonalRight(int i){
        String diagonal = "";
        int z = 0;
        for( int k=BOARD_SIZE-i; k<BOARD_SIZE; k++){
                diagonal += board[k][z].getColor().toString();
                System.out.println(k + " " + z);
          
            z++;
        }
        System.out.println(" x ");
        
        return diagonal;
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
//        movesList.add(board[7][6].doAll(Color.WHITE, 4));
//        movesList.add(board[9][8].doAll(Color.BLACK, 5));
//        movesList.add(board[6][7].doAll(Color.WHITE, 6));
//        movesList.add(board[8][8].doAll(Color.BLACK, 7));
//        movesList.add(board[10][8].doAll(Color.WHITE, 8));
//        movesList.add(board[9][9].doAll(Color.BLACK, 9));
//        movesList.add(board[10][10].doAll(Color.WHITE, 10));
    }

    public static void print() {
        //for (int i = BOARD_SIZE - 1; i >= 0; i--) {
        for( int i=0; i<BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(String.format("%3s", board[i][j]));
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
