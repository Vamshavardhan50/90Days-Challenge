import java.util.*;
public class MaxNumberOfBallons{
    public static void main(String[] args) {
        
    String text = "leetcode";
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : text.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        String target = "balloon";
        HashMap<Character, Integer> need = new HashMap<>();

        for (char ch : target.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        int instance = Integer.MAX_VALUE;

        for (char ch : need.keySet()) {
            if (!map.containsKey(ch)) {
                System.out.println(0);
            }

            instance = Math.min(instance, map.get(ch) / need.get(ch));
        }

        System.out.println(instance);
    }
}