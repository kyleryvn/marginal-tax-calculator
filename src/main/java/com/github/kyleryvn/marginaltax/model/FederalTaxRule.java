package com.github.kyleryvn.marginaltax.model;

public class FederalTaxRule {
    private final float rate;
    private final String status;
    private final float salaryRange1;
    private final float salaryRange2;

    public FederalTaxRule(float rate, String status, float salaryRange1, float salaryRange2) {
        this.rate = rate;
        this.status = status;
        this.salaryRange1 = salaryRange1;
        this.salaryRange2 = salaryRange2;
    }

    public float getRate() {
        return rate;
    }

    public String getStatus() {
        return status;
    }

    public float getSalaryRange1() {
        return salaryRange1;
    }

    public float getSalaryRange2() {
        return salaryRange2;
    }

    @Override
    public String toString() {
        return "FederalTaxRule{" +
                "rate=" + rate +
                ", status='" + status + '\'' +
                ", salaryRange1=" + salaryRange1 +
                ", salaryRange2=" + salaryRange2 +
                '}';
    }
}
