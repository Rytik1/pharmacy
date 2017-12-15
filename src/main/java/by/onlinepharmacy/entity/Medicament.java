package by.onlinepharmacy.entity;


public class Medicament extends Entity {
    private int id;
    private String name;
    private String dosage;
    private double amountInStock;
    private String country;
    private String imagePath;
    private double price;
    private boolean recipeRequired;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(double amountInStock) {
        this.amountInStock = amountInStock;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRecipeRequired() {
        return recipeRequired;
    }

    public void setRecipeRequired(boolean recipeRequired) {
        this.recipeRequired = recipeRequired;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", amountInStock=" + amountInStock +
                ", country='" + country + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price=" + price +
                ", recipeRequired=" + recipeRequired +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicament that = (Medicament) o;

        if (id != that.id) return false;
        if (Double.compare(that.amountInStock, amountInStock) != 0) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (recipeRequired != that.recipeRequired) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (dosage != null ? !dosage.equals(that.dosage) : that.dosage != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return imagePath != null ? imagePath.equals(that.imagePath) : that.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        temp = Double.doubleToLongBits(amountInStock);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (recipeRequired ? 1 : 0);
        return result;
    }
}
