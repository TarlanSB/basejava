package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private final File directory;
    private final StreamSerializer strategy;

    protected FileStorage(File dir, StreamSerializer strategy) {
        Objects.requireNonNull(dir, "directory must not be null");
        Objects.requireNonNull(strategy, "strategy must not be null");

        this.strategy = strategy;
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not directory");
        }
        if (!dir.canRead() || !dir.canWrite()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not readable/writable");
        }

        this.directory = dir;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(file, r);
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        File[] files = getAllFiles();
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = getAllFiles();
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return getAllFiles().length;
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    private File[] getAllFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        return files;
    }
}
