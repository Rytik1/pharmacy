package by.onlinepharmacy.entity;

import java.sql.Date;


public class Recipe extends Entity {

    //inner information in pharmacy to check number recipe from user and Ministry of Health
    private int id;
    private String numberReceipt;
    private int medicamentId;
    private Date validityRecipe;
    private String name;
    private String dosage;
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberReceipt(String numberReceipt) {
        this.numberReceipt = numberReceipt;
    }

    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
    }

    public void setValidityRecipe(Date validityRecipe) {
        this.validityRecipe = validityRecipe;
    }


    public int getId() {
        return id;
    }

    public String getNumberReceipt() {
        return numberReceipt;
    }

    public int getMedicineId() {
        return medicamentId;
    }

    public Date getValidityRecipe() {
        return validityRecipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (id != recipe.id) return false;
        if (medicamentId != recipe.medicamentId) return false;
        if (state != recipe.state) return false;
        if (numberReceipt != null ? !numberReceipt.equals(recipe.numberReceipt) : recipe.numberReceipt != null)
            return false;
        if (validityRecipe != null ? !validityRecipe.equals(recipe.validityRecipe) : recipe.validityRecipe != null)
            return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        return dosage != null ? dosage.equals(recipe.dosage) : recipe.dosage == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numberReceipt != null ? numberReceipt.hashCode() : 0);
        result = 31 * result + medicamentId;
        result = 31 * result + (validityRecipe != null ? validityRecipe.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 31 * result + (state ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", numberReceipt='" + numberReceipt + '\'' +
                ", medicineId=" + medicamentId +
                ", validityRecipe=" + validityRecipe +

                '}';
    }
}
