package ru.javawebinar.basejava.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    private final static SortedArrayStorage storage = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(storage);
    }
}