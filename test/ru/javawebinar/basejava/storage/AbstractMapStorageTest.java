package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import java.util.List;

public abstract class AbstractMapStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "fullName1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME_2 = "fullName2";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_3 = "fullName3";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME_4 = "fullName4";
    private static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);

    private static final int SIZE = 3;
    private static final String UUID_NOT_EXIST = "dummy";
    private static final String UPDATE_FULL_NAME = "updateFullName";

    public AbstractMapStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_2, UPDATE_FULL_NAME);
        storage.update(r);
        Assert.assertSame(r, storage.get(UUID_2));
    }

    @Test
    public void size() {
        assertSize(SIZE);
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

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(expected, storage.getAllSorted());
    }
}