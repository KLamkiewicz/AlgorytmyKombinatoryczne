package gomoku;

/**
 *
 * @author krzysiek
 */
public class Cell {

    private Color color;
    private int move;
    private int i;
    private int j;

    public Cell(int i, int j){
        this.i = i;
        this.j = j;
        this.color = Color.NONE;
    }
    
    public Cell doAll(Color color, int move){
        this.color = color;
        this.move = move;
        return this;
    }
    
    public int getMove(){
        return move;
    }
    
    public Color getColor(){
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    
    @Override
    public String toString(){
        return color.toString();
    }
}
