package th.ac.kmitl.a59070040.friend;

public class Friend {

    private int id;
    private String name;
    private String email;
    private String website;
    private String phone;

    public Friend() {}

    public Friend (int id, String name, String email, String website, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.website = website;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
