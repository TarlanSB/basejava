package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

public class ListStorageTest {

    private final ListStorage listStorage = new ListStorage();

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    @Before
    public void setUp() {
        listStorage.clear();
        listStorage.save(RESUME_1);
        listStorage.save(RESUME_2);
        listStorage.save(RESUME_3);
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(0, listStorage.getIndex(UUID_1));
    }

    @Test
    public void clear() {
        listStorage.clear();
        Assert.assertEquals(0, listStorage.size());
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(expected, listStorage.getAll());
    }
}