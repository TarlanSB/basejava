package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    private final static ArrayStorage storage = new ArrayStorage();

    public ArrayStorageTest() {
        super(storage);
    }
}
