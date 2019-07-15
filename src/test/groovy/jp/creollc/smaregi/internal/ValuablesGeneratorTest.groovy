package jp.creollc.smaregi.internal

import org.apache.commons.io.FileUtils
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class ValuablesGeneratorTest extends Specification {

    @Ignore("Only when generating new class")
    def "main test" () {
        when:
        String[] args = ["/Users/yasuflatland/Desktop/input.txt", "/Users/yasuflatland/Desktop/"]
        ValuablesGenerator.main(args);

        def f = new File("/Users/yasuflatland/Desktop/Stock.java")
        def f2 = new File("/Users/yasuflatland/Desktop/StockConstants.java")

        then:
        f.exists()
        f2.exists()
    }

    def "constantsClassGenerator Test" () {
        when:
        def modelName = "Product"
        def prefix = "public String "
        List<String> values = new ArrayList() {{
            add("Product");
            add("storeId");
            add("productId");
        }}
        def result = ValuablesGenerator.constantsClassGenerator(values);

        then:
        result == ["public class ProductConstants {", 'public static final String STORE_ID = "storeId";', 'public static final String PRODUCT_ID = "productId";', '}']
    }

    def "classGenerator Test" () {
        when:
        def modelName = "Product"
        def prefix = "public String "
        List<String> values = new ArrayList() {{
            add("Product");
            add("storeId");
            add("productId");
        }}
        def result = ValuablesGenerator.classGenerator(values);

        then:
        result == ["@Data", "@JsonInclude(JsonInclude.Include.NON_EMPTY)", "public class Product {", "public String storeId;", "public String productId;", "}"];
    }

    def "valueGenerator Test"() {
        when:
        def prefix = "public String "
        List<String> values = new ArrayList() {{
            add("storeId");
            add("productId");
        }}

        def result = ValuablesGenerator.valueGenerator(prefix, values);

        then:
        result == ["public String storeId;", "public String productId;"]

    }
}
