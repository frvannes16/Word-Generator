import java.io.File;


/**
 * Created by Franklin on 10/21/2015.
 */
public class WordGen {

    public static void main(String args[]) {
        String startingString;
        String fileName;
        int maxOutputLength = Table.MAX_OUTPUT_LENGTH;

        Table table = null;
        if (args.length != 3) {
            quit(new IllegalArgumentException(args.length + " arguments provided"));
        } else {
            fileName = args[0];
            startingString = args[1];
            try {
                //arguments could go wrong here.
                maxOutputLength = Integer.parseInt(args[2]);
                table = new Table(toFile(fileName), startingString.length());
            } catch (Exception exc) {
                quit(exc);
            }

            System.out.println("Creating the table");
            System.out.println(table);
            System.out.println("Generating the random text.");
            System.out.println(table.generateText(startingString, maxOutputLength));

        }


    }

    public static File toFile(String filename) {
        File inFile = new File(filename);
        return inFile;
    }

    private static void quit(Exception exc) {
        if (exc instanceof IllegalArgumentException) {
            System.out.println("Details: " + exc.getMessage());
            System.out.println("Program should run with the following arguments: \n" +
                    "run WordGen [FileName] [Starting String] [Max Output Length]");
        }
        System.exit(1);
    }
}
