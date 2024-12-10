import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

class FileScans {
    public static void main(String[] args) {
        File fileToScan;

        if (args.length > 0) {
            fileToScan = new File(args[0]);
            if (!fileToScan.exists()) {
                System.out.println("File not found: " + args[0]);
                return;
            }
        } else {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                fileToScan = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected. Exiting...");
                return;
            }
        }

        try {
            scanFile(fileToScan);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void scanFile(File file) throws FileNotFoundException {
        int charCount = 0, wordCount = 0, lineCount = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                charCount += line.length();
                wordCount += line.split("\\s+").length;
            }
        }

        System.out.println("File: " + file.getName());
        System.out.println("Characters: " + charCount);
        System.out.println("Words: " + wordCount);
        System.out.println("Lines: " + lineCount);
    }
}