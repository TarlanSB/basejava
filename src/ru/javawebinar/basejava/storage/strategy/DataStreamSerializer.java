package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.util.*;
import java.util.List;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            Map<SectionType, AbstractSection> sections = r.getSections();

            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                AbstractSection abstractSection = entry.getValue();

                writeSection(dos, sectionType, abstractSection);
            }
        }
    }

    private void writeSection(DataOutputStream dos, SectionType sectionType,
                              AbstractSection abstractSection) throws IOException {
        dos.writeUTF(sectionType.getTitle());
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) abstractSection).getText());
            case ACHIEVEMENT, QUALIFICATIONS -> {
                dos.writeInt(((ListSection) abstractSection).getList().size());
                for (String s : ((ListSection) abstractSection).getList()) {
                    dos.writeUTF(s);
                }
            }
            case EXPERIENCE, EDUCATION -> {
                List<Organization> organizations = ((OrganizationSection) abstractSection).getOrganizations();
                dos.writeInt(organizations.size());
                ObjectOutputStream oos = new ObjectOutputStream(dos);
                for (Organization organization : organizations) {
                    oos.writeObject(organization);
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            while (dis.available() > 0) {
                String sectionTypeRaw = dis.readUTF();

                SectionType sectionType = EnumSet.allOf(SectionType.class)
                        .stream()
                        .filter(s -> s.getTitle().equals(sectionTypeRaw))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("SectionType is absent"));

                Map<SectionType, AbstractSection> sections = resume.getSections();

                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> sections.put(sectionType, readTextSection(dis));
                    case ACHIEVEMENT, QUALIFICATIONS -> sections.put(sectionType, readListSection(dis));
                    case EXPERIENCE, EDUCATION -> sections.put(sectionType, readOrganizationSection(dis));
                }
            }
            return resume;

        }
    }

    private TextSection readTextSection(DataInputStream dis) throws IOException {
        return new TextSection(dis.readUTF());
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        return new ListSection(list);
    }

    private OrganizationSection readOrganizationSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Organization> organizations = new ArrayList<>();

        ObjectInputStream ois = new ObjectInputStream(dis);
        try {
            for (int i = 0; i < size; i++) {
                organizations.add((Organization) ois.readObject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new OrganizationSection(organizations);
    }
}
