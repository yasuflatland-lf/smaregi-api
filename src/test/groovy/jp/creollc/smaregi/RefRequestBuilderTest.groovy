package jp.creollc.smaregi

import jp.creollc.smaregi.RefRequestBuilder
import jp.creollc.smaregi.SmaregiRequest
import jp.creollc.smaregi.api.category.constants.CategoryConstants
import jp.creollc.smaregi.api.product.constants.ProductConstants
import jp.creollc.smaregi.constants.SmaregiConstants
import jp.creollc.smaregi.util.JsonUtil
import spock.lang.Specification

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
        def params = JsonUtil.toJSON(request.getParams());

        then:
        params == '\"{\\n  \\"conditions\\" : [ {\\n    \\"key1\\" : \\"param1\\"\\n  }, {\\n    \\"key2\\" : \\"param2\\"\\n  } ],\\n  \\"limit\\" : 0,\\n  \\"page\\" : 1\\n}\"'
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
        def params = JsonUtil.toJSON(request.getParams());
        def expected = '\"{\\n  \\"conditions\\" : [ {\\n    \\"categoryId\\" : \\"1\\"\\n  }, {\\n    \\"productName like\\" : \\"%沖縄%\\"\\n  } ],\\n  \\"limit\\" : 500,\\n  \\"page\\" : 1,\\n  \\"table_name\\" : \\"Product\\"\\n}\"'

        then:
        params == expected
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
        def params = JsonUtil.toJSON(request.getParams());
        def expected = '\"{\\n  \\"conditions\\" : [ {\\n    \\"categoryId\\" : \\"1\\"\\n  }, {\\n    \\"productName like\\" : \\"%沖縄%\\"\\n  } ],\\n  \\"limit\\" : 500,\\n  \\"page\\" : 1,\\n  \\"table_name\\" : \\"Product\\"\\n}\"'

        then:
        params == expected
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

        def params = JsonUtil.toJSON(request.getParams());
        def expected = '\"{\\n  \\"fields\\" : [ \\"categoryId\\", \\"categoryName\\" ],\\n  \\"conditions\\" : [ {\\n    \\"categoryName like\\" : \\"%沖縄%\\"\\n  } ],\\n  \\"order\\" : [ \\"categoryId desc\\", \\"categoryName desc\\" ],\\n  \\"limit\\" : 500,\\n  \\"page\\" : 1,\\n  \\"table_name\\" : \\"Category\\"\\n}\"'

        then:
        params == expected
    }

}
