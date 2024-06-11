
/**
 * Reads a .txt file to generate a level
 */
import java.io.*;

public class LevelGen {

    static int[][] f1;

    int code;

    /*
     * Reads the file in which the "level" is stored in
     */
    public static int[][] readFile(String name) {
        try {

            File file1 = new File(name);
            FileReader fin1 = new FileReader(file1);
            BufferedReader reader1 = new BufferedReader(fin1);

            int lineNum = 0;
            String line = reader1.readLine();

            while (line != null) {
                line = reader1.readLine();
                lineNum++;
            }

            reader1.close();
            fin1.close();

            fin1 = new FileReader(file1);
            reader1 = new BufferedReader(fin1);

            String count1 = reader1.readLine();
            f1 = new int[count1.length()][lineNum];

            for (int i = 0; i < count1.length(); i++) {
                for (int j = 0; j < lineNum) {
                    f1[i] = Integer.parseInt(String.valueOf(i));
                }
                count1 = reader1.readLine();
            }

            reader1.close();
            fin1.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error - File Not Found, please enter a valid file name \n");
        } catch (IOException e) {
            System.out.println("Error");
        }

        return f1;
    }

    public static void main(String[] args) {
        int[][] x = readFile("test.txt");

        // for (int[] is : x) {
        //   System.out.println(Arrays.toString(is));
        //}
    }
}
