/**
 * Classe représentant la relation Many-to-Many entre une recette et un ingrédient
 */
public class RecipeIngredient {
    private int idRecipeIngredient;
    private int idRecipe;
    private int idIngredient;
    private double quantity;
    private Recipe recipe;
    private Ingredient ingredient;

    public RecipeIngredient(int idRecipeIngredient, int idRecipe, int idIngredient, double quantity) {
        this.idRecipeIngredient = idRecipeIngredient;
        this.idRecipe = idRecipe;
        this.idIngredient = idIngredient;
        this.quantity = quantity;
    }

    public RecipeIngredient(int idRecipe, int idIngredient, double quantity) {
        this.idRecipe = idRecipe;
        this.idIngredient = idIngredient;
        this.quantity = quantity;
    }

    // Constructeur avec objets complets
    public RecipeIngredient(int idRecipeIngredient, Recipe recipe, Ingredient ingredient, double quantity) {
        this.idRecipeIngredient = idRecipeIngredient;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.idRecipe = recipe.getIdRecipe();
        this.idIngredient = ingredient.getIdIngredient();
    }

    // Getters et Setters
    public int getIdRecipeIngredient() {
        return idRecipeIngredient;
    }

    public void setIdRecipeIngredient(int idRecipeIngredient) {
        this.idRecipeIngredient = idRecipeIngredient;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "idRecipeIngredient=" + idRecipeIngredient +
                ", idRecipe=" + idRecipe +
                ", idIngredient=" + idIngredient +
                ", quantity=" + quantity +
                '}';
    }
}
