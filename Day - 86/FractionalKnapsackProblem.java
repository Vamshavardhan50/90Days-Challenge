import java.util.Map;
import java.util.TreeMap;

public class FractionalKnapsackProblem{
    public static void main(String[] args) {
        int[] val ={8 2 10 1 9 7 2 6 4 9};
        int[] wt = {10, 20};
        int capacity = 50;


        Map<Integer,Integer> map = new TreeMap<>();

        for(int i=0;i<val.length;i++){
            map.put(val[i], wt[i]);
        }

        System.out.println(map);

        int total =0;
        for(Map.Entry<Integer,Integer> entry :map.entrySet()){
            int currentwt = entry.getValue();
            if(currentwt<capacity && capacity - currentwt >0){
                capacity -=currentwt;
                total+=entry.getKey();
            }
            else{
                int fractionalpart = entry.getKey()/entry.getValue();
                fractionalpart = capacity*fractionalpart;

                total+=fractionalpart;
            }
        }
        System.out.println(total);
    }
}