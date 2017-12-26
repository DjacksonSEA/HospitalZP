package hospital.sea.edu.hospital;

/**
 * Created by User on 04.11.2017.
 */

public class UserMS {

    private String id;
    private String name;
    private String comment;

    public UserMS(){

    }

    public UserMS(String id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
