package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void add(Resume r) {
        int insertionPoint = Math.abs(getIndex(r.getUuid()) + 1);
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size);
        storage[insertionPoint] = r;
    }

    @Override
    protected void remove(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1);
        storage[size--] = null;
    }
}

