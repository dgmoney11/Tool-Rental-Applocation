package toolrentalapplication.dylangirard;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.RentalAgreement;
import toolrentalapplication.dylangirard.src.Checkout.Interfaces.ICheckoutBusiness;

import java.time.LocalDate;

@RequestMapping("/api/checkout")
@RestController
public class CheckoutController {
    ICheckoutBusiness checkoutBusiness;
    public CheckoutController(ICheckoutBusiness checkoutBusiness)
    {
        this.checkoutBusiness = checkoutBusiness;
    }
    @RequestMapping(method = RequestMethod.POST)
    public RentalAgreement PerformCheckout(@RequestBody String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate)
    {
        return checkoutBusiness.PerformCheckout(toolCode, rentalDayCount,discountPercent, checkoutDate);
    }

    @RequestMapping(method = RequestMethod.GET, path = "Heartbeat")
    public String GetHeartbeat()
    {
        return "The service is running.";
    }
}
