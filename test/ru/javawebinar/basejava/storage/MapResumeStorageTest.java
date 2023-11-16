package ru.javawebinar.basejava.storage;

public class MapResumeStorageTest extends AbstractMapStorageTest {

    private final static MapResumeStorage storage = new MapResumeStorage();

    public MapResumeStorageTest() {
        super(storage);
    }
}