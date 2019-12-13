public interface HumanActions {
    void respiration(WorldObject object);

    void heal(World world, Human... humans);

    void goToObjectWith(Human object, World world, WorldObject worldObject);

    void giveItem(Item item);
}
