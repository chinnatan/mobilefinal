package th.ac.kmitl.a59070040.albums;

public class Albums {

    private int id;
    private String title;

    public Albums() {}

    public Albums(int id, String title) {
        this.setId(id);
        this.setTitle(title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
