package jp.creollc.smaregi.util;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

/**
 * Smaregi API Date Time
 *
 * <p>
 * For Transaction related API for example, needs to configure the range of date time to fetch records.
 * This class provides the start and end datetime range shorthands methods for Smaregi APIs
 * </p>
 *
 * @author Yasuyuki Takeo
 */
public class SmaregiDateTime {
    protected DateTimeFormatter _formatter
        = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    protected ZonedDateTime startDateTime;
    protected ZonedDateTime endDateTime;

    /**
     * Constructor
     *
     * @param startDateTime
     * @param endDateTime
     */
    SmaregiDateTime(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static SmaregiDateTimeBuilder builder() {
        return new SmaregiDateTimeBuilder();
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getStart() {
        return startDateTime.format(_formatter);
    }

    public String getEnd() {
        return endDateTime.format(_formatter);
    }

    public String toString() {
        return "SmaregiDateTime(startDateTime="
                   + this.getStartDateTime()
                   + ", endDateTime=" + this.getEndDateTime()
                   + ", start=" + this.getStart()
                   + ", end=" + this.getEnd() + ")";
    }

    public static class SmaregiDateTimeBuilder {
        protected ZoneId _zoneId = ZoneId.of("Asia/Tokyo");

        protected ZonedDateTime startDateTime;
        protected ZonedDateTime endDateTime;

        SmaregiDateTimeBuilder() {
        }

        /**
         * Range of a month
         *
         * <p>
         * Returns the specific month's start date time and end date time.
         * </p>
         *
         * @param startYear  The year to start
         * @param startMonth The month to start
         * @return
         */
        public SmaregiDateTimeBuilder ofAMonth(int startYear, int startMonth) {
            byStartRange(startYear, startMonth, 1);
            return this;
        }

        /**
         * Range by month
         * <p>
         * Get range with an offset of month from the start datetime.
         * </p>
         *
         * @param startYear
         * @param startMonth
         * @param rangeOfMonth
         * @return
         */
        public SmaregiDateTimeBuilder byStartRange(int startYear, int startMonth, int rangeOfMonth) {

            if (rangeOfMonth < 1) {
                throw new InvalidParameterException("rangeOfMonth should be largher than 1");
            }

            startDateTime
                = ZonedDateTime
                      .of(startYear, startMonth, 1, 0, 0, 0, 0, _zoneId)
                      .with(TemporalAdjusters.lastDayOfMonth())
                      .with(ChronoField.NANO_OF_DAY, LocalTime.MAX.toNanoOfDay());

            endDateTime = startDateTime.minusMonths(rangeOfMonth - 1)
                              .with(TemporalAdjusters.firstDayOfMonth())
                              .with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay());
            return this;
        }

        /**
         * Range between start and end
         * <p>
         * datetime by both start and end with year, month format.
         * The datetime of start should be later than the datetime of end or throws exception.
         * </p>
         *
         * @param startYear
         * @param startMonth
         * @param endYear
         * @param endMonth
         * @return
         */
        public SmaregiDateTimeBuilder byStartEndRange(int startYear, int startMonth, int endYear, int endMonth) {
            startDateTime
                = ZonedDateTime
                      .of(startYear, startMonth, 1, 0, 0, 0, 0, _zoneId)
                      .with(TemporalAdjusters.lastDayOfMonth())
                      .with(ChronoField.NANO_OF_DAY, LocalTime.MAX.toNanoOfDay());

            endDateTime
                = ZonedDateTime
                      .of(endYear, endMonth, 1, 0, 0, 0, 0, _zoneId)
                      .with(TemporalAdjusters.firstDayOfMonth());

            long diff = startDateTime.compareTo(endDateTime);

            if (diff < 0) {
                throw new InvalidParameterException("Start Date Time must be larger later than End Date Time");
            }

            return this;
        }

        public SmaregiDateTime build() {
            return new SmaregiDateTime(startDateTime, endDateTime);
        }

        public String toString() {
            return "SmaregiDateTime.SmaregiDateTimeBuilder(startDateTime="
                       + this.startDateTime
                       + ", endDateTime=" + this.endDateTime
                       + ")";
        }
    }
}
