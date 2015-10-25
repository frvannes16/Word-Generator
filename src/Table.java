import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Franklin on 10/21/2015.
 */
public class Table {
    private String input;
    private HashMap<String, FrequencyList> table;
    public static final int DEFAULT_SEED_SIZE = 2;
    public static final int MAX_OUTPUT_LENGTH = 200;
    public final int seedSize; //important to keep final for when the table is created.
    private static Scanner scanner;

    public Table(String str) {
        this(str, DEFAULT_SEED_SIZE);
    }

    public Table(File file) {
        this(fileToString(file));
    }

    public Table(File file, int seedSize) {
        this(fileToString(file), seedSize);
    }

    public Table(String str, int seedSize) {
        input = str;
        table = new HashMap<>();
        this.seedSize = seedSize;
        parseString(); //parse through input
    }

    public String generateText(String seed, int max) {
        StringBuilder result = new StringBuilder(seed);
        int idx = 0;
        char c; //next character.
        while (idx < max) {
//            System.out.println(result.substring(idx));
            FrequencyList f = table.get(result.toString().substring(idx));
//            System.out.println(f);
            if (f == null) break; //Can't find a character set to work from. End the string.
            c = f.getNextChar();
            result.append(c);
            idx++;
        }
        return result.toString();
    }

    private static String fileToString(File file) {
        try {
            StringBuilder result = new StringBuilder();
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) result.append(scanner.nextLine());
            return result.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found. Table not created");
            return null;
        }

    }

    private void parseString() {
        int idx = 0;
        String chars;
        char nextChar;
        do {
            chars = input.substring(idx, idx + seedSize);
            nextChar = input.charAt(idx + seedSize); //get next character.
            if (chars.length() == seedSize) add(chars, nextChar); //add it to the table
            idx++;


        } while (idx + seedSize < input.length()); //if the substring is shorter the while loop will quit.


    }

    private void add(String chars, char followingChar) {
        //want to add a new <String, FrequencyList> entry to hashmap
        if (table.containsKey(chars)) update(chars, followingChar); //Already exists, update the counts.
        else { //Doesn't exist, create it.
            FrequencyList freqList = new FrequencyList();
            freqList.add(followingChar);
            table.put(chars, freqList);
        }
    }

    private void update(String chars, char nextChar) {
        FrequencyList freqList = table.get(chars);
        freqList.add(nextChar); //will increment the count.
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String str : table.keySet()) {
            result.append(str + ":\n" + table.get(str).toString() + "\n");
        }
        return result.toString();
    }
}
