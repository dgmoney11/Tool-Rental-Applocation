package toolrentalapplication.dylangirard.src.Checkout.Interfaces;

import toolrentalapplication.dylangirard.src.Checkout.DataModels.ToolDTO;

public interface ICheckoutRepository {
    ToolDTO GetToolByToolCode(String toolCode);
}
