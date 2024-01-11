package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.List;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, AbstractSection> sections = r.getSections();
            writeWithException(dos, sections.entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                AbstractSection abstractSection = entry.getValue();
                dos.writeUTF(sectionType.name());
                writeSection(dos, sectionType, abstractSection);
            });
        }
    }

    private void writeSection(DataOutputStream dos, SectionType sectionType,
                              AbstractSection abstractSection) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                writeTextSection(dos, (TextSection) abstractSection);
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                writeWithException(dos, ((ListSection) abstractSection).getList(), dos::writeUTF);
                break;
            case EXPERIENCE:
            case EDUCATION:
                writeWithException(dos, ((OrganizationSection) abstractSection).getOrganizations(), organization -> {
                    writeLink(dos, organization);
                    writeWithException(dos, organization.getPositions(), position -> {
                        writeLocalDate(dos, position.getStartDate());
                        writeLocalDate(dos, position.getEndDate());
                        dos.writeUTF(position.getTitle());
                        dos.writeUTF(position.getDescription());
                    });
                });
                break;
        }
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection,
                                        ItemWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T list : collection) {
            writer.write(list);
        }
    }

    private interface ItemWriter<T> {
        void write(T t) throws IOException;
    }

    private void writeTextSection(DataOutputStream dos, TextSection textSection) throws IOException {
        dos.writeUTF(textSection.getText());
    }

    private void writeLink(DataOutputStream dos, Organization organization) throws IOException {
        dos.writeUTF(organization.getHomePage().getName());
        dos.writeUTF(organization.getHomePage().getUrl());
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readContacts(dis, () -> resume.getContacts().put(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readSection(dis, resume);
            return resume;
        }
    }

    private void readContacts(DataInputStream dis, ContactDescription description) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            description.doDescription();
        }
    }

    private interface ContactDescription {
        void doDescription() throws IOException;
    }

    private void readSection(DataInputStream dis, Resume resume) throws IOException {
        final int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            Map<SectionType, AbstractSection> sections = resume.getSections();
            SectionType sectionType = SectionType.valueOf(dis.readUTF());
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    sections.put(sectionType, readTextSection(dis));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    sections.put(sectionType, new ListSection(readWithException(dis, dis::readUTF)));
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    sections.put(sectionType, new OrganizationSection(readWithException(dis, () ->
                            new Organization(readLink(dis), readWithException(dis, () ->
                                    new Organization.Position(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()))))));
                    break;
            }
        }
    }

    private TextSection readTextSection(DataInputStream dis) throws IOException {
        return new TextSection(dis.readUTF());
    }

    private <T> List<T> readWithException(DataInputStream dis, ItemReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ItemReader<T> {
        T read() throws IOException;
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        String url = dis.readUTF();
        return new Link(name, url);
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.of(dis.readInt()), 1);
    }
}
