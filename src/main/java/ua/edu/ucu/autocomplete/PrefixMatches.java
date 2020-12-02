package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 *
 * @author andrii
 */
public class PrefixMatches {
    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String[] strings) {
        if (strings.length == 0) {
            throw new NullPointerException();
        }
        int counter = 0;
        for (String str : strings) {
            if (str.length() >= 2) {
                Tuple tuple = new Tuple(str, str.length());
                trie.add(tuple);
                counter++;
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) {
            return null;
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return trie.wordsWithPrefix(pref, k);
    }

    public int size() {
        return trie.size();
    }
}
