package applications;

public class MediaApp extends Applications {

    private String type;

    public MediaApp(String name, double price, String type) {
        super(name, price);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Media app " + super.toString() + " is of type " + this.type;
    }
}
