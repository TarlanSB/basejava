package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage{

    @Override
    public final void save(Resume r) {
        if (isSizeLimit()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (getIndex(r.getUuid()) >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            add(r);
        }
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            remove(index);
        }
    }

    @Override
    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            updateResume(index, r);
            System.out.println("Резюме с uuid \"" + r.getUuid() + "\" обновилось");
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return getResumeByIndex(index);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Resume getResumeByIndex(int index);

    protected abstract boolean isSizeLimit();
    protected abstract int getIndex(String uuid);

    protected abstract void add(Resume r);

    protected abstract void remove(int index);

    protected abstract void updateResume(int index, Resume r);
}
