/**
 * Classe représentant un plat
 */
public class Dish {
    private int idDish;
    private String name;
    private String dishType;
    private Double sellingPrice; // peut être NULL

    public Dish(int idDish, String name, String dishType, Double sellingPrice) {
        this.idDish = idDish;
        this.name = name;
        this.dishType = dishType;
        this.sellingPrice = sellingPrice;
    }

    public Dish(String name, String dishType, Double sellingPrice) {
        this.name = name;
        this.dishType = dishType;
        this.sellingPrice = sellingPrice;
    }

    // Getters et Setters
    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "idDish=" + idDish +
                ", name='" + name + '\'' +
                ", dishType='" + dishType + '\'' +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
