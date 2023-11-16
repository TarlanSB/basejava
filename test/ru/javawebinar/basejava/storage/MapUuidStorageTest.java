package ru.javawebinar.basejava.storage;

public class MapUuidStorageTest extends AbstractMapStorageTest {

    private final static MapUuidStorage storage = new MapUuidStorage();

    public MapUuidStorageTest() {
        super(storage);
    }
}