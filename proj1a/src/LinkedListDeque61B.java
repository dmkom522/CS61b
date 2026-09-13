import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    public Node sentinel = new Node();
    int size = 0;

    public class Node{
        public Node prev;
        public T item;
        public Node next;
    }




    @Override
    public void addFirst(T x) {

        Node first = new Node();

        first.prev = sentinel;
        first.item = x;
        if(size == 0){
            first.next = sentinel;
            sentinel.prev = first;
        }
        else{
            first.next = sentinel.next;  /*process the next chain*/
        }

        sentinel.next.prev = first;  /*process the prev chain*/
        sentinel.next = first;
        size += 1;
    }

    @Override
    public void addLast(T x) {

        Node last = new Node();

        if(size == 0){
            last.prev = sentinel;
            sentinel.next = last;
        }
        else{
            last.prev = sentinel.prev; /*process the prev chain*/
        }
        last.item = x;
        last.next = sentinel;

        sentinel.prev.next = last;  /*process the next chain*/
        sentinel.prev = last;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        Node now = sentinel.next;

        while(now != sentinel){
            returnList.add(now.item);
            now = now.next;
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){return true;}
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size > 0){
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
        }

        return null;
    }

    @Override
    public T removeLast() {
        if(size > 0){
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
        }
        return null;
    }

    @Override
    public T get(int index) {
        Node now = sentinel;

        if(index >= 0 && index < size){
            for(int i = 0;i <= index;i++){
                now = now.next;
            }
            return now.item;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if(index >= 0 && index < size) {
            return getRecursivehelp(sentinel.next, index);
        }
        return null;
    }

    public T getRecursivehelp(Node i,int index){
        if(index == 0){
            return i.item;
        }
        else{
            return getRecursivehelp(i.next, index - 1);
        }
    }
    public LinkedListDeque61B() {
        sentinel.prev = sentinel;
        sentinel.item = null;
        sentinel.next = sentinel;

    }
}
