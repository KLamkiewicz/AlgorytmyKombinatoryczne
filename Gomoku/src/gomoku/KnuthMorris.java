package gomoku;

public class KnuthMorris {
        boolean kmpMatcher(String text, String pattern){
                int n = text.length();
                int m = pattern.length();
                int pi[] = computePrefix(pattern);
                int q = 0;
                
                for(int i=1; i<=n; i++){
                        while(q>0 && pattern.charAt(q)!=text.charAt(i-1)){
                                q = pi[q];
                        }
                        if(pattern.charAt(q)==text.charAt(i-1)){
                                q++;
                        }
                        if(q==m){
                            return true;
                       }
                }
                return false;
        }

        int[] computePrefix(String pattern){
                int m = pattern.length();
                int pi[] = new int[m];
                pi[0] = 0;
                int k = 0;

                for(int q=2; q<m; q++){
                        while(k>0 && pattern.charAt(k+1)!=pattern.charAt(q)){
                                k = pi[k];
                        }
                        if(pattern.charAt(k+1)==pattern.charAt(q)){
                                k++;
                        }
                        pi[q] = k;
                }
                return pi;
        }
        public static void main(String[] args) {
                KnuthMorris km = new KnuthMorris();
                String text = "gabcdabdabaxwr";
                String pattern = "aba";
                System.out.println("Tekst : " + text);
                System.out.println("Wzorzec : " + pattern);
                km.kmpMatcher(text,pattern);

        }
}