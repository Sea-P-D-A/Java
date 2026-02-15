import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> AL = new ArrayList<>();
        Set<Integer> HS = new HashSet<>();
        List<Integer> LL = new LinkedList<>();
        Set<Integer> TS = new TreeSet<>();
        String time;

        //Add
        // ArrayList - добавляет В КОНЕЦ
        // ArrayList.add() - в среднем O(1)
        time = CollectionBenchmarkUtils.testAdd(AL);
        System.out.println(time);
        // HashSet.add() - в среднем O(1)
        time = CollectionBenchmarkUtils.testAdd(HS);
        System.out.println(time);
        // LinkedList.add() - O(1)
        time = CollectionBenchmarkUtils.testAdd(LL);
        System.out.println(time);
        // TreeSet.add() - O(log n)
        time = CollectionBenchmarkUtils.testAdd(TS);
        System.out.println(time);
        System.out.println("\n");

        //O(n)
        time = CollectionBenchmarkUtils.testContais(AL);
        System.out.println(time);
        //O(1)
        time = CollectionBenchmarkUtils.testContais(HS);
        System.out.println(time);
        //O(n)
        time = CollectionBenchmarkUtils.testContais(LL);
        System.out.println(time);
        //O(log n)
        time = CollectionBenchmarkUtils.testContais(TS);
        System.out.println(time);

        System.out.println("\n");
        time = CollectionBenchmarkUtils.testFixedIndex(AL);
        System.out.println(time);
        time = CollectionBenchmarkUtils.testFixedIndex(LL);
        System.out.println(time);

        System.out.println("\n");
        time = CollectionBenchmarkUtils.testPushFront(AL);
        System.out.println(time);
        time = CollectionBenchmarkUtils.testPushFront(LL);
        System.out.println(time);

        System.out.println("\n");
        time = CollectionBenchmarkUtils.testPushBack(AL);
        System.out.println(time);
        time = CollectionBenchmarkUtils.testPushBack(LL);
        System.out.println(time);



    }
}

