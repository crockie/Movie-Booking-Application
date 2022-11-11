package entity;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;

/**
 * The class to read the database and write to the database
 */
public class SerializeDB {
    /**
     * This method reads the database
     * 
     * @param pathName The path of the database
     * @return database The database
     */
    public static DataBase readSerializedObject(String path) {
        DataBase database = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(path);
            in = new ObjectInputStream(fis);
            database = (DataBase) in.readObject();
            in.close();
        } catch (EOFException ex) {
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // print out the size
        // System.out.println(" Database Size: " + database.size());
        // System.out.println();
        return database;
    }

    /**
     * This method writes to the database
     * 
     * @param path     The path of the database
     * @param database The database to be written
     */
    public static void writeSerializedObject(String path, DataBase database) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(path);
            out = new ObjectOutputStream(fos);
            out.writeObject(database);
            out.close();
            // System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
