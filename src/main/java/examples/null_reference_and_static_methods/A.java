package examples.null_reference_and_static_methods;

/**
 * Объяснение вывода задачи:
 *
 * Мы можем вызвать статические методы, используя переменную, ссылающуюся на null.
 * Дело в том, что статические методы находятся на уровне класса. Именно поэтому
 * мы можем вызывать статические функции как при помощи самого класса, так и
 * при помощи переменной, равной null.
 */
public class A {
    public static void show(){

        System.out.println("Static method called");
    }

    public static void main(String[] args)  {

        A obj = null;
        obj.show();

    }
}
