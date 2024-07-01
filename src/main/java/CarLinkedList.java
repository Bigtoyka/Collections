public class CarLinkedList implements CarList {
    Node first;
    Node last;
    int size = 0;

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(Car car) {
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
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;
        for(int i = 0; i< size;i++){
            if(node.value.equals(car)){
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
        if (nodeNext != null){
            nodeNext.previous = nodePrevious;
        }else {
            last = nodePrevious;
        }
        if (nodePrevious != null){
            nodePrevious.next = nodeNext;
        }else{
            first = nodeNext;
        }
        size --;
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
        if(index == size){
            add(car);
            return;
        }
        Node nodeNext = getNode(index);
        Node nodePrevious = nodeNext.previous; // возможен null
        Node newnode = new Node(nodePrevious,car,nodeNext);
        nodeNext.previous = newnode;
        if(nodePrevious != null){
            nodePrevious.next = newnode;
        }else {
            first = newnode;
        }
        size++;
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
    public Node getNode(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }
}
