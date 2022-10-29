Part1

[Link](https://github.com/wahanucsd/lab3/blob/main/lab2-SearchEngine.md)
![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-28%20at%209.45.43%20PM.png)
import java.io.IOException; import java.net.URI; import java.util.ArrayList; import java.util.List;

class Handler implements URLHandler { // The one bit of state on the server: a number that will be manipulated by // various requests. int num = 0;

public String handleRequest(URI url) {
    ArrayList<String> store = new ArrayList<String>();
    if (url.getPath().contains("/add")) {
        String[] parameters = url.getQuery().split("=");
        if (parameters[0].equals("s")) {
        store.add(parameters[1]);
        }
    }
    if (url.getPath().contains("/search")) {
        ArrayList<String> storesearch = new ArrayList<String>();
        String[] parameters = url.getQuery().split("=");
        if (parameters[0].equals("s")) {
            for(int i = 0; i < store.size(); i++){
                if(store.get(i).contains(parameters[1])){
                storesearch.add(store.get(i));
                }
            }
                return (storesearch.get(0));
            }
    }
        return "404 Not Found!";
    }
}

class SearchEngine { public static void main(String[] args) throws IOException { if(args.length == 0){ System.out.println("Missing port number! Try any number between 1024 to 49151"); return; }

    int port = Integer.parseInt(args[0]);

    Server.start(port, new Handler());
}
}
					    

screenshots1
![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-14%20at%209.22.43%20PM.png)

I call the method "handleRequest". This URL contains "add" so it should use the first if statement. 
In my code, I want to save the String that after "=", which is "anewstringtoadd", to a new arraylist which name is "store".
However, it returns "404 Not Found!".
Because only the else statement at the end of the method could return "404 Not Found!", 
this shows that it isn't use the first if statdment.
So there are some bugs in my code.

screenshots2
![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-14%20at%209.22.53%20PM.png)

I call the method "handleRequest". This URL contains "add" so it should use the first if statement. 
In my code, I want to save the String that after "=", which is "pineapple", to a new arraylist which name is "store".
However, it returns "404 Not Found!".
Because only the else statement at the end of the method could return "404 Not Found!", 
this shows that it isn't use the first if statdment.
So there are some bugs in my code.



screenshots3
![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-14%20at%209.23.03%20PM.png)

I called the method "handleRequest". This URL contains "search" so it should use the second if statement.
In my code, I want to save all the String in "store" that contains the string after "=" to a new arraylist which name is "storesearch".
So I want to save "pineapple" because "pineapple" contains "app".
But it return "java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0".
This means I didn't save any string to "store".
So there are some bugs in my code.







Part2

First Bug

The failure-inducing input (the code of the test):
		    
![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-28%20at%209.49.03%20PM.png)
		    
@Test
public void testaverageWithoutLowest3() {
  double[] input1 = { 2.0, 4.0, 3.0, 2.0, 5.0};
  assertEquals(3.5, ArrayExamples.averageWithoutLowest(input1), 0.1);
}



The symptom (the failing test output):

![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-28%20at%209.49.48%20PM.png)
		    
testaverageWithoutLowest3(ArrayTests)
3) testaverageWithoutLowest3(ArrayTests)
java.lang.AssertionError: expected:<3.5> but was:<3.0>
at org.junit.Assert.fail(Assert.java:89)
at org.junit.Assert.failNotEquals(Assert.java:835)
at org.junit.Assert.assertEquals(Assert.java:555)
at org.junit.Assert.assertEquals(Assert.java:685)
at ArrayTests.testaverageWithoutLowest3(ArrayTests.java:47)


The bug (the code fix needed):

![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-28%20at%209.51.47%20PM.png)		    
		    
static double averageWithoutLowest(double[] arr) {
    if(arr.length < 2) { return 0.0; }
    double lowest = arr[0];
    int i = 0;
    for(double num: arr) {
      if(num < lowest) { 
        lowest = num; 
        i = arr.indexOf(num);
        }
    }

    double sum = 0;
    for(double num: arr) {
      if(num.indexOf(num) != arr.indexOf(i)) { sum += num; }
    }
    return sum / (arr.length - 1);
  }


}

I change the part of the code. In the first for loop, the code will remember the index of the lowest number instead of the number itself.
So, in the next for loop, the sum will minus the number of that index instead of the number it self.
Because of this, if there are many lowest number, the loop will not remove all of them.



Then, explain the connection between the symptom and the bug. Why does the bug cause that particular symptom for that particular input?

In the original code, it just save the value of the lowest value. So for example, if the lowest value is 2.0 and there are two 2.0 in the list, all of these two number will be deleted. This is why in my test we expect 3.5 when we use (4.0 + 3.0 + 2.0 + 5.0)/4. But the output is (4.0 + 3.0 + 5.0)/4 = 3.0.








Second Bug

The failure-inducing input (the code of the test):

![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-28%20at%2011.13.08%20PM.png)				

@Test
  public void testReversed2() {
    int[] input1 = { 3, 2, 1};
    assertArrayEquals(new int[]{1, 2, 3 }, ArrayExamples.reversed(input1));
  }

The symptom (the failing test output):

![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-28%20at%2011.14.47%20PM.png)		    
		    
testReversed2(ArrayTests)
arrays first differed at element [0]; expected:<1> but was:<0>
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:78)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:28)
        at org.junit.Assert.internalArrayEquals(Assert.java:534)
        at org.junit.Assert.assertArrayEquals(Assert.java:418)
        at org.junit.Assert.assertArrayEquals(Assert.java:429)
        at ArrayTests.testReversed2(ArrayTests.java:29)
        ... 30 trimmed
Caused by: java.lang.AssertionError: expected:<1> but was:<0>
        at org.junit.Assert.fail(Assert.java:89)
        at org.junit.Assert.failNotEquals(Assert.java:835)
        at org.junit.Assert.assertEquals(Assert.java:120)
        at org.junit.Assert.assertEquals(Assert.java:146)
        at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:76)
        ... 36 more

The bug (the code fix needed):

![Image](https://github.com/wahanucsd/lab3/blob/main/Screen%20Shot%202022-10-28%20at%2011.15.32%20PM.png)

static int[] reversed(int[] arr) {
    int[] newArray = new int[arr.length];
    for(int i = 0; i < arr.length; i += 1) {
      newArray[i] = arr[arr.length - i - 1];
    }
    return newArray;
  }
		    

I change the for loop from "arr[i] = newArray[arr.length - i - 1];" to "newArray[i] = arr[arr.length - i - 1];".
Because we want to save the elements from the old arraylist to the new arraylist.
So the original code is wrong because it try to save the element from the new to the old but there are nothing in the new.
		    

Then, explain the connection between the symptom and the bug. Why does the bug cause that particular symptom for that particular input?

The symptom shows that the first element is 0 but we expect 1. Because the original code try to save the element of the new arraylist to 
the old arraylist. But there are nothing in the new, the element is O in the symtom.
After I change the order in the for loop(from "arr[i] = newArray[arr.length - i - 1];" to "newArray[i] = arr[arr.length - i - 1];"),
The elements in the old arraylist will save to the new arraylist and also change the order, so there is no bug anymore.


