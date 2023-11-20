package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class
MapResumeStorage extends AbstractMapStorage {

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(Object resume) {
        return storage.containsValue((Resume) resume);
    }

    @Override
    public Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }
}

