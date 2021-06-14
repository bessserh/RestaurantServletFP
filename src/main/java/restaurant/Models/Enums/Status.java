package restaurant.Models.Enums;

public enum Status {
    MAKING(0),
    APPROVING(1),
    COOKING(2),
    DELIVERING(3),
    CLOSED(4),
    CANCELED(5);

    private Integer id;

    Status(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    public static Status findStatusById(Integer id){
        for(Status s : Status.values()){
            if(s.getId().equals(id)) return s;
        }
        return null;
    }


    @Override
    public String toString() {
        return this.name();
    }
}