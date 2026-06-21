import java.util.HashMap;

public class WeightedWordMapping {
    public static void main(String[] args) {
        String[] words = {"a","b","c"};
        int[] weights = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

        String res = "";
        for(int i=0;i<words.length;i++){
            int eachWordSum =0;
            for(int j=0;j<words[i].length();j++){
                int charToDigit = words[i].charAt(j)-'a';
                eachWordSum+=weights[charToDigit];
            }
            int mod = 26-eachWordSum%26;
            res=res+(char) (96+mod);

        }
        System.out.println(res);
    }
}
