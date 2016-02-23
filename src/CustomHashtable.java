import java.util.LinkedList;
import java.util.List;

/**
 * Created by Usiel on 21.02.2016.
 */
public class CustomHashtable<K, V> {
    private List<Entry<K,V>>[] t;
    private int size;

    public static void main (String[] args) throws java.lang.Exception {
        CustomHashtable<Integer, String> table = new CustomHashtable<>(3);
        table.put(new Entry<>(5, "fünf"));
        table.put(new Entry<>(2, "zwei"));
        table.put(new Entry<>(1, "eins"));
        table.put(new Entry<>(3, "drei"));
        table.put(new Entry<>(13, "dreizehn"));
        table.remove(13);
        table.print();
        System.out.println(table.get(5).value);
    }

    public CustomHashtable(int size) {
        this.size = 0;
        t = new List[size];
    }

    public Entry<K, V> get(K key) {
        int index = hash(key);
        if (t[index] == null) {
            return null;
        }
        return t[hash(key)].stream().filter(e -> e.key.equals(key)).findFirst().get();
    }


    public void put(Entry<K, V> e) {
        int k = hash(e.key);
        int i = k;

        if (size >= t.length) {
            grow();
        }

        if (t[i] == null) {
            t[i] = new LinkedList<>();
        }
        t[i].add(e);
        size++;
    }

    public void put(List<Entry<K, V>> l) {
        for (Entry e : l) {
            put(e);
        }
    }

    private void grow() {
        setCapacity(t.length*2);
    }

    private void setCapacity(int cap) {
        List<Entry<K,V>>[] old = t;
        t = new List[cap];
        size = 0;
        for (int i=0; i<old.length; i++) {
            if (old[i] != null) {
                put(old[i]);
            }
        }
    }

    public void remove(K key) {
        int index = hash(key);
        Entry en = t[index].stream().filter(e -> e.key.equals(key)).findFirst().get();
        if (en != null) {
            t[index].remove(en);
            size--;
        }
        if (size < t.length/2) {
            shrink();
        }
    }

    private void shrink() {
        setCapacity(t.length/2);
    }

    public int hash(K key) {
        return key.hashCode() % t.length;
    }

    public void print() {
        System.out.println("Table of size " + size);
        for (int i=0; i<t.length; i++) {
            if (t[i] != null) {
                System.out.println("t[" + i +"]: " + String.join(", ", t[i].toString()));
            }
        }
    }
}

