package toolrentalapplication.dylangirard.src.Checkout.Interfaces;


import org.springframework.context.annotation.Bean;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.RentalAgreement;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.ToolDTO;

import java.time.LocalDate;
import java.util.List;
public interface ICheckoutBusiness {
    double ApplyDiscount(double totalDailyCharges, int discountRate);

    boolean IsDateOnWeekend(LocalDate date);

    LocalDate GetObservedIndependenceDay(LocalDate date);

    RentalAgreement PerformCheckout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate);

    boolean IsDateOnLaborDay(LocalDate date);

    boolean IsDateOnIndependenceDay(LocalDate date);

    int GetTotalDaysToCharge(ToolDTO tool, int rentalDayCount, LocalDate checkoutDate, LocalDate dueDate);

    int GetTotalWeekendDates(LocalDate checkoutDate, LocalDate dueDate);

    int GetTotalHolidayDates(LocalDate checkoutDate, LocalDate dueDate);

    boolean IsDateOnHoliday(LocalDate currentDate);

    List<LocalDate> GetAllRentalDays(LocalDate checkoutDate, LocalDate dueDate);
}
