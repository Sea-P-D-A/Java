public abstract class Animal {

    public String name;
    protected String voise;
    private String type = "Animal";

    public abstract String getName();
    public abstract String getVoise();

    public String getType() {
        return type;
    }
}
