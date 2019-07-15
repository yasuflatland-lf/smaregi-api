package jp.creollc.smaregi.api.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * TransactionHead class
 *
 * <p>
 * TransactionHead table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionHead {
    public String transactionHeadId;
    public String transactionDateTime;
    public String transactionHeadDivision;
    public String cancelDivision;
    public String unitNonDiscountsubtotal;
    public String unitDiscountsubtotal;
    public String subtotal;
    public String subtotalForDiscount;
    public String subtotalDiscountPrice;
    public String subtotalDiscountRate;
    public String subtotalDiscountDivision;
    public String pointDiscount;
    public String total;
    public String taxExclude;
    public String taxInclude;
    public String roundingDivision;
    public String roundingPrice;
    public String cashTotal;
    public String creditTotal;
    public String deposit;
    public String depositCash;
    public String depositCredit;
    public String charge;
    public String changeDifference;
    public String amount;
    public String returnAmount;
    public String costTotal;
    public String salesHeadDivision;
    public String inTaxSalesTotal;
    public String outTaxSalesTotal;
    public String nonTaxSalesTotal;
    public String nonSalesTargetTotal;
    public String nonSalesTargetOutTaxTotal;
    public String nonSalesTargetInTaxTotal;
    public String nonSalesTargetTaxFreeTotal;
    public String nonSalesTargetCostTotal;
    public String nonSalesTargetAmount;
    public String nonSalesTargetReturnAmount;
    public String newPoint;
    public String spendPoint;
    public String point;
    public String totalPoint;
    public String currentMile;
    public String earnMile;
    public String totalMile;
    public String adjustmentMile;
    public String adjustmentMileDivision;
    public String adjustmentMileValue;
    public String storeId;
    public String storeCode;
    public String terminalId;
    public String customerId;
    public String customerCode;
    public String terminalTranId;
    public String terminalTranDateTime;
    public String sumDivision;
    public String adjustmentDateTime;
    public String sumDateTime;
    public String customerRank;
    public String customerGroupId;
    public String customerGroupId2;
    public String customerGroupId3;
    public String customerGroupId4;
    public String customerGroupId5;
    public String staffId;
    public String staffName;
    public String staffCode;
    public String paymentCount;
    public String slipNumber;
    public String cancelSlipNumber;
    public String authNumber;
    public String authDate;
    public String cardCompany;
    public String memo;
    public String receiptMemo;
    public String paymentMethodId1;
    public String paymentMethodName1;
    public String depositOthers1;
    public String paymentMethodId2;
    public String paymentMethodName2;
    public String depositOthers2;
    public String paymentMethodId3;
    public String paymentMethodName3;
    public String depositOthers3;
    public String carriage;
    public String commission;
    public String guestNumbers;
    public String taxFreeSalesDivision;
    public String netTaxFreeGeneralTaxInclude;
    public String netTaxFreeGeneralTaxExclude;
    public String netTaxFreeConsumableTaxInclude;
    public String netTaxFreeConsumableTaxExclude;
    public String tags;
    public String pointGivingDivision;
    public String pointGivingUnitPrice;
    public String pointGivingUnit;
    public String pointSpendDivision;
    public String mileageDivision;
    public String mileageLabel;
    public String customerPinCode;
    public String disposeDivision;
    public String disposeServerTransactionHeadId;
    public String cancelDateTime;
    public String sellDivision;
    public String taxRate;
    public String taxRounding;
    public String discountRoundingDivision;
    public String transactionUuid;
    public String giftReceiptValidDays;
    public String receiptIssueNumberOfTimes;
    public String pickupTransactionHeadI;
    public String pickUpDate;
    public String partPayment;
    public String partPaymentClass;
    public String layawayServerTransactionHeadId;
    public String disabledEdit;
    public String updDateTime;
}
