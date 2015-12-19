package ulam;

import java.util.ArrayList;
import java.util.List;

public class Ulam {

    private static final int RANGE = 800;
    private List<Integer> numbers = new ArrayList<>();
    int[] values = new int[RANGE + 1];

    public static void main(String args[]) {
        Ulam ulam = new Ulam();
        ulam.fillList();
        ulam.findUlamNumbers();
        
    }
    
    public void findUlamNumbers() {
        
        for (int i = 1; i <= RANGE; i++) {
            for (int j = 1; j < i; j++) {
                int size = numbers.size();
                for (int n = 0; n < size && numbers.contains((i+j)) && numbers.contains(i) && numbers.contains(j); n++) {
                    Integer number = numbers.get(n);
                    if (number.equals((i + j))) {
                        values[number]++;
                    }
                    if (values[number] > 1) {
                        numbers.remove(number);
                        size--;n--;
                        break;
                    }
                    for (Integer z = 4; z <= i; z++) {
                        if (values[z] == 0) {
                            while( numbers.contains(z)){
                                numbers.remove(z);
                                size--;n--;
                            }
                        }
                    }
                }
            }
        }
        
        int size = numbers.size();
        for (int i=1; i<size;i++) {
            System.out.print(String.format("%4s", numbers.get(i)) + " ");
            if (i % 10 == 0) {
                System.out.println("");
            }
        }

    }
    public void fillList() {
        for (int i = 0; i < RANGE; i++) {
            numbers.add(i);
        }
    }
}
