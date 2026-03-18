// public class Practise {
//   public static void print(int n) {
//     if (n == 0) {
//       return;
//     }
//     print(n - 1);
//     System.out.print(n + " ");
//   }

//   public static void main(String[] args) {
//     int n = 7;
//     print(n);
//   }
// }

//Factorial of number
public class Practise {
  public static int factorial(int n) {
    if (n == 1) { // 5*4*3*2*1*0(return 1);
      return 1;
    }
    return n * factorial(n - 1);

  }

  public static void main(String[] args) {
    System.out.println(factorial(5));
  }
}