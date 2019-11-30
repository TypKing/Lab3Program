public interface Actions {
    WorldObject Checking(Status status);
    void Heal(WorldObject pacient);
    void Respiration(WorldObject pacient);
    void HandsUpandDown(WorldObject pacient);
    Status CheckPacient(WorldObject pacient);
    boolean CheckBreath(WorldObject pacient);
    void commandGaz(WorldObject courier);
    void BringGaz(WorldObject pacient);
    void fill(Item item);
    void follow(WorldObject obj);
}
