//Rohan Gopinathan
//rsg190003

public class Node {

    private Node next;
    private Node down;
    private Seat payload;

    //default
    public Node (){

    }

    //overlaoded
    public Node (Node next, Node down, Seat payload)   {
        this.next = null;
        this.down = null;
        this.payload = payload;
    }

    //accessors
    public Node getNext() {
        return this.next;
    }

    public Node getDown() {
        return this.down;
    }

    public Seat getPayload() {
        return this.payload;
    }

    //mutators
    public void setNext(Node temp) {
        this.next = temp;
    }
    public void setDown(Node down) {
        this.down = down;
    }
    public void setPayload(Seat payload) {
        this.payload = payload;
    }
}
