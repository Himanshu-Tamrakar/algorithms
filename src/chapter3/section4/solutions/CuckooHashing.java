package chapter3.section4.solutions;

public class CuckooHashing {
    private int tableSize;
    private final int hashFunctions = 2;
    private int[][] hashtable;
    private int[] pos;


    public CuckooHashing(int ts) {
        this.tableSize = ts;
        this.hashtable = new int[hashFunctions][tableSize];
        for (int i = 0; i < hashFunctions; i++) {
            for (int j = 0; j < tableSize; j++) {
                this.hashtable[i][j] = -1;
            }
        }
    }

    private int hash(int key, int r) {
        switch (r) {
            case 0: return h1(key);
            default: return h2(key);
        }
    }

    private int h1(int key) {
        return key % tableSize;
    }
    private int h2(int key) {
        return (key / tableSize) % tableSize;
    }

    public void insert(int item) throws Exception {
        int h1Index = h1(item);
        if (hashtable[0][h1Index] == -1) {
            hashtable[0][h1Index] = item;
            return;
        }
        int cuckooValue = hashtable[0][h1Index];
        int h2Index = h2(cuckooValue);
        if (hashtable[1][h2Index] == -1) {
            hashtable[0][h1Index] = item;
            hashtable[1][h2Index] = cuckooValue;
            return;
        }

        int relocate = (int)Math.log(tableSize);
        for (int i = 0; i < 11; i++) {
            int row = i % hashFunctions;
            int hash = this.hash(item, row);
            if (hashtable[row][hash] == -1) {
                hashtable[row][hash] = item;
                return;
            }
            int oldValue = hashtable[row][hash];
            hashtable[row][hash] = item;
            item = oldValue;
        }
        resize(tableSize * 2);
    }

    private void resize(int size) {
        CuckooHashing var0 = new CuckooHashing(size);
        for (int i = 0; i < hashFunctions; i++) {
            for (int j = 0; j < tableSize; j++) {
                if (hashtable[i][j] != -1) {
                    try {
                        var0.insert(hashtable[i][j]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        tableSize = var0.tableSize;
        hashtable = var0.hashtable;

    }

    public boolean contains(int item) {
        return (hashtable[0][hash(item, 0)] != -1 && hashtable[0][hash(item, 0)] == item) || (hashtable[1][hash(item, 1)] != -1 && hashtable[1][hash(item, 1)] == item);
    }

    public static void main(String[] args) {
        CuckooHashing cuckooHashing = new CuckooHashing(11);
        try {
            cuckooHashing.insert(20);
            cuckooHashing.insert(50);
            cuckooHashing.insert(53);
            cuckooHashing.insert(75);
            cuckooHashing.insert(100);
            cuckooHashing.insert(67);
            cuckooHashing.insert(105);
            cuckooHashing.insert(3);
            cuckooHashing.insert(36);
            cuckooHashing.insert(39);
            cuckooHashing.insert(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] inputs = new int[] {20, 50, 53, 75, 100, 67, 105, 3, 36, 39, 6, 168};

        for (int n: inputs) {
            System.out.printf("is %d exists %b\n", n, cuckooHashing.contains(n));
        }

    }

}
