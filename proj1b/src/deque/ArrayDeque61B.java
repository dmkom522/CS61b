package deque;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items = (T[]) new Object[8];
    private int nextfirst;
    private int nextlast;
    private int size = 0;

    @Override
    public void addFirst(T x) {
        items[nextfirst] = x;
        if(nextfirst == nextlast){
            resize(true);
        } else{
            nextfirst = Math.floorMod((nextfirst - 1), items.length);
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        items[nextlast] = x;
        if(nextfirst == nextlast){
            resize(true);
        } else{
            nextlast = Math.floorMod((nextlast + 1), items.length);
        }
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = (nextfirst + 1) % items.length;
        while(items[index] != null){
            returnList.add(items[index]);
            index = (index + 1) % items.length;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T removed = items[(nextfirst + 1) % items.length];
        items[(nextfirst + 1) % items.length] = null;
        nextfirst = (nextfirst + 1) % items.length;
        size -= 1;
        if(size < items.length / 4 && size != 0){
            resize(false);
        }
        return removed;
    }

    @Override
    public T removeLast() {
        T removed = items[(nextlast - 1) % items.length];
        items[(nextlast - 1) % items.length] = null;
        nextlast = Math.floorMod((nextlast - 1), items.length);
        size -= 1;
        if(size < items.length / 4 && size != 0){
            resize(false);
        }
        return removed;
    }

    @Override
    public T get(int index) {
        if(index >= 0 && index < items.length){
            return items[(index + 1 + nextfirst) % items.length];
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public void resize(boolean flag){
        T[] _items;
        if(flag) {
            _items = (T[]) new Object[items.length * 2];
        }else{
            _items = (T[]) new Object[(int)(items.length / 2)];
        }
        int index = 1;
        for(int i = (nextfirst + 1) % items.length; items[i % items.length] != null; i = (i + 1) % items.length){
            _items[index++] = items[i];
        }
        items = _items;
        nextfirst = 0;
        nextlast = size;
    }

    public ArrayDeque61B() {
        nextlast = 4;
        nextfirst = 3;
    }
}
