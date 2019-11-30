public class World {
    private int sizey;
    private int sizex;
    private String[][] ground;
    private Status[][] status;
    World(){
        this.sizey = 4;
        this.sizex = 40;
        this.ground = new String[sizex][sizey];
        this.status = new Status[sizex][sizey];
        for(int i=0;i<sizex;i++)
            for(int j=0;j<sizey;j++){
                ground[i][j] = "";
                status[i][j] = Status.Ground;
            }
        System.out.println("МИР СОЗДАН!");

    }
    public void addtoworld(ShortHuman human, int x, int y){
        if (x<sizex && y<sizey && ground[x][y].equals("")) {
            this.ground[x][y] = human.getName();
            this.status[x][y] = human.getStatus();
            System.out.println("Персонаж " + human.getName() + " был добавлен в локацию " + getLocation(human.getName()));
        }
        else if(x<sizex & y<sizey)System.out.println("Ты не можешь поместить персонажа на другого персонажа!");
        else System.out.println("Ты не можешь поместить персонажа за пределы мира!");
    }
    public void addtoworld(Item item, int x, int y) {
        if (x < sizex && y < sizey && ground[x][y].equals("")) {
            this.ground[x][y] = item.getName();
            this.status[x][y] = item.getStatus();
            System.out.println("Предмет " + item.getName() + " был добавлен в локацию " + getLocation(item.getName()));
        } else if (x < sizex & y < sizey) System.out.println("Ты не можешь поместить персонажа на другого персонажа!");
        else System.out.println("Ты не можешь поместить персонажа за пределы мира!");
    }
    public Locations getLocation(String name) {
        for (int i = 0; i<sizex; i++)
            for (int j = 0;j<sizey;j++)
                if (ground[i][j].equals(name))
                    if(i<=3 & j<=3) return Locations.Door;
                    else if(i<=4 & j<=3) return Locations.Corridor;
                    else if(i<=6 & j<=3) return Locations.GazCamera;
                    else return Locations.Other;
        return Locations.NotAWorld;
    }
    public Coordinates find(Status status){
        for (int i = 0; i<sizex; i++)
            for (int j = 0;j<sizey;j++)
                if (this.status[i][j].equals(status))
                    return new Coordinates(i,j);
        return new Coordinates(-1,-1);
    }
    public boolean setPosition(WorldObject obj,Coordinates coor){
        //TODO  Тут будет изменение координат обьекта
        return false;
    }
}
