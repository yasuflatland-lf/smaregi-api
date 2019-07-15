package jp.creollc.smaregi

import jp.creollc.smaregi.SmaregiRequest
import jp.creollc.smaregi.UpdRequestBuilder
import jp.creollc.smaregi.constants.SmaregiConstants
import spock.lang.Specification

class SmaregiRequestTest extends Specification {

    def "Smaregi API will be returend When no url is configured"() {
        when:
        SmaregiRequest request = UpdRequestBuilder.builder()
                .procName(SmaregiConstants.CMD_PRODUCT_UPD)
                .procDivision(SmaregiConstants.PROCESS_UPDATE)
                .build();

        then:
        request.getUrl() == SmaregiConstants.SMAREGI_API_URI;
    }
}
