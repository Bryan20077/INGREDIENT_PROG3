import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/recipes_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie!");

            RecipeDAO dao = new RecipeDAO(connection);

            System.out.println("\n========== TOUTES LES RECETTES ==========");
            dao.getAllRecipes().forEach(recipe -> System.out.println(recipe));

            System.out.println("\n========== TOUS LES INGRÉDIENTS ==========");
            dao.getAllIngredients().forEach(ingredient -> System.out.println(ingredient));

            System.out.println("\n========== INGRÉDIENTS DE LA RECETTE 1 ==========");
            dao.getIngredientsForRecipe(1).forEach(ri -> {
                System.out.println(ri.getIdIngredient() + " - Quantité: " + ri.getQuantity());
            });

            System.out.println("\n========== COÛT DES RECETTES ==========");
            for (Recipe recipe : dao.getAllRecipes()) {
                double cost = dao.getRecipeCost(recipe.getIdRecipe());
                System.out.println(recipe.getName() + ": " + String.format("%.2f €", cost));
            }

            System.out.println("\n========== RECETTES CONTENANT LA FARINE ==========");
            dao.getRecipesWithIngredient(1).forEach(recipe -> System.out.println(recipe.getName()));

            System.out.println("\n========== AJOUTER UNE NOUVELLE RECETTE ==========");
            Recipe newRecipe = new Recipe("Pain", "Moyen", 4, 1500);
            dao.addRecipe(newRecipe);
            System.out.println("Recette ajoutée: " + newRecipe.getName() + " (ID: " + newRecipe.getIdRecipe() + ")");

            System.out.println("\n========== AJOUTER UN INGRÉDIENT À LA RECETTE ==========");
            dao.addIngredientToRecipe(newRecipe.getIdRecipe(), 1, 0.5);
            dao.addIngredientToRecipe(newRecipe.getIdRecipe(), 6, 0.01);
            System.out.println("Ingrédients ajoutés à: " + newRecipe.getName());

            System.out.println("\n========== METTRE À JOUR UNE QUANTITÉ ==========");
            dao.updateQuantityInRecipe(1, 1, 0.35);
            System.out.println("Quantité mise à jour");

            System.out.println("\n========== SUPPRIMER UN INGRÉDIENT D'UNE RECETTE ==========");
            dao.removeIngredientFromRecipe(2, 2);
            System.out.println("Ingrédient supprimé");

            System.out.println("\n========== DÉTAILS COMPLETS DE LA RECETTE 1 ==========");
            RecipeDetails details = dao.getRecipeDetails(1);
            if (details != null) {
                System.out.println("Recette: " + details.getRecipe().getName());
                System.out.println("Nombre d'ingrédients: " + details.getIngredients().size());
                System.out.println("Coût total: " + String.format("%.2f €", details.getTotalCost()));
            }

            // ========== TESTS POUR LES PLATS ==========
            System.out.println("\n\n========== TOUS LES PLATS ==========");
            try {
                dao.getAllDishes().forEach(dish -> System.out.println(dish));
            } catch (Exception e) {
                System.out.println("Les tables Dish n'existent pas encore. Exécutez setup_dish.sql d'abord.");
            }

            System.out.println("\n========== COÛT DES PLATS ==========");
            try {
                for (Dish dish : dao.getAllDishes()) {
                    double cost = dao.getDishCost(dish.getIdDish());
                    System.out.println("Plat: " + dish.getName() + " - Coût: " + String.format("%.2f", cost));
                }
            } catch (Exception e) {
                System.out.println("Impossible de récupérer les plats.");
            }

            System.out.println("\n========== MARGE BRUTE DES PLATS ==========");
            try {
                for (Dish dish : dao.getAllDishes()) {
                    double margin = dao.getDishGrossMargin(dish.getIdDish());
                    Double price = dish.getSellingPrice();
                    if (price != null) {
                        System.out.println("Plat: " + dish.getName() + " - Marge: " + String.format("%.2f", margin));
                    } else {
                        System.out.println("Plat: " + dish.getName() + " - Marge: Exception (prix NULL)");
                    }
                }
            } catch (Exception e) {
                System.out.println("Impossible de calculer les marges.");
            }

            System.out.println("\n========== DÉTAILS COMPLETS DES PLATS ==========");
            try {
                dao.getDishesWithDetails().forEach(detail -> System.out.println(detail));
            } catch (Exception e) {
                System.out.println("Impossible de récupérer les détails des plats.");
            }

            System.out.println("\n========== INGRÉDIENTS D'UN PLAT ==========");
            try {
                System.out.println("Ingrédients du plat 1:");
                dao.getIngredientsForDish(1).forEach(di -> System.out.println("  - ID Ingrédient: " + di.getIdIngredient() + ", Quantité: " + di.getQuantityRequired() + " " + di.getUnit()));
            } catch (Exception e) {
                System.out.println("Impossible de récupérer les ingrédients du plat.");
            }

            connection.close();
            System.out.println("\n✓ Programme terminé avec succès!");

        } catch (ClassNotFoundException e) {
            System.err.println("Erreur: Driver PostgreSQL non trouvé!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur de base de données!");
            e.printStackTrace();
        }
    }
}
