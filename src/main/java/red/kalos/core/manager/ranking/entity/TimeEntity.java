package red.kalos.core.manager.ranking.entity;

import org.jetbrains.annotations.NotNull;

public class TimeEntity implements Comparable<TimeEntity>{
    private String name;
    private long time;
    private String date;

    public TimeEntity(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(@NotNull TimeEntity o) {
        int result;
        result = (int) (this.time-o.getTime());

        return result;
    }
}
