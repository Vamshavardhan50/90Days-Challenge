public class ProcessStringOperationsII {
    public static void main(String[] args) {
        String s = "z*#";
        String result = "";
        int k = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch)) {
                result += ch;
            }
            if (ch == '#') {
                result += result;
            }

            if (ch == '%') {
                String reversed = new StringBuilder(result).reverse().toString();
                result = reversed;
            }
            if (ch == '*') {
                String sub = result.substring(0, result.length() - 1);
                result = sub;
            }
        }
        if (k < 0 || k >= result.length()) {
            System.out.println(".");
        } else {
            System.out.println(result.charAt(k));
        }
    }
}
