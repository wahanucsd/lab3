import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;


public class ListTests {
	@Test 
	public void testFilter1() {
    ArrayList<String> test1 = new ArrayList<>();
    test1.add("apple");
    test1.add("banana");
    test1.add("peach");
    ArrayList<String> test2 = new ArrayList<>();
    test2.add("banana");
    assertEquals(test2, ListExamples.filter(test1, new ListExamples()));
	}
/*
    @Test 
	public void testmerge1() {
    List<String> test1 = new ArrayList<String>();
    test1.add("a");
    test1.add("b");
    List<String> test2 = new ArrayList<>();
    test2.add("b");
    test2.add("c");
    List<String> test3 = new ArrayList<>();
    test3.add("a");
    test2.add("b");
    test2.add("c");
    assertEquals(test3, ListExamples.merge(test1, test2));
	}
*/
}