import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StreamClassTest {

    private StreamClass sc;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before StreamClass.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After StreamClass.class");
    }

    @Before
    public void initTest() {
        sc = new StreamClass();
    }

    @After
    public void afterTest() {
        sc = null;
    }

    @Test
    public void searchMax() throws Exception {
        assertEquals(39, sc.searchMaxNumberWithStream(new int[] {9,12,36,21,7,39,8}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchMaxIAE() throws Exception {
        assertEquals(0, sc.searchMaxNumberWithStream(new int[] {}));
    }

    @Test
    public void searchMaxWordsByLength() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Aya");
        list.add("Shameimaru");
        list.add("Reimu");
        list.add("Kek");
        list.add("Inubashiri");

        String[] assertArray = new String[] {"Shameimaru","Inubashiri"};

        assertArrayEquals(assertArray, sc.searchMaxWordsByLength(list).toArray());
    }

    @Test
    public void searchMaxWordsByLengthV2() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Aya");
        list.add("Shameimaru");
        list.add("Reimu");
        list.add("Azazazazazz");
        list.add("Kek");
        list.add("Kekekekekek");
        list.add("Inubashiri");

        String[] assertArray = new String[] {"Azazazazazz","Kekekekekek"};

        assertArrayEquals(assertArray, sc.searchMaxWordsByLengthV2(list).toArray());
    }

}