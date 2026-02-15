public class Dog extends Animal{

    public String bonus = "dog";

    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public String getVoise(){
        return this.voise;
    }
    public void uniq(){
        System.out.println("its Dog");
    }
    public Dog(String name,String  voice) {
        this.name = name;
        this.voise = voice;
    }
}
