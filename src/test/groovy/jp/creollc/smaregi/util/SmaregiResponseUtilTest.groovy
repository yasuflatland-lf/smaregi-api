package jp.creollc.smaregi.util

import com.fasterxml.jackson.core.type.TypeReference
import groovy.json.JsonOutput
import jp.creollc.smaregi.api.product.Product
import jp.creollc.smaregi.SmaregiResponse
import spock.lang.Specification

class SmaregiResponseUtilTest extends Specification {

    def "getRefResultByObject to Product List Test" () {
        when:
        def requestJson = JsonOutput.toJson([result: [
                [productId: 113, groupCode: 1, productName: '項目1'],
                [productId: 114, groupCode: 2, productName: '項目2'],
                [productId: 115, groupCode: 3, productName: '項目3']
        ]])

        SmaregiResponse smaregiResponse = SmaregiResponse.builder().resultJSON(requestJson).build()
        List<Product> products = SmaregiResponseUtil.getRefResultByObject(requestJson, new TypeReference<List<Product>>() {}, Product.class)

        then:
        products[0].getProductId() == "113"
        products[1].getProductName() == "項目2"
    }
}
