import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Usiel on 22.02.2016.
 */
public class Powerset {
    public Set<Integer> set;
    private Set<Set<Integer>> powerSet;
    private Hashtable<String, Set<Set<Integer>>> calculatedPowerSets;

    public Powerset(Set<Integer> set) {
        this.set = set;
        calculatedPowerSets = new Hashtable<String, Set<Set<Integer>>>();
        calculatePowerSet();
    }

    public void calculatePowerSet() {
        // A B C => , empty, A, B, C, A B, A C, B C, A B C
        powerSet = new HashSet<>();
        powerSet.add(new HashSet<>()); // empty set

        List<Integer> elements = set.stream().collect(Collectors.toList());
        for (int s=0; s<=set.size(); s++) {
            powerSet.addAll(getCombinations(s, elements));
        }
    }

    public Set<Set<Integer>> getCombinations(int size, List<Integer> elements) {
        String key = getCombinationKey(size, elements);
        if (calculatedPowerSets.containsKey(key)) {
            return calculatedPowerSets.get(key);
        }
        Set<Set<Integer>> combinations = new HashSet<>();
        if (size == 0) {
            combinations.add(new HashSet<>());
            return combinations;
        }

        for (int i=0; i<elements.size()-size+1; i++) {
            if (size == 1) {
                Set<Integer> current = new HashSet<>();
                current.add(elements.get(i));
                combinations.add(current);
            } else {
                combinations.addAll(
                        combine(
                            elements.get(i),
                            getCombinations(size-1, elements.subList(i+1, elements.size()))));
            }
        }
        calculatedPowerSets.put(key, combinations);
        return combinations;
    }

    private String getCombinationKey(int size, List<Integer> elements) {
        String key = size + "-";
        for (int i=0; i<elements.size(); i++) {
            key += elements.toString() + "-";
        }
        return key;
    }

    private Set<Set<Integer>> combine(Integer value, Set<Set<Integer>> combinations) {
        Set<Set<Integer>> copy = new HashSet<>(combinations.stream().map(integers -> new HashSet<>(integers.stream().collect(Collectors.toList()))).collect(Collectors.toList()));
        Iterator<Set<Integer>> it = copy.iterator();
        while (it.hasNext()) {
            it.next().add(value);
        }
        return copy;
    }

    public void print() {
        Iterator<Set<Integer>> it = powerSet.iterator();
        while (it.hasNext()) {
            print(it.next());
        }
    }

    private void print(Set<Integer> next) {
        Iterator<Integer> it = next.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i=1; i<20; i++) {
            set.add(i);
        }
        Powerset s = new Powerset(set);
        s.print();
        System.out.println("2^n=" + Math.pow(2, set.size()) + " and ?=" + s.powerSet.size());
    }
}
