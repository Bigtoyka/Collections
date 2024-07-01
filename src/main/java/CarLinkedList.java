public class CarLinkedList implements CarList {
    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(Car car) {
        if (size == 0) {
            Node node = new Node(null, car, null);
            first = node;
            last = node;
        } else {
            Node secondlasdt = last;
            last = new Node(secondlasdt, car, null);
            secondlasdt.next = last;
        }
        size++;
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node node = getNode(index);
        Node nodeNext = node.next;
        Node nodePrev = node.prev;
        if (nodeNext != null) {
            nodeNext.prev = nodePrev;
        } else {
            last = nodePrev;
        }
        if (nodePrev != null) {
            nodePrev.next = nodeNext;
        } else {
            first = nodeNext;
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(car);
            return;
        }
        Node nodeNext = getNode(index);
        Node nodePrev = nodeNext.prev;
        Node node = new Node(nodePrev, car, nodeNext);
        nodeNext.prev = node;
        if (nodePrev != null) {
            nodePrev.next = node;
        } else {
            first = node;
        }
        size++;
    }

    static class Node {
        private Node prev;
        private Node next;
        private Car value;

        public Node(Node prev, Car value, Node next) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    public Node getNode(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
