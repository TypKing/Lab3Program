import java.util.Objects;

abstract public class WorldObject implements StandardMethods {
    public String toString() {
        return name;
    }

    public int hashCode() {
        return Objects.hash(name, status);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldObject that = (WorldObject) o;
        return Objects.equals(name, that.name) &&
                status == that.status;
    }

    private String name;
    private Status status;
    private int oxygenInc;

    WorldObject(String name, Status status, int oxygenInc) {
        this.name = name;
        this.status = status;
        this.oxygenInc = oxygenInc;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Status getStatus() {
        return status;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    public int getOxygenInc() {
        return oxygenInc;
    }

    public void setOxygenInc(int oxygenInc) {
        this.oxygenInc = oxygenInc;
    }
}
