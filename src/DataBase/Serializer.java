package DataBase;

import java.io.*;
import GameMenu.LevelsWindow.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Serializer
{
    public static void main(String[] args){
        /*
        List<Integer> integerList = new ArrayList<>();
        integerList.add(4);
        integerList.add(6);
        integerList.add(3);
        serialize(integerList);

        integerList = deserialize();
        ListIterator iterator = integerList.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        */
    }

    public static void serialize(List<Level> list)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\guyal\\Desktop\\boards.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    public static List<Level> deserialize()
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\guyal\\Desktop\\boards.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Level> list = (List<Level>) in.readObject();
            in.close();
            fileIn.close();
            return list;
        }
        catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}