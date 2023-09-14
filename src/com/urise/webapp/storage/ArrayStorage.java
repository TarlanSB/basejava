package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private static int size;
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
        if (isEqual(r.getUuid())) {
            System.out.println("Резюме \"" + r + "\" уже существует");
        } else {
            storage[size++] = r;
        }
    }

    boolean isEqual(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                return true;
            }
        }
        return false;
    }

    public void update(Resume r) {
        if (isEqual(r.getUuid())) {
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
        if (isEqual(uuid)) {
            return storage[index];
        }
        info(uuid);
        return null;
    }

    public void delete(String uuid) {
        if (isEqual(uuid)) {
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
}
