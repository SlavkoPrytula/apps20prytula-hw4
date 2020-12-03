
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);
        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);
        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K_LessBound() {
        String pref = "abc";
        int k = 0;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);
        String[] expResult = null;

        assertNull(result);
    }

    @Test
    public void testWordsWithPrefix_String_LessBound() {
        String pref = "a";

        Iterable<String> result = pm.wordsWithPrefix(pref);
        String[] expResult = null;

        assertNull(result);
    }

    @Test
    public void testWordsWithPrefix_String_and_K_LengthPref2() {
        String pref = "ab";
        int k = 2;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);
        String[] expResult = {"abc", "abce", "abcd"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_Delete() {
        pm.delete("abce");
        boolean result = pm.contains("abce");

        assertFalse(result);
    }

    @Test
    public void testWordsWithPrefix_String_Size() {
        int result = pm.size();
        int expResult = 5;

        assertEquals(result, expResult);
    }

    @Test
    public void testWordsWithPrefix_String_Contains() {
        boolean result = pm.contains("abce");

        assertTrue(result);
    }

    @Test
    public void testWords_String() {
        String pref = "";

        RWayTrie rw = new RWayTrie();
        rw.add(new Tuple("abc" ,3));
        rw.add(new Tuple("abcde" ,3));

        Iterable<String> result = rw.words();
        String[] expResult = {"abc", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

}
