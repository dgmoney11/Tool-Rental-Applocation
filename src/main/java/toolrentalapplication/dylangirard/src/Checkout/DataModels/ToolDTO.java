package toolrentalapplication.dylangirard.src.Checkout.DataModels;

public class ToolDTO {
    public String ToolType;

    public ToolDTO(String toolType, String toolBrand, String toolCode, double dailyCost, boolean isChargedOnWeekends, boolean isChargedOnHolidays) {
        ToolType = toolType;
        ToolBrand = toolBrand;
        ToolCode = toolCode;
        DailyCost = dailyCost;
        IsChargedOnWeekends = isChargedOnWeekends;
        IsChargedOnHolidays = isChargedOnHolidays;
    }

    public String ToolBrand;
    public String ToolCode;
    public double DailyCost;
    public boolean IsChargedOnWeekends;
    public boolean IsChargedOnHolidays;

    public String getToolType() {
        return this.ToolType;
    }

    public void setToolType(String toolType) {
        this.ToolType = toolType;
    }

    public String getToolBrand() {
        return this.ToolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.ToolBrand = toolBrand;
    }

    public String getToolCode() {
        return this.ToolCode;
    }

    public void setToolCode(String toolCode) {
        this.ToolCode = toolCode;
    }
    public double getDailyCost() {
        return DailyCost;
    }

    public void setDailyCost(double dailyCost) {
        DailyCost = dailyCost;
    }
    public boolean isChargedOnWeekends() {
        return IsChargedOnWeekends;
    }

    public void setChargedOnWeekends(boolean chargedOnWeekends) {
        IsChargedOnWeekends = chargedOnWeekends;
    }

    public boolean isChargedOnHolidays() {
        return IsChargedOnHolidays;
    }

    public void setChargedOnHolidays(boolean chargedOnHolidays) {
        IsChargedOnHolidays = chargedOnHolidays;
    }
}
