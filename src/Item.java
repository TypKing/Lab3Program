public class Item extends WorldObject{
    private Human owner;
    Item(Items name){
        super(name.toString(),Status.Item);
    }
}
