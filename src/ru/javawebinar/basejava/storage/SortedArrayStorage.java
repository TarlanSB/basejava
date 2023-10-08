package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Переполнение storage");
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Резюме \"" + r + "\" уже существует");
        } else if (size == 0) {
            storage[size++] = r;
        } else {
            sort(r);
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    private void sort(Resume r) {
        int insertionPoint = Arrays.binarySearch(storage, 0, size, r);
        if (Math.abs(insertionPoint + 1) == size) {
            storage[size] = r;
        } else {
            for (int i = 0; i < size; i++) {
                if (i == Math.abs(insertionPoint + 1)) {
                    System.arraycopy(storage, i, storage, i + 1, size);
                    storage[Math.abs(insertionPoint + 1)] = r;
                }
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
            storage[size--] = null;
        } else {
            info(uuid);
        }
    }
}
