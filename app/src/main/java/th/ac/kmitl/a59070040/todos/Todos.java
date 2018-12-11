package th.ac.kmitl.a59070040.todos;

public class Todos {

    private int id;
    private String title;
    private boolean completed;

    public Todos() {

    }

    public Todos(int id, String title, boolean completed) {
        this.setId(id);
        this.setTitle(title);
        this.setCompleted(completed);
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
