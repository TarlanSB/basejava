package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;
    private int index;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public void save(Resume r) {
        if (size > storage.length) {
            System.out.println("Переполнение storage");
            return;
        }
        if (getIndex(r.getUuid()) > 0) {
            System.out.println("Резюме \"" + r + "\" уже существует");
        } else {
            storage[size++] = r;
        }
    }

    public void update(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            storage[index] = r;
            System.out.println("Резюме с uuid \"" + r.getUuid() + "\" обновилось");
        } else {
            info(r.getUuid());
        }
    }

    void info(String uuid) {
        System.out.println("Такого резюме с uuid \"" + uuid + "\" нет");
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) >= 0) {
            return storage[index];
        }
        info(uuid);
        return null;
    }

    public void delete(String uuid) {
        if (getIndex(uuid) >= 0) {
            storage[index] = storage[size - 1];
            storage[size--] = null;
        } else {
            info(uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return index = i;
            }
        }
        return -1;
    }
}
