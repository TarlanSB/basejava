package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public class ListStorageTest {

    private final ListStorage storage = new ListStorage();

    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "fullName1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME_2 = "fullName2";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_3 = "fullName3";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void getSearchKey() {
        Integer expected = 0;
        Assert.assertEquals(expected, storage.getSearchKey(UUID_1));
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