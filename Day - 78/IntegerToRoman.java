public class IntegerTORoman{
    public static void main(String[] args) {
      
        int number = 3749;

        String numStr = String.valueOf(number);
        int length = numStr.length();

        for (int i = 0; i < length; i++) {
            int digit = numStr.charAt(i) - '0';

            int zeros = length - i - 1;

            int value = digit * (int)Math.pow(10, zeros);

            if (value != 0) {
                System.out.println(value);
            }
      }
    }
}