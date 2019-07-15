package jp.creollc.smaregi.api.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Customer class
 *
 * <p>
 * Customer table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer {
    public String customerId;
    public String customerCode;
    public String customerNo;
    public String rank;
    public String staffRank;
    public String lastName;
    public String firstName;
    public String lastKana;
    public String firstKana;
    public String postCode;
    public String address;
    public String phoneNumber;
    public String faxNumber;
    public String mobileNumber;
    public String mailAddress;
    public String mailAddress2;
    public String mailAddress3;
    public String companyName;
    public String departmentName;
    public String managerialPosition;
    public String sex;
    public String birthDate;
    public String mile;
    public String point;
    public String pointExpireDate;
    public String lastComeDateTime;
    public String entryDate;
    public String leaveDate;
    public String pointGivingUnitPrice;
    public String pointGivingUnit;
    public String pinCode;
    public String passportNo;
    public String nationality;
    public String alphabetName;
    public String mailReceiveFlag;
    public String note;
    public String note2;
    public String favoriteList;
    public String browsingList;
    public String status;
    public String storeId;
    public String insDateTime;
    public String updDateTime;
}
