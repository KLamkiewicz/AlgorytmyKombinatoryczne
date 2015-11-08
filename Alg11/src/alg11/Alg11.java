package alg11;

import java.util.Arrays;

/**
 *
 * @author krzysiek
 */
public class Alg11 {

    
    public static void generate(int n){
        int[] B = new int[n];
        for(int i=0; i<n; i++){
            B[i] = 0;
        }
        int i=0;
        int p=0;
        int j=0;
        while(p<n){
            print(B); 
            i++;
            p=0;
            j=i;
            while(j%2==0){
                j=j/2;
                p++;
            }
            if(p<n){
                B[p]=1-B[p];
            }
        }
    }
    
    public static void print(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print( arr[i] + " ");
        }
        System.out.println("");
    }
    
    String[] strCode;
    
    
    public static void main(String[] args) {
        //Alg11.generate(4);
        int ile = 5;
        Alg11 alg11 = new Alg11(ile);
        
        alg11.gray(ile);
        alg11.print();

    }
    
    public Alg11(int n){
        int count = (int) Math.pow(2, n);
        this.strCode = new String[count];
        Arrays.fill(this.strCode, "");
    }
    
    public void gray(int n) {
        if (n >= 1) {
            gray(n - 1);
            int iWordCount = (int) Math.pow(2, n);
            if (n > 1) {
                for (int i = iWordCount - 1; i >= iWordCount / 2; i--) {
                    strCode[i] += strCode[iWordCount - i - 1];
                }
            }
            for (int i = 0; i < iWordCount; i++) {
                if (i < iWordCount / 2) {
                    strCode[i] += "0";
                } else {
                    strCode[i] += "1";
                }
            }
        }
    }
    
    public void print(){
        for(String str : strCode ){
            System.out.println(str);
        }
    }  
    
}
