import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RecipeDAO {
    private Connection connection;

    public RecipeDAO(Connection connection) {
        this.connection = connection;
    }

    public void addRecipe(Recipe recipe) throws SQLException {
        String sql = "INSERT INTO recipe (name, difficulty, nb_persons, nb_kcal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, recipe.getName());
            pstmt.setString(2, recipe.getDifficulty());
            pstmt.setInt(3, recipe.getNbPersons());
            pstmt.setInt(4, recipe.getNbKcal());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    recipe.setIdRecipe(rs.getInt(1));
                }
            }
        }
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipe";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                recipes.add(new Recipe(
                        rs.getInt("id_recipe"),
                        rs.getString("name"),
                        rs.getString("difficulty"),
                        rs.getInt("nb_persons"),
                        rs.getInt("nb_kcal")
                ));
            }
        }
        return recipes;
    }

   
    public Recipe getRecipeById(int id) throws SQLException {
        String sql = "SELECT * FROM recipe WHERE id_recipe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Recipe(
                            rs.getInt("id_recipe"),
                            rs.getString("name"),
                            rs.getString("difficulty"),
                            rs.getInt("nb_persons"),
                            rs.getInt("nb_kcal")
                    );
                }
            }
        }
        return null;
    }

 
    public void updateRecipe(Recipe recipe) throws SQLException {
        String sql = "UPDATE recipe SET name = ?, difficulty = ?, nb_persons = ?, nb_kcal = ? WHERE id_recipe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recipe.getName());
            pstmt.setString(2, recipe.getDifficulty());
            pstmt.setInt(3, recipe.getNbPersons());
            pstmt.setInt(4, recipe.getNbKcal());
            pstmt.setInt(5, recipe.getIdRecipe());
            pstmt.executeUpdate();
        }
    }

    
    public void deleteRecipe(int id) throws SQLException {
        String sql = "DELETE FROM recipe WHERE id_recipe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

 
    public void addIngredient(Ingredient ingredient) throws SQLException {
        String sql = "INSERT INTO ingredient (name, unit, price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, ingredient.getName());
            pstmt.setString(2, ingredient.getUnit());
            pstmt.setDouble(3, ingredient.getPrice());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ingredient.setIdIngredient(rs.getInt(1));
                }
            }
        }
    }


    public List<Ingredient> getAllIngredients() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredient";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ingredients.add(new Ingredient(
                        rs.getInt("id_ingredient"),
                        rs.getString("name"),
                        rs.getString("unit"),
                        rs.getDouble("price")
                ));
            }
        }
        return ingredients;
    }


    public Ingredient getIngredientById(int id) throws SQLException {
        String sql = "SELECT * FROM ingredient WHERE id_ingredient = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Ingredient(
                            rs.getInt("id_ingredient"),
                            rs.getString("name"),
                            rs.getString("unit"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }


    public void updateIngredient(Ingredient ingredient) throws SQLException {
        String sql = "UPDATE ingredient SET name = ?, unit = ?, price = ? WHERE id_ingredient = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, ingredient.getName());
            pstmt.setString(2, ingredient.getUnit());
            pstmt.setDouble(3, ingredient.getPrice());
            pstmt.setInt(4, ingredient.getIdIngredient());
            pstmt.executeUpdate();
        }
    }


    public void deleteIngredient(int id) throws SQLException {
        String sql = "DELETE FROM ingredient WHERE id_ingredient = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }


    public void addIngredientToRecipe(int recipeId, int ingredientId, double quantity) throws SQLException {
        String sql = "INSERT INTO recipe_ingredient (id_recipe, id_ingredient, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recipeId);
            pstmt.setInt(2, ingredientId);
            pstmt.setDouble(3, quantity);
            pstmt.executeUpdate();
        }
    }

    public List<RecipeIngredient> getIngredientsForRecipe(int recipeId) throws SQLException {
        List<RecipeIngredient> ingredients = new ArrayList<>();
        String sql = "SELECT ri.*, i.name, i.unit, i.price FROM recipe_ingredient ri " +
                "JOIN ingredient i ON ri.id_ingredient = i.id_ingredient " +
                "WHERE ri.id_recipe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recipeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Ingredient ingredient = new Ingredient(
                            rs.getInt("id_ingredient"),
                            rs.getString("name"),
                            rs.getString("unit"),
                            rs.getDouble("price")
                    );
                    ingredients.add(new RecipeIngredient(
                            rs.getInt("id_recipe_ingredient"),
                            recipeId,
                            rs.getInt("id_ingredient"),
                            rs.getDouble("quantity")
                    ));
                }
            }
        }
        return ingredients;
    }

    public List<Recipe> getRecipesWithIngredient(int ingredientId) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT DISTINCT r.* FROM recipe r " +
                "JOIN recipe_ingredient ri ON r.id_recipe = ri.id_recipe " +
                "WHERE ri.id_ingredient = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, ingredientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    recipes.add(new Recipe(
                            rs.getInt("id_recipe"),
                            rs.getString("name"),
                            rs.getString("difficulty"),
                            rs.getInt("nb_persons"),
                            rs.getInt("nb_kcal")
                    ));
                }
            }
        }
        return recipes;
    }

    public void removeIngredientFromRecipe(int recipeId, int ingredientId) throws SQLException {
        String sql = "DELETE FROM recipe_ingredient WHERE id_recipe = ? AND id_ingredient = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recipeId);
            pstmt.setInt(2, ingredientId);
            pstmt.executeUpdate();
        }
    }


    public void updateQuantityInRecipe(int recipeId, int ingredientId, double quantity) throws SQLException {
        String sql = "UPDATE recipe_ingredient SET quantity = ? WHERE id_recipe = ? AND id_ingredient = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, quantity);
            pstmt.setInt(2, recipeId);
            pstmt.setInt(3, ingredientId);
            pstmt.executeUpdate();
        }
    }


    public double getRecipeCost(int recipeId) throws SQLException {
        String sql = "SELECT SUM(ri.quantity * i.price) as total_cost FROM recipe_ingredient ri " +
                "JOIN ingredient i ON ri.id_ingredient = i.id_ingredient " +
                "WHERE ri.id_recipe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, recipeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total_cost");
                }
            }
        }
        return 0;
    }


    public RecipeDetails getRecipeDetails(int recipeId) throws SQLException {
        Recipe recipe = getRecipeById(recipeId);
        if (recipe == null) return null;

        List<RecipeIngredient> ingredients = getIngredientsForRecipe(recipeId);
        double cost = getRecipeCost(recipeId);

        return new RecipeDetails(recipe, ingredients, cost);
    }


    public void addDish(Dish dish) throws SQLException {
        String sql = "INSERT INTO dish (name, dish_type, selling_price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, dish.getName());
            pstmt.setString(2, dish.getDishType());
            if (dish.getSellingPrice() != null) {
                pstmt.setDouble(3, dish.getSellingPrice());
            } else {
                pstmt.setNull(3, java.sql.Types.DOUBLE);
            }
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    dish.setIdDish(rs.getInt(1));
                }
            }
        }
    }


    public List<Dish> getAllDishes() throws SQLException {
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * FROM dish";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Double price = rs.getDouble("selling_price");
                if (rs.wasNull()) {
                    price = null;
                }
                dishes.add(new Dish(
                        rs.getInt("id_dish"),
                        rs.getString("name"),
                        rs.getString("dish_type"),
                        price
                ));
            }
        }
        return dishes;
    }


    public Dish getDishById(int id) throws SQLException {
        String sql = "SELECT * FROM dish WHERE id_dish = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Double price = rs.getDouble("selling_price");
                    if (rs.wasNull()) {
                        price = null;
                    }
                    return new Dish(
                            rs.getInt("id_dish"),
                            rs.getString("name"),
                            rs.getString("dish_type"),
                            price
                    );
                }
            }
        }
        return null;
    }


    public void updateDish(Dish dish) throws SQLException {
        String sql = "UPDATE dish SET name = ?, dish_type = ?, selling_price = ? WHERE id_dish = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, dish.getName());
            pstmt.setString(2, dish.getDishType());
            if (dish.getSellingPrice() != null) {
                pstmt.setDouble(3, dish.getSellingPrice());
            } else {
                pstmt.setNull(3, java.sql.Types.DOUBLE);
            }
            pstmt.setInt(4, dish.getIdDish());
            pstmt.executeUpdate();
        }
    }


    public void deleteDish(int id) throws SQLException {
        String sql = "DELETE FROM dish WHERE id_dish = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }


    public void addIngredientToDish(int dishId, int ingredientId, double quantityRequired, String unit) throws SQLException {
        String sql = "INSERT INTO dish_ingredient (id_dish, id_ingredient, quantity_required, unit) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dishId);
            pstmt.setInt(2, ingredientId);
            pstmt.setDouble(3, quantityRequired);
            pstmt.setString(4, unit);
            pstmt.executeUpdate();
        }
    }


    public List<DishIngredient> getIngredientsForDish(int dishId) throws SQLException {
        List<DishIngredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM dish_ingredient WHERE id_dish = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dishId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ingredients.add(new DishIngredient(
                            rs.getInt("id_serial"),
                            rs.getInt("id_dish"),
                            rs.getInt("id_ingredient"),
                            rs.getDouble("quantity_required"),
                            rs.getString("unit")
                    ));
                }
            }
        }
        return ingredients;
    }

        public List<DishIngredient> findDishIngredientByDishId(Integer dishId) throws SQLException {
        List<DishIngredient> dishIngredients = new ArrayList<>();
        String sql = "SELECT di.id_serial, di.id_dish, di.id_ingredient, di.quantity_required, di.unit " +
                "FROM dish_ingredient di " +
                "JOIN ingredient i ON di.id_ingredient = i.id_ingredient " +
                "JOIN dish d ON di.id_dish = d.id_dish " +
                "WHERE di.id_dish = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dishId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    dishIngredients.add(new DishIngredient(
                            rs.getInt("id_serial"),
                            rs.getInt("id_dish"),
                            rs.getInt("id_ingredient"),
                            rs.getDouble("quantity_required"),
                            rs.getString("unit")
                    ));
                }
            }
        }
        return dishIngredients;
    }

    public double getDishCost(int dishId) throws SQLException {
        String sql = "SELECT SUM(di.quantity_required * i.price) as total_cost FROM dish_ingredient di " +
                "JOIN ingredient i ON di.id_ingredient = i.id_ingredient " +
                "WHERE di.id_dish = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dishId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double cost = rs.getDouble("total_cost");
                    return rs.wasNull() ? 0 : cost;
                }
            }
        }
        return 0;
    }


    public double getDishGrossMargin(int dishId) throws SQLException {
        Dish dish = getDishById(dishId);
        if (dish == null || dish.getSellingPrice() == null) {
            return 0; 
        }
        
        double cost = getDishCost(dishId);
        return dish.getSellingPrice() - cost;
    }


    public List<String> getDishesWithDetails() throws SQLException {
        List<String> details = new ArrayList<>();
        List<Dish> dishes = getAllDishes();
        
        for (Dish dish : dishes) {
            double cost = getDishCost(dish.getIdDish());
            Double sellingPrice = dish.getSellingPrice();
            
            String detail = "Plat: " + dish.getName() + " - Coût: " + cost;
            if (sellingPrice != null) {
                double margin = sellingPrice - cost;
                detail += " - Prix de vente: " + sellingPrice + " - Marge: " + margin;
            } else {
                detail += " - Prix de vente: NULL";
            }
            details.add(detail);
        }
        
        return details;
    }
}
