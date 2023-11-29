package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage <SK> implements Storage {

   protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    public final void save(Resume r) {
        SK searchKey = getExistingSearchKey(r.getUuid());
        doSave(searchKey, r);
    }

    @Override
    public final void delete(String uuid) {
        SK searchKey = getNotExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public final void update(Resume r) {
        SK searchKey = getNotExistingSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    @Override
    public final Resume get(String uuid) {
        SK searchKey = getNotExistingSearchKey(uuid);
        return doGet(searchKey);
    }


    private SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {

        List<Resume> list = doGetAllSorted();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doSave(SK searchKey, Resume r);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(SK searchKey, Resume r);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List <Resume> doGetAllSorted();
}
