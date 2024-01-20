package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.List;

public class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    protected static final ResumeTestData RESUME_FACTORY = new ResumeTestData();

    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "fullName1";
    private static final Resume RESUME_1 = RESUME_FACTORY.createResume(UUID_1, FULL_NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME_2 = "fullName2";
    private static final Resume RESUME_2 = RESUME_FACTORY.createResume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_3 = "fullName3";
    private static final Resume RESUME_3 = RESUME_FACTORY.createResume(UUID_3, FULL_NAME_3);

    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME_4 = "fullName4";
    private static final Resume RESUME_4 = RESUME_FACTORY.createResume(UUID_4, FULL_NAME_4);

    private static final int SIZE = 3;
    private static final String UUID_NOT_EXIST = "dummy";
    private static final String UPDATE_FULL_NAME = "updateFullName";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_2);
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void update() throws Exception {
        Resume r = new Resume(UUID_2, UPDATE_FULL_NAME);
        storage.update(r);
        Assert.assertEquals(r, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void size() throws Exception {
        assertSize(SIZE);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[]{}, storage.getAllSorted().toArray(new Resume[0]));
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> expected = storage.getAllSorted();
        Assert.assertEquals(3, SIZE);
        Assert.assertEquals(expected, storage.getAllSorted());
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}