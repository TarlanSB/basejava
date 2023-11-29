package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume r) {
        storage[searchKey] = r;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size()));
    }

    protected boolean isNotSizeLimit(Resume r) {
        if (size < STORAGE_LIMIT) {
            return true;
        }
        throw new StorageException("Storage overflow", r.getUuid());
    }

    protected void reduceSize() {
        storage[size--] = null;
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}
