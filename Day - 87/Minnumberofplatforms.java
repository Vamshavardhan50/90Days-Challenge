import java.sql.Date;
import java.util.Arrays;

public class Minnumberofplatforms {
    public static void main(String[] args) {
        int[] arr = {900, 945, 955, 1100, 1500, 1800};
        int[] dep = {920, 1200, 1130, 1150, 1900, 2000};
    
        Arrays.sort(arr);
        Arrays.sort(dep);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(dep));

        int platforms = 1;
        int result = 1;
        int i = 1, j = 0;
 
        while(i<arr.length && j<arr.length){
            if(arr[i]<=dep[j]){
                platforms++;
                i++;
            }
            else{
                platforms--;
                j++;
            }
            result =Math.max(result, platforms);
        }
        System.out.println(result);
 
    }
}
