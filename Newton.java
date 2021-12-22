package newton;

import java.util.ArrayList;

public class Newton {
    private ArrayList<Double> xvalues = new ArrayList<Double>();
    private ArrayList<Double> yvalues = new ArrayList<Double>();

    public Newton(ArrayList<Double> xvalues, ArrayList<Double> yvalues){
        this.xvalues=xvalues;
        this.yvalues=yvalues;
    }

   public Polynomial build(){
        int numberOfPoints = xvalues.size();
        ArrayList<Polynomial> polList = new ArrayList<Polynomial>();
        ArrayList<Double> coefficientList = new ArrayList<Double>();
        double[] coeff = new double[1];
        coeff[0] = yvalues.get(0);
        coefficientList.add(coeff[0]);
        polList.add(new Polynomial(coeff));
        Polynomial mk;
        Polynomial tempPolynomial;
        for (int i=1; i<=numberOfPoints-1;i++){
            double yk = yvalues.get(i);
            Polynomial previous = polList.get(i-1);
            double upper = yk-previous.evaluateAt(xvalues.get(i));
            double lower;
            lower=xvalues.get(i)-xvalues.get(0);
            for(int j=1; j<=i-1;j++){
                lower=lower*(xvalues.get(i)-xvalues.get(j));
            }
            double ck = upper/lower;
            coefficientList.add(ck);
            var tempArray = new ArrayList<Double>();
            for(int a=0;a<=i-1;a++){
                tempArray.add(xvalues.get(a));
            }
            tempPolynomial = Polynomial.rootToExpanded(ck,tempArray);
            mk = polList.get(i-1).addPolynomial(tempPolynomial);
            polList.add(mk);
        }

        Polynomial output = polList.get(numberOfPoints-1);
        return output;
    }


}
