//Isaac Lichter
package myarrayliststring;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements List<T> {

    private T[] backingStore;
    private int insertionPoint;
    private int modificationCount;

    public MyArrayList() {
        backingStore = (T[]) new Object[5];
        modificationCount = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException(String.format("Your index can't be negative. It was %d.", capacity));
        }

        backingStore = (T[]) new Object[capacity];
        modificationCount = 0;
    }

    @Override
    public boolean add(T e) {
        if (insertionPoint > backingStore.length) {
            ensureCapacity();
        }
        backingStore[insertionPoint] = e;
        insertionPoint++;
        modificationCount++;
        return true;
    }

    @Override
    public int size() {
        return insertionPoint;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (backingStore[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= insertionPoint) {
            throw new IndexOutOfBoundsException(
                    String.format("index = [%d]. Maximum is [%d]", index, insertionPoint - 1));
        }
        return (T) backingStore[index];
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size(); i++) {
            if (backingStore[i].equals(o)) {
                this.remove(i);
                modificationCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size(); i++) {
            backingStore[i] = null;
        }
        insertionPoint = 0;
        modificationCount++;
    }

    @Override
    public T set(int index, T element) {
        checkInBounds(index);
        while (index > backingStore.length - 1) {
            ensureCapacity();
        }
        T str = backingStore[index];
        backingStore[index] = element;
        modificationCount++;
        return str;
    }

    @Override
    public void add(int index, T element) {
        checkInBounds(index);
        while (index > backingStore.length - 1 || this.size() == backingStore.length) {
            ensureCapacity();
        }
        for (int i = this.size(); i > index; i--) {
            backingStore[i] = backingStore[i - 1];
        }
        backingStore[index] = element;
        if (this.size() < index) {
            insertionPoint = index;
        } else {
            insertionPoint++;
        }
        modificationCount++;
    }

    @Override
    public T remove(int index) {
        checkInBounds(index);
        if (index > this.size() - 1) {
            throw new IndexOutOfBoundsException(
                    String.format("Your index was out of bounds. Your index was %d, and the maximum element in the array is %d.", index, this.size()));
        }
        T str = backingStore[index];
        while (index < this.size() - 1) {
            backingStore[index] = backingStore[index + 1];
            index++;
        }
        insertionPoint--;
        modificationCount++;
        return str;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size(); i++) {
            if (backingStore[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.size() - 1; i >= 0; i--) {
            if (backingStore[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity() {
        T temp[] = (T[]) new Object[backingStore.length * 2 + 1];
        System.arraycopy(backingStore, 0, temp, 0, size());
        backingStore = temp;
    }

    private void checkInBounds(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException(
                    String.format("Your index was out of bounds. Your index was %d. It must be a nonnegative integer within the size.", index));
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] temp = c.toArray();

        for (int i = 0; i < temp.length; i++) {
            if (!this.contains(temp[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {

        if (c.size() == 0) {
            return false;
        }

        Object[] temp = c.toArray();

        while (c.size() + this.size() > backingStore.length) {
            ensureCapacity();
        }

        System.arraycopy(temp, 0, backingStore, this.size(), c.size());

        insertionPoint += c.size();

        modificationCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {

        if (c.size() == 0) {
            return false;
        }

        Object[] temp = c.toArray();

        checkInBounds(index);

        while (c.size() + this.size() > backingStore.length) {
            ensureCapacity();
        }

        System.arraycopy(temp, 0, backingStore, index, c.size());

        insertionPoint += c.size();

        modificationCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (int backingStoreIndex = 0; backingStoreIndex < this.size(); backingStoreIndex++) {
            if (c.contains(backingStore[backingStoreIndex])) {
                this.remove(backingStoreIndex);
                isChanged = true;
                backingStoreIndex--;
                modificationCount++;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (int backingStoreIndex = 0; backingStoreIndex < this.size(); backingStoreIndex++) {
            if (!c.contains(backingStore[backingStoreIndex])) {
                this.remove(backingStoreIndex);
                isChanged = true;
                backingStoreIndex--;
                modificationCount++;
            }
        }
        return isChanged;
    }

    @Override
    public Object[] toArray() {
        T temp[] = (T[]) new Object[size()];
        System.arraycopy(backingStore, 0, temp, 0, size());
        return temp;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            return (T[]) this.toArray();
        }
        System.arraycopy(backingStore, 0, a, 0, size());
        return a;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    //HW7
    private class MyArrayListIterator implements Iterator<T> {

        int boundaryBeforeElement;
        int modificationCount;
        boolean isRemovable;

        public MyArrayListIterator() {
            boundaryBeforeElement = 0;
            modificationCount = MyArrayList.this.modificationCount;
            isRemovable = false;
        }

        @Override
        public boolean hasNext() {
            checkConcurrentModification();
            if (this.boundaryBeforeElement < MyArrayList.this.insertionPoint) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            checkConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element.");
            }
            isRemovable = true;
            return backingStore[boundaryBeforeElement++];
        }

        @Override
        public void remove() {
            checkConcurrentModification();

            if (!isRemovable) {
                throw new IllegalStateException("there is no element to remove.");
            }

            MyArrayList.this.remove(backingStore[boundaryBeforeElement - 1]);

            isRemovable = false;
            modificationCount++;
        }

        //Extra Credit
        private void checkConcurrentModification() {
            if (modificationCount != MyArrayList.this.modificationCount) {
                throw new ConcurrentModificationException("the data that the iterator ");
            }
        }
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
