public class Loan {
    private double principal;
    private double rate;
    private double term;

    public Loan(double principal, double rate, double term) {
        this.principal = principal;
        this.rate = rate;
        this.term = term;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getRate() {
        return rate;
    }

    public double getTerm() {
        return term;
    }
}
