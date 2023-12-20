package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class MainFile {

    public static void printDirectoryDeeply(File dir, String intent) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(intent + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(intent + "Directory: " + file.getName());

                    printDirectoryDeeply(file, "    ");
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
        File file2 = new File("./");
        printDirectoryDeeply(file2, "");
    }
}
