package DataBase;

import GameComponents.Level;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Vector;

public class Serializer
{
    public static void serialize(Vector<Level> vector)
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(vector);
        try{
            File file = new File("C:\\Users\\guyal\\Desktop\\levels.txt");
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.write(jsonString);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<Level> deserialize()
    {
        Gson gson = new Gson();
        Vector<Level> vector = null;
        try {
            File file = new File("C:\\Users\\guyal\\Desktop\\levels.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String jsonString = new String(data, "UTF-8");
            Type type = new TypeToken<Vector<Level>>(){}.getType();
            vector = gson.fromJson(jsonString, type);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
}