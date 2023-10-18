package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.fail;

public class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp()  {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);

        storage.get(UUID_2);
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);

        Assert.assertEquals(r, storage.get(UUID_2));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Resume r = new Resume(UUID_2);

        Assert.assertEquals(r, storage.get(UUID_2));
    }

    @Test
    public void clear() {
        storage.clear();

        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume r1 = new Resume(UUID_1);
        Assert.assertEquals(r1, storage.get(UUID_1));

        Resume r2 = new Resume(UUID_2);
        Assert.assertEquals(r2, storage.get(UUID_2));

        Resume r3 = new Resume(UUID_3);
        Assert.assertEquals(r3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        try {
            for (int i = 0; i < 1000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Overflow has happen early");
        }
        storage.save(new Resume());
    }
}