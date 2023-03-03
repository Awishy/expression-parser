package expression.generic;

public class GenericCommandLine {
    public static void main(String[] args) {
        GenericTabulator tabulator = new GenericTabulator();
        try {
            Object[][][] arr = tabulator.tabulate(args[0], args[1], -2, 2, -2, 2, -2, 2);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        System.out.println(arr[i][j][k]);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
