import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * FrequencyList is a key-value data type manager which stores the frequency of characters.
 * Data is used to determine the probability of character occurrences.
 * @author Franklin van Nes
 */
public class FrequencyList {

    private final Random random = new Random();
    private HashMap<Character, Integer> freqList = new HashMap<Character, Integer>();
    private int totalCount;

    public FrequencyList() {
        totalCount = 0;
    }


    /**
     * Adds the character to the Frequency List. If
     * the character already exists in the list, it increments the count.
     * @param c, a character
     */
    public void add(Character c) {
        //First see if freqList contains the given association.
        if (freqList.containsKey(c)) update(c);
        else freqList.put(c, 1); //update if it already exists.
        totalCount++;
    }

    /**
     * Returns the next character for this frequency list's character seed.
     * @return char, based on its probability on appearing after the character seed.
     */
    public char getNextChar() {
        int rand = random.nextInt(totalCount + 1);
        int sum = 0;
        //select the character based on probability
        for (Character character : freqList.keySet()) {
            int count = freqList.get(character);
            sum += count;
            if (sum >= rand) return character;
        }
        return (char) 0;
    }


    /**
     * Updates the count of given character by 1.
     * @param c, which must already exist in the frequency list.
     */
    private void update(Character c) {
        int count = freqList.get(c);
        freqList.put(c, count + 1); //increment the value in the hashmap by 1.
    }

    /**
     * Tests if the given object is equal to the frequency list which
     * this method is called on.
     * @param o, another object.
     * @return equality to this FrequencyList object.
     */
    public boolean equals(Object o) {
        if (o instanceof FrequencyList) {
            FrequencyList otherFreqList = (FrequencyList) o;
            return otherFreqList == this || (freqList.equals(otherFreqList.freqList));
        }
        return false;
    }

    /**
     * Calculates and returns the probability of a given character appearing.
     * @param c, any character that must already exist in the FrequencyList
     * @return probability from 0 to 1. 0 meaning no chance of appearing (doesn't exist in the freqency table). 1 is guaranteed..
     */
    private double getCharProbability(Character c) {
        double count = freqList.get(c); //Will automatically cast.
        if(count == 0) return 0d;
        return ( count / totalCount);

    }

    /**
     * Retrieves and formats the character probability to two decimal places.
     * @param c any character that must already exist in the FrequencyList.
     * @return a string double of format x.xx
     */
    public String getCharProbabilityString(Character c) {
        double probability = getCharProbability(c);
        DecimalFormat df = new DecimalFormat("0.00"); //two decimal places. JAVA API
        return df.format(probability);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Character character : freqList.keySet()) {
            result.append("\t\t" + character.toString() + " --> " + getCharProbabilityString(character) +  "\n");
        }

        return result.toString();
    }
}
