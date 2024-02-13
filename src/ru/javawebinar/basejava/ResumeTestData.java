package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class ResumeTestData {

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
    }

    public Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.MOBILE_PHONE, "+7(909) 987-1946");
        resume.addContact(ContactType.SKYPE, "");
        resume.addContact(ContactType.EMAIL, "tarlanworkmail@gmail.com");
        resume.addContact(ContactType.LINKEDIN, "");
        resume.addContact(ContactType.GITHUB, "https://github.com/TarlanSB");
        resume.addContact(ContactType.STACKOVERFLOW, "");
        resume.addContact(ContactType.HOME_PAGE, "");

        resume.addSection(SectionType.PERSONAL, personal);
        resume.addSection(SectionType.OBJECTIVE, objective);
        resume.addSection(SectionType.ACHIEVEMENT, achievements);
        resume.addSection(SectionType.QUALIFICATIONS, qualifications);
        resume.addSection(SectionType.EXPERIENCE, experience);//
        resume.addSection(SectionType.EDUCATION, education);

        return resume;
    }
}
