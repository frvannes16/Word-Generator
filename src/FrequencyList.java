import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Franklin on 10/21/2015.
 */
public class FrequencyList {

    private ArrayList<Association<Character, Integer>> freqList = new ArrayList<Association<Character, Integer>>();
    private int totalCount;
    private final Random random = new Random();

    public FrequencyList() {
        totalCount = 0;
    }

    //CRUD - CREATE READ UPDATE DELETE
    //CREATE
    protected void add(Association<Character, Integer> a) {
        //First see if freqList contains the given association.
        int idx = freqList.indexOf(a);
        if (idx == -1) freqList.add(a);
        else update(idx, a); //update if it already exists.
        totalCount++;
    }

    public void add(char c) {
        add(new Association<Character, Integer>(c, 1));
    }


    public char getNextChar() {
        int rand = random.nextInt(totalCount);
        int sum = 0;
        //select the character based on probability
        for (Association<Character, Integer> item : freqList) {
            int count = item.getValue();
            sum += count;
            if (sum >= rand) return item.getKey();
        }
        return (char) 0;
    }

    //UPDATE
    private void update(int idx, Association<Character, Integer> a) {
        Association<Character, Integer> association = freqList.get(idx);
        association.setValue(association.getValue() + a.getValue()); //add the count
    }

    private void update(int idx, char c) {
        update(idx, new Association<Character, Integer>(c, 1));
    }

    //DELETE
    private boolean remove(Association<Character, Integer> a) {
        int idx = freqList.indexOf(a);
        if (idx != -1) freqList.remove(idx);
        else return false; //update if it already exists.
        return true; //true if successful.
    }

    public boolean equals(Object o) {
        if (o instanceof FrequencyList) {
            FrequencyList otherFreqList = (FrequencyList) o;
            if (otherFreqList == this) return true;
            for (Association<Character, Integer> association : freqList) {
                if (otherFreqList.freqList.indexOf(association) == -1) break;
            }
            return true;

        }
        return false;
    }

    private double getCharProbability(Association<Character, Integer> entry) {
        return ((double) entry.getValue() / totalCount);

    }

    private String getCharProbabilityString(Association<Character, Integer> entry) {
        double probability = getCharProbability(entry);
        //TODO Add the Decimal format source info:
        //Found on stack overflow http://stackoverflow.com/questions/8819842/best-way-to-format-a-double-value-to-2-decimal-places
        DecimalFormat df = new DecimalFormat("0.00"); //two decimal places.
        String probabilityString = df.format(probability); //apply the format
        return probabilityString;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < freqList.size(); i++) {
            Association<Character, Integer> item = freqList.get(i);
            result.append("\t\t" + item.getKey().toString() + " --> " + getCharProbabilityString(item) + "\n");
        }
        return result.toString();
    }
}
