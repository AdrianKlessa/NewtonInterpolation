package newton;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");



        Polynomial pol1 = new Polynomial(3,2,5,-7); // 3 +2x+5x^2-7x^3
        System.out.println("Evaluation: "+pol1.evaluateAt(-17.5));
        System.out.println(pol1.printOut());
        Polynomial pol2 = new Polynomial(2); // f(x)=2
        System.out.println("Evaluation: "+pol2.evaluateAt(5));



        Polynomial pol3 = new Polynomial(0,8); // 8x
        System.out.println("Evaluation: "+pol3.evaluateAt(2));
        Polynomial pol4 = new Polynomial(0,0,-12,8); //-12x^2+8x^3
        System.out.println("Evaluation: "+pol4.evaluateAt(4));

        Polynomial pol5 = pol1.addPolynomial(pol4);
        System.out.println(pol5.printOut());

        Polynomial polTemp = pol1.multiplyByPolynomial(pol4);

        var testRoots = new ArrayList<Double>();
        testRoots.add(2.0);
        testRoots.add(4.0);
        testRoots.add(-8.0);

        System.out.println(Polynomial.rootToExpanded(7.0,testRoots).printOut());
        System.out.println(polTemp.printOut());
        System.out.println("Interpolating sqrt function: ");
        var xvalues = new ArrayList<Double>();
        var yvalues = new ArrayList<Double>();
        xvalues.add(100.0);
        xvalues.add(121.0);
        xvalues.add(144.0);
        yvalues.add(10.0);
        yvalues.add(11.0);
        yvalues.add(12.0);
        Polynomial interpolating = new Newton(xvalues,yvalues).build();
        System.out.println("At 100: "+interpolating.evaluateAt(100));
        System.out.println("At 121: "+interpolating.evaluateAt(121));
        System.out.println("At 144: "+interpolating.evaluateAt(144));
        System.out.println("At 117: "+interpolating.evaluateAt(117));
        System.out.println("Interpolating polynomial: "+interpolating.printOut());

    }
}
