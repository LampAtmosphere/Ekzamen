public class Calculator {

    public static double pervoe(double x) {
        return 3 * x + 5;
    }

    public static double vtoroe(double a, double b) {
        if ((a-b) == 0) return -1;
        return (a + b)/(a - b);
    }

    public static double tretie(double a, double x, double b) {
        if (b == 0) return -1;
        double ax = Math.pow(a*x/b, a*x/b);
        return ax;
    }
}