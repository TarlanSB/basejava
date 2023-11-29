package ru.javawebinar.basejava.storage;

import org.junit.Test;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("FullName_" + i));
            }
        } catch (StorageException e) {
            fail("Overflow has happen early");
        }
        storage.save(new Resume("Overflow"));
    }
}