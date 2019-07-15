package jp.creollc.smaregi.util

import spock.lang.Specification
import spock.lang.Unroll

import java.security.InvalidParameterException

class SmaregiDateTimeTest extends Specification {

    @Unroll("ofAMonth Test #startYear/#startMonth => start <#startStr> end <#endStr>")
    def "ofAMonth Test"() {
        when:
        SmaregiDateTime sdt = SmaregiDateTime.builder().ofAMonth(startYear, startMonth).build()

        then:
        sdt.getStart() == startStr
        sdt.getEnd() == endStr

        where:
        startYear | startMonth | startStr              | endStr
        2044      | 2          | "2044/02/29 23:59:59" | "2044/02/01 00:00:00"
        2019      | 10         | "2019/10/31 23:59:59" | "2019/10/01 00:00:00"
        2012      | 2          | "2012/02/29 23:59:59" | "2012/02/01 00:00:00"
        1980      | 2          | "1980/02/29 23:59:59" | "1980/02/01 00:00:00"
    }

    @Unroll("byStartRange Test #startYear/#startMonth and range of month is #rangeOfMonth => start <#startStr> end <#endStr>")
    def "byStartRange Test"() {
        when:
        SmaregiDateTime sdt = SmaregiDateTime.builder().byStartRange(startYear, startMonth, rangeOfMonth).build()

        then:
        sdt.getStart() == startStr
        sdt.getEnd() == endStr

        where:
        startYear | startMonth | rangeOfMonth | startStr              | endStr
        2019      | 10         | 1            | "2019/10/31 23:59:59" | "2019/10/01 00:00:00"
        2012      | 2          | 2            | "2012/02/29 23:59:59" | "2012/01/01 00:00:00"
        2018      | 1          | 2            | "2018/01/31 23:59:59" | "2017/12/01 00:00:00"
    }

    @Unroll("byStartRange Error Test #startYear/#startMonth and range of month is #rangeOfMonth => start <#startStr> end <#endStr>")
    def "byStartRange Error Test"() {
        when:
        SmaregiDateTime sdt = SmaregiDateTime.builder().byStartRange(startYear, startMonth, rangeOfMonth).build()

        then:
        thrown(InvalidParameterException.class)

        where:
        startYear | startMonth | rangeOfMonth
        2019      | 10         | -1
    }

    @Unroll("byStartEndRange Test #startYear/#startMonth and #endYear/#endMOnth => start <#startStr> end <#endStr>")
    def "byStartEndRange Test"() {
        when:
        SmaregiDateTime sdt = SmaregiDateTime.builder().byStartEndRange(startYear, startMonth, endYear, endMonth).build()

        then:
        sdt.getStart() == startStr
        sdt.getEnd() == endStr

        where:
        startYear | startMonth | endYear | endMonth | startStr              | endStr
        2019      | 10         | 2019    | 3        | "2019/10/31 23:59:59" | "2019/03/01 00:00:00"
        2019      | 3          | 2019    | 3        | "2019/03/31 23:59:59" | "2019/03/01 00:00:00"
    }

    @Unroll("byStartEndRange Error Test #startYear/#startMonth and #endYear/#endMonth")
    def "byStartEndRange Error Test"() {
        when:
        SmaregiDateTime sdt = SmaregiDateTime.builder().byStartEndRange(startYear, startMonth, endYear, endMonth).build()

        then:
        thrown(InvalidParameterException.class)

        where:
        startYear | startMonth | endYear | endMonth
        2011      | 3          | 2011    | 10
        2019      | 1          | 2020    | 1
        2040      | 12         | 2041    | 1

    }
}
