package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.*;

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

    private final LocalDate startDate1 = LocalDate.of(2013, 10, 1);
    private final LocalDate endDate1 = LocalDate.now();
    private final Period period1 = new Period(startDate1, endDate1, "Автор проекта.",
            "Создание, организация и проведение Java онлайн проектов и стажировок");

    private final Company company1 = new Company("Java Online Projects", "http://javaops.ru/",
            Arrays.asList(period1));

    private final LocalDate startDate2 = LocalDate.of(2013, 10, 1);
    private final LocalDate endDate2 = LocalDate.of(2016, 1, 1);
    private final Period period2 = new Period(startDate2, endDate2, "Старший разработчик (backend)",
            "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                    "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                    "авторизация по OAuth1, OAuth2, JWT SSO.");

    private final Company company2 = new Company("Wrike", "https://www.wrike.com/", Arrays.asList(period2));

    protected AbstractSection experience = new CompanySection(Arrays.asList(company1, company2));

    private final LocalDate startDate3 = LocalDate.of(1993, 9, 1);
    private final LocalDate endDate3 = LocalDate.of(1996, 7, 1);
    private final Period period3 = new Period(startDate3, endDate3, "Аспирантура (программист С, С++)", "");
    private final LocalDate startDate4 = LocalDate.of(1987, 9, 1);
    private final LocalDate endDate4 = LocalDate.of(1993, 7, 1);
    private final Period period4 = new Period(startDate4, endDate4, "Инженер (программист Fortran, C)", "");

    private final Company company3 = new Company("Санкт-Петербургский национальный исследовательский университет " +
            "информационныхтехнологий, механики и оптики", "http://www.ifmo.ru/",
            Arrays.asList(period3, period4));

    protected AbstractSection education = new CompanySection(Arrays.asList(company3));

    public ResumeTestData(){
        this.resume = new Resume("Грикорий Кислин");
    }

    public Resume createResume(String uuid, String fullName){
        Resume resume = new Resume(uuid, fullName);
        resume.getContacts().put(ContactType.MOBILE_PHONE, mobilePhone);
        resume.getContacts().put(ContactType.SKYPE, skype);
        resume.getContacts().put(ContactType.EMAIL, email);
        resume.getContacts().put(ContactType.LINKEDIN, linkedIn);
        resume.getContacts().put(ContactType.GITHUB, github);
        resume.getContacts().put(ContactType.STACKOVERFLOW, stackoverflow);
        resume.getContacts().put(ContactType.HOME_PAGE, homePage);

        resume.getSections().put(SectionType.PERSONAL, personal);
        resume.getSections().put(SectionType.OBJECTIVE, objective);
        resume.getSections().put(SectionType.ACHIEVEMENT, achievements);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualifications);
        resume.getSections().put(SectionType.EXPERIENCE, experience);
        resume.getSections().put(SectionType.EDUCATION, education);

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
                objective, achievements, qualifications, startDate1, endDate1, period1, company1, startDate2, endDate2,
                period2, company2, experience, startDate3, endDate3, period3, startDate4, endDate4, period4, company3,
                education);
    }

    @Override
    public String toString() {
        return "ResumeTestData{" +
                "resume=" + resume +
                '}';
    }
}

