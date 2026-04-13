package collection;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Кастомная коллекция (список)
 * @param <T>
 */
public class List<T> extends AbstractList<T> {
    private int capacity = 100;
    private Object[] arr = new Object[capacity];
    private int size = 0;

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)arr[index];
    }

    @Override
    public boolean add(T object) {
        addCapacityIfNecessary();
        arr[size++] = object;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        for(T object : collection) {
            add(object);
        }
        return true;
    }

    @Override
    public void add(int index, T object) {
        addCapacityIfNecessary();
        for(int i = size - 1; i >= index; --i) {
            arr[i + 1] = arr[i];
        }
        arr[index] = object;
        ++size;
    }

    @Override
    public T set(int index, T object) {
        checkIndex(index);
        T replacedObject = (T)arr[index];
        arr[index] = object;
        return replacedObject;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedObject = (T)arr[index];
        for(int i = index; i < size - 1; ++i) {
            arr[i] = arr[i + 1];
        }
        --size;
        return removedObject;
    }

    @Override
    public boolean remove(Object object) {
        int i = 0;
        while (i < size && !arr[i].equals(object)) {
            ++i;
        }
        if (i < size) {
            remove(i);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<T> subList(int startIndex, int endIndex) {
        checkIndex(startIndex);
        checkLastIndex(endIndex);
        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Начальный индекс не может быть больше конечного");
        }
        List<T> result = new List<>();
        for(int index = startIndex; index < endIndex; ++index) {
            result.add((T)arr[index]);
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    public static <T> List<T> copyOf(List<T> list) {
        if (list == null) {
            return list;
        }
        List<T> result = new List<T>();
        for(T object : list) {
            result.add(object);
        }
        return result;
    }

    private void addCapacityIfNecessary() {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, arr.length + capacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkLastIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

}
