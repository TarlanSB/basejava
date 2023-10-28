package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storageList = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storageList.contains(resume) ? storageList.indexOf(resume) : -1;
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    protected void add(Resume r) {
        storageList.add(r);
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[0]);
    }

    @Override
    protected void remove(int index) {
        storageList.remove(index);
    }

    @Override
    protected boolean isSizeLimit() {
        return storageList.size() == Integer.MAX_VALUE;
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storageList.set(index, r);
    }

    @Override
    protected Resume getResumeByIndex(int index) {
        return storageList.get(index);
    }
}
