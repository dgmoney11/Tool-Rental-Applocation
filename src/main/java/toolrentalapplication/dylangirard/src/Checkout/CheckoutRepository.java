package toolrentalapplication.dylangirard.src.Checkout;

import org.springframework.stereotype.Component;
import toolrentalapplication.dylangirard.src.Checkout.DataModels.ToolDTO;
import toolrentalapplication.dylangirard.src.Checkout.Interfaces.ICheckoutRepository;

import java.util.ArrayList;
import java.util.Optional;
@Component
//Mock repository layer for data access.
public class CheckoutRepository implements ICheckoutRepository
{
    public CheckoutRepository()
    {
        tools.add(ladder);
        tools.add(chainsaw);
        tools.add(jackhammer1);
        tools.add(jackhammer2);
    }

    private ArrayList<ToolDTO> tools = new ArrayList<ToolDTO>();

    private ToolDTO ladder = new ToolDTO("Ladder",
            "Werner",
            "LADW",
            1.99,
            true,
            true
    );
    private ToolDTO chainsaw = new ToolDTO("ChainSaw",
            "Stihl",
            "CHNS",
        1.49,
        false,
        true
        );
    private ToolDTO jackhammer1 = new ToolDTO("Jackhammer",
            "Ridgid",
            "JAKD",
            2.99,
            false,
            false
    );
    private ToolDTO jackhammer2 = new ToolDTO("Jackhammer",
            "DeWalt",
            "JAKR",
            2.99,
            false,
            false
    );
    //Return the tool based on the tool codes being equal
    public ToolDTO GetToolByToolCode(String toolCode)
    {
        Optional<ToolDTO> toolToReturn = tools.stream().filter(tool -> tool.getToolCode().equals(toolCode)).findFirst();
        return toolToReturn.get();
    }
}

