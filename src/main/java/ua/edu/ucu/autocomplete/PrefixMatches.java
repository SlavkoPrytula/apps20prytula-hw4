package ua.edu.ucu.autocomplete;

import ua.edu.ucu.itterator.Iterator;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {
    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        if (strings == null) {
            throw new NullPointerException();
        }
        int counter = 0;
        for (String str : strings) {
            String[] s = str.split("\\s+");
            for (String word : s) {
                if (word.length() >= 2) {
                    Tuple tuple = new Tuple(word, word.length());
                    trie.add(tuple);
                    counter++;
                }
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

    public Iterable<String> wordsWithPrefix(String pref,
                                            int k) {
        if (k < 1) {
            return null;
        }
        if (pref.length() == 2) {
            k = 3;
        }

        ArrayList<String> result = new ArrayList<>();
        Iterable<String> words = trie.wordsWithPrefix(pref);
        Iterator<String> iterator =
                (Iterator<String>) words.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (word.length() < pref.length() + k) {
                result.add(word);
            }
        }
        return result;
    }

    public int size() {
        return trie.size();
    }
}
