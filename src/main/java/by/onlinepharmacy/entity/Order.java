package by.onlinepharmacy.entity;


public class Order extends Entity {

    private int id;
    private String name;
    private int medicamentID;
    private String userLogin;
    private String dosage;
    private double count;
    private String country;
    private double price;


    public int getMedicamentID() {
        return medicamentID;
    }


    public void setMedicamentID(int medicamentID) {
        this.medicamentID = medicamentID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

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

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (medicamentID != order.medicamentID) return false;
        if (Double.compare(order.count, count) != 0) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (userLogin != null ? !userLogin.equals(order.userLogin) : order.userLogin != null) return false;
        if (dosage != null ? !dosage.equals(order.dosage) : order.dosage != null) return false;
        return country != null ? country.equals(order.country) : order.country == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + medicamentID;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        temp = Double.doubleToLongBits(count);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", medicamentID=" + medicamentID +
                ", userLogin='" + userLogin + '\'' +
                ", dosage='" + dosage + '\'' +
                ", count=" + count +
                ", country='" + country + '\'' +
                ", price=" + price +
                '}';
    }
}
