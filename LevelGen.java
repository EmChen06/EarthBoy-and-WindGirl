
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

            String[][] temp1 = new String[100][11]; //max 100 rows
            int tempLine = 0;

            while (line != null) {
                line = reader1.readLine();
                String[] temp2 = line.split(" ");
                if (temp2[0].equals("1")) { //If it's a platform, it will put numbers into an array of 5 columns
                    for (int j = 0; j < 5; j++) {
                        temp1[tempLine][j] = temp2[j]; //Will input the information into an array
                    }
                    for (int j = 0; j < 6; j++) {
                        temp1[tempLine][j] = "0"; //Fill 0
                    }
                } else if (temp2[0].equals("2")) { //If it's a moving platform, it will put numbers into 11 columns 
                    for (int j = 0; j < 11; j++) {
                        temp1[tempLine][j] = temp2[j]; //Fill 0
                    }
                } else if (temp2[0].equals("3")) { //If it's a Pressure Plate, it will put numbers into 5 columns 
                    for (int j = 0; j < 5; j++) {
                        temp1[tempLine][j] = temp2[j]; //Fill 0
                    }
                } else if (temp2[0].equals("4")) { //If it's a Door, it will put numbers into 5 columns 
                    for (int j = 0; j < 5; j++) {
                        temp1[tempLine][j] = temp2[j]; //Fill 0
                    }
                } else if (temp2[0].equals("5")) { //If it's a Wind Girl, it will put numbers into 5 columns 
                    for (int j = 0; j < 5; j++) {
                        temp1[tempLine][j] = temp2[j]; //Fill 0
                    }
                } else if (temp2[0].equals("6")) { //If it's a Earth Boy, it will put numbers into 5 columns 
                    for (int j = 0; j < 5; j++) {
                        temp1[tempLine][j] = temp2[j]; //Fill 0
                    }
                }

                lineNum++;
            }

            f1 = new int[lineNum][11];

            for (int i = 0; i < (temp1.length); i++) {
                for (int j = 0; j < 11; j++) {
                    f1[i][j] = Integer.parseInt(String.valueOf(temp1[j])); //Will input the information into an array
                }
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

    public static void main(String args[]) {
        int[][] x = readFile("\\Images\\testingDoc.txt");

        for (int i = 0; i < (x.length) / 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(x[i][j]);
            }
        }
    }

}
