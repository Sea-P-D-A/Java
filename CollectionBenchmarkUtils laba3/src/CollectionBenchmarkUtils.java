import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CollectionBenchmarkUtils {
    public static final int benchmarkTest = 1000000;

    private CollectionBenchmarkUtils() {
    }

    public static String testAdd(Collection<Integer> collection) {
        long startTime = System.currentTimeMillis();
        collection.clear();
        for (int i = 0; i < benchmarkTest; i++) {
            collection.add(i);
        }
        long endTime = System.currentTimeMillis();
        long tookTime = endTime - startTime;
        String realClassName = collection.getClass().getSimpleName();

        return String.format("Method add for class %s , took time %d ms", realClassName, tookTime);
    }

    public static String testContais(Collection<Integer> collection) {
        collection.clear();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            collection.add(i);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            collection.contains(i);
        }

        long endTime = System.currentTimeMillis();
        long tookTime = endTime - startTime;
        String realClassName = collection.getClass().getSimpleName();

        return String.format("Method contains for class %s , took time %d ms", realClassName, tookTime);
    }

    public static String testFixedIndex(List<Integer> list) {
        list.clear();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            list.add(i);
        }
        String realClassName = list.getClass().getSimpleName();
        int center = list.size() / 2;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            list.add(center, i);
        }
        long endTime = System.currentTimeMillis();
        long tookTime = endTime - startTime;
        return String.format("Method addFixed for class %s , took time %d ms", realClassName, tookTime);
    }

    public static String testPushFront(List<Integer> list) {
        list.clear();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            list.add(i);
        }
        String realClassName = list.getClass().getSimpleName();
        int center = list.size() / 2;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            list.add(0, i);
        }
        long endTime = System.currentTimeMillis();
        long tookTime = endTime - startTime;
        return String.format("Method addFront for class %s , took time %d ms", realClassName, tookTime);
    }

    public static String testPushBack(List<Integer> list) {
        list.clear();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            list.add(i);
        }
        String realClassName = list.getClass().getSimpleName();
        int center = list.size() / 2;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < benchmarkTest / 10; i++) {
            list.add(list.size() - 1, i);
        }
        long endTime = System.currentTimeMillis();
        long tookTime = endTime - startTime;
        return String.format("Method addBack for class %s , took time %d ms", realClassName, tookTime);
    }


}
