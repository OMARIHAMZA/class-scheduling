public class GroupStudent {
    String name;
    Boolean theoretical;

    public GroupStudent( Boolean theoretical) {
        this.theoretical = theoretical;
    }

    public GroupStudent(String name) {
        this.name = name;
        this.theoretical = false;
    }
}
