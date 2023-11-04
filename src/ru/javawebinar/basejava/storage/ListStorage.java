package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume resume = new Resume(uuid);
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).equals(resume)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Object searchKey, Resume r) {
        storageList.add(r);
    }

    @Override
    protected void remove(Object searchKey) {
        storageList.remove(getResumeByIndex(searchKey));
    }

    @Override
    protected void updateResume(Object searchKey, Resume r) {
        storageList.set((Integer) searchKey, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected Resume getResumeByIndex(Object searchKey) {
        return storageList.get((Integer) searchKey);
    }
}
