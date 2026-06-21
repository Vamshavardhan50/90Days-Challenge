import java.util.Arrays;
public class MaxIceCreamBars{
    public static void main(String[] args) {
        int[] arr = {10,6,8,7,7,8};
        int coins = 5;
        Arrays.sort(arr);

        int count=0;
        for(int i=0;i<arr.length;i++){
            if(coins<=0){
                break;
            }
            else{
                coins =coins-arr[i];
                count++;
            }
        }
System.out.println(count);
        
    }
}