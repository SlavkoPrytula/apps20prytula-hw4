
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.itterator.Iterator;
import ua.edu.ucu.tries.RWayTrie;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        String[] strings = {"abc", "abce", "abcd", "abcde", "abcdef"};
        pm.load(strings);
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
        Iterator<String> iterator = (Iterator<String>) result.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        String[] expResult = {"abc", "abce", "abcd", "abcde"};


        assertThat(result, containsInAnyOrder(expResult));
    }

}
