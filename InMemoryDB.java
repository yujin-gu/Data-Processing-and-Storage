import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InMemoryDB {
    private Map<String, Integer> db;
    private Map<String, Integer> transactionSnapshot;
    private boolean transactionInProgress;

    public InMemoryDB() {
        db = new HashMap<>();
        transactionSnapshot = new HashMap<>();
        transactionInProgress = false;
    }

    public Integer get(String key) {
        if (db.containsKey(key)) {
            return db.get(key);
        }
        return null;
    }

    public void put(String key, int val) {

        if (!transactionInProgress) {
            try {

                throw new RuntimeException("No ongoing transaction to rollback");
                // Other code within the transaction
            } catch (RuntimeException e) {
                // Handle the exception here
                System.out.println("Caught RuntimeException: " + e.getMessage());
            }
        }
        else
            transactionSnapshot.put(key, val);

    }

    public void beginTransaction() {
        if (transactionInProgress) {
            try{
            throw new RuntimeException("Transaction already in progress");
            }
            catch (RuntimeException e) {
                // Handle the exception here
                System.out.println("Caught RuntimeException: " + e.getMessage());
            }
        }
        else{
            transactionInProgress = true;
            transactionSnapshot = new HashMap<>(db);

        }

    }

    public void commit() {
        if (!transactionInProgress) {
            try{
            throw new RuntimeException("No open transaction to commit");
            }
            catch (RuntimeException e) {
                // Handle the exception here
                System.out.println("Caught RuntimeException: " + e.getMessage());
            }

        }
        else{
            db.putAll(transactionSnapshot);
            transactionInProgress = false;
            transactionSnapshot.clear();
        }

    }

    public void rollback() {
        if (!transactionInProgress) {
            try{
            throw new RuntimeException("No ongoing transaction to rollback");
            }
            catch (RuntimeException e) {
                // Handle the exception here
                System.out.println("Caught RuntimeException: " + e.getMessage());
            }
        }
        else{

            transactionSnapshot.clear();
        }

    }

    public static void main(String[] args) {
        InMemoryDB inMemoryDB = new InMemoryDB();
        boolean programRun = true;
        // Testing
        System.out.println(inMemoryDB.get("A")); // should return null
        inMemoryDB.put("A", 5);

        inMemoryDB.beginTransaction();
        inMemoryDB.put("A", 5);
        System.out.println(inMemoryDB.get("A")); // should return null
        inMemoryDB.put("A", 6);
        inMemoryDB.commit();
        System.out.println(inMemoryDB.get("A")); // should return 6
// throws an error, because there is no open transaction
        inMemoryDB.commit();
        inMemoryDB.rollback();
        System.out.println(inMemoryDB.get("B"));

        inMemoryDB.beginTransaction();
        inMemoryDB.put("B", 10);
        inMemoryDB.rollback();
        inMemoryDB.commit();
        System.out.println(inMemoryDB.get("B")); // should return null


        inMemoryDB.beginTransaction();
        inMemoryDB.put("B", 10);
        inMemoryDB.commit();
        System.out.println(inMemoryDB.get("B")); // should return null
        System.out.println(inMemoryDB.get("A")); // should return 6

        //User input menu
        while(programRun){
            System.out.println("In Memory Database Menu");
            System.out.println("---------------------");
            System.out.println("1. Start Transaction");
            System.out.println("2. Store/Update data into database");
            System.out.println("3. Get Stored Data");
            System.out.println("4. Rollback changes");
            System.out.println("5. Commit Changes");
            System.out.println("6. Exit");
            System.out.println("---------------------");


            System.out.println("Type the number of the command you wish to execute: ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();


            switch (userInput) {
                case "1":
                    inMemoryDB.beginTransaction();
                    break;
                case "2":
                    System.out.println("Enter key: ");
                    String key = scanner.nextLine();
                    System.out.println("Enter value: ");
                    String value = scanner.nextLine();
                    inMemoryDB.put(key, Integer.parseInt(value));
                    break;
                case "3":
                    System.out.println("Enter key: ");
                    key = scanner.nextLine();
                    System.out.println(inMemoryDB.get(key));
                    break;
                case "4":
                    System.out.println("Rollback all changes...");
                    inMemoryDB.rollback();
                    break;
                case "5":
                    System.out.println("Commit all changes...");
                    inMemoryDB.commit();
                    break;
                case "6":
                    System.out.println("Exit database...");
                    programRun = false;
                    break;
                default:

                    System.out.println("Invalid Input, please enter a valid input");
                    break;


            }

        }

    }
}
