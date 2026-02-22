public class SumofBeautyofAllSubstrings {
  public static void main(String[] args) {
    int ans = 0;

    String s = "aabcbaa";
    
    for (int i = 0; i < s.length(); i++) {
      int[] freq = new int[26];
      for (int j = i; j < s.length(); j++) {
        char ch = s.charAt(j);
        freq[s.charAt(j) - 'a']++;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int k = 0; k < 26; k++) {
          if (freq[k] > 0) {
            max = Math.max(freq[k], max);
            min = Math.min(freq[k], min);
          }
        }
        ans += max - min;
      }
    }
    System.out.println(ans);
  }
}
