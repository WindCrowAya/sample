package examples.object_with_new_method;

/**
 * Объяснение вывода задачи:
 *
 * Ссылке, объявленной как final, нельзя присвоить новый объект, однако
 * можно изменять состояние объекта, т.е. в данном случае элементы
 * массива изменять разрешается, программа успешно скомпилируется
 */
public class Main {

    /*void testCode() {
        final int[] array = {1,2,3,4,5};
        new Object() {
            void test() {
                for (int i = 0; i < array.length; i++) {
                    array[i] *= 2;
                    System.out.println(array[i]);
                }
            }
        }.test();
    }*/

    public static void main(String[] args) {
        final int[] array = {1,2,3,4,5};
        new Object() {
            void test() {
                for (int i = 0; i < array.length; i++) {
                    array[i] *= 2;
                    System.out.println(array[i]);
                }
            }
        }.test();

//        new Main().testCode();
    }
}
