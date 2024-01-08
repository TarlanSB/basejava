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
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

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
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> writeTextSection(dos, sectionType, (TextSection) abstractSection);
            case ACHIEVEMENT, QUALIFICATIONS -> writeListSection(dos, sectionType, (ListSection) abstractSection);
            case EXPERIENCE, EDUCATION ->
                    writeOrganizationSection(dos, sectionType, ((OrganizationSection) abstractSection).getOrganizations());
        }
    }

    private void writeTextSection(DataOutputStream dos, SectionType sectionType, TextSection textSection) throws IOException {
        dos.writeUTF(sectionType.name());
        dos.writeUTF(textSection.getText());
    }

    private void writeListSection(DataOutputStream dos, SectionType sectionType, ListSection listSection) throws IOException {
        dos.writeUTF(sectionType.name());
        dos.writeInt(listSection.getList().size());
        for (String s : listSection.getList()) {
            dos.writeUTF(s);
        }
    }

    private void writeOrganizationSection(DataOutputStream dos, SectionType sectionType,
                                          List<Organization> organizations) throws IOException {
        dos.writeUTF(sectionType.name());
        dos.writeInt(organizations.size());
        for (Organization organization : organizations) {
            writeLink(dos, organization);
            writeOrganizationPosition(dos, organization.getPositions());
        }
    }

    private void writeLink(DataOutputStream dos, Organization organization) throws IOException {
        dos.writeUTF(organization.getHomePage().getName());
        dos.writeUTF(organization.getHomePage().getUrl());
    }

    private void writeOrganizationPosition(DataOutputStream dos, List<Organization.Position> positions) throws IOException {
        dos.writeInt(positions.size());
        for (Organization.Position position : positions) {
            dos.writeUTF(position.getDescription());
            dos.writeUTF(position.getTitle());

            LocalDate ld = position.getStartDate();
            writeLocalDate(dos, ld);

            LocalDate ld2 = position.getEndDate();
            writeLocalDate(dos, ld2);
        }
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
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            readSection(dis, resume);
            return resume;
        }
    }

    private void readSection(DataInputStream dis, Resume resume) throws IOException {
        while (dis.available() > 0) {
            Map<SectionType, AbstractSection> sections = resume.getSections();
            SectionType sectionType = SectionType.valueOf(dis.readUTF());
            switch (sectionType) {
                case PERSONAL, OBJECTIVE -> sections.put(sectionType, readTextSection(dis));
                case ACHIEVEMENT, QUALIFICATIONS -> sections.put(sectionType, readListSection(dis));
                case EXPERIENCE, EDUCATION -> sections.put(sectionType, readOrganizationSection(dis));
            }
        }
    }

    private TextSection readTextSection(DataInputStream dis) throws IOException {
        return new TextSection(dis.readUTF());
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        return new ListSection(list);
    }

    private OrganizationSection readOrganizationSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Organization> organizations = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Link homepage = readLink(dis);
            organizations.add(new Organization(homepage, readPositions(dis)));
        }
        return new OrganizationSection(organizations);
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        String url = dis.readUTF();
        return new Link(name, url);
    }

    private List<Organization.Position> readPositions(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Organization.Position> listPositions = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String description = dis.readUTF();
            String title = dis.readUTF();
            LocalDate startDate = readLocalDate(dis);
            LocalDate endDate = readLocalDate(dis);
            listPositions.add(new Organization.Position(startDate, endDate, title, description));
        }
        return listPositions;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.of(dis.readInt()), 1);
    }
}
