package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class ResumeTestData {

    private final Resume resume;

    private final String mobilePhone = "+7(921) 855-0482";
    private final String skype = "grigory.kislin";
    private final String email = "gkislin@yandex.ru";
    private final String linkedIn = "https://www.linkedin.com/in/gkislin";
    private final String github = "https://github.com/gkislin";
    private final String stackoverflow = "https://stackoverflow.com/users/548473";
    private final String homePage = "http://gkislin.ru/";

    private final AbstractSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, " +
            "инициативность. Пурист кода и архитектуры.");

    private final AbstractSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web " +
            "и Enterprise технологиям");

    private final AbstractSection achievements = new ListSection(Arrays.asList("Организация команды и успешная реализация " +
                    "Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, " +
                    "система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, " +
                    "многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
            "\n С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven." +
                    " Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                    " Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
            "\n Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с " +
                    "Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.")
    );
    private final AbstractSection qualifications = new ListSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, " +
                    "JBoss, Tomcat, Jetty, WebLogic, WSO2",
            "\nVersion control: Subversion, Git, Mercury, ClearCase, Perforce",
            "\nDB: PostgreSQL(наследование, pgplsql, PL/Python),Redis (Jedis),H2,Oracle,MySQL,SQLite, MS SQL, HSQLDB",
            "\nLanguages:Java,Scala,Python/Jython/PL-Python,JavaScript,Groovy")
    );

    private final LocalDate startDate1 = of(2013, Month.OCTOBER);
    private final LocalDate endDate1 = NOW;
    private final Organization.Position position1 = new Organization.Position(startDate1, endDate1, "Автор проекта.",
            "Создание, организация и проведение Java онлайн проектов и стажировок");

    private final Organization organization1 = new Organization("Java Online Projects", "http://javaops.ru/",
            position1);

    private final LocalDate startDate2 = of(2013, Month.OCTOBER);
    private final LocalDate endDate2 = of(2016, Month.OCTOBER);
    private final Organization.Position position2 = new Organization.Position(startDate2, endDate2, "Старший разработчик (backend)",
            "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                    "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                    "авторизация по OAuth1, OAuth2, JWT SSO.");

    private final Organization organization2 = new Organization("Wrike", "https://www.wrike.com/", position2);

    protected AbstractSection experience = new OrganizationSection(organization1, organization2);

    private final LocalDate startDate3 = of(1993, Month.SEPTEMBER);
    private final LocalDate endDate3 = of(1996, Month.JULY);
    private final Organization.Position position3 = new Organization.Position(startDate3, endDate3, "Аспирантура (программист С, С++)", "");
    private final LocalDate startDate4 = of(1987, Month.SEPTEMBER);
    private final LocalDate endDate4 = of(1993, Month.JULY);
    private final Organization.Position position4 = new Organization.Position(startDate4, endDate4, "Инженер (программист Fortran, C)", "");

    private final Organization organization3 = new Organization("Санкт-Петербургский национальный исследовательский университет " +
            "информационныхтехнологий, механики и оптики", "http://www.ifmo.ru/", position3, position4);

    protected AbstractSection education = new OrganizationSection(organization3);

    public ResumeTestData() {
        this.resume = new Resume("Грикорий Кислин");

        resume.addContact(ContactType.MOBILE_PHONE, mobilePhone);
        resume.addContact(ContactType.SKYPE, skype);
        resume.addContact(ContactType.EMAIL, email);
        resume.addContact(ContactType.LINKEDIN, linkedIn);
        resume.addContact(ContactType.GITHUB, github);
        resume.addContact(ContactType.STACKOVERFLOW, stackoverflow);
        resume.addContact(ContactType.HOME_PAGE, homePage);
//
//        resume.addSection(SectionType.PERSONAL, personal);
//        resume.addSection(SectionType.OBJECTIVE, objective);
//        resume.addSection(SectionType.ACHIEVEMENT, achievements);
//        resume.addSection(SectionType.QUALIFICATIONS, qualifications);
//        resume.addSection(SectionType.EXPERIENCE, experience);//
//        resume.addSection(SectionType.EDUCATION, education);
    }

    public Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.getContacts().put(ContactType.MOBILE_PHONE, mobilePhone);
        resume.getContacts().put(ContactType.SKYPE, skype);
        resume.getContacts().put(ContactType.EMAIL, email);
        resume.getContacts().put(ContactType.LINKEDIN, linkedIn);
        resume.getContacts().put(ContactType.GITHUB, github);
        resume.getContacts().put(ContactType.STACKOVERFLOW, stackoverflow);
        resume.getContacts().put(ContactType.HOME_PAGE, homePage);
//
//        resume.getSections().put(SectionType.PERSONAL, personal);
//        resume.getSections().put(SectionType.OBJECTIVE, objective);
//        resume.getSections().put(SectionType.ACHIEVEMENT, achievements);
//        resume.getSections().put(SectionType.QUALIFICATIONS, qualifications);
//        resume.getSections().put(SectionType.EXPERIENCE, experience);
//        resume.getSections().put(SectionType.EDUCATION, education);

        return resume;
    }

    public static void main(String[] args) {
        ResumeTestData resumeTestData = new ResumeTestData();

        System.out.println(resumeTestData.getResume());

        for (Map.Entry<ContactType, String> entry : resumeTestData.getResume().getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + " " + entry.getValue());
        }

        for (Map.Entry<SectionType, AbstractSection> entry : resumeTestData.getResume().getSections().entrySet()) {
            System.out.println("\n" + entry.getKey().getTitle() + ":\n" + entry.getValue());
        }
    }

    public Resume getResume() {
        return resume;
    }


    @Override
    public int hashCode() {
        return Objects.hash(resume, mobilePhone, skype, email, linkedIn, github, stackoverflow, homePage, personal,
                objective, achievements, qualifications, startDate1, endDate1, position1, organization1, startDate2, endDate2,
                position2, organization2, experience, startDate3, endDate3, position3, startDate4, endDate4, position4, organization3,
                education);
    }

    @Override
    public String toString() {
        return "ResumeTestData{" +
                "resume=" + resume +
                '}';
    }
}

