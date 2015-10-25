import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Franklin on 10/21/2015.
 */
public class Table {
    private String input;
    private ArrayList<Association<String, FrequencyList>> table;
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
        table = new ArrayList<>();
        this.seedSize = seedSize;
        parseString(); //parse through input

    }

    public String generateText(String seed, int length) {
        if (seed.length() != seedSize) throw new IllegalArgumentException("The starting seed length does not match the seed size (" + seedSize + ")declared upon table initialization.");
        StringBuilder result = new StringBuilder(seed);
        int idx = 0;
        int end = length;
        char c; //next character.
        while (idx < end) {
            int associationIndex = indexOf(result.toString().substring(idx));
            if (associationIndex == -1) break; //Can't find a character set to work from. End the string.
            c = table.get(associationIndex).getValue().getNextChar();
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
        System.out.println("done");

    }

    private void add(String chars, char followingChar) {
        int idx = indexOf(chars);
        if (idx != -1) update(idx, followingChar); //Already exists, update the counts.
        else { //Doesn't exist, create it.
            FrequencyList freqList = new FrequencyList();
            freqList.add(followingChar);
            Association<String, FrequencyList> tableEntry = new Association<>(chars, freqList);
            table.add(tableEntry);

        }
    }


    private int indexOf(String chars) {
        int result = -1;
        for (int i = 0; i < table.size(); i++) {
            String test = table.get(i).getKey();
            if (test.equals(chars)) result = i;
        }
        return result;
    }

    private void update(int idx, char nextChar) {
        Association<String, FrequencyList> tableEntry = table.get(idx);
        tableEntry.getValue().add(nextChar);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Association<String, FrequencyList> item : table) {
            result.append(item.getKey().toString() + ":\n" + item.getValue().toString() + "\n");
        }
        return result.toString();
    }
}
