public class Rat extends Animal{
    public String bonus = "Rat";

    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public String getVoise(){
        return this.voise;
    }
    public void uniq(){
        System.out.println("its Rat");
    }

    public Rat(String name,String  voice) {
        this.name = name;
        this.voise = voice;
    }
}
