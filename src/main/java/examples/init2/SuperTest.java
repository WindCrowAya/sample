package examples.init2;

public class SuperTest {
    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    public SuperTest() {
        System.out.println("3");
    }
}
