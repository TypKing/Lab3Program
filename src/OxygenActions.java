public interface OxygenActions {
    int checkOxygen();

    void fillOxygen(int oxygen);

    void giveOxygen(WorldObject object, int oxygen);
}
