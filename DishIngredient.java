
public class DishIngredient {
    private int idSerial;
    private int idDish;
    private int idIngredient;
    private double quantityRequired;
    private String unit;

    public DishIngredient(int idSerial, int idDish, int idIngredient, double quantityRequired, String unit) {
        this.idSerial = idSerial;
        this.idDish = idDish;
        this.idIngredient = idIngredient;
        this.quantityRequired = quantityRequired;
        this.unit = unit;
    }

    public DishIngredient(int idDish, int idIngredient, double quantityRequired, String unit) {
        this.idDish = idDish;
        this.idIngredient = idIngredient;
        this.quantityRequired = quantityRequired;
        this.unit = unit;
    }

    // Getters et Setters
    public int getIdSerial() {
        return idSerial;
    }

    public void setIdSerial(int idSerial) {
        this.idSerial = idSerial;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public double getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(double quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "idSerial=" + idSerial +
                ", idDish=" + idDish +
                ", idIngredient=" + idIngredient +
                ", quantityRequired=" + quantityRequired +
                ", unit='" + unit + '\'' +
                '}';
    }
}