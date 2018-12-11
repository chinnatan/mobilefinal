package th.ac.kmitl.a59070040.photos;

public class Photos {

    private int id;
    private String title;
    private String thumbnail;

    public Photos() {
    }

    public Photos(int id, String title, String thumbnail) {
        this.setId(id);
        this.setTitle(title);
        this.setThumbnail(thumbnail);
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
