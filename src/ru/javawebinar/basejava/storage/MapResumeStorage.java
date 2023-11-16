package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected Resume doGet(Object resume) {
        return storage.get(getResumeUuid(resume));
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Object resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(getResumeUuid(resume));
    }

    @Override
    protected void doUpdate(Object resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(Object resume) {
//       return resume == null;
//       return storage.containsValue((Resume) resume);

        return storage.containsKey((String) resume);
    }

    private String getResumeUuid(Object resume) {
        return ((Resume) resume).getUuid();
    }
}
