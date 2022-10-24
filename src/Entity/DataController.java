package Entity;

public class DataController {
    private static DataBase database;
    private static String filename = "";

    public DataController() {
    }

    public static void init() {
        database = new DataBase();
    }

    public static void load() {
        Object newObj = SerializeDB.readSerializedObject(filename);

        if (newObj == null || !(newObj instanceof DataBase))
            init();
        else
            database = (Database) newObj;
    }

    public static void update() {
        SerializeDB.writeSerializedObject(filename, database);
    }

    public DataBase getDataBase() {
        return database;
    }

}