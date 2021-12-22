package newton;

import java.util.ArrayList;

public class Polynomial {
    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public ArrayList<Double> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(ArrayList<Double> coefficients) {
        this.coefficients = coefficients;
    }

    private int degree;
    private ArrayList<Double> coefficients = new ArrayList<Double>();

    //Coefficients passed in increasing order of x, eg C0 +C1X + C1X^2 + C2X^3
    public Polynomial(double... coefficients){
        for(Double c : coefficients) {
            this.coefficients.add(c);
        }
        this.degree=coefficients.length-1;
    }

    public Polynomial(Double... coefficients){
        for(Double c : coefficients) {
            this.coefficients.add(c);
        }
        this.degree=coefficients.length-1;
    }

    public Double evaluateAt(double x){
        Double output;
        output = coefficients.get(degree);
        if(degree!=0){
            for(int counter=degree; counter!=0; counter--){
                output=output*x;
                output=output+coefficients.get(counter-1);
            }

        }
        return output;
    }

    public String printOut(){
        StringBuilder builder = new StringBuilder();
        builder.append(coefficients.get(0));
        double coefficient;
        if(degree!=0){
            for(int i=1; i<=degree; i++){
                coefficient = coefficients.get(i);
                if(coefficient!=0){

                    if(coefficient>=0){
                        builder.append("+");
                    }
                    builder.append(coefficient);
                    builder.append("x");
                    if(i!=1){
                        builder.append("^"+i);
                    }
                }
            }
        }
        return builder.toString();
    }

    public Polynomial addPolynomial(Polynomial other){
        int thisDegree = this.degree;
        int otherDegree = other.degree;

        int maxDegree;
        if(thisDegree>otherDegree){
            maxDegree=thisDegree;
        }else{
            maxDegree=otherDegree;
        }
        var outputCoefficients = new ArrayList<Double>();
        Polynomial output;
        double current;
        for(int i=0; i<=maxDegree;i++){

            if(i>thisDegree){
                current = other.getCoefficients().get(i);
            }else if(i>otherDegree){
                current = this.getCoefficients().get(i);
            }else{
                current = other.getCoefficients().get(i)+this.getCoefficients().get(i);
            }
            outputCoefficients.add(current);
        }

        return new Polynomial(outputCoefficients.toArray(new Double[outputCoefficients.size()]));
    }

    public Polynomial multiplyByPolynomial(Polynomial other){
        ArrayList<Polynomial> toSum = new ArrayList<Polynomial>();

        var thisCoefficients = this.getCoefficients();
        var otherCoefficients = other.getCoefficients();
        Polynomial tempPolynomial;
        for(int thisPower=0; thisPower<=thisCoefficients.size()-1;thisPower++){
            for(int otherPower=0; otherPower<=otherCoefficients.size()-1;otherPower++){
                double thisCoefficient = thisCoefficients.get(thisPower);
                double otherCoefficient = otherCoefficients.get(otherPower);
                int tempPower=thisPower+otherPower;
                double tempCoefficient=thisCoefficient*otherCoefficient;
                double coeffs[] = new double[tempPower+1];
                for(int x=0; x<=tempPower; x++){
                    if(x==tempPower){
                        coeffs[x]=tempCoefficient;
                    }else{
                        coeffs[x]=0;
                    }
                }
                tempPolynomial = new Polynomial(coeffs);
                toSum.add(tempPolynomial);
            }
        }

        Polynomial output=toSum.get(0);
        for(int x=1; x<=toSum.size()-1;x++){
            output=output.addPolynomial(toSum.get(x));
        }
        return output;
    }

    public Polynomial multiplyByValue(double x){
        var tempArray = new double[1];
        tempArray[0]=x;
        Polynomial temp = new Polynomial(tempArray);
        return this.multiplyByPolynomial(temp);
    }

    public static Polynomial rootToExpanded(double c, ArrayList<Double> roots){
        double firstCoeff = roots.get(0)*-1*c;
        var tempArray = new double[2];
        tempArray[0]=firstCoeff;
        tempArray[1]=c;
        Polynomial temp = new Polynomial(tempArray);

        for(int x=1; x<=roots.size()-1;x++){
            tempArray[0]=roots.get(x)*-1;
            tempArray[1]=1;
            Polynomial second = new Polynomial(tempArray);
            temp=temp.multiplyByPolynomial(second);
        }

        return temp;
    }
}
