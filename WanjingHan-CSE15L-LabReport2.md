Part1

[Link](https://github.com/wahanucsd/lab3/blob/main/lab2-SearchEngine.md)

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

@Test
  public void testaverageWithoutLowest3() {
    double[] input1 = { 2.0, 4.0, 3.0, 2.0, 5.0};
    assertEquals(3.5, ArrayExamples.averageWithoutLowest(input1), 0.1);
  }



The symptom (the failing test output):

testaverageWithoutLowest3(ArrayTests)
3) testaverageWithoutLowest3(ArrayTests)
java.lang.AssertionError: expected:<3.5> but was:<3.0>
        at org.junit.Assert.fail(Assert.java:89)
        at org.junit.Assert.failNotEquals(Assert.java:835)
        at org.junit.Assert.assertEquals(Assert.java:555)
        at org.junit.Assert.assertEquals(Assert.java:685)
        at ArrayTests.testaverageWithoutLowest3(ArrayTests.java:47)


The bug (the code fix needed):

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





Then, explain the connection between the symptom and the bug. Why does the bug cause that particular symptom for that particular input?

In the original code, it just save the value of the lowest value. So for example, if the lowest value is 2.0 and there are two 2.0 in the list, all of these two number will be deleted. This is why in my test we expect 3.5 when we use (4.0 + 3.0 + 2.0 + 5.0)/4. But the output is (4.0 + 3.0 + 5.0)/4 = 3.0.








Second Bug

The failure-inducing input (the code of the test):


@Test 
	public void testReverseInPlace2() {
    int[] input1 = { 3, 2, 1 };
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{ 1, 2, 3 }, input1);
	}

The symptom (the failing test output):

testReverseInPlace2(ArrayTests)
arrays first differed at element [2]; expected:<3> but was:<1>
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:78)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:28)
        at org.junit.Assert.internalArrayEquals(Assert.java:534)
        at org.junit.Assert.assertArrayEquals(Assert.java:418)
        at org.junit.Assert.assertArrayEquals(Assert.java:429)
        at ArrayTests.testReverseInPlace2(ArrayTests.java:23)
        ... 30 trimmed
Caused by: java.lang.AssertionError: expected:<3> but was:<1>
        at org.junit.Assert.fail(Assert.java:89)
        at org.junit.Assert.failNotEquals(Assert.java:835)
        at org.junit.Assert.assertEquals(Assert.java:120)
        at org.junit.Assert.assertEquals(Assert.java:146)
        at org.junit.internal.ExactComparisonCriteria.assertElementsEqual(ExactComparisonCriteria.java:8)
        at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:76)
        ... 36 more

The bug (the code fix needed):





Then, explain the connection between the symptom and the bug. Why does the bug cause that particular symptom for that particular input?




