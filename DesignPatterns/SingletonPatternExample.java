import java.util.ArrayList;
import java.util.List;

class Logger {
    private static volatile Logger instance;
    private List<String> logMessages;

    private Logger() {
        logMessages = new ArrayList<>();
        System.out.println("Logger instance created.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        String entry = "[LOG] " + message;
        logMessages.add(entry);
        System.out.println(entry);
    }

    public void info(String message) {
        String entry = "[INFO] " + message;
        logMessages.add(entry);
        System.out.println(entry);
    }

    public void warn(String message) {
        String entry = "[WARN] " + message;
        logMessages.add(entry);
        System.out.println(entry);
    }

    public void error(String message) {
        String entry = "[ERROR] " + message;
        logMessages.add(entry);
        System.out.println(entry);
    }

    public int getMessageCount() {
        return logMessages.size();
    }
}

public class SingletonPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Test ===\n");

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println("\n--- Test 1: Same Instance Check ---");
        System.out.println("logger1 == logger2 ? " + (logger1 == logger2));

        System.out.println("\n--- Test 2: Shared State ---");
        logger1.info("Message from logger1");
        logger2.warn("Message from logger2");
        logger1.error("Another from logger1");

        System.out.println("\nTotal messages (via logger1): " + logger1.getMessageCount());
        System.out.println("Total messages (via logger2): " + logger2.getMessageCount());
        System.out.println("Same count? " + (logger1.getMessageCount() == logger2.getMessageCount()));

        System.out.println("\n--- Test 3: Multi-Component Logging ---");
        simulateUserService();
        simulateOrderService();
        System.out.println("Total messages after all components: " + Logger.getInstance().getMessageCount());

        System.out.println("\n=== All tests passed! ===");
    }

    static void simulateUserService() {
        Logger logger = Logger.getInstance();
        logger.info("UserService: User logged in");
    }

    static void simulateOrderService() {
        Logger logger = Logger.getInstance();
        logger.info("OrderService: Order placed");
    }
}
