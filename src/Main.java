public class Main {
    public static void main(String[] args){
        World world = new World();
        ShortHuman CourierTwo = new ShortHuman("Винтик",Status.Courier);
        world.addtoworld(CourierTwo,1,1);
        ShortHuman Courier = new ShortHuman("Шпунтик",Status.Courier);
        world.addtoworld(Courier,1,2);
        ShortHuman Pacient = new ShortHuman("Незнайка",Status.Pacient);
        world.addtoworld(Pacient,0,1);
        ShortHuman Doctor = new ShortHuman("Пилюлькин",Status.Doctor);
        world.addtoworld(Doctor,1,3);
        Item Gaz = new Item(Items.OxygenBallon);
        world.addtoworld(Gaz,5,3);
        Item Pad = new Item(Items.Rubberpad);
        CourierTwo.give(Pad);
        Pacient.setStatus(Status.Pacient);
        Doctor.check(world);
    }
}
