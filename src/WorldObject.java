abstract public class WorldObject {
    private String name;
    private Status status;
    WorldObject(String name,Status status) {
        this.name = name;
        this.status = status;
    }
    String getName() {
        return name;
    }
    Status getStatus(){
        return status;
    }
    void  setStatus(Status status){
        if(status.equals(Status.Normal) && this.status.equals(Status.Pacient)){
            this.status = status;
            System.out.println("Страшная бледность стала спадать с " + getName() + ". И он открыл глаза.");
        }
        this.status = status;
    }
}
