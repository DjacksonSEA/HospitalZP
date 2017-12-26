package hospital.sea.edu.hospital;

/**
 * Created by User on 02.11.2017.
 */

public class ItemFNameList {

    private int id;
    private String name;
    private String street;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public ItemFNameList(int id, String name, String street) {
        this.id = id;
        this.name = name;
        this.street = street;
    }
}
