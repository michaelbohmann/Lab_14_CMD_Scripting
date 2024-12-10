import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author wulft
 *
 * Demonstrates how to use Java NIO, a thread safe File IO library
 * to write a text file
 */
public class DataSaver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList <String>recs = new ArrayList<>();
        int idCounter = 1;
        boolean more = true;
        while (more)
        {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter last name");
            String idNumber = String.format("%06d", idCounter); // got help w/ id counter
            idCounter++;
            String email = SafeInput.getRegExString(in, "Enter Email (e.g., user@example.com)", "^[\\w.-]+@[\\w.-]+\\.\\w+$"); // got help w/ regex
            int birthYear = SafeInput.getRangedInt(in, "Enter Year of Birth (1900-2024)", 1900, 2024);
            String record = firstName + "," + lastName + "," + idNumber + "," + email + "," + birthYear;
            recs.add(record);
            more = SafeInput.getYNConfirm(in, "Do you want to add more");
        }
        String fileName = SafeInput.getNonZeroLenString(in, "Enter file name to save data");
        Path workingDirectory = Paths.get(System.getProperty("user.dir") + "/src");
        Path file = Paths.get(workingDirectory.toString(), fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(file))
        {
            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
