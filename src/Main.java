public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!\n");
        Dog dog = new Dog("BOB", "voise1");
        Rat rat = new Rat("Krisa", "voise2");
        Animal mix = new Dog("BOB", "voise3");
        Sova sova = new Sova();

        System.out.println("Name: " + dog.getName() + " Voise: " + dog.getVoise() + " Type: " + dog.getType() + " Hash: " + dog.hashCode());
        System.out.println("Name: " + rat.getName() + " Voise: " + rat.getVoise() + " Type: " + rat.getType() + " Hash: " + rat.hashCode());
        System.out.println("Name: " + mix.getName() + " Voise: " + mix.getVoise() + " Type: " + mix.getType() + " Hash: " + mix.hashCode());
        dog.name = "Wait";

        System.out.println("dog posle rename " + dog.hashCode() + " Name: " + dog.getName());


        System.out.println("DOG uniq");
        dog.uniq();
        System.out.println("RAT uniq");
        rat.uniq();

        System.out.println("DOG bonus " + dog.bonus);
        System.out.println("RAT bonus " + rat.bonus);

        System.out.println(sova.getName() + ' ' + sova.getVoise() + ' ' + sova.getType());


    }
}