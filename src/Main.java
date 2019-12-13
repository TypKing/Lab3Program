public class Main {
    public static void main(String[] args) {
        World world = new World("Корабль");
        ShortHuman Courier = new ShortHuman("Винтик", Status.Courier);
        world.addToWorld(Courier, 2, 1);
        ShortHuman CourierTwo = new ShortHuman("Шпунтик", Status.Courier);
        world.addToWorld(CourierTwo, 2, 2);
        ShortHuman Patient = new ShortHuman("Незнайка", Status.Patient);
        world.addToWorld(Patient, 1, 3);
        ShortHuman Doctor = new ShortHuman("Пилюлькин", Status.Doctor);
        world.addToWorld(Doctor, 2, 3);
        ShortHuman SomePeople = new ShortHuman("Бублик", Status.Normal);
        world.addToWorld(SomePeople, 1, 4);
        ShortHuman SomePeopleTwo = new ShortHuman("Пончик", Status.Normal);
        world.addToWorld(SomePeopleTwo, 1, 2);
        Item Gaz = new GazBallon(1000);
        world.addToWorld(Gaz, 40, 4);
        Item Pad = new RubberPad(0);
        Courier.giveItem(Pad);
        CourierTwo.giveItem(Pad);
        //world.map();
        world.start(Doctor, Courier, CourierTwo, Patient, Gaz, SomePeople, SomePeopleTwo);
        Patient.setStatus(Status.Patient);
    }
}
