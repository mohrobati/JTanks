import java.util.Map;
import java.util.TreeMap;

/**
 * Checks if certain patterns of keys are pressed,
 * and then does some special changes on your tank.
 */
public class CheatCode {


    private int[] sequence;
    private Map<Integer, Integer>[] graph;
    private int currentNode = 0;

    public CheatCode(int [] sequence) {
        this.sequence = sequence;
        graph = generateSequenceMap(sequence);
    }

    public boolean checkCode(int keyPressed) {
        Integer nextNode = graph[currentNode].get(keyPressed);
        currentNode = (nextNode==null ? 0 : nextNode);
        return currentNode == sequence.length-1;
    }


    private Map<Integer, Integer>[] generateSequenceMap(int[] sequence) {

        Map<Integer, Integer>[] graph = new Map[sequence.length];
        for(int i=0 ; i<sequence.length ; i++) {
            graph[i] = new TreeMap<Integer,Integer>();
        }
        for(int i=0 ; i<sequence.length ; i++) {
            loop : for(int j=i ; j<sequence.length-1 ; j++) {
                if(sequence[j-i] == sequence[j]) {
                    Integer value = graph[j].get(sequence[j-i+1]);
                    if(value == null || value < j-i+1)
                        graph[j].put(sequence[j-i+1], j-i+1);
                }
                else
                    break loop;
            }
        }
        return graph;
    }
}