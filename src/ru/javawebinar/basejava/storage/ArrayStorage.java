package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        if(isNotSizeLimit(r)) {
            storage[size] = r;
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(Integer)searchKey] = storage[size - 1];
        reduceSize();
    }
}
