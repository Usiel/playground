package List;

import sun.awt.image.ImageWatched;

import java.util.List;

/**
 * Created by Usiel on 23.02.2016.
 */
public class LinkedList<E> {
    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<Integer>();
        for(int i=0; i<10; i++) {
            l.add(i);
        }
        l.print();
        l.reverse();
        l.print();

        System.out.println(l.get(3));
        l.remove(4);
        l.print();
    }

    public LinkedElement<E> head;

    public void add(E value) {
        if (head == null) {
            head = new LinkedElement<E>(value);
        } else {
            head.add(value);
        }
    }

    public E get(int index) {
        int current = 0;
        LinkedElement<E> element = head;
        while (current < index) {
            element = element.getNext();
            current++;
        }
        return element.getValue();
    }

    public void remove(E value) {
        if (head.getValue().equals(value)) {
            head = head.getNext();
        } else {
            head.remove(value);
        }
    }

    public void reverse() {
        head = head.reverse(null);
    }

    private LinkedElement<E> getLast() {
        LinkedElement<E> last = head;
        while (head.getNext() != null) {
            last = head;
        }
        return last;
    }

    public void print() {
        System.out.println("LIST:");
        LinkedElement ele = head;
        while (ele != null) {
            System.out.println(ele.getValue().toString());
            ele = ele.getNext();
        }
    }

    private class LinkedElement<E> {
        private E value;
        private LinkedElement<E> next;

        public LinkedElement(E value) {
            this.value = value;
        }

        public void add(E value) {
            if (getNext() == null) {
                setNext(new LinkedElement<E>(value));
            } else {
                getNext().add(value);
            }
        }

        public void remove(E value) {
            if (getNext() != null) {
                if (getNext().getValue().equals(value)) {
                    setNext(getNext().getNext());
                } else {
                    getNext().remove(value);
                }
            }
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public LinkedElement<E> getNext() {
            return next;
        }

        public void setNext(LinkedElement<E> next) {
            this.next = next;
        }

        public LinkedElement<E> reverse(LinkedElement<E> element) {
            if (getNext() != null) {
                LinkedElement<E> originalNext = getNext();
                setNext(element);
                return originalNext.reverse(this);
            }
            setNext(element);
            return this;
        }
    }
}
