package marginaltax;

import com.google.gson.Gson;
import marginaltax.model.FederalTaxRule;
import marginaltax.utility.ResourceStreamUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

@DisplayName("Test Resource Stream Utility")
public class Test_ResourceStreamUtility {

    @Test
    @DisplayName("Test file load")
    void testLoadFile() {
        Gson gson = new Gson();
        Function<String, FederalTaxRule> convert = json -> gson.fromJson(json, FederalTaxRule.class);
        String filename = "docs/fedTaxRules.txt";

        List<FederalTaxRule> federalTaxRules = ResourceStreamUtility.getResource(filename, 0, convert);
        federalTaxRules.forEach(System.out::println);

    }
}
