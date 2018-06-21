import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toolrentalapplication.dylangirard.src.Checkout.CheckoutBusiness;
import toolrentalapplication.dylangirard.src.Checkout.Interfaces.ICheckoutBusiness;

@Configuration
public class AppConfig {
    @Bean
    public ICheckoutBusiness checkoutBusiness() {
        return new CheckoutBusiness();
    }
}