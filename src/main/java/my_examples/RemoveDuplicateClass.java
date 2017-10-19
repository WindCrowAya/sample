package my_examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicateClass {

    private static int[] removeDuplicate(int[] a) {
        if (a == null || a.length == 0)
            return null;

        List<Integer> list = new ArrayList<>();

        list.add(a[0]);

        //удаляем дубликаты
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j])
                    break;
                else if (j == i-1)
                    list.add(a[i]);
            }
        }

        //получаем массив int
        int[] arrayWithoutDuplicate = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arrayWithoutDuplicate[i] = list.get(i);
        }

        return arrayWithoutDuplicate;
    }

    private static int[] removeDuplicateWithStream(int[] a) {
        return Arrays.stream(a).distinct().toArray();
    }

    public static void main(String[] args) {
//        int[] a = new int[] {2,1,4,2,3,4,7,7,7,2,8,12,1};
        int[] a = null;
//        int[] afterRemoving = removeDuplicate(a);
        int[] afterRemoving = removeDuplicateWithStream(a);
        for (int i = 0; i < afterRemoving.length; i++) {
            System.out.print(afterRemoving[i] + " ");
        }
    }
}
