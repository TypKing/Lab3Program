abstract public class Human extends WorldObject implements WalkActions, HumanActions {
    private double height;
    private Item[] inventory = new Item[5];
    private int stackInv = 0;

    Human(String name, double h, Status status) {
        super(name, status, 0);
        this.height = h;
        if (status.equals(Status.Patient)) setOxygenInc(0);
        else setOxygenInc(100);
    }

    public void heal(World world, Human... humans) { // Метод доктора для лечения
        WorldObject patient = world.getPoint(world.find(Status.Patient)).getObject();
        if (check(world, patient)) {
            moveto(world, world.find(Status.Patient)); // Перемещение к пациенту
            System.out.print("Не теряя ни секунды времени: ");
            respiration(patient); // Реанимация путем искуственного дыхания
            if (humans.length > 1) {
                System.out.println("Коротышки c тревогой наблюдают");
            }
            for (int i = 1; i < 50; i++) {
                if (!checkHeartbeat(patient)) {
                    System.out.print("Продолжает: ");
                    respiration(patient); // Реанимация путем искуственного дыхания
                    if (humans.length > 1) {
                        checkTime(i);
                    }
                } else break;
            }
            if (checkHeartbeat(patient)) {
                System.out.println(getName() + " насторожился но продолжает");
                while (!checkBreath(patient)) {
                    respiration(patient);
                }
            }
        }
    }

    public void command(Human human, Human patient) {
        System.out.println("Велел понемногу выпускать кислород из трубочки у рта больного");
        human.getItem().giveOxygen(patient, 100);
    }

    public void checkTime(int i) {
        if (i >= 1) {
            System.out.println("Никто не знал сколько прошло времени");
            System.out.println("Всем казалось что очень много");
        }
    }

    public void handing(WorldObject object) {
        object.setOxygenInc(object.getOxygenInc() + 3);
        System.out.println("Ритмически поднимает и опускает руки и прижимает их плотно к груди " + object.getName());
    }

    public boolean checkHeartbeat(WorldObject worldObject) {
        if (worldObject.getStatus().equals(Status.Ground) || worldObject.getStatus().equals(Status.Wall) || worldObject.getStatus().equals(Status.Item)) {
            System.out.println(getName() + " попытался проверить биение сердца у " + worldObject.getName() + " по-моему ему пора в дурку");
            return false;
        } else {
            System.out.println(getName() + " прислонивший ухом к груди " + worldObject.getName() + " попытался уловить биение сердца");
            if (worldObject.getOxygenInc() >= 6) {
                System.out.println(getName() + " послышалось что: " + worldObject.getName() + " вздохнул");
                return true;
            } else {
                System.out.println("Биения нет");
                return false;
            }
        }
    }

    public boolean checkBreath(WorldObject worldObject) {
        if (worldObject.getStatus().equals(Status.Ground) || worldObject.getStatus().equals(Status.Wall) || worldObject.getStatus().equals(Status.Item)) {
            System.out.println(getName() + " попытался проверить дыхание у " + worldObject.getName() + " по-моему ему пора в дурку");
            return false;
        } else {
            if (worldObject.getOxygenInc() >= 9) {
                System.out.println(getName() + " убедился, что дыхание восстановилось у: " + worldObject.getName());
                return true;
            } else {
                return false;
            }
        }
    }

    public void goToObjectWith(Human human, World world, WorldObject worldObject) {//Доставка кислорода вместе с
        if (getStatus().equals(Status.Courier) && human.getStatus().equals(Status.Courier)) {// напарником
            System.out.println(getName() + " и " + human.getName() + " помчались в " + world.getPoint(world.find(worldObject)).getLocation() + " где находился: " + worldObject.getName());
            moveto(world, worldObject);//перемещение к нужному объекту
            human.moveto(world, worldObject);//перемещение к нужному объекту напарника
        } else System.out.println("Мы не будем РАБАМИ!");
    }

    public void respiration(WorldObject object) {
        System.out.println(getName() + " делает искуственное дыхание " + object.getName());
        handing(object);
    }

    boolean check(World world, WorldObject worldObject) {
        for (int i = world.find(this).x - 1; i < world.find(this).x + 2; i++)
            for (int j = world.find(this).y - 1; j < world.find(this).y + 2; j++) {
                if (world.getPoint(new Coordinates(i, j)).getObject().equals(worldObject))
                    return true;
            }
        return false;
    }

    public void giveItem(Item item) {
        if (stackInv < 5 && item.isMovable()) {
            inventory[stackInv] = item;
            stackInv++;
            System.out.println(getName() + " схватил " + item.getName());
        } else if (item.isMovable()) {
            System.out.println("Инвентарь полон!");
        } else {
            System.out.println("Предмет слишком тяжелый!");
        }
    }

    public Item getItem() {
        return inventory[stackInv - 1];
    }

    public void moveto(World world, WorldObject object) {
        for (int i = world.find(object).x - 1; i <= world.find(object).x + 1; i++) {
            for (int j = world.find(object).y - 1; j <= world.find(object).y + 1; j++) {
                if (world.getPoint(new Coordinates(i, j)).getObject().getStatus().equals(Status.Ground)) {
                    world.setPosition(this, new Coordinates(i, j));
                    System.out.println(getName() + " пришел к " + object.getName());
                    return;
                }
            }
        }
        System.out.println(getName() + " не может встать рядом с " + object.getName());
    }

    public void moveto(World world, Coordinates coordinates) {
        for (int i = coordinates.x - 1; i <= coordinates.x + 1; i++) {
            for (int j = coordinates.y - 1; j <= coordinates.y + 1; j++) {
                if (world.getPoint(new Coordinates(i, j)).getObject().getStatus().equals(Status.Ground)) {
                    world.setPosition(this, new Coordinates(i, j));
                    System.out.println(getName() + " пришел к " + world.getPoint(coordinates).getObject().getName());
                    return;
                }
            }
        }
    }
}
