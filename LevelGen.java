/**
 * Reads a .txt file to generate a level
 */

import java.util.*;
import java.io.*;
import java.io.FileWriter;		
import java.io.BufferedWriter;

public class LevelGen{

    int[] f1;

    /*
     * Reads the file in which the "level" is stored in
     */
    public int[] readFile(String name){
        try{
            
            File file1 = new File(name);
            FileReader fin1 = new FileReader(file1);
            BufferedReader reader1 = new BufferedReader(fin1);
            String[] count1 = (reader1.readLine()).split(" ");
            f1 = new int[count1.length];
            for (int i = 0; i < count1.length; i++) {
                f1[i] = (Integer.parseInt(count1[i]));
            }
            reader1.close();
            fin1.close();

        } catch(FileNotFoundException e) {
            System.out.println("Error - File Not Found, please enter a valid file name \n");
        } catch (IOException e) {
            System.out.println("Error");
        }

        return f1;
    }
}