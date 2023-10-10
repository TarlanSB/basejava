package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public final void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Переполнение storage");
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Резюме \"" + r + "\" уже существует");
        } else if (size == 0) {
            storage[size++] = r;
        } else {
            add(r);
            size++;
        }
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            info(uuid);
        } else {
            remove(index);
        }
    }

    @Override
    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Резюме с uuid \"" + r.getUuid() + "\" обновилось");
        } else {
            info(r.getUuid());
        }
    }

    void info(String uuid) {
        System.out.println("Такого резюме с uuid \"" + uuid + "\" нет");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        info(uuid);
        return null;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void add(Resume r);

    protected abstract void remove(int index);
}
