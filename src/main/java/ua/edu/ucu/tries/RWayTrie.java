package ua.edu.ucu.tries;

import ua.edu.ucu.queue.Queue;

public class RWayTrie implements Trie {
    private final Node root = new Node();

    @Override
    public void add(Tuple t) {
        if (t.term == null) {
            throw new NullPointerException();
        }
        Node node = root;

        for (char chr : t.term.toCharArray()) {
            Node nextNode = new Node();
            if (node.next[chr - 97] != null) {
                node = node.next[chr - 97];
                continue;
            }
            node.next[chr - 97] = nextNode;
            nextNode.data = chr;
            node = nextNode;

            if (chr == t.term.charAt(t.term.length() - 1)) {
                node.index = t.weight;
            }
        }
    }

    @Override
    public boolean contains(String word) {
        Node node = root;

        for (char chr : word.toCharArray()) {
            if (node.next[chr - 97] != null
                    && node.next[chr - 97].data != null
                    && node.next[chr - 97].data.equals(chr)) {
                node = node.next[chr - 97];
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        return delete(root.next[word.charAt(0) - 97],
                word, 1) != null;
    }

    private static int countElements(Node[] node) {
        int count = 0;
        for(Node el : node)
            if (el != null)
                count++;
        return count;
    }

    private static Node delete(Node node, String word, int index) {
        char chr = word.charAt(index);
        if (node == null) {
            return null;
        }

        if (index == word.length() - 1) {
            node.next[chr - 97] = null;
            return node;
        } else {
            node.next[chr - 97] = delete(node.next[chr - 97],
                    word,
                    index + 1);
        }
        if (countElements(node.next) == 1) {
            node.next[chr - 97] = null;
        }
        if (node.data != null) {
            return node;
        }

        for (char c = 0; c < 26; c++) {
            if (node.next[c] != null) {
                return node;
            }
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        Queue<String> queue = new Queue<>();
        Node node = root;
        for (char chr : pref.toCharArray()) {
            if (node.next[chr - 97] != null) {
                node = node.next[chr - 97];
            }
        }
        getWords(node, queue, pref);
        return queue;
    }

    public void getWords(Node node,
                         Queue<String> queue, String pref) {
        if (node == null) {
            return;
        }
        if (node.index != null) {
            queue.enqueue(pref);
        }
        for (char c = 0; c < 26; c++) {
            getWords(node.next[c], queue,
                    pref + (char) (c + 97));
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        int counter = 0;
        if (node.index != null) {
            counter++;
        }
        for (char c = 0; c < 26; c++) {
            counter += size(node.next[c]);
        }
        return counter;
    }
}
