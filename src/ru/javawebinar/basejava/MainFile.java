package ru.javawebinar.basejava;

import ru.javawebinar.basejava.exception.StorageException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MainFile {

    public static void printDirectoryDeeply(File dir) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.printf("%10s%s%n","File: ",file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    printDirectoryDeeply(file);
                }
            }
        }
    }

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Рекурсивный обход и вывод имени файлов в каталогах и подкаталогах");
        File file2 = new File("./src/ru/javawebinar/basejava");
        printDirectoryDeeply(file2);
    }
}