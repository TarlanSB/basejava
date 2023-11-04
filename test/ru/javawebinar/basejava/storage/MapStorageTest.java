package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class MapStorageTest {

    private final MapStorage mapStorage = new MapStorage();

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    @Before
    public void setUp() {
        mapStorage.clear();
        mapStorage.save(RESUME_1);
        mapStorage.save(RESUME_2);
        mapStorage.save(RESUME_3);
    }

    @Test
    public void clear() {
      mapStorage.clear();
        Assert.assertEquals(0,  mapStorage.size());
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] got = mapStorage.getAll();

        Arrays.sort(got, Comparator.comparing(Resume::getUuid));
        Assert.assertArrayEquals(expected, got);
    }

    @Test
    public void getSearchKey() {

        Assert.assertEquals(UUID_1, mapStorage.getSearchKey(UUID_1));
    }
}