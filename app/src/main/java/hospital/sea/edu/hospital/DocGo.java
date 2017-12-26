package hospital.sea.edu.hospital;

/**
 * Created by User on 09.11.2017.
 */

public class DocGo {
    private String id;
    private String name;
    private String born;
    private String phone;
    private String doc;

    public DocGo() {
    }

    public DocGo(String id, String name, String born, String phone, String doc) {
        this.id = id;
        this.name = name;
        this.born = born;
        this.phone = phone;
        this.doc = doc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
