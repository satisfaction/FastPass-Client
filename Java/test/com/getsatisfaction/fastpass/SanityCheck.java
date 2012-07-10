// Get Satisfaction; Copyright (C) 2012; All rights reserved.
package com.getsatisfaction.fastpass;

/*
 * This file is intended to provide instruction on how to create unit tests.
 * In order to facilitate the creation of unit tests without needing to
 * consult documentation on the Internet, it is advisable that you leave
 * this file in your project so that it may serve as a reference for
 * creating additional unit tests. While this file provides plenty
 * of good examples of how to create unit tests, more info may be found at:
 *
 *  http://kentbeck.github.com/junit/javadoc/4.7/
 *
 */


// Provides the @Test annotation needed
// to mark methods to be run as unit tests.
import org.junit.Test;

// Provides the various assert functions,
// including assertTrue, assertFalse, assertEquals,
// assertNull, assertNotNull, assertThat, etc.
import static org.junit.Assert.*; 

// Provides the various Hamcrest matchers that
// may be used with the more complicated
// (but more expressive) assertThat syntax.
import static org.hamcrest.CoreMatchers.*;

public class SanityCheck
{
    @Test
    public void testThatAssertTrueAndAssertFalseWork(){
        // When verifying that a boolean value is a certain
        // value, one can use assertTrue or assertFalse
        assertTrue(true);
        assertFalse(false);
        assertTrue(!false);
        assertFalse(!true);
    }
    
    @Test
    public void testThatHamcrestVersionsOfAssertTrueFalseWork(){
        // It is possible to express the function
        // "testThatAssertTrueAndAssertFalseWork", above
        // using the Hamcrest matchers as in:
        assertThat(true,is(true));
        assertThat(false,is(not(true)));
        assertThat(!false,is(true));
        assertThat(!true,is(false));
    }
    
    @Test
    public void testThatAssertEqualsWorks(){
        // For verifying that two objects have the same
        // value (using the ".equals()" method), one can
        // use the assertEquals function:
        
        String helloworld=new String("Hello world!");
        assertEquals("Hello world!",helloworld); // Note: order is "expected", then "actual"
    }
    
    @Test
    public void testThatHamcrestVersionOfAssertEqualsWorks(){
        // It is possible to express the function
        // "testThatAssertEqualsWorks", above
        // using the Hamcrest matchers as in:
        String helloworld=new String("Hello world!");
        assertThat(helloworld,is(equalTo("Hello world!")));
    }
    
    @Test
    public void testThatAssertSameAndAssertNotSameWork(){
        // When comparing the identity of two variables
        // (i.e. whether they point to the same memory location)
        // one can use assertSame and assertNotSame
        
        String poolstr = "Hello world!"; // This string lives in the string pool
        String heapstr = new String(poolstr); // This string lives in the heap and is distinct from poolstr
        String poolstrptr = poolstr; // This now points to the same location as poolstr
        
        assertSame(poolstr,poolstrptr);
        assertNotSame(poolstr,heapstr);
    }
    
    @Test
    public void testThatHamcrestVersionOfAssertSameAndAssertNotSameWork(){
        // It is possible to express the function
        // "testThatAssertSameAndAssertNotSameWork", above
        // using the Hamcrest matchers as in:
        String poolstr = "Hello world!"; // This string lives in the string pool
        String heapstr = new String(poolstr); // This string lives in the heap and is distinct from poolstr
        String poolstrptr = poolstr; // This now points to the same location as poolstr
        
        assertThat(poolstrptr,is(sameInstance(poolstr)));
        assertThat(heapstr,is(not(sameInstance(poolstr))));
    }
    
    @Test
    public void testThatAssertNullAndAssertNotNullWork(){
        // We can verify that values are or are not null as in:
        assertNull(null);
        assertNotNull("This string isn't null.");
    }
    
    @Test
    public void testThatHamcrestVersionOfAssertNullAndNotNullWork(){
        // Or, using the Hamcrest variety:
        assertThat(null,is(nullValue()));
        assertThat("This string isn't null.",is(not(nullValue())));
    }
}
