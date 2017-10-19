package examples.new_reference_with_same_name;

/**
 * Объяснение вывода задачи (https://vk.com/page-43948962_51327481):
 *
 * Каждый раз, когда мы создаем объект какого-либо класса, первым вызывается конструктор
 * и выделяется память для всех нестатических переменных.
 *
 * Здесь B b = new B(); объекту класса B присваивается новый объект того же класса.
 *
 * Запись B b = new B(); приводит к рекурсивному исполнению конструктора, что создает
 * бесконечные объекты. Именно поэтому во время выполнения этого кода будет возбуждено
 * исключение java.lang.StackOverFlowError в потоке "main".
 *
 * Распространенной причиной появления такого рода исключений является плохая рекурсия.
 * Обычно это происходит из-за неправильного условия завершения.
 */
public class B {
    B b = new B();

    public int show() {
        return (true ? null : 0);
    }

    public static void main(String[] args) {
        B b = new B();
        b.show();
    }
}