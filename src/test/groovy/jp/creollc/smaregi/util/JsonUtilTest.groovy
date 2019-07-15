package jp.creollc.smaregi.util

import com.fasterxml.jackson.core.type.TypeReference
import groovy.json.JsonOutput
import jp.creollc.smaregi.api.product.Product
import org.apache.commons.io.FilenameUtils
import spock.lang.Specification
import jp.creollc.smaregi.test.util.TestUtil
import org.apache.commons.io.FileUtils

import java.util.concurrent.ConcurrentHashMap

class JsonUtilTest extends Specification {
    static def workTempDir = TestUtil.getTempPath() + "smaregitest";

    def setup() {
        FileUtils.deleteDirectory(new File(workTempDir));
        FileUtils.forceMkdir(new File(workTempDir))
    }

    def "convertMapListToObject Test" () {
        when:
        List<Map<String, String>> list = new ArrayList() {{
            add(new ConcurrentHashMap() {{
                put("productId", "113");
                put("no", "1");
                put("value", "項目1");
            }});
            add(new ConcurrentHashMap() {{
                put("productId", "114");
                put("no", "2");
                put("value", "項目2");
            }});
        }}

        List<Product> products = JsonUtil.convertMapListToObject(list, Product.class)

        then:
        products[0].getProductId() == "113"
        products[1].getProductId() == "114"
    }

    def "toObject with Type Reference Test"() {
        when:
        def requestJson = JsonOutput.toJson([
                [color: "red"],
                [color: "green"]
        ])
        def result = JsonUtil.toObject(requestJson, new TypeReference<List<Product>>() {})

        then:
        result[0].color == "red"
        result[1].color == "green"
    }

    def "toJSONFile and getObject test" () {
        when:
        def path = FilenameUtils.concat(workTempDir, "tmp.json")
        Product product = new Product();
        product.setColor("red")
        product.setCatchCopy("Hello world")

        File f = new File(path);

        JsonUtil.toJSONFile(path, product)
        Product retObj = JsonUtil.getObject(path, Product.class)

        then:
        f.exists()
        retObj.getColor() == "red"
        retObj.getCatchCopy() == "Hello world"
    }
}
