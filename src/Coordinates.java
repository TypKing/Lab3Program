import java.util.Objects;

public class Coordinates {
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }

    int x;
    int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
