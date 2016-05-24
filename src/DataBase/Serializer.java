package DataBase;

import com.google.gson.Gson;

import java.io.*;
import java.util.Vector;


public class Serializer
{
    public static void serialize(Vector vector)
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

    public static Vector deserialize()
    {
        Gson gson = new Gson();
        Vector vector = null;
        try {
            File file = new File("C:\\Users\\guyal\\Desktop\\levels.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String jsonString = new String(data, "UTF-8");
            vector = gson.fromJson(jsonString, Vector.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
}