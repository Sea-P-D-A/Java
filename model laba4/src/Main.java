import repository.ProfileFileRepository;
import service.CheckSumService;
import ui.ConsoleInterface;
import service.ProfileService;



public class Main {
    public static void main(String[] args) {


        ConsoleInterface consoleinterface = new ConsoleInterface
                (new ProfileService(new CheckSumService(), new ProfileFileRepository()));
        consoleinterface.start();
    }
}