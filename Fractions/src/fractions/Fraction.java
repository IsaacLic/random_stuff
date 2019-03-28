package mathematics;

public class Fraction {

    private int numerator, denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        validation();
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Fraction() {
        this(0);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setNumerator(int i) {
        numerator = i;
        validation();
    }

    public void setDenominator(int i) {
        if (denominator == 0) {
            throw new RuntimeException(String.format(
                    "Denominator must not be 0; it was %d", i));
        }
        denominator = i;
        validation();
    }

    @Override
    public String toString() {
        return denominator == 1
                ? numerator + ""
                : String.format("%d/%d", numerator, denominator);
    }

    private void validation() { 
// private bc principle of least privilage (access only as needed)
        if (numerator < 0 && denominator < 0) {
            denominator *= -1;
            denominator = -denominator;
        }
    }

    public void reduce() {
        for (int i = denominator + numerator; i > 1; i--) {
            if ((denominator % i == 0) && (numerator % i == 0)) {
                denominator /= i;
                numerator /= i;
            }
        }
    }

    public Fraction plus(Fraction that) {
        return new Fraction(
                (this.numerator * that.denominator) + 
                        (that.numerator * this.denominator),
                this.denominator * that.denominator);

    }

    public Fraction minus(Fraction that) {
        return new Fraction(
                (this.numerator * that.denominator) - (that.numerator * this.denominator),
                this.denominator * that.denominator);
    }

    public Fraction dividedBy(Fraction that) {
        return new Fraction(
                this.numerator * that.denominator,
                this.denominator * that.numerator);
    }

    public void negate() {
        if (numerator < 0) {
            numerator = -numerator;
        } else {
            denominator = -denominator;
        }
    }

    public Fraction times(Fraction that) { // return new fraction
        return new Fraction(
                this.numerator * that.numerator,
                this.denominator * that.denominator);
    }
}

