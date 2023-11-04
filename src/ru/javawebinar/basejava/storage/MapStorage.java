package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    protected Resume getResumeByIndex(Object searchKey) {
        return mapStorage.get((String) searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void insertElement(Object searchKey, Resume r) {
        mapStorage.put((String) searchKey, r);
    }

    @Override
    protected void remove(Object searchKey) {
        mapStorage.remove((String) searchKey);
    }

    @Override
    protected void updateResume(Object searchKey, Resume r) {
        mapStorage.put((String) searchKey, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return mapStorage.containsKey((String) searchKey);
    }
}
