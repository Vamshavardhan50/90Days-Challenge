import java.util.*;

public class DailyProblem {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            String s = sc.next();

            int ones = 0, zeroes = 0;

            for (char ch : s.toCharArray()) {
                if (ch == '0') {
                    zeroes++;
                } else {
                    ones++;
                }
            }

            int i;
            for (i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    if (ones > 0) {

                        ones--;
                    } else {
                        break;
                    }
                } else {
                    if (zeroes > 0) {
                        zeroes--;
                    } else {
                        break;
                    }
                }
            }
            System.out.println(s.length() - i);
        }
    }
}