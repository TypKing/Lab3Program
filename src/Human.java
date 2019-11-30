abstract public class Human extends WorldObject implements WalkActions,Actions{
    private double Height;
    private Item[] inventory = new Item[5];
    private int stackinv = 0;
    Human(String name, double h, Status status) {
        super(name,status);
        this.Height = h;
    }
    void check(World world){
        if(getStatus().equals(Status.Doctor)){
            for (int i=0;i<3;i++)
                for (int j=0;j<3;j++){
                }
        }
    }
    public boolean give(Item item){
        if(stackinv<5) {
            inventory[stackinv] = item;
            stackinv++;
            System.out.println(item.getName()+" был добавлен персонажу " + getName());
            return true;
        }
        else{
            System.out.println("Инвентарь полон!");
            return false;
        }
    }
    public void moveto(World world,WorldObject object){
        //TODO Перемещение персонажей
    };
    public WorldObject Checking(Status status){
        return null;
    };
    public void Heal(WorldObject pacient){};
    public void Respiration(WorldObject pacient){};
    public void HandsUpandDown(WorldObject pacient){};
    public Status CheckPacient(WorldObject pacient){
        return null;
    };
    public boolean CheckBreath(WorldObject pacient){
        return false;
    };
    public void commandGaz(WorldObject courier){};
    public void BringGaz(WorldObject pacient){};
    public void fill(Item item){};
    public void follow(WorldObject obj){
        System.out.println("Следит за " + obj.getName());
    };
}
