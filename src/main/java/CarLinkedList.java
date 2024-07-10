import java.util.Iterator;

public class CarLinkedList implements CarList, CarQueue {
    Node first;
    Node last;
    int size = 0;

    @Override
    public Car peek() {
//        return  size > 0 ? get(0) : null; тоже самое, что ниже
        if (size > 0) {
            return get(0);
        } else {
            return null;
        }

    }

    @Override
    public Car poll() { // тут нет if else, т.к кинуть исключение для данного метода нормально
        Car car = get(0);
        removeAt(0);
        return car;
    }

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public boolean add(Car car) {
        if (size == 0) {
            Node newnode = new Node(null, car, null);
            first = newnode;
            last = newnode;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, car, null);
            secondLast.next = last;
        }
        size++;
        return true;
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
        Node nodePrevious = node.previous;
        if (nodeNext != null) {
            nodeNext.previous = nodePrevious;
        } else {
            last = nodePrevious;
        }
        if (nodePrevious != null) {
            nodePrevious.next = nodeNext;
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
    public boolean add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return add(car);

        }
        Node nodeNext = getNode(index);
        Node nodePrevious = nodeNext.previous; // возможен null
        Node newnode = new Node(nodePrevious, car, nodeNext);
        nodeNext.previous = newnode;
        if (nodePrevious != null) {
            nodePrevious.next = newnode;
        } else {
            first = newnode;
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {

            private Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Car next() {
                Car car = node.value;
                node = node.next;
                return car;
            }
        };
    }

    static class Node {
        Node previous;
        Car value;
        Node next;

        public Node(Node previous, Car value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }

    public Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
