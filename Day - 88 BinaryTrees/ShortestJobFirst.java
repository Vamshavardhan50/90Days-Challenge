import java.util.Arrays;

public class ShortestJobFirst {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 5};
        Arrays.sort(arr);

        int t=0;
        int wttime=0;

        for(int n : arr){
            wttime+=t;
            t+=n;
        }
        System.out.println(wttime);
    }
}
