package marginaltax.service;

import com.google.gson.Gson;
import marginaltax.model.FederalTaxRule;
import marginaltax.utility.FormatUtility;
import marginaltax.utility.ResourceUtility;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class MarginalTaxService {
    private static final List<FederalTaxRule> fedTaxRules;

    static {
        Gson gson = new Gson();
        Function<String, FederalTaxRule> convert = json -> gson.fromJson(json, FederalTaxRule.class);
        fedTaxRules = ResourceUtility.get("fedTaxRules.txt", 0, convert);
    }

    public static float getTaxPaid(String status, float salary) {
        ToDoubleFunction<FederalTaxRule> map = e -> {
            float rangeTwo = e.salaryRange2() > salary ? salary : e.salaryRange2();
            float taxPaid = (rangeTwo - e.salaryRange1()) * e.rate();
            return taxPaid;
        };

        double taxPaid = fedTaxRules.stream()
                .filter(e -> e.status().equalsIgnoreCase(status))
                .filter(e -> salary > e.salaryRange1())
                .peek(System.out::println) // comment this line out to turn off FederalTaxRule brackets in output
                .mapToDouble(map)
                .sum();

        return (float) taxPaid;
    }
}
