import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 * Client Class to create tables from an input and to generate an output based on the aformentioned input.
 * @author Franklin van Nes
 */
public class WordGen {

    //Defaults
    public static final String DEFAULT_FILE_NAME = "default.txt";

    /**
     * Operational method. Prints auto-generated words to the console.
     * @param args, argument 1 [optional]: file name, argument 2[optional, requires arg1]: starting string, argument 3[optional, requires args1 and 2] max output length
     */
    public static void main(String args[]) {
        //Assign to defaults
        String startingString = ""; //will be assigned later based on the input string.
        String fileName = DEFAULT_FILE_NAME;
        int maxOutputLength = Table.MAX_OUTPUT_LENGTH;

        //Process the command line arguments.
        switch (args.length) {
            case 3:
                maxOutputLength = Integer.parseInt(args[2]);
                if(maxOutputLength < 0) quit(new IllegalArgumentException("The max output length, " + maxOutputLength + " is less than 0, so is unacceptable."));
            case 2:
                startingString = args[1];
            case 1:
                fileName = args[0];
                break;
            case 0:
                break;
            default:
                quit(new IllegalArgumentException(args.length + " arguments provided"));
                break;
        }

        String inputString = fileToString(toFile(fileName));
        if (startingString.isEmpty()) startingString = getStartingSeed(inputString);
        Table table = new Table(inputString, startingString.length());


        System.out.println("Creating the table");
        System.out.println(table);
        System.out.println("Generating the random text.");
        System.out.println(table.generateText(startingString, maxOutputLength));

    }

    /**
     * Picks a starting point at random from the default string size of two.
     *
     * @param inputString, the string to be analyzed.
     * @return starting string for word generation.
     */
    private static String getStartingSeed(String inputString) {
        Random random = new Random();
        int idx = random.nextInt(inputString.length() - Table.DEFAULT_SEED_SIZE);
        return inputString.substring(idx, idx + Table.DEFAULT_SEED_SIZE);
    }

    /**
     * Reads and return the text from a given file.
     *
     * @param file, to be used as input for the word generator
     * @return String of the file's content.
     */
    private static String fileToString(File file) {
        try {
            StringBuilder result = new StringBuilder();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) result.append(" " + scanner.nextLine());
            scanner.close();
            return result.toString();

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Table not created");
            quit(e);
            return null;
        }

    }

    /**
     * Takes a filename and returns the associated File object.
     *
     * @param filename, the name of the file to be analyzed.
     * @return File
     */
    public static File toFile(String filename) {
        File inFile;

        inFile = new File(filename);

        return inFile;
    }

    /**
     * Handles the program exit.
     * Called in the main method when processing command line arguments.
     * Exits the program before printing any necessary messages.
     *
     * @param exc, the Exception given to justify quiting.
     */
    private static void quit(Exception exc) {
        if (exc instanceof IllegalArgumentException) {
            System.out.println("Details: " + exc.getMessage());
            System.out.println("Program should run with the following arguments: \n" +
                    "run WordGen [FileName] [Starting String] [Max Output Length]");
        }
        if (exc instanceof  FileNotFoundException){
            System.out.println(exc.getMessage());
        }
        System.exit(1);
    }
}
