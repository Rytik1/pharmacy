package by.onlinepharmacy.entity;


import java.util.*;

public class HistoryBuying extends Entity {
    private int id;
    private int medicamentId;
    private int userId;
    private Date dateBuying;
    private double countByuing;
    private String name;
    private String dosage;

    public HistoryBuying() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateBuying() {
        return dateBuying;
    }

    public void setDateBuying(Date dateBuying) {
        this.dateBuying = dateBuying;
    }

    public double getCountByuing() {
        return countByuing;
    }

    public void setCountByuing(double countByuing) {
        this.countByuing = countByuing;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    @Override
    public String toString() {
        return "HistoryBuying{" +
                "id=" + id +
                ", medicamentId=" + medicamentId +
                ", userId=" + userId +
                ", dateBuying=" + dateBuying +
                ", countByuing=" + countByuing +
                ", name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryBuying that = (HistoryBuying) o;

        if (id != that.id) return false;
        if (medicamentId != that.medicamentId) return false;
        if (userId != that.userId) return false;
        if (Double.compare(that.countByuing, countByuing) != 0) return false;
        if (dateBuying != null ? !dateBuying.equals(that.dateBuying) : that.dateBuying != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return dosage != null ? dosage.equals(that.dosage) : that.dosage == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + medicamentId;
        result = 31 * result + userId;
        result = 31 * result + (dateBuying != null ? dateBuying.hashCode() : 0);
        temp = Double.doubleToLongBits(countByuing);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        return result;
    }
}
