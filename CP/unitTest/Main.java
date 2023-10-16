public class Main {
       
       public static void main(String[] args) {
           
           Calculator calc = new Calculator();
           
           double y1 = calc.pervoe(10);
           double y2 = calc.vtoroe(7, 3);
           double y3 = calc.tretie(2, 4, 6);
           
           System.out.println("Значение выражения 1: " + y1);
           System.out.println("Значение выражения 2: " + y2);
           System.out.println("Значение выражения 3: " + y3);      
       }
   }
   

