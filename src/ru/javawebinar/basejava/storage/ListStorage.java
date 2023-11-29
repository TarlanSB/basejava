package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(doGet(searchKey));
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return new ArrayList<>(storage);
    }
}
