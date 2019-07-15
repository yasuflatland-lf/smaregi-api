package jp.creollc.smaregi.api.dailysum;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * DailySum class
 *
 * <p>
 * DailySum table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DailySum {
    public String sumDate;
    public String storeId;
    public String cashDrawerId;
    public String status;
    public String salesTotal;
    public String discount;
    public String pointDiscount;
    public String taxExcludeReceive;
    public String nonSalesTargetTotal;
    public String total;
    public String totalExcludTax;
    public String inTaxSalesTotal;
    public String taxInclude;
    public String outTaxSalesTotal;
    public String taxExclude;
    public String taxTotal;
    public String nonTaxSalesTotal;
    public String taxFreeTotal;
    public String nonSalesTargetTaxFreeTotal;
    public String costTotal;
    public String grossMargin;
    public String amount;
    public String transactionCount;
    public String returnAmount;
    public String carriage;
    public String commission;
    public String preparationCash;
    public String cashSales;
    public String creditSales;
    public List<OtherSalseList> otherSalseList;
    public String partPayment;
    public String partPaymentCash;
    public String partPaymentCredit;
    public String receivedDepositCash;
    public String receivedDepositCashTotal;
    public String receivedDepositCreditTotal;
    public String partPaymentCancel;
    public String partPaymentCashCancel;
    public String partPaymentCreditCancel;
    public String deposit;
    public String returnDeposit;
    public String receipt;
    public String payment;
    public String nonSalesCashTotal;
    public String nonSalesCreditTotal;
    public String nonSalesOtherTotal;
    public String nonSalesTaxFreeTotal;
    public String changeDifference;
    public String calculateBalance;
    public String realBalance;
    public String difference;
    public String saving;
    public String carryOver;
    public String tenThousandYen;
    public String fiveThousandYen;
    public String twoThousandYen;
    public String oneThousandYen;
    public String fiveHundredYen;
    public String oneHundredYen;
    public String fiftyYen;
    public String tenYen;
    public String fiveYen;
    public String oneYen;
    public String comment;
    public String insDateTime;
    public String updDateTime;
    public String salesTotalNonSalesTargetDivision;
    public String totalTaxFreeDivision;
}
