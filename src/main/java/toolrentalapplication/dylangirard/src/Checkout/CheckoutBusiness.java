package toolrentalapplication.dylangirard.src.Checkout;


import org.springframework.stereotype.Component;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.RentalAgreement;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.ToolDTO;
import toolrentalapplication.dylangirard.src.Checkout.Interfaces.ICheckoutBusiness;
import toolrentalapplication.dylangirard.src.Checkout.Interfaces.ICheckoutRepository;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Component
public class CheckoutBusiness implements ICheckoutBusiness {
    public ICheckoutRepository checkoutRepository;

    //Use dependency injection so I can test calls made to checkoutRepository.
    public CheckoutBusiness(ICheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public CheckoutBusiness() {

    }

    @Override
    public RentalAgreement PerformCheckout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {

        if (discountPercent < 0 || discountPercent > 100 || rentalDayCount < 1) {
            try
            {
                throw new Exception("Please enter a discount between 0 and 100, and a rental day count greater than 0.");
            }
            catch(Exception e){}
        }


        //tool includes tool code, tool type, tool brand, daily charge
        ToolDTO tool = checkoutRepository.GetToolByToolCode(toolCode);
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);
        int numberOfDaysToCharge = GetTotalDaysToCharge(tool, rentalDayCount, checkoutDate.plusDays(1), dueDate);
        double preDiscountTotalCost = numberOfDaysToCharge * ((ToolDTO) tool).getDailyCost();
        double discountAmount = ApplyDiscount(preDiscountTotalCost, discountPercent);
        double finalCharge = preDiscountTotalCost - discountAmount;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String checkoutDateString = checkoutDate.format(dateFormatter);
        String dueDateString = dueDate.format(dateFormatter);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String finalChargeString = currencyFormatter.format(finalCharge);
        String dailyRentalCharge = currencyFormatter.format(tool.getDailyCost());
        String preDiscountString = currencyFormatter.format(preDiscountTotalCost);
        String discountAmountString = currencyFormatter.format(discountAmount);
        String discountPercentString = Integer.toString(discountPercent) + "%";
        List<LocalDate> rentalDays = GetAllRentalDays(checkoutDate, dueDate);

        RentalAgreement agreement = new RentalAgreement(
                tool.getToolCode(),
                tool.getToolType(),
                tool.getToolBrand(),
                dailyRentalCharge,
                rentalDays,
                checkoutDateString,
                dueDateString,
                numberOfDaysToCharge,
                preDiscountString,
                discountAmountString,
                discountPercentString,
                finalChargeString);

        return agreement;
    }

    @Override
    //Applies discount
    public double ApplyDiscount(double totalDailyCharges, int discount) {
        if (discount > 0 && discount < 100) {
            double discountAmount = totalDailyCharges * (discount / 100.00);
            //Rounds to the nearest hundredth
            return ((int) ((discountAmount * 100.0) + ((discountAmount < 0.0) ? -0.5 : 0.5))) / 100.0;
            } else if (discount == 0) {
            return 0.00;
        } else {
            return totalDailyCharges;
        }
    }

    @Override
    //Returns true if the day of the week is either saturday or sunday, and returns false otherwise.
    public boolean IsDateOnWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    //Gets the Observed Independence Day, if Independence Day happens to fall on the weekend.
    public LocalDate GetObservedIndependenceDay(LocalDate date) {
        if (IsDateOnIndependenceDay(date)) {
            //If the day of the week for independence day is saturday, then subtract one day to observe Friday instead of Saturday.
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                return date.minusDays(1);
            }
            //If the day is sunday, then return Monday.
            else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return date.plusDays(1);
            }
            //Otherwise return the original date
            else {
                return date;
            }
        } else {
            return date;
        }
    }

    @Override
    //Checks to see if the date is on Labor Day
    public boolean IsDateOnLaborDay(LocalDate date) {
        LocalDate firstMonday = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        return date.compareTo(firstMonday) == 0 && date.getMonth() == Month.SEPTEMBER;
    }

    @Override
    //Gets the total number of days that are actually charged, subtracting holidays and weekends if applicable
    public int GetTotalDaysToCharge(ToolDTO tool, int rentalDayCount, LocalDate checkoutDate, LocalDate dueDate) {
        int totalWeekendDaysNotCharged = 0;
        int totalHolidaysNotCharged = 0;
        LocalDate firstDateToCharge = checkoutDate.plusDays(1);
        if (!tool.IsChargedOnWeekends) {
            totalWeekendDaysNotCharged = GetTotalWeekendDates(firstDateToCharge, dueDate);
        }
        if (!tool.IsChargedOnHolidays) {
            totalHolidaysNotCharged = GetTotalHolidayDates(firstDateToCharge, dueDate);
        }
        return rentalDayCount - totalWeekendDaysNotCharged - totalHolidaysNotCharged;
    }

    @Override
    //Cycle through all of the dates until the due date has been reached, if a date falls on the weekend, increment
    //the number of weekend days in the date range.
    public int GetTotalWeekendDates(LocalDate checkoutDate, LocalDate dueDate) {
        int numberOfWeekendDays = 0;
        LocalDate currentDate = checkoutDate;
        do {
            if (IsDateOnWeekend(currentDate)) {
                numberOfWeekendDays++;
            }
            currentDate = currentDate.plusDays(1);
        } while (currentDate.compareTo(dueDate) <= 0);

        return numberOfWeekendDays;
    }

    @Override
    //Similar to GetTotalWeekendDates, but instead checks for holidays instead of weekend dates.
    //These methods could potentially be merged together, taking in a callback function as a parameter to be called
    //instead of calling each method directly.
    public int GetTotalHolidayDates(LocalDate checkoutDate, LocalDate dueDate) {
        int numberOfHoliDays = 0;
        LocalDate currentDate = checkoutDate;
        do {
            if (IsDateOnHoliday(currentDate)) {
                numberOfHoliDays++;
            }
            currentDate = currentDate.plusDays(1);
        } while (currentDate.compareTo(dueDate) <= 0);
        return numberOfHoliDays;
    }

    @Override
    //Checks to see if the date is on a holiday.
    public boolean IsDateOnHoliday(LocalDate currentDate) {
        return IsDateOnLaborDay(currentDate) || IsDateOnIndependenceDay(currentDate);
    }

    @Override
    //Checks to see if the date is on Independence ////day
    public boolean IsDateOnIndependenceDay(LocalDate currentDate) {
        return currentDate.getMonth() == Month.JULY && currentDate.getDayOfMonth() == 4;
    }

    @Override
    public List<LocalDate> GetAllRentalDays(LocalDate checkoutDate, LocalDate dueDate) {
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate currentDate = checkoutDate;
        while (currentDate.compareTo(dueDate) != 0) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        dates.add(dueDate);
        return dates;
    }
}
