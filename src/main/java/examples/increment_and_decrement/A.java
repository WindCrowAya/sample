package examples.increment_and_decrement;

public class A {
    static int a = 1111;
    static
    {
//        a = a-- - --a;
    }

    {
        a = a++ + ++a; // TODO: 20.10.2017 почему игнорируется?
    }

    public static void main(String[] args) {
        System.out.println(a);
    }
}
