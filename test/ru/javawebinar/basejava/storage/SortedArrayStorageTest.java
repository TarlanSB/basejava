package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;


public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    private final static SortedArrayStorage storage = new SortedArrayStorage();

    private static final String UUID_1 = "uuid1";

    public SortedArrayStorageTest() {
        super(storage);
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(0, storage.getIndex(UUID_1));
    }
}