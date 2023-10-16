public class Icklucheniya{

public void divide(int a, int b) throws ArithmeticException {
    a = 14;
    b = 4;
    if (b == 0) {
        throw new ArithmeticException("Деление на ноль невозможно");
    }
    int result = a / b;
    System.out.println("Результат: " + result);
}}