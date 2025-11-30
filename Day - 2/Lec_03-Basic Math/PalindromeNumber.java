// problem : checking if the number is palindrome or not

// so i noticed that if the number is negative it cannot be a palindrome ---> so i returned false 

// if nnumber if positive then i reversed the number and compared it with original number if both are same then its a palindrome otherwise not

public class PalindromeNumber {
  public static void main(String[] args) {
    int n = 121;

    boolean isNegative = false;
    int original = n;
    int reversed = 0;

    if (n < 0) {
      isNegative = true;
      System.out.println("Negative numbers cannot be palindromes.");

    }
    while (n > 0) {
      int ld = n % 10;
      reversed = reversed * 10 + ld;
      n /= 10;
    }
    System.out.println(reversed == original);

  }
}
