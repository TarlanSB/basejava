package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;

public class MapUuidStorageTest extends AbstractMapStorageTest {

    private final static MapUuidStorage storage = new MapUuidStorage();
    private static final String UUID_1 = "uuid1";

    public MapUuidStorageTest() {
        super(storage);
    }

    @Test
    public void getSearchKey() {
        Assert.assertEquals(UUID_1, storage.getSearchKey(UUID_1));
    }
}