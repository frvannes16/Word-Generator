import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Franklin on 10/21/2015.
 */
public class FrequencyList {

    private HashMap<Character, Integer> freqList = new HashMap<Character, Integer>();
    private int totalCount;
    private final Random random = new Random();

    public FrequencyList() {
        totalCount = 0;
    }

    //CRUD - CREATE READ UPDATE DELETE
    //CREATE
    protected void add(Character c) {
        //First see if freqList contains the given association.
        if (freqList.containsKey(c)) update(c);
        else freqList.put(c, 1); //update if it already exists.
        totalCount++;
    }


    public char getNextChar() {
        int rand = random.nextInt(totalCount);
        int sum = 0;
        //select the character based on probability
        for (Character character : freqList.keySet()) {
            int count = freqList.get(character);
            sum += count;
            if (sum >= rand) return character;
        }
        return (char) 0;
    }

    //UPDATE
    private void update(Character c) {
        int count = freqList.get(c);
        freqList.put(c, count + 1); //increment the value in the hashmap by 1.
    }

    public boolean equals(Object o) {
        if (o instanceof FrequencyList) {
            FrequencyList otherFreqList = (FrequencyList) o;
            return otherFreqList == this || (freqList.equals(otherFreqList.freqList));
        }
        return false;
    }

    private double getCharProbability(Character c) {
        return ((double) freqList.get(c) / totalCount);

    }

    private String getCharProbabilityString(Character c) {
        double probability = getCharProbability(c);
        //TODO Add the Decimal format source info:
        //Found on stack overflow http://stackoverflow.com/questions/8819842/best-way-to-format-a-double-value-to-2-decimal-places
        DecimalFormat df = new DecimalFormat("0.00"); //two decimal places.
        return df.format(probability);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Character character : freqList.keySet()) {
            result.append("\t\t" + character.toString() + " --> " + getCharProbabilityString(character) + "\n");
        }
        return result.toString();
    }
}
