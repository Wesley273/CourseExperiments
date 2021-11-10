package arithmetic.indefiniteequation;

public class IndefiniteEquation {
    public static void main(String[] args) {
        for (int x = 1; x <= 5; x++) {
            for (int y = 1; y <= 5; y++) {
                for (int z = 1; z <= 53; z++) {
                    if (100 * x + 10 * y + z + 100 * y + 11 * z == 532) {
                        System.out.println("X=" + x + " " + " Y=" + y + " " + " Z=" + z);
                    }
                }
            }
        }
    }
}
