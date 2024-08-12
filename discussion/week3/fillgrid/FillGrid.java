package fillgrid;

public class FillGrid {
    public static void fillGrid(int[] LL, int[] UR, int[][] S) {
        int N = S.length;
        int kL, kR;
        kL = kR = 0;

        for (int i = 0; i < N; i++) {
            int x = S[i].length;
            for (int j = 0; j < x; j++) {
                if (i != j) {
                    if (j > i) {
                        S[i][j] = UR[kR++];
                    }
                    else {
                        S[i][j] = LL[kL++];
                    }
                }
            }
        }
    }
}
