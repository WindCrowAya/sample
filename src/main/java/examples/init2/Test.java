package examples.init2;

public class Test extends SuperTest {
    static {
        System.out.println("4");
    }

    {
        System.out.println("5");
    }

    public Test() {
        System.out.println("6");
    }
}
