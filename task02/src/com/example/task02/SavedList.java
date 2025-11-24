package com.example.task02;

import javax.xml.bind.Element;
import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File file;
    private List<E> savedList = new ArrayList<>();

    public SavedList(File file) {
        this.file = file;

        if (file.exists() && file.isFile()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(
                    Files.newInputStream(file.toPath()))) {

                savedList = (List<E>) objectInputStream.readObject();


            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Ошибка загрузки данных", e);
            }
        }
    }

    private void writeToFile() {
        try (ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            stream.writeObject(savedList);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи данных", e);
        }
    }



    @Override
    public E get(int index) {
        return savedList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E Element = savedList.set(index, element);
        writeToFile();
        return Element;
    }

    @Override
    public int size() {
        return savedList.size();
    }

    @Override
    public void add(int index, E element) {
        savedList.add(index, element);
        writeToFile();
    }

    @Override
    public E remove(int index) {
        E Element = savedList.remove(index);
        writeToFile();
        return Element;
    }
}
