/**
 * Classe représentant une recette
 */
public class Recipe {
    private int idRecipe;
    private String name;
    private String difficulty;
    private int nbPersons;
    private int nbKcal;

    public Recipe(int idRecipe, String name, String difficulty, int nbPersons, int nbKcal) {
        this.idRecipe = idRecipe;
        this.name = name;
        this.difficulty = difficulty;
        this.nbPersons = nbPersons;
        this.nbKcal = nbKcal;
    }

    public Recipe(String name, String difficulty, int nbPersons, int nbKcal) {
        this.name = name;
        this.difficulty = difficulty;
        this.nbPersons = nbPersons;
        this.nbKcal = nbKcal;
    }

    // Getters et Setters
    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getNbPersons() {
        return nbPersons;
    }

    public void setNbPersons(int nbPersons) {
        this.nbPersons = nbPersons;
    }

    public int getNbKcal() {
        return nbKcal;
    }

    public void setNbKcal(int nbKcal) {
        this.nbKcal = nbKcal;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "idRecipe=" + idRecipe +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", nbPersons=" + nbPersons +
                ", nbKcal=" + nbKcal +
                '}';
    }
}
