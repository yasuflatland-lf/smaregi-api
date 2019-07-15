package jp.creollc.smaregi

import jp.creollc.smaregi.api.UpdData
import jp.creollc.smaregi.util.JsonUtil
import spock.lang.Specification

class UpdDataBuilderTest extends Specification {

    def "UpdDataBuilderBase.getUpdDataIndex Test" () {
        when:
        List<UpdData> updDatas = UpdDataBuilder.builder()
                .updData("tablea", "key1", "value1", "key2", "value2")
                .updData("tablea", "key3", "value3")
                .updData("tableb", "key4", "value4")
                .updData("tablea", "key5", "value5")
                .build();
        def json = JsonUtil.toJSON(updDatas);
        def expected = $/[ {
  "rows" : [ {
    "key1" : "value1",
    "key2" : "value2"
  }, {
    "key3" : "value3"
  }, {
    "key5" : "value5"
  } ],
  "table_name" : "tablea"
}, {
  "rows" : [ {
    "key4" : "value4"
  } ],
  "table_name" : "tableb"
} ]/$

        then:
        json == expected
    }
}
