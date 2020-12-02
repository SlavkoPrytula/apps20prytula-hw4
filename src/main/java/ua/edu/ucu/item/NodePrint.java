package ua.edu.ucu.item;

import ua.edu.ucu.itterator.Iterator;

public class NodePrint {
    NodeCollection nodes;

    public NodePrint(NodeCollection nodes) {
        this.nodes = nodes;
    }

    public void printNotifications() {
        Iterator<String> iterator = nodes.createIterator();

        System.out.println("-------NOTIFICATION BAR------------");
        while (iterator.hasNext()) {
            Item n = (Item) iterator.next();
            System.out.println(n.getNotification());
        }
    }
}
