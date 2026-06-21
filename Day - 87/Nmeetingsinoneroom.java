import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Nmeetingsinoneroom {
    public static void main(String[] args) {
        int[] start = {1,3,0,5,8,5}, ends =  {2,4,5,7,9,9};

        List<int[]> meetings = new ArrayList<>();

        for(int i=0;i<start.length;i++){
            meetings.add(new int[]{ends[i],start[i],i+1});
        }

        meetings.sort(Comparator.comparingInt(a->a[0]));

        List<Integer> res = new ArrayList<>();

        int lastEnd =-1;

        for(int[] m : meetings){
            if(m[1]>=lastEnd){
                res.add(m[2]);
                lastEnd=m[0];
            }
        }
        System.out.println(res);
    }
}
