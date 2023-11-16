package ru.javawebinar.basejava.storage;

import org.junit.Assert;

import org.junit.Test;


public class MapResumeStorageTest  extends AbstractMapStorageTest {

    private final static MapResumeStorage storage = new MapResumeStorage();

    private static final String UUID_1 = "uuid1";

    public MapResumeStorageTest() {
        super(storage);
    }

    @Test
    public void getSearchKey() {
        Assert.assertEquals(UUID_1, storage.getSearchKey(UUID_1));
    }
}