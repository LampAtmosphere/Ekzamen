import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void method1(){
    Calculator calc = new Calculator();
    double y1 = calc.pervoe(10);
    assertEquals(35.0,y1,0001);
    }
    @Test
    public void method2(){
    Calculator calc = new Calculator();
    double y2 = calc.vtoroe(7, 3);
    assertEquals(2.5,y2,0001);
    }
    @Test
    public void method3(){
    Calculator calc = new Calculator();
    double y3 = calc.tretie(2, 4, 6);
    assertEquals(1.467523221730945,y3,0001);
    }
}
