import java.util.List;

/**
 * Classe pour représenter les détails complets d'une recette avec ses ingrédients et coût
 */
public class RecipeDetails {
    private Recipe recipe;
    private List<RecipeIngredient> ingredients;
    private double totalCost;

    public RecipeDetails(Recipe recipe, List<RecipeIngredient> ingredients, double totalCost) {
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.totalCost = totalCost;
    }

    // Getters et Setters
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "RecipeDetails{" +
                "recipe=" + recipe +
                ", ingredients=" + ingredients +
                ", totalCost=" + totalCost +
                '}';
    }

    public void displayDetails() {
        System.out.println("\n========== DÉTAILS DE LA RECETTE ==========");
        System.out.println("Recette: " + recipe.getName());
        System.out.println("Difficulté: " + recipe.getDifficulty());
        System.out.println("Personnes: " + recipe.getNbPersons());
        System.out.println("Calories: " + recipe.getNbKcal());
        System.out.println("\nIngrédients:");
        for (RecipeIngredient ri : ingredients) {
            System.out.println("  - " + ri.getIngredient().getName() + ": " + ri.getQuantity() + " " + ri.getIngredient().getUnit());
        }
        System.out.println("\nCoût total: " + String.format("%.2f", totalCost) + " €");
        System.out.println("==========================================\n");
    }
}
