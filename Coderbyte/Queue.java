package Coderbyte;

/**
 * https://coderbyte.com/video/introducion-to-queues
 */
public class Queue {
    public static void main(String[] args) {
        Queue q = new Queue();
        System.out.println(q.dequeue());
        q.enqueue(new QueueNode(1));
        q.enqueue(new QueueNode(2));
        q.enqueue(new QueueNode(3));
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }

    private QueueNode front, back;
    private int size = 0;

    public void enqueue(QueueNode n) {
        if (size == 0) {
            front = n;
            front.next =n;
            back = n;
        } else {
            back.next = n;
            back = n;
        }
        size++;
    }

    public QueueNode dequeue() {
        if (size == 0) {
            return null;
        } else {
            QueueNode deq = front;
            front = front.next;
            size--;
            return deq;
        }
    }

}

class QueueNode {
    public QueueNode(int value) {
        this.value = value;
    }

    private int value;
    QueueNode next;

    @Override
    public String toString() {
        return "v=" + value;
    }
}
