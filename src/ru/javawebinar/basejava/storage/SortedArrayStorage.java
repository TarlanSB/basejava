package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Object searchKey, Resume r) {
        if(isNotSizeLimit(r)) {
            int insertionPoint = -(Integer) searchKey - 1;
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
            storage[insertionPoint] = r;
            size++;
        }
    }

    @Override
    protected void remove(Object searchKey) {
        System.arraycopy(storage, (Integer) searchKey + 1, storage, (Integer) searchKey, size - 1);
        reduceSize();
    }
}

