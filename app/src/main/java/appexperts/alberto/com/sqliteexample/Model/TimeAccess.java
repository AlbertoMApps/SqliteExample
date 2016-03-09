package appexperts.alberto.com.sqliteexample.Model;

public class TimeAccess {

    private int id;
    private String title;
    private int time;



    public TimeAccess(String title, int time, int id) {
        this.id = id;

        this.title = title;
        this.time = time;
    }

    public TimeAccess(){
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }
}
