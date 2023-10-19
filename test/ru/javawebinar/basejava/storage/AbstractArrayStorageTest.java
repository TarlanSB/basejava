package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.fail;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);

        assertSize(2);
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);

        Assert.assertSame(r, storage.get(UUID_2));
    }

    @Test
    public void size() {
        int size = 3;
        assertSize(size);
    }

    void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    @Test
    public void get() {
        assertGet(RESUME_1);

        assertGet(RESUME_2);

        assertGet(RESUME_3);
    }

    void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void clear() {
        storage.clear();

        assertSize(0);

        Assert.assertArrayEquals(new Resume[]{}, storage.getAll());
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        Resume resume = new Resume();

        storage.save(resume);

        assertGet(resume);

        assertSize(4);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 3; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Overflow has happen early");
        }
        storage.save(new Resume());
    }
}