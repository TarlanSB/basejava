package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        info(uuid);
        return null;
    }

    void info(String uuid) {
        System.out.println("Такого резюме с uuid \"" + uuid + "\" нет");
    }


    protected abstract int getIndex(String uuid);
}
