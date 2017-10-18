import java.util.Arrays;
import java.util.stream.Stream;

public class WordCountInStringClass {
    private static int wordCount(String s) {
        if (s == null || s.equals(""))
            return 0;

        String[] array = s.split(" ");
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(""))
                count++;
        }
        System.out.println("Длина разделенной строки: " + array.length);
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
        return count;
    }

    private static int wordCountWithStream(String s) {
        Stream<String> stream = Arrays.stream(s.split(" "));
        return (int) stream.filter(s1 -> s1.length() > 0).count();
    }

    public static void main(String[] args) {
        String s = "Кек  Л О_О ОЛ  а   крутойкод  ";
//        System.out.println("Количество слов: " + wordCount(s));
        System.out.println("Количество слов: " + wordCountWithStream(s));
//        String s = "";
//        System.out.println("Количество слов: " + wordCount(s));
    }
}
