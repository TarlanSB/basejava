package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorageTest extends AbstractMapStorageTest {

    private final static MapResumeStorage storage = new MapResumeStorage();
    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "fullName1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);

    public MapResumeStorageTest() {
        super(storage);
    }

    @Test
    public void getSearchKey() {
        Assert.assertEquals(RESUME_1, storage.getSearchKey(UUID_1));
    }
}