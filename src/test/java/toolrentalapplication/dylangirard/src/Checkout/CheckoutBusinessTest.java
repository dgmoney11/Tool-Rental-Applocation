package toolrentalapplication.dylangirard.src.Checkout;

import org.junit.Test;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.RentalAgreement;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.ToolDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CheckoutBusinessTest {
    private CheckoutBusiness checkoutBusiness = new CheckoutBusiness();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Test(expected = Exception.class)
    public void performCheckout() {
        String toolCode = "JAK";
        LocalDate checkoutDate = LocalDate.parse("09/03/2015", formatter);
        int rentalDayCount = 5;
        int discountPercent = 101;

        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        RentalAgreement agreement = checkoutBusiness
                .PerformCheckout(
                        toolCode,
                        rentalDayCount,
                        discountPercent,
                        checkoutDate
                );
    }

    @Test
    public void performCheckout2() {
        String toolCode = "LADW";
        LocalDate checkoutDate = LocalDate.parse("09/03/2015", formatter);
        int rentalDayCount = 5;
        int discountPercent = 10;

        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        RentalAgreement agreement = checkoutBusiness
                .PerformCheckout(
                        toolCode,
                        rentalDayCount,
                        discountPercent,
                        checkoutDate
                );
        assertEquals("09/08/2015", agreement.getDateDue());
        assertEquals("$1.99", agreement.getDailyRentalCharge());
        assertEquals(5, agreement.getNumberOfDaysToCharge());
        assertEquals("$9.95", agreement.getPreDiscountTotalCost());
        assertEquals("10%", agreement.getDiscountPercent());
        assertEquals("$1.00", agreement.getDiscountAmount());
        assertEquals("$8.95", agreement.getFinalCharge());
    }


    @Test
    public void performCheckout3() {
        String toolCode = "CHNS";
        LocalDate checkoutDate = LocalDate.parse("07/02/2015", formatter);
        int rentalDayCount = 5;
        int discountPercent = 25;

        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        RentalAgreement agreement = checkoutBusiness
                .PerformCheckout(
                        toolCode,
                        rentalDayCount,
                        discountPercent,
                        checkoutDate
                );
        assertEquals("07/07/2015", agreement.getDateDue());
        assertEquals("$1.49", agreement.getDailyRentalCharge());
        assertEquals(3, agreement.getNumberOfDaysToCharge());
        assertEquals("$4.47", agreement.getPreDiscountTotalCost());
        assertEquals("25%", agreement.getDiscountPercent());
        assertEquals("$1.12", agreement.getDiscountAmount());
        assertEquals("$3.35", agreement.getFinalCharge());
    }


    @Test
    public void performCheckout4() {
        String toolCode = "JAKD";
        LocalDate checkoutDate = LocalDate.parse("09/03/2015", formatter);
        int rentalDayCount = 6;
        int discountPercent = 0;

        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        RentalAgreement agreement = checkoutBusiness
                .PerformCheckout(
                        toolCode,
                        rentalDayCount,
                        discountPercent,
                        checkoutDate
                );
        assertEquals("09/09/2015", agreement.getDateDue());
        assertEquals("$2.99", agreement.getDailyRentalCharge());
        assertEquals(3, agreement.getNumberOfDaysToCharge());
        assertEquals("$8.97", agreement.getPreDiscountTotalCost());
        assertEquals("0%", agreement.getDiscountPercent());
        assertEquals("$0.00", agreement.getDiscountAmount());
        assertEquals("$8.97", agreement.getFinalCharge());
    }
    @Test
    public void performCheckout5() {
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.parse("07/02/2015", formatter);
        int rentalDayCount = 9;
        int discountPercent = 0;

        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        RentalAgreement agreement = checkoutBusiness
                .PerformCheckout(
                        toolCode,
                        rentalDayCount,
                        discountPercent,
                        checkoutDate
                );
        assertEquals("07/11/2015", agreement.getDateDue());
        assertEquals("$2.99", agreement.getDailyRentalCharge());
        assertEquals(5, agreement.getNumberOfDaysToCharge());
        assertEquals("$14.95", agreement.getPreDiscountTotalCost());
        assertEquals("0%", agreement.getDiscountPercent());
        assertEquals("$0.00", agreement.getDiscountAmount());
        assertEquals("$14.95", agreement.getFinalCharge());
    }

    @Test
    public void performCheckout6() {
        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.parse("07/02/2020", formatter);
        int rentalDayCount = 4;
        int discountPercent = 50;

        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        RentalAgreement agreement = checkoutBusiness
                .PerformCheckout(
                        toolCode,
                        rentalDayCount,
                        discountPercent,
                        checkoutDate
                );
        assertEquals("07/06/2020", agreement.getDateDue());
        assertEquals("$2.99", agreement.getDailyRentalCharge());
        assertEquals(1, agreement.getNumberOfDaysToCharge());
        assertEquals("$2.99", agreement.getPreDiscountTotalCost());
        assertEquals("50%", agreement.getDiscountPercent());
        assertEquals("$1.50", agreement.getDiscountAmount());
        assertEquals("$1.49", agreement.getFinalCharge());
    }

    @Test
    public void applyDiscount() {
        double totalDailyCharges = 1000.00;
        int discount = 30;
        double discountedPrice = checkoutBusiness.ApplyDiscount(totalDailyCharges, discount);
        assertEquals(300.00, discountedPrice, .001);
    }

    @Test
    public void applyDiscount1() {
        double totalDailyCharges = 1000.00;
        int discount = 100;
        double discountedPrice = checkoutBusiness.ApplyDiscount(totalDailyCharges, discount);
        assertEquals(1000.00, discountedPrice, .001);
    }

    @Test
    public void applyDiscount2() {
        double totalDailyCharges = 1000.00;
        int discount = 0;
        double discountedPrice = checkoutBusiness.ApplyDiscount(totalDailyCharges, discount);
        assertEquals(0.00, discountedPrice, .001);
    }

    @Test
    public void applyDiscount3() {
        double totalDailyCharges = 1000.00;
        int discount = -1;
        double discountedPrice = checkoutBusiness.ApplyDiscount(totalDailyCharges, discount);
        assertEquals(1000.00, discountedPrice, .001);
    }

    @Test
    public void applyDiscount4() {
        double totalDailyCharges = 1000.00;
        int discount = 1000;
        double discountedPrice = checkoutBusiness.ApplyDiscount(totalDailyCharges, discount);
        assertEquals(1000.00, discountedPrice, .001);
    }

    @Test
    public void produceRentalAgreement() {
    }

    @Test
    public void isDateOnWeekend() {
        LocalDate laborDay = LocalDate.parse("09/02/2018", formatter);
        boolean result = checkoutBusiness.IsDateOnWeekend(laborDay);
        assertEquals(true, result);
    }

    @Test
    public void isDateOnWeekend2() {
        LocalDate laborDay = LocalDate.parse("09/01/2018", formatter);
        boolean result = checkoutBusiness.IsDateOnWeekend(laborDay);
        assertEquals(true, result);
    }

    @Test
    public void isDateOnWeekend3() {
        LocalDate laborDay = LocalDate.parse("09/03/2018", formatter);
        boolean result = checkoutBusiness.IsDateOnWeekend(laborDay);
        assertEquals(false, result);
    }
    @Test
    public void getObservedIndependenceDay() {
        LocalDate independenceDay = LocalDate.parse("07/04/2018", formatter);
        LocalDate observedIndependenceDay = checkoutBusiness.GetObservedIndependenceDay(independenceDay);
        assertEquals(observedIndependenceDay, independenceDay);
    }

    @Test
    public void getObservedIndependenceDay2() {
        LocalDate independenceDay = LocalDate.parse("07/04/2015", formatter);
        LocalDate observedIndependenceDay = checkoutBusiness.GetObservedIndependenceDay(independenceDay);
        assertEquals(observedIndependenceDay, independenceDay.minusDays(1));
    }
    @Test
    public void getObservedIndependenceDay3() {
        LocalDate independenceDay = LocalDate.parse("07/04/2021", formatter);
        LocalDate observedIndependenceDay = checkoutBusiness.GetObservedIndependenceDay(independenceDay);
        assertEquals(observedIndependenceDay, independenceDay.plusDays(1));
    }
    @Test
    public void getObservedIndependenceDay4() {
        LocalDate independenceDayEve = LocalDate.parse("07/03/2018", formatter);
        LocalDate observedIndependenceDay = checkoutBusiness.GetObservedIndependenceDay(independenceDayEve);
        assertEquals(observedIndependenceDay, independenceDayEve);
    }
    @Test
    public void getObservedIndependenceDay5() {
        LocalDate notIndependenceDay = LocalDate.parse("10/04/2018", formatter);
        LocalDate observedIndependenceDay = checkoutBusiness.GetObservedIndependenceDay(notIndependenceDay);
        assertEquals(observedIndependenceDay, notIndependenceDay);
    }
    @Test
    public void isDateOnMemorialDay() {
        LocalDate laborDay = LocalDate.parse("09/03/2018", formatter);
        boolean result = checkoutBusiness.IsDateOnLaborDay(laborDay);
        assertEquals(true, result);
    }

    @Test
    public void isDateOnMemorialDay2() {
        LocalDate laborDay = LocalDate.parse("09/04/2018", formatter);
        boolean result = checkoutBusiness.IsDateOnLaborDay(laborDay);
        assertEquals(false, result);
    }
    @Test
    public void isDateOnMemorialDay3() {
        LocalDate laborDay = LocalDate.parse("10/04/2015", formatter);
        boolean result = checkoutBusiness.IsDateOnLaborDay(laborDay);
        assertEquals(false, result);
    }

    @Test
    public void getTotalDaysToCharge() {
        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        ToolDTO tool = checkoutBusiness.checkoutRepository.GetToolByToolCode("LADW");
        int rentalDayCount = 15; //Integer.parseInt()ChronoUnit.DAYS.between(checkoutDate.plusDays(1), dueDate);
        LocalDate checkoutDate = LocalDate.parse("07/01/2018", formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);// LocalDate.parse("07/25/2018", formatter);
        int totalDaysToCharge = checkoutBusiness.GetTotalDaysToCharge(tool, rentalDayCount, checkoutDate, dueDate);
        assertEquals(rentalDayCount, totalDaysToCharge);
    }


    @Test
    public void getTotalDaysToCharge2() {
        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        ToolDTO tool = checkoutBusiness.checkoutRepository.GetToolByToolCode("CHNS");
        int rentalDayCount = 15; //Integer.parseInt()ChronoUnit.DAYS.between(checkoutDate.plusDays(1), dueDate);
        LocalDate checkoutDate = LocalDate.parse("07/01/2018", formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);// LocalDate.parse("07/25/2018", formatter);
        int totalDaysToCharge = checkoutBusiness.GetTotalDaysToCharge(tool, rentalDayCount, checkoutDate, dueDate);
        assertEquals(11, totalDaysToCharge);
    }

    @Test
    public void getTotalDaysToCharge3() {
        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        ToolDTO tool = checkoutBusiness.checkoutRepository.GetToolByToolCode("JAKR");
        int rentalDayCount = 15;
        LocalDate checkoutDate = LocalDate.parse("07/01/2018", formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);// LocalDate.parse("07/25/2018", formatter);
        int totalDaysToCharge = checkoutBusiness.GetTotalDaysToCharge(tool, rentalDayCount, checkoutDate, dueDate);
        assertEquals(10, totalDaysToCharge);
    }

    @Test
    public void getTotalDaysToCharge4() {
        checkoutBusiness.checkoutRepository = new CheckoutRepository();

        ToolDTO tool = checkoutBusiness.checkoutRepository.GetToolByToolCode("JAKD");
        int rentalDayCount = 15;
        LocalDate checkoutDate = LocalDate.parse("07/01/2018", formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);// LocalDate.parse("07/25/2018", formatter);
        int totalDaysToCharge = checkoutBusiness.GetTotalDaysToCharge(tool, rentalDayCount, checkoutDate, dueDate);
        assertEquals(10, totalDaysToCharge);
    }
    @Test
    public void getTotalWeekendDates() {
        LocalDate checkoutDate = LocalDate.parse("07/05/2018", formatter);
        LocalDate dueDate = LocalDate.parse("07/25/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalWeekendDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 6);
    }

    @Test
    public void getTotalWeekendDates2() {
        LocalDate checkoutDate = LocalDate.parse("01/01/2018", formatter);
        LocalDate dueDate = LocalDate.parse("12/31/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalWeekendDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 104);
    }

    @Test
    public void getTotalWeekendDates3() {
        LocalDate checkoutDate = LocalDate.parse("07/01/2018", formatter);
        LocalDate dueDate = LocalDate.parse("07/03/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalWeekendDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 1);
    }

    @Test
    public void getTotalWeekendDates4() {
        LocalDate checkoutDate = LocalDate.parse("07/02/2018", formatter);
        LocalDate dueDate = LocalDate.parse("07/04/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalWeekendDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 0);
    }

    @Test
    public void getTotalHolidayDates() {
        LocalDate checkoutDate = LocalDate.parse("05/05/2018", formatter);
        LocalDate dueDate = LocalDate.parse("09/25/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalHolidayDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 2);
    }

    @Test
    public void getTotalHolidayDates2() {
        LocalDate checkoutDate = LocalDate.parse("08/05/2018", formatter);
        LocalDate dueDate = LocalDate.parse("09/25/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalHolidayDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 1);
    }

    @Test
    public void getTotalHolidayDates3() {
        LocalDate checkoutDate = LocalDate.parse("05/05/2018", formatter);
        LocalDate dueDate = LocalDate.parse("08/25/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalHolidayDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 1);
    }

    @Test
    public void getTotalHolidayDates4() {
        LocalDate checkoutDate = LocalDate.parse("08/05/2018", formatter);
        LocalDate dueDate = LocalDate.parse("08/25/2018", formatter);

        int numberOfHolidays = checkoutBusiness.GetTotalHolidayDates(checkoutDate, dueDate);

        assertEquals(numberOfHolidays, 0);
    }
    @Test
    public void isDateOnHoliday() {
        LocalDate independenceDay = LocalDate.parse("07/04/2018", formatter);
        boolean onIndependenceDay = checkoutBusiness.IsDateOnHoliday(independenceDay);
        assertEquals(onIndependenceDay, true);
    }

    @Test
    public void isDateOnHoliday2() {
        LocalDate independenceDay = LocalDate.parse("09/03/2018", formatter);
        boolean onIndependenceDay = checkoutBusiness.IsDateOnHoliday(independenceDay);
        assertEquals(onIndependenceDay, true);
    }

    @Test
    public void isDateOnHoliday3() {
        LocalDate independenceDay = LocalDate.parse("09/02/2018", formatter);
        boolean onIndependenceDay = checkoutBusiness.IsDateOnHoliday(independenceDay);
        assertEquals(onIndependenceDay, false);
    }
    @Test
    public void isDateOnIndependenceDay() {
        LocalDate independenceDay = LocalDate.parse("07/04/2018", formatter);
        boolean onIndependenceDay = checkoutBusiness.IsDateOnIndependenceDay(independenceDay);
        assertEquals(onIndependenceDay, true);
    }

    @Test
    public void isDateOnIndependenceDay2() {
        LocalDate independenceDay = LocalDate.parse("07/05/2018", formatter);
        boolean onIndependenceDay = checkoutBusiness.IsDateOnIndependenceDay(independenceDay);
        assertEquals(onIndependenceDay, false);
    }


    @Test
    public void isDateOnIndependenceDay3() {
        LocalDate independenceDay = LocalDate.parse("09/04/2018", formatter);
        boolean onIndependenceDay = checkoutBusiness.IsDateOnIndependenceDay(independenceDay);
        assertEquals(onIndependenceDay, false);
    }

    @Test
    public void isDateOnIndependenceDay4() {
        LocalDate independenceDay = LocalDate.parse("09/05/2018", formatter);
        boolean onIndependenceDay = checkoutBusiness.IsDateOnIndependenceDay(independenceDay);
        assertEquals(onIndependenceDay, false);
    }
    @Test
    public void getAllRentalDays() {
        LocalDate checkoutDate = LocalDate.parse("09/05/2018", formatter);
        LocalDate dueDate = LocalDate.parse("09/25/2018", formatter);

        List<LocalDate> allRentalDays = checkoutBusiness.GetAllRentalDays(checkoutDate, dueDate);

        assertEquals(allRentalDays.size(), 21);
    }
    @Test
    public void getAllRentalDays2() {
        LocalDate checkoutDate = LocalDate.parse("01/01/2018", formatter);
        LocalDate dueDate = LocalDate.parse("12/31/2018", formatter);

        List<LocalDate> allRentalDays = checkoutBusiness.GetAllRentalDays(checkoutDate, dueDate);

        assertEquals(allRentalDays.size(), 365);
    }
}