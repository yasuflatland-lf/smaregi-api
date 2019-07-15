package jp.creollc.smaregi

import groovy.json.JsonSlurper
import jp.creollc.smaregi.api.category.constants.CategoryConstants
import jp.creollc.smaregi.api.UpdData
import jp.creollc.smaregi.api.UpdRow
import jp.creollc.smaregi.api.product.constants.ProductConstants
import jp.creollc.smaregi.api.stock.constants.StockConstants
import jp.creollc.smaregi.api.store.constants.StoreConstants
import jp.creollc.smaregi.api.transaction.constants.TransactionDetailConstants
import jp.creollc.smaregi.api.transaction.constants.TransactionHeadConstants
import jp.creollc.smaregi.constants.SmaregiConstants
import jp.creollc.smaregi.test.util.TestUtil
import jp.creollc.smaregi.util.JsonUtil
import jp.creollc.smaregi.util.SmaregiDateTime
import spock.lang.Ignore
import spock.lang.Specification

import java.security.InvalidParameterException

/**
 * Smaregi API Real API call test use case
 *
 * <p>
 *     TestUtil.X_CONTACT_ID and TestUtil.X_ACCESS_TOKEN are fetched from the environment values.
 *     For running tests in this class, please confirm if those environment values are configured in your IDE
 *     or console, where you run this tests.
 *
 * @author Yasuyuki Takeo
 */
public class SmaregiClientRealTest extends Specification {

