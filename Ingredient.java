/**
 * Classe représentant un ingrédient
 */
public class Ingredient {
    private int idIngredient;
    private String name;
    private String unit;
    private double price;

    public Ingredient(int idIngredient, String name, String unit, double price) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public Ingredient(String name, String unit, double price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    // Getters et Setters
    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                '}';
    }
}
