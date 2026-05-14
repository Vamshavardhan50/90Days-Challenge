public class CelbrityPerson {

  public static void main(String[] args) {

    int[][] mat = {
        { 1, 1, 0 },
        { 0, 1, 0 },
        { 0, 1, 1 }
    };

    int n = mat.length;
    int celebrity = -1;

    for (int i = 0; i < n; i++) {

      boolean knowsNobody = true;
      boolean knownByEveryone = true;

      for (int j = 0; j < n; j++) {

        // Ignore self check
        if (i != j) {

          // Celebrity should not know anyone
          if (mat[i][j] == 1) {
            knowsNobody = false;
          }

          // Everyone should know celebrity
          if (mat[j][i] == 0) {
            knownByEveryone = false;
          }
        }
      }

      if (knowsNobody && knownByEveryone) {
        celebrity = i;
        break;
      }
    }

    System.out.println("Celebrity is: " + celebrity);
  }
}