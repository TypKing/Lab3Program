abstract public class Item extends WorldObject implements OxygenActions {
    private boolean isMovable;

    Item(String name, int oxygenInc) {
        super(name, Status.Item, oxygenInc);
    }

    public int checkOxygen() {
        return 0;
    }


    public void fillOxygen(int oxygen) {
        setOxygenInc(getOxygenInc() + oxygen);
    }

    public void giveOxygen(WorldObject object, int oxygenInc) {
        if (getOxygenInc() >= oxygenInc) {
            object.setOxygenInc(getOxygenInc() + oxygenInc);
            setOxygenInc(getOxygenInc() - oxygenInc);
        } else System.out.println(getName() + " не хватает кислорода");
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
    }
}
