//Isaac Lichter
package ListVsHashSpeedTest;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class ListSet<T> implements Set<T> {

    private LinkedList<T> list;

    public ListSet() {
        this.list = new LinkedList<T>();
    }

    @Override
    public boolean add(T e) {
        if (list.contains(e))
            return false;
        return list.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isSuccessful = true;
        for (T e : c){
            if (list.contains(e))
                isSuccessful = false;
            else list.add(e);
        }
        return isSuccessful;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

}
