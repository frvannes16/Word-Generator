import java.util.HashMap;

/**
 * Table parse through a string to populate a frequency table of character occurrences. This data can then be used to generate a
 * "random" string.
 * @author Franklin van Nes
 */
public class Table {
    public static final int DEFAULT_SEED_SIZE = 3;
    public static final int MAX_OUTPUT_LENGTH = 200;

    private final int seedSize; //important to keep final for when the table is created.
    private String input;
    private HashMap<String, FrequencyList> table;

    public Table(String str, int seedSize) {
        input = str;
        table = new HashMap<>();
        this.seedSize = seedSize;
        parseString(); //parse through input and populate the table.
    }

    /**
     * Creates a string based on the character occurrences of the input string.
     * @param seed, string to start the output.
     * @param max, maximum number of characters to output.
     * @return a generated string.
     */
    public String generateText(String seed, int max) {
        StringBuilder result = new StringBuilder(seed);
        int idx = 0;
        char c; //next character.
        while (idx < max) {
//            System.out.println(result.substring(idx));
            FrequencyList f = table.get(result.toString().substring(idx));
//            System.out.println(f);
            if (f == null){
                if(idx == 0) System.out.println("Starting string is not mentioned in the text and thus cannot be used.");

                break; //Can't find a character set to work from. End the string.
            }
            c = f.getNextChar();
            result.append(c);
            idx++;
        }
        return result.toString();
    }



    /**
     * Analyzes the input string:
     * Parses through the input string, adding character sets to populate the table
     * and the following characters to their associated FrequencyList
     */
    private void parseString() {
        int idx = 0;
        String chars; //to be stored as a key in the HashMap
        char nextChar; //to be added to the FrequencyList value in the HashMap.
        do {
            chars = input.substring(idx, idx + seedSize);
            nextChar = input.charAt(idx + seedSize); //get next character.
            if (chars.length() == seedSize) add(chars, nextChar); //add it to the table
            idx++;


        } while (idx + seedSize < input.length()); //if the substring is shorter the while loop will quit.


    }

    /**
     * Adds a <String, FrequencyList> key-value pair to the table. Updates the FrequencyList if charSet already exists as a value.
     * @param charSet key for the table.
     * @param followingChar character value following the charSet to be stored in the HashMap table.
     */
    private void add(String charSet, char followingChar) {
        if (table.containsKey(charSet)) update(charSet, followingChar); //Already exists, update the counts.
        else { //Doesn't exist, create the entry.
            FrequencyList freqList = new FrequencyList();
            freqList.add(followingChar);
            table.put(charSet, freqList);
        }
    }

    /**
     * Updates the FrequencyList in the table.
     * @param charSet, the key charSet to the table
     * @param nextChar, the following character. The count of this char will be incremented by 1 in the FrequencyList.
     */
    private void update(String charSet, char nextChar) {
        FrequencyList freqList = table.get(charSet);
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
