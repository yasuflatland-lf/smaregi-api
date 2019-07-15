package jp.creollc.smaregi


import com.github.tomakehurst.wiremock.junit.WireMockRule
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import jp.creollc.smaregi.api.UpdData
import jp.creollc.smaregi.api.product.constants.ProductConstants
import jp.creollc.smaregi.api.product.constants.ProductPriceConstants
import jp.creollc.smaregi.constants.SmaregiConstants
import org.junit.Rule
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

class SmaregiClientTest extends Specification {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort());

    def "Product Reference Smoke test"() {
        given:
        // This is temporally test URL for mock server
        def url = "http://localhost:" + wireMockRule.port() + "/access";

        def requestJson = JsonOutput.toJson([result: [
                [productId: 113, no: 1, value: '項目1'],
                [productId: 113, no: 1, value: '項目2'],
                [productId: 113, no: 1, value: '項目3']
        ]])
        stubFor(post(urlEqualTo("/access"))
                .withRequestBody(containing("limit%22+%3A+500"))
                .willReturn(okJson(requestJson)
                        .withStatus(200)
                        .withHeader(SmaregiConstants.CONTENT_TYPE, SmaregiConstants.DEFAULT_RESPONSE_CONTENT_TYPE)));

        when:
        List<Map<String, String>> conditions = new ArrayList<>()
        conditions.add(new HashMap() {{
            put(ProductConstants.PRODUCT_ID, "113")
        }});

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_PRODUCT_REF)
                .url(url)
                .conditions(conditions)
                .tableName(ProductConstants.TABLE_NAME)
                .limit(500)
                .build();

        def client = new SmaregiClient("dummy", "dummy")

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()

        verify(postRequestedFor(urlMatching("/access"))
                .withHeader(SmaregiConstants.CONTENT_TYPE, matching(SmaregiConstants.DEFAULT_REQUEST_CONTENT_TYPE))
                .withHeader(SmaregiConstants.X_CONTACT_ID, matching("dummy"))
                .withHeader(SmaregiConstants.X_ACCESS_TOKEN, matching("dummy")));

        json.result[0].productId == 113
        json.result[0].value == '項目1'
        json.result[1].productId == 113
        json.result[1].value == '項目2'
    }

    def "Product Update Smoke test"() {
        given:
        // This is temporally test URL for mock server
        def url = "http://localhost:" + wireMockRule.port() + "/access";

        def requestJson = JsonOutput.toJson([result: [
                [Product: 1, ProductPrice: 1]
        ]])
        stubFor(post(urlEqualTo("/access"))
                .withRequestBody(containing("proc_info"))
                .willReturn(okJson(requestJson)
                        .withStatus(200)
                        .withHeader(SmaregiConstants.CONTENT_TYPE, SmaregiConstants.DEFAULT_RESPONSE_CONTENT_TYPE)));

        when:
        List<Map<String, String>> conditions = new ArrayList<>()
        conditions.add(new HashMap() {{
            put(ProductConstants.PRODUCT_ID, "113")
        }});

        // Build Product update parameter
        List<UpdData> updData = new ArrayList() {
            {
                new UpdData(ProductConstants.TABLE_NAME, new HashMap<String, String>() {
                    {
                        put(ProductConstants.PRODUCT_ID, "113");
                        put(ProductConstants.CATEGORY_ID, "2");
                        put(ProductConstants.PRODUCT_CODE, "4729711845057");
                        put(ProductConstants.PRODUCT_NAME, "ジーンズ");
                        put(ProductConstants.TAX_DIVISION, "0");
                        put(ProductConstants.PRICE, "1050");
                        put(ProductConstants.COST, "666");
                        put(ProductConstants.TAX_FREE_DIVISION, "0");
                    }
                });
                new UpdData(ProductPriceConstants.TABLE_NAME, new HashMap<String, String>() {
                    {
                        put(ProductPriceConstants.PRODUCT_ID, "113");
                        put(ProductPriceConstants.STORE_ID, "1");
                        put(ProductPriceConstants.PRICE_DIVISION, "1");
                        put(ProductPriceConstants.START_DATE, "2013/01/17");
                        put(ProductPriceConstants.END_DATE, "2013/02/17");
                        put(ProductPriceConstants.PRICE, "840");
                    }
                });
            }
        };

        SmaregiRequest request = UpdRequestBuilder.builder()
                .url(url)
                .procName(SmaregiConstants.CMD_PRODUCT_UPD)
                .procDivision(SmaregiConstants.PROCESS_UPDATE)
                .data(updData)
                .build();

        def client = new SmaregiClient("dummy", "dummy")

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        true == result.isSuccessful()
        verify(postRequestedFor(urlMatching("/access"))
        //.withHeader(SmaregiConstants.CONTENT_TYPE, matching(SmaregiConstants.DEFAULT_REQUEST_CONTENT_TYPE))
                .withHeader(SmaregiConstants.X_CONTACT_ID, matching("dummy"))
                .withHeader(SmaregiConstants.X_ACCESS_TOKEN, matching("dummy")));

        json.result[0].Product == 1
        json.result[0].ProductPrice == 1
    }

    def "Product Reference 400 Error test"() {
        given:

        // This is temporally test URL for mock server
        def url = "http://localhost:" + wireMockRule.port() + "/access";

        def requestJson = JsonOutput.toJson([
                error_code       : 21,
                error            : "認証に失敗しました",
                error_description: "アクセスキー=xxxxxxxxxx, 契約ID=xx0000"
        ])
        stubFor(post(urlEqualTo("/access"))
                .willReturn(okJson(requestJson)
                        .withStatus(400)
                        .withHeader(SmaregiConstants.CONTENT_TYPE, SmaregiConstants.DEFAULT_RESPONSE_CONTENT_TYPE)));

        when:
        List<Map<String, String>> conditions = new ArrayList<>()
        conditions.add(new HashMap() {{
            put(ProductConstants.PRODUCT_ID, "113")
        }});

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_PRODUCT_REF)
                .url(url)
                .conditions(conditions)
                .tableName(ProductConstants.TABLE_NAME)
                .limit(500)
                .build();

        def client = new SmaregiClient("dummy", "dummy")

        def result = client.execute(request);

        def json = new JsonSlurper().parseText(result.getResultJSON())

        then:
        false == result.isSuccessful()

        verify(postRequestedFor(urlMatching("/access"))
                .withHeader(SmaregiConstants.CONTENT_TYPE, matching(SmaregiConstants.DEFAULT_REQUEST_CONTENT_TYPE))
                .withHeader(SmaregiConstants.X_CONTACT_ID, matching("dummy"))
                .withHeader(SmaregiConstants.X_ACCESS_TOKEN, matching("dummy")));

        json.error_code == 21
        json.error == "認証に失敗しました"
        json.error_description == "アクセスキー=xxxxxxxxxx, 契約ID=xx0000"

    }

    def "Product Reference 404 Error test"() {
        given:

        // This is temporally test URL for mock server
        def url = "http://localhost:" + wireMockRule.port() + "/access";

        stubFor(post(urlEqualTo("/access"))
                .willReturn(aResponse()
                        .withStatus(404)));

        when:
        List<Map<String, String>> conditions = new ArrayList<>()
        conditions.add(new HashMap() {{
            put(ProductConstants.PRODUCT_ID, "113")
        }});

        SmaregiRequest request = RefRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_PRODUCT_REF)
                .url(url)
                .conditions(conditions)
                .tableName(ProductConstants.TABLE_NAME)
                .limit(500)
                .build();

        def client = new SmaregiClient("dummy", "dummy")

        def result = client.execute(request);

        then:
        false == result.isSuccessful()

        verify(postRequestedFor(urlMatching("/access"))
                .withHeader(SmaregiConstants.CONTENT_TYPE, matching(SmaregiConstants.DEFAULT_REQUEST_CONTENT_TYPE))
                .withHeader(SmaregiConstants.X_CONTACT_ID, matching("dummy"))
                .withHeader(SmaregiConstants.X_ACCESS_TOKEN, matching("dummy")));

    }
}
