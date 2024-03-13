package src;
import java.io.IOException;
import src.User;

public class Main {

    public static void main(String[] args) throws IOException {
        SchoolSystem system = new SchoolSystem();
        Observable observable = new Observable();
        observable.addObserver(system);
        system.run();
    }
}