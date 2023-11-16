package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage {
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
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        Resume[] sortedResumes = Arrays.copyOf(storage, size());
        return new ArrayList<>(Arrays.asList(sortedResumes));
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

    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }
}
