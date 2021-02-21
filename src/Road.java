import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Road {
    private int id;
    private String name;
    private int from;
    private int to;
    private List<Integer> through;
    private int speedLimit;
    private int length;
    private boolean biDirectional;

    public Road() {
    }

    public Road(int id) {
        this.id = id;
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

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<Integer> getThrough() {
        if (through==null)
            through = new ArrayList<>();
        return through;
    }

    public void setThrough(List<Integer> through) {
        this.through = through;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isBiDirectional() {
        return biDirectional;
    }

    public void setBiDirectional(boolean biDirectional) {
        this.biDirectional = biDirectional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return id == road.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getTime() {
        int day = length / (speedLimit * 24);
        int hour = (length-day*speedLimit*24) /(speedLimit);
        int min = (length-day*speedLimit*24 - hour * speedLimit) * 60 / speedLimit;
        String result = "";
        result = addToTime(day, result);
        result +=":";
        result = addToTime(hour, result);
        result +=":";
        result = addToTime(min, result);
        return result;
    }

    private static String addToTime(int day, String result) {
        if (day > 9)
            result += day;
        else
            result += "0" + day;
        return result;
    }
}
