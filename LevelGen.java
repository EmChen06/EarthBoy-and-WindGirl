/**
 * Reads a .txt file to generate a level
 */

import java.io.*;
import java.io.FileReader;		
import java.io.BufferedReader;

public class LevelGen{

    static int[][] f1;

    /*
     * Reads the file in which the "level" is stored in
     */
    public static int[][] readFile(String name){
        try{
            
            File file1 = new File(name);
            FileReader fin1 = new FileReader(file1);
            BufferedReader reader1 = new BufferedReader(fin1);

            int lineNum = 0;
            String x = reader1.readLine();
            while(x != null){
                x = reader1.readLine();
                lineNum += 1;
            }

            

            

            reader1.close();
            fin1.close();

            fin1 = new FileReader(file1);
            reader1 = new BufferedReader(fin1);

            String count1 = reader1.readLine();
            f1 = new int[lineNum][count1.length()];

            for (int i = 0; i < lineNum; i++) {
                for (int j = 0; j < count1.length(); j++) {
                    f1[i][j] = Integer.parseInt(String.valueOf(count1.charAt(j)));
                }
                count1 = reader1.readLine();
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

    public static void main(String[] args) {
        int[][] x = readFile("test.txt");

        for (int[] is : x) {
            System.out.println(Arrays.toString(is));
        }
        
    }
}