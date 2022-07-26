package bean;

public class Country {
    private int id;
    private String name;
    private String nationality;

    public Country() {

    }

    public Country(int id, String name, String countryName) {
        this.id = id;
        this.name = name;
        this.nationality = countryName;
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

    public String getCountryName() {
        return nationality;
    }

    public void setCountryName(String countryName) {
        this.nationality = countryName;
    }

    @Override
    public String toString() {
        return "Nationality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryName='" + nationality + '\'' +
                '}';
    }


}
