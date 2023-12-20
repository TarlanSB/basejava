package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    public Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "SearchKeyFullName");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void doSave(Integer searchKey, Resume r) {
        if (isNotSizeLimit(r)) {
            int insertionPoint = -(Integer) searchKey - 1;
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
            storage[insertionPoint] = r;
            size++;
        }
    }

    @Override
    protected void doDelete(Integer searchKey) {
        System.arraycopy(storage, searchKey + 1, storage, searchKey, size - 1);
        reduceSize();
    }
}