    @Ignore("For real API server test")
    def "Product Reference Smoke test"() {
        when:
        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_PRODUCT_REF)
                .condition(ProductConstants.PRODUCT_ID, TestUtil.PRODUCT_ID_OKINAWA_01)
                .tableName(ProductConstants.TABLE_NAME)
                .limit(500)
                .page(1)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json.result[0].productId == TestUtil.PRODUCT_ID_OKINAWA_01
        json.result[0].productName == "沖縄01"
    }

    @Ignore("For real API server test")
    def "Stock Update Smoke test"() {
        when:
        // Build Stock update parameter
        List<UpdData> updData = new ArrayList() {
            {
                List<UpdRow> updRows = new ArrayList() {
                    {
                        add(new UpdRow(new HashMap<String, String>() {
                            {
                                put(StockConstants.STORE_ID, TestUtil.STORE_ID_TEST);
                                put(StockConstants.PRODUCT_ID, TestUtil.PRODUCT_ID_OKINAWA_01);
                                put(StockConstants.STOCK_AMOUNT, "1");
                                put(StockConstants.STOCK_DIVISION, StockConstants.STOCK_DIVISION_DEFAULT);
                            }
                        }))
                    }
                };
                add(new UpdData(StockConstants.TABLE_NAME, updRows));
            }
        };

        SmaregiRequest request = UpdRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_STOCK_UPD)
                .procDivision(SmaregiConstants.PROCESS_UPDATE)
                .procDetailDivision(SmaregiConstants.PROCESS_DIVISION_ABSOLUTE)
                .data(updData)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json.result.Stock == 1
    }

    @Ignore("For real API server test")
    def "Stock Update Chain methods Smoke test"() {
        when:
        // Build Stock update parameter
        List<UpdData> updData =
                UpdDataBuilder
                        .builder()
                        .updData(StockConstants.TABLE_NAME,
                                StockConstants.STORE_ID, TestUtil.STORE_ID_TEST,
                                StockConstants.PRODUCT_ID, TestUtil.PRODUCT_ID_OKINAWA_01,
                                StockConstants.STOCK_AMOUNT, "3",
                                StockConstants.STOCK_DIVISION, StockConstants.STOCK_DIVISION_DEFAULT
                        ).build();

        SmaregiRequest request = UpdRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_STOCK_UPD)
                .procDivision(SmaregiConstants.PROCESS_UPDATE)
                .procDetailDivision(SmaregiConstants.PROCESS_DIVISION_ABSOLUTE)
                .data(updData)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json.result.Stock == 1
    }

    @Ignore("For real API server test")
    def "Stock Reference Smoke test"() {
        when:
        List<Map<String, String>> conditions = new ArrayList() {
            {
                add(new HashMap() {
                    {
                        put(StockConstants.STORE_ID, TestUtil.STORE_ID_TEST);
                        put(StockConstants.PRODUCT_ID, TestUtil.PRODUCT_ID_OKINAWA_01);
                    }
                });
                add(new HashMap() {
                    {
                        put(StockConstants.STORE_ID, TestUtil.STORE_ID_TEST);
                        put(StockConstants.PRODUCT_ID, TestUtil.PRODUCT_ID_OKINAWA_02);
                    }
                });
            }
        }

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_STOCK_REF)
                .conditions(conditions)
                .tableName(StockConstants.TABLE_NAME)
                .limit(500)
                .page(1)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json.total_count == "1"
        json.result[0].storeId == TestUtil.STORE_ID_TEST
        json.result[0].stockAmount == "2"
        json.result[0].productId == TestUtil.PRODUCT_ID_OKINAWA_01
    }

    @Ignore("For real API server test")
    def "Category Reference Smoke Test"() {
        when:
        List<Map<String, String>> conditions = new ArrayList() {
            {
                add(new HashMap() {
                    {
                        put(CategoryConstants.CATEGORY_NAME + " like", "%沖縄%");
                    }
                });
            }
        }

        List<String> fields = new ArrayList() {
            {
                add(CategoryConstants.CATEGORY_ID);
                add(CategoryConstants.CATEGORY_NAME);
            }
        }

        List<String> orders = new ArrayList() {
            {
                add(CategoryConstants.CATEGORY_ID + " " + SmaregiConstants.SEARCH_ORDER_DESC)
            }
        }

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_CATEGORY_REF)
                .tableName(CategoryConstants.TABLE_NAME)
                .conditions(conditions)
                .fields(fields)
                .orders(orders)
                .limit(500)
                .page(1)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json.total_count == "2"
        json.result[0].categoryName != null
        json.result[1].categoryName != null
    }

    @Ignore("For real API server test")
    def "Store Reference Smoke test"() {
        when:
        List<String> orders = new ArrayList() {
            {
                add(StoreConstants.STORE_ID + " " + SmaregiConstants.SEARCH_ORDER_DESC)
            }
        }

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_STORE_REF)
                .tableName(StoreConstants.TABLE_NAME)
                .orders(orders)
                .limit(500)
                .page(1)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json != null
    }

    @Ignore("For real API server test")
    def "TransactionHead Reference Range Smoke Test"() {
        when:
        SmaregiDateTime sdt = SmaregiDateTime.builder().ofAMonth(2019, 1).build();

        List<Map<String, String>> conditions = new ArrayList() {
            {
                add(new HashMap() {
                    {
                        put(TransactionHeadConstants.TRANSACTION_DATE_TIME + " <=", sdt.getStart());
                        put(TransactionHeadConstants.TRANSACTION_DATE_TIME + " >=", sdt.getEnd());
                    }
                });
            }
        }

        List<String> order = new ArrayList() {
            {
                add(TransactionHeadConstants.TRANSACTION_DATE_TIME + " " + SmaregiConstants.SEARCH_ORDER_DESC)
            }
        }

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_TRANSACTION_REF)
                .tableName(TransactionHeadConstants.TABLE_NAME)
                .conditions(conditions)
                .orders(order)
                .limit(500)
                .page(1)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json.total_count != "0"
        json != null
    }

    @Ignore("For real API server test")
    def "TransactionDetail Reference Range Smoke Test"() {
        when:
        SmaregiDateTime sdt = SmaregiDateTime.builder().ofAMonth(2019, 1).build();

        List<Map<String, String>> conditions = new ArrayList() {
            {
                add(new HashMap() {
                    {
                        put(TransactionDetailConstants.TRANSACTION_DATE_TIME + " <=", sdt.getStart());
                        put(TransactionDetailConstants.TRANSACTION_DATE_TIME + " >=", sdt.getEnd());
                    }
                });
            }
        }

        List<String> order = new ArrayList() {
            {
                add(TransactionDetailConstants.TRANSACTION_DATE_TIME + " " + SmaregiConstants.SEARCH_ORDER_DESC)
            }
        }

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_TRANSACTION_REF)
                .tableName(TransactionDetailConstants.TABLE_NAME)
                .conditions(conditions)
                .orders(order)
                .limit(500)
                .page(1)
                .build();

        def client = new SmaregiClient(TestUtil.X_CONTACT_ID, TestUtil.X_ACCESS_TOKEN);

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        json.total_count != "0"
        json != null
    }

}
