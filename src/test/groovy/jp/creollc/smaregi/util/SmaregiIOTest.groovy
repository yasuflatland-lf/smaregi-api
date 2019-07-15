package jp.creollc.smaregi.util

import groovy.json.JsonSlurper
import jp.creollc.smaregi.constants.SmaregiConstants
import jp.creollc.smaregi.SmaregiIO

import org.junit.Rule
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.junit.WireMockRule

class SmaregiIOTest extends Specification {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort());

    @Unroll("Smoke Test for post")
    def "Smoke Test for post" () {
        given: "Mock server is running"

        int port = wireMockRule.port();
        stubFor(post(urlEqualTo("/access"))
                .willReturn(okJson("{ \"data\": \"success\" }")
                    .withStatus(200)
                    .withHeader(SmaregiConstants.CONTENT_TYPE, SmaregiConstants.DEFAULT_RESPONSE_CONTENT_TYPE)));

        when: "I send a simple request"
        SmaregiIO smg = new SmaregiIO("dummy", "dummy");
        def result = smg.post("http://localhost:" + port + "/access","test", "test")
        def json = new JsonSlurper().parseText(result)

        then: "I receive a json response"
        verify(postRequestedFor(urlMatching("/access"))
                .withHeader(SmaregiConstants.CONTENT_TYPE, matching(SmaregiConstants.DEFAULT_REQUEST_CONTENT_TYPE)));

        json.data == 'success'

    }
}
