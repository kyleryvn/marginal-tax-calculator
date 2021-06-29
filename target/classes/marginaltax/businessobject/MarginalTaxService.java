package marginaltax.businessobject;

import com.google.gson.Gson;
import marginaltax.model.FederalTaxRule;
import marginaltax.utility.ResourceStreamUtility;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class MarginalTaxService {
    private static final List<FederalTaxRule> fedTaxRules;

    static {
        Gson gson = new Gson();
        Function<String, FederalTaxRule> convert = json -> gson.fromJson(json, FederalTaxRule.class);
        fedTaxRules = ResourceStreamUtility.getResource("docs/fedTaxRules.txt", 0, convert);
    }

    public static float getTaxPaid(String status, float salary) {
        ToDoubleFunction<FederalTaxRule> map = taxRule -> {
            float rangeTwo = Math.min(taxRule.getSalaryRange2(), salary);
            return (rangeTwo - taxRule.getSalaryRange1()) * taxRule.getRate();
        };

        double taxPaid = fedTaxRules.stream()
                .filter(taxRule -> taxRule.getStatus().equalsIgnoreCase(status))
                .filter(taxRule -> salary > taxRule.getSalaryRange1())
                .peek(System.out::println) // comment this line out to turn off FederalTaxRule brackets in output
                .mapToDouble(map)
                .sum();

        return (float) taxPaid;
    }
}
