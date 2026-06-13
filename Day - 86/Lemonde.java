public class Lemonde {
    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};

        int five = 0;
        int ten = 0;

        boolean possible = true;

        for (int bill : bills) {

            if (bill == 5) {
                five++;
            }
            else if (bill == 10) {
                if (five == 0) {
                    possible = false;
                    break;
                }
                five--;
                ten++;
            }
            else { // bill == 20

                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                }
                else if (five >= 3) {
                    five -= 3;
                }
                else {
                    possible = false;
                    break;
                }
            }
        }

        System.out.println(possible);
    }
}