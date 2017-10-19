package examples.init;

public class B extends Main {
    String variable = null;
    Object object;

    public B(){
        System.out.println("variable value = " + variable);
        y(variable);
        objectNull(object);
    }

    protected void printVariable(){
        variable = "variable is initialized in examples.init2.B Class";
    }

    public void x(String s) {
        System.out.println(s);
    }

    public void y(String s) {
        x(s);
    }

    public void objectNull(Object o) {
        o.equals(variable);
    }
}
