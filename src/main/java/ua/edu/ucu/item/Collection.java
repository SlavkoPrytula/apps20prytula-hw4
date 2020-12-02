package ua.edu.ucu.item;
import ua.edu.ucu.itterator.Iterator;

public interface Collection {
    Iterator<String> createIterator();
}
