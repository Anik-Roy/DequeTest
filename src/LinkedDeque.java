import java.util.Collection;
import java.util.Iterator;

public class LinkedDeque<T> {
    private Node head;
    private Node tail;

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public String next() {
                return null;
            }
        };
    }

    static class Node{
        //data
        String i;
        // next node in the list
        Node next;
        // previous node in the list
        Node prev;
        Node(String i){
            this.i = i;
        }
        public void displayData(){
            System.out.print(i + " ");
        }
    }
    // constructor
    public LinkedDeque(){
        this.head = null;
        this.tail = null;
    }

    public LinkedDeque(Collection<T> collection, boolean atRear) {
        Iterator<T> iterator = collection.iterator();
        if(atRear) {
            while (iterator.hasNext()) {
                Node newNode = new Node(iterator.next().toString());
                // if first insertion head should
                // also point to this node
                if(isEmpty()){
                    head = newNode;
                }else{
                    tail.next = newNode;
                    newNode.prev = tail;
                }
                tail = newNode;
            }
        } else {
            while (iterator.hasNext()) {
                Node newNode = new Node(iterator.next().toString());
                // if first insertion tail should
                // also point to this node
                if(isEmpty()){
                    tail = newNode;
                }else{
                    head.prev = newNode;
                }
                newNode.next = head;
                head = newNode;
            }
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void enqueueFront(String i){
        //Create a new node
        Node newNode = new Node(i);
        // if first insertion tail should
        // also point to this node
        if(isEmpty()){
            tail = newNode;
        }else{
            head.prev = newNode;
        }
        newNode.next = head;
        head = newNode;
    }


    public void enqueueRear(String i){
        Node newNode = new Node(i);
        // if first insertion head should
        // also point to this node
        if(isEmpty()){
            head = newNode;
        }else{
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
    }

    public String dequeueFront(){
        if(head == null){
            throw new RuntimeException("Deque is empty");
        }
        Node first = head;
        if(head.next == null){
            tail = null;
        }else{
            // previous of next node (new first) becomes null
            head.next.prev = null;
        }
        head = head.next;
        return first.i;
    }

    public String dequeueRear(){
        if(tail == null){
            throw new RuntimeException("Deque is empty");
        }
        Node last = tail;
        if(head.next == null){
            head = null;
        }else{
            // next of previous node (new last) becomes null
            tail.prev.next = null;
        }
        tail = tail.prev;
        return last.i;
    }

    public String first(){
        if(isEmpty()){
            throw new RuntimeException("Deque is empty");
        }
        return head.i;
    }

    public String last(){
        if(isEmpty()){
            throw new RuntimeException("Deque is empty");
        }
        return tail.i;
    }

    // Method for forward traversal
    public void displayForward(){
        Node current = head;
        while(current != null){
            current.displayData();
            current = current.next;
        }
        System.out.println("");
    }

    // Method to traverse and display all nodes
    public void displayBackward(){
        Node current = tail;
        while(current != null){
            current.displayData();
            current = current.prev;
        }
        System.out.println("");
    }

    public int size() {
        Node cur = head;
        int cnt = 0;
        while(cur != null) {
            cnt++;
            cur = cur.next;
        }

        return cnt;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public String toString() {
        String result = "[";
        Node tmp = head;
        while(tmp != null) {
            result = result + tmp.i;
            tmp = tmp.next;
            if(tmp != null) {
                result = result + ", ";
            }
        }
        result = result + "]";
        return result;
    }
}
