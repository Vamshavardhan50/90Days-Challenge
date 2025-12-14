import java.util.ArrayList;
import java.util.List;

class SprialMatrix {
  public List<Integer> spiralOrder(int[][] mat) {
    List<Integer> ans = new ArrayList<>();
    int m = mat.length;
    int n = mat[0].length;

    int srow = 0, scol = 0, erow = m - 1, ecol = n - 1;

    while (srow <= erow && scol <= ecol) {
      // top row
      for (int i = scol; i <= ecol; i++) {
        ans.add(mat[srow][i]);
      }

      // right column
      for (int i = srow + 1; i <= erow; i++) {
        ans.add(mat[i][ecol]);
      }

      // bottom row (if more than one row remains)
      if (srow < erow) {
        for (int i = ecol - 1; i >= scol; i--) {
          ans.add(mat[erow][i]);
        }
      }

      // left column (if more than one column remains)
      if (scol < ecol) {
        for (int i = erow - 1; i > srow; i--) {
          ans.add(mat[i][scol]);
        }
      }

      // shrink boundaries
      srow++;
      erow--;
      scol++;
      ecol--;
    }

    return ans;
  }
}
