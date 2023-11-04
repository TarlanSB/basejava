package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void save(Resume r) {
        insertElement(getExistingSearchKey(r.getUuid()), r);
    }

    @Override
    public final void delete(String uuid) {
        remove(getNotExistingSearchKey(uuid));
    }

    @Override
    public final void update(Resume r) {
        Object searchKey = r.getUuid();
        updateResume(getNotExistingSearchKey(searchKey.toString()), r);
    }

    @Override
    public final Resume get(String uuid) {
        return getResumeByIndex(getNotExistingSearchKey(uuid));
    }


    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Resume getResumeByIndex(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void insertElement(Object searchKey, Resume r);

    protected abstract void remove(Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume r);

    protected abstract boolean isExist(Object searchKey);
}
