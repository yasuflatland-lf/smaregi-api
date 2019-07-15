package jp.creollc.smaregi

import groovy.json.StringEscapeUtils
import jp.creollc.smaregi.RefRequestBuilder
import jp.creollc.smaregi.SmaregiRequest
import jp.creollc.smaregi.api.category.constants.CategoryConstants
import jp.creollc.smaregi.api.product.constants.ProductConstants
import jp.creollc.smaregi.constants.SmaregiConstants
import jp.creollc.smaregi.util.JsonUtil
import spock.lang.Specification
import jp.creollc.smaregi.test.util.TestUtil

import java.security.InvalidParameterException

class RefRequestBuilderTest extends Specification {

    def "RefRequestBuilder Error test"() {
        when:
        SmaregiRequest request = RefRequestBuilder.builder()
                .conditions("key1", "param1", "key2", "param2", "key3")
                .build();
        then:
        thrown(InvalidParameterException.class)
    }

    def "RefRequestBuilder test"() {
        when:
        SmaregiRequest request = RefRequestBuilder.builder()
                .conditions("key1", "param1", "key2", "param2")
                .build();
        def params =  StringEscapeUtils.unescapeJava(JsonUtil.toJSON(request.getParams()))
        def expected = $/"{
  "conditions" : [ {
    "key1" : "param1"
  }, {
    "key2" : "param2"
  } ],
  "limit" : 0,
  "page" : 1
}"/$
        then:
        params.replace(TestUtil.LTM, "\n") == expected.replace(TestUtil.LTM, "\n")
    }

    def "Builder conditions Test"() {
        when:
        List<Map<String, String>> conditions = new ArrayList() {
            {
                add(new HashMap() {
                    {
                        put(ProductConstants.CATEGORY_ID, "1");
                    }
                });
                add(new HashMap() {
                    {
                        put(ProductConstants.PRODUCT_NAME + " like", "%沖縄%");
                    }
                });
            }
        }

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_PRODUCT_REF)
                .conditions(conditions)
                .tableName(ProductConstants.TABLE_NAME)
                .limit(500)
                .page(1)
                .build();
        def params = StringEscapeUtils.unescapeJava(JsonUtil.toJSON(request.getParams()))
        def expected = $/"{
  "conditions" : [ {
    "categoryId" : "1"
  }, {
    "productName like" : "%沖縄%"
  } ],
  "limit" : 500,
  "page" : 1,
  "table_name" : "Product"
}"/$
        then:
        params.replace(TestUtil.LTM, "\n") == expected.replace(TestUtil.LTM, "\n")
    }

    def "Builder condition Test"() {
        when:

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_PRODUCT_REF)
                .condition(ProductConstants.CATEGORY_ID, "1").condition(ProductConstants.PRODUCT_NAME + " like", "%沖縄%")
                .tableName(ProductConstants.TABLE_NAME)
                .limit(500)
                .page(1)
                .build();
        def params = StringEscapeUtils.unescapeJava(JsonUtil.toJSON(request.getParams()))
        def expected = $/"{
  "conditions" : [ {
    "categoryId" : "1"
  }, {
    "productName like" : "%沖縄%"
  } ],
  "limit" : 500,
  "page" : 1,
  "table_name" : "Product"
}"/$
        then:
        params.replace(TestUtil.LTM, "\n") == expected.replace(TestUtil.LTM, "\n")
    }

    def "Builder field and order Test"() {
        when:
        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_CATEGORY_REF)
                .tableName(CategoryConstants.TABLE_NAME)
                .condition(CategoryConstants.CATEGORY_NAME + " like", "%沖縄%")
                .field(CategoryConstants.CATEGORY_ID)
                .field(CategoryConstants.CATEGORY_NAME)
                .order(CategoryConstants.CATEGORY_ID + " " + SmaregiConstants.SEARCH_ORDER_DESC)
                .order(CategoryConstants.CATEGORY_NAME + " " + SmaregiConstants.SEARCH_ORDER_DESC)
                .limit(500)
                .page(1)
                .build();

        def params = StringEscapeUtils.unescapeJava(JsonUtil.toJSON(request.getParams()))
        def expected = $/"{
  "fields" : [ "categoryId", "categoryName" ],
  "conditions" : [ {
    "categoryName like" : "%沖縄%"
  } ],
  "order" : [ "categoryId desc", "categoryName desc" ],
  "limit" : 500,
  "page" : 1,
  "table_name" : "Category"
}"/$
        then:
        params.replace(TestUtil.LTM, "\n") == expected.replace(TestUtil.LTM, "\n")
    }

}
