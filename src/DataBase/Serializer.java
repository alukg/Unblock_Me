package DataBase;

import GameComponents.Level;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Vector;

/**
 * The class contain the serialize and deserialize actions with gson.
 */
public class Serializer
{
    /**
     * Serialize function.
     * @param vector the DB of levels.
     */
    public static void serialize(Vector<Level> vector)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(vector); //convert by gson the vector to string of gson.
        try{ //save this string to file.
            File file = new File("levels.txt");
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.write(jsonString);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deSerialize function
     * @return DB of the levels.
     */
    public static Vector<Level> deserialize()
    {
        Gson gson = new Gson();
        Vector<Level> vector = null;
        try {
            File file = new File("levels.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data); //read the gson string that represents the vector object from the file.
            fis.close();
            String jsonString = new String(data, "UTF-8");
            Type type = new TypeToken<Vector<Level>>(){}.getType();
            vector = gson.fromJson(jsonString, type); //convert the string to vector object of levels.
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
}