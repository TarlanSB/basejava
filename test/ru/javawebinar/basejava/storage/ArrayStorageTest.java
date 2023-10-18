package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    private final static ArrayStorage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";

    public ArrayStorageTest() {
        super(storage);
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(0, storage.getIndex(UUID_1));
    }
}
