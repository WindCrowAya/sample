import java.util.*;
import java.util.stream.Collectors;

/**
 * This class have some methods with using Stream API
 */
public class StreamClass {

    /**
     * Searches for the max number in the array
     * @param a is int[]
     * @throws IllegalArgumentException if {@code a.length == 0}
     * @throws NullPointerException if {@code a} is null
     * @return max number
     */
    public int searchMaxNumberWithStream(int[] a) {
        if (a.length == 0)
            throw new IllegalArgumentException();
        return Arrays.stream(a).max().getAsInt();
    }

    /**
     * Searches for the max string (or strings) in list
     * @param list is List<String>
     * @throws IllegalArgumentException if {@code list.size() == 0}
     * @throws NullPointerException if {@code list} is null
     * @return string list which have max length of string
     */
    public List<String> searchMaxWordsByLength(List<String> list) {
        if (list.size() == 0)
            throw new IllegalArgumentException();

        int maxLength = 0;

        for (String word: list)
            if (word.length() > maxLength)
                maxLength = word.length();

        final int foundedMaxLength = maxLength;

        return list.stream().filter(word -> word.length() == foundedMaxLength).collect(Collectors.toList());
    }

    /**
     * The same as {@code searchMaxWordsByLength(List<String> list)} //правильно ли так писать названия методов?
     * but in one return
     */
    public List<String> searchMaxWordsByLengthV2(List<String> list) { // TODO: 14.10.2017 разобраться в работе данного метода
        if (list.size() == 0)
            throw new IllegalArgumentException();

        return list.stream()
                .collect(Collectors.groupingBy(String::length))  //группирование по возрастанию или наоборот?
                .entrySet()                                      //создание Set?
                .stream()
                .max(Map.Entry.comparingByKey())                 //почему именно Map.Entry?
                .get()
                .getValue();

        //полная версия для наглядности работы метода
        /*
        Optional<Map.Entry<Integer, List<String>>> listOfMaxWords = list.stream()
                .collect(Collectors.groupingBy(String::length))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey());

        return listOfMaxWords.get().getValue();
        */
    }
}
