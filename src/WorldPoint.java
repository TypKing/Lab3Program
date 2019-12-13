import java.util.Objects;

public class WorldPoint implements StandardMethods {
    @Override
    public String toString() {
        return "Точка мира{" +
                "Локация: " + location +
                ", Объект: " + object +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldPoint that = (WorldPoint) o;
        return location == that.location &&
                Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, object);
    }

    private Locations location;
    private WorldObject object;

    WorldPoint() {
        location = Locations.NotAWorld;
        object = new Ground();
    }

    public void setLocation(int x, int y) {
        if (x <= 4 & y <= 4) this.location = Locations.Door;// Изменяет локацию точки в соответствии с шаблоном
        else if (x >= 34 & y <= 4) this.location = Locations.GazCamera;//
        else if (y <= 4) this.location = Locations.Corridor;//
        else this.location = Locations.Other;//
    }

    public void setObject(WorldObject object) {
        this.object = object;
    }

    public Locations getLocation() {
        return location;
    }

    public WorldObject getObject() {
        return object;
    }
}
