package gomoku;

/**
 *
 * @author krzysiek
 */
enum Color {

        BLACK("X"),
        WHITE("O"),
        NONE("-");

        private int i;
        private int j;
        private String color;
        
        Color( String color ){
            this.color = color;
        }
        
        public void setPosition(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getPositionI() {
            return i;
        }

        public int getPositionJ() {
            return j;
        }
        
        @Override
        public String toString(){
            return color;
        }

    }