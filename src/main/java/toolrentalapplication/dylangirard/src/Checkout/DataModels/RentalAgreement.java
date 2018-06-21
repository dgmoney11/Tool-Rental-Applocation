package toolrentalapplication.dylangirard.src.Checkout.DataModels;

import java.time.LocalDate;
import java.util.List;

public class RentalAgreement {
    public String ToolCode;
    public String ToolType;
    public String ToolBrand;
    public String DailyRentalCharge;

    public String getToolCode() {
        return ToolCode;
    }

    public void setToolCode(String toolCode) {
        ToolCode = toolCode;
    }

    public String getToolType() {
        return ToolType;
    }

    public void setToolType(String toolType) {
        ToolType = toolType;
    }

    public String getToolBrand() {
        return ToolBrand;
    }

    public void setToolBrand(String toolBrand) {
        ToolBrand = toolBrand;
    }

    public String getDailyRentalCharge() {
        return DailyRentalCharge;
    }

    public void setDailyRentalCharge(String dailyRentalCharge) {
        DailyRentalCharge = dailyRentalCharge;
    }

    public String getPreDiscountTotalCost() {
        return PreDiscountTotalCost;
    }

    public void setPreDiscountTotalCost(String preDiscountTotalCost) {
        PreDiscountTotalCost = preDiscountTotalCost;
    }

    public String getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        DiscountAmount = discountAmount;
    }

    public String getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        DiscountPercent = discountPercent;
    }

    public RentalAgreement(String toolCode,
                           String toolType,
                           String toolBrand,
                           String dailyRentalCharge,
                           List<LocalDate> rentalDays,
                           String checkoutDate,
                           String dateDue,
                           int numberOfDaysToCharge,

                           String preDiscountTotalCost,
                           String discountAmount,
                           String discountPercent,
                           String finalCharge) {
        ToolCode = toolCode;
        ToolType = toolType;
        ToolBrand = toolBrand;
        DailyRentalCharge = dailyRentalCharge;
        RentalDays = rentalDays;
        CheckoutDate = checkoutDate;
        DateDue = dateDue;
        NumberOfDaysToCharge = numberOfDaysToCharge;
        PreDiscountTotalCost = preDiscountTotalCost;
        DiscountAmount = discountAmount;
        DiscountPercent = discountPercent;
        FinalCharge = finalCharge;
    }

    public List<LocalDate> RentalDays;

    public String CheckoutDate;

    public List<LocalDate> getRentalDays() {
        return RentalDays;
    }

    public void setRentalDays(List<LocalDate> rentalDays) {
        RentalDays = rentalDays;
    }

    public String getCheckoutDate() {
        return CheckoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        CheckoutDate = checkoutDate;
    }

    public String getDateDue() {
        return DateDue;
    }

    public void setDateDue(String dateDue) {
        DateDue = dateDue;
    }

    public int getNumberOfDaysToCharge() {
        return NumberOfDaysToCharge;
    }

    public void setNumberOfDaysToCharge(int numberOfDaysToCharge) {
        NumberOfDaysToCharge = numberOfDaysToCharge;
    }

    public String getFinalCharge() {
        return FinalCharge;
    }

    public void setFinalCharge(String finalCharge) {
        FinalCharge = finalCharge;
    }

    public String DateDue;

    public int NumberOfDaysToCharge;

    public String PreDiscountTotalCost;
    public String DiscountAmount;
    public String DiscountPercent;

    public String FinalCharge;
}
