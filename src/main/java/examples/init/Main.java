package examples.init;

public class Main {
    String variable;
    public static void main(String[] args) {
        System.out.println("Hello World!");
        B b = new B();
    }

    public Main(){
        printVariable();
    }

    protected void printVariable(){
        variable = "variable is initialized in examples.init2.Main Class";
    }
}

