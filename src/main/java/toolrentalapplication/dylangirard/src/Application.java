package toolrentalapplication.dylangirard.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import toolrentalapplication.dylangirard.src.Checkout.CheckoutBusiness;
import toolrentalapplication.dylangirard.src.Checkout.CheckoutRepository;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        new CheckoutBusiness(new CheckoutRepository()).PerformCheckout("LADW", 80, 20,LocalDate.now());
    }
}