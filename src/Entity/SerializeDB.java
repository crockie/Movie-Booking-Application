package Entity;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;

public class SerializeDB {
    public static Object readSerializedObject(String filename) {
        Object myObj = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            myObj = in.readObject();
            in.close();
        } catch (EOFException ex) {
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // print out the size
        // System.out.println(" Details Size: " + pDetails.size());
        // System.out.println();
        return myObj;
    }

    public static void writeSerializedObject(String filename, Object myObj) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(myObj);
            out.close();
            // System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}