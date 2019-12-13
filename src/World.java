import java.util.Arrays;
import java.util.Objects;

public class World implements StandardMethods {

    public String toString() {
        return name;
    }

    public int hashCode() {
        int result = Objects.hash(name, sizeY, sizeX);
        result = 31 * result + Arrays.hashCode(points);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return sizeY == world.sizeY &&
                sizeX == world.sizeX &&
                Objects.equals(name, world.name) &&
                Arrays.equals(points, world.points);
    }

    private String name;
    private int sizeY;
    private int sizeX;
    private WorldPoint[][] points;

    World(String name) {
        this.name = name;
        this.sizeY = 6;
        this.sizeX = 42;
        this.points = new WorldPoint[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++) {
                if (i == 0 || j == 0 || i == sizeX - 1 || j == sizeY - 1) {
                    this.points[i][j] = new WorldPoint();
                    points[i][j].getObject().setStatus(Status.Wall);
                } else this.points[i][j] = new WorldPoint();
                points[i][j].setLocation(i, j);
            }
        System.out.println(name + " создан");

    }

    public void addToWorld(Human human, int x, int y) {
        if (x < sizeX && y < sizeY && points[x][y].getObject().getClass().equals(Ground.class)) {
            points[x][y].setObject(human);
            points[x][y].setLocation(x, y);
            System.out.println("Персонаж " + human.getName() + " был добавлен в локацию " + points[x][y].getLocation());
        } else if (x < sizeX & y < sizeY) System.out.println("Ты не можешь поместить персонажа на другого персонажа!");
        else System.out.println("Ты не можешь поместить персонажа за пределы мира!");
    }

    public void addToWorld(Item item, int x, int y) {
        if (x < sizeX && y < sizeY && points[x][y].getObject().getClass().equals(Ground.class)) {
            points[x][y].setObject(item);
            points[x][y].setLocation(x, y);
            System.out.println("Предмет " + item.getName() + " был добавлен в локацию " + points[x][y].getLocation());
        } else if (x < sizeX & y < sizeY) System.out.println("Ты не можешь поместить персонажа на другого персонажа!");
        else System.out.println("Ты не можешь поместить персонажа за пределы мира!");
    }

    public Coordinates find(WorldObject object) {
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++)
                if (this.points[i][j].getObject().equals(object))
                    return new Coordinates(i, j);
        return new Coordinates(-1, -1);
    }

    public Coordinates find(Status status) {
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++)
                if (this.points[i][j].getObject().getStatus().equals(status))
                    return new Coordinates(i, j);
        return new Coordinates(-1, -1);
    }

    public WorldPoint getPoint(Coordinates coordinates) {
        if (coordinates.x >= 0 && coordinates.y >= 0 && coordinates.x < sizeX && coordinates.y < sizeY)
            return points[coordinates.x][coordinates.y];
        else {
            return null;
        }
    }

    public void setPosition(WorldObject obj, Coordinates coordinates) {
        if (points[coordinates.x][coordinates.y].getObject().getStatus().equals(Status.Ground)) {
            if (!points[coordinates.x][coordinates.y].getLocation().equals(points[find(obj).x][find(obj).y].getLocation())) {
                System.out.println(obj.getName() + " переместился в " + points[coordinates.x][coordinates.y].getLocation());
            }
            Coordinates coordinates1 = find(obj);
            points[coordinates.x][coordinates.y].setObject(obj);
            points[coordinates1.x][coordinates1.y].setObject(new Ground());
        }
        //map();
    }

    public void map() {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (points[j][i].getObject().getStatus().equals(Status.Ground)) System.out.print(" . ");
                if (points[j][i].getObject().getStatus().equals(Status.Patient)) System.out.print(" P ");
                if (points[j][i].getObject().getStatus().equals(Status.Doctor)) System.out.print(" D ");
                if (points[j][i].getObject().getStatus().equals(Status.Item)) System.out.print(" I ");
                if (points[j][i].getObject().getStatus().equals(Status.Courier)) System.out.print(" C ");
                if (points[j][i].getObject().getStatus().equals(Status.Wall)) System.out.print(" ▒ ");
                if (points[j][i].getObject().getStatus().equals(Status.Normal)) System.out.print(" H ");
            }
            System.out.println("");
        }
    }

    public void start(Human doctor, Human courier, Human courier2, Human patient, Item Gaz, Human... humans) {
        courier.goToObjectWith(courier2, this, Gaz);
        if (courier.check(this, Gaz)) Gaz.giveOxygen(courier.getItem(), 100);
        doctor.heal(this, humans);
        courier.goToObjectWith(courier2, this, patient);
        doctor.command(courier, patient);
        if (patient.getOxygenInc() >= 100 && humans.length > 1) System.out.println("Коротышки с облегчением заметили " +
                "как страшная бледность стала спадать с лица " + patient.getName());
        if (patient.getOxygenInc() >= 100) System.out.println(patient.getName() + " открыл глаза");
        else System.out.println(patient.getName() + " был хорошим коротышкой, помянем.");
    }
}
