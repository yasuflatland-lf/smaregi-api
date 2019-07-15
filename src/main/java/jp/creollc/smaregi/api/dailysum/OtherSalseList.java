package jp.creollc.smaregi.api.dailysum;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * OtherSalseList class
 *
 * <p>
 * OtherSalseList object in DailySum table mapping for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OtherSalseList {
    public String id;
    public String name;
    public String paymentMethodDivision;
    public String paymentMethodDivisionName;
    public String sales;
}
