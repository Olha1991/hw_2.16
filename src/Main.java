import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(4);
        integerList.add(10);
        integerList.add(5);
        integerList.add(-8);
        integerList.add(4, 5);
        System.out.println(integerList.contains(10));
        System.out.println(integerList.contains(11));
        System.out.println(Arrays.toString(integerList.toArray()));
    }
}