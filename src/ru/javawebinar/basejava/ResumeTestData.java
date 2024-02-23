package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.*;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class ResumeTestData {

    private final AbstractSection personal = new TextSection("Аналитический склад ума, быстрая обучаемость, " +
            "целеустремленность, стрессоустойчивость, аккуратность, без вредных привычек");

    private final AbstractSection objective = new TextSection("Java Developer");

    private final AbstractSection achievements = new ListSection(Arrays.asList("Проект. Разрабатывали приложение для " +
            "мониторинга уровней шума на прилегающей территории вблизи объектов использования атомной энергии. " +
            "Приложение предоставляло пользователю информацию о текущей шумовой обстановки в виде числовых значений и" +
            " графических зависимостей.\n",
            "В рамках проекта произвели миграцию существующего приложения на современный стек технологий - " +
            "java 8, Spring, Hibernate.\n",
            "Обязанности: перенос кодовой базы с java 5 на java 8 " +
            "(использование Stream API, функциональных интерфейсов, лямбда-выражений, работа с датами), " +
            "реализация рест-контроллеров, разработка сервисного слоя с реализацией бизнес-логики, " +
            "проектирование DTO и сущностей БД, написание SQL-запросов к БД, unit-тестирование")
    );
    private final AbstractSection qualifications = new ListSection(Arrays.asList("Java 8, IntelliJ IDEA, GitHub/Git, " +
            "Сервлеты, JSP, JSTL, Tomcat, JUnit, PostgreSQL, GSON, JAXB", "АРМ Акустика, AutoCAD"));

    private final Organization organization1 = new Organization("АО ГСПИ", "https://www.aogspi.ru//",
            new Organization.Position(of(2023, Month.JANUARY), NOW, "java Developer",
                    "Разработка приложения для мониторинга уровней шума на прилегающей территории вблизи " +
                            "объектов использования атомной энергии."),
            new Organization.Position(of(2013, Month.SEPTEMBER), of(2023, Month.JANUARY),
                    "Инженер-проектировщик.",
                    "Разработка проектной документации \"Мероприятия по защите от шума и вибрации\""));

    private final Organization organization2 = new Organization("ООО ДТ-ФОТО", "",
            new Organization.Position(of(2010, Month.JANUARY), of(2013, Month.SEPTEMBER), "Менеджер",
                    ""));

    private final Organization organization3 = new Organization("ООО КПаскаль", "",
            new Organization.Position(of(2009, Month.MARCH), of(2010, Month.JANUARY), "Помошник менеджера",
                    ""));

    //Собираем experience
    protected AbstractSection experience = new OrganizationSection(organization1, organization2, organization3);

    private final Organization organization4 = new Organization("java online project",
            "https://javaops.ru/",
            new Organization.Position(of(2023, Month.SEPTEMBER), of(2024, Month.FEBRUARY), "BASEJAVA",
                    "Участие в разработке полнофункционального веб-приложения \"База данных резюме\" " +
                            "по темам курса: массивы, коллекции, объектная модель, система ввода-вывода, работа " +
                            "с файлами, сериализация, работа с XML, JSON, SQL, сохранение в базу данных PostgreSQL, " +
                            "сервлеты, JSP/JSTL, web-контайнер Tomcat, HTML, JUnit, логирование, система контроля " +
                            "версий Git и деплоем приложения на собственный выделенный сервер.\n" +
                            "Participation in the development of a full-featured web-application \"Resume Database\" " +
                            "based on the following topics: arrays, collections, object model, input/output system, " +
                            "working with files, serialization, working with XML, JSON, SQL, saving to the PostgreSQL " +
                            "database, servlets, JSP/JSTL, Tomcat, HTML, JUnit, logging, Git version-control system and " +
                            "deployment to owns VDS"),
            new Organization.Position(of(2023, Month.MAY), of(2023, Month.SEPTEMBER), "STARTJAVA",
                    "Освоен синтаксис языка Java, массивы, ООП, работа с консолью, основы Git, SQL и PostgreSQL\n" +
                            "Mastered the syntax of Java, arrays, OOP, working with the console, the Git basics, SQL and" +
                            " PostgreSQL"));

    private final Organization organization5 = new Organization("Udemy", "https://www.udemy.com/",
            new Organization.Position(of(2024, Month.FEBRUARY), of(2024, Month.FEBRUARY), "Beginner's " +
                    "Guide to SQL and PostgreSQL", ""),
            new Organization.Position(of(2023, Month.JANUARY), of(2023, Month.JANUARY), "JDBC",
                    ""),
            new Organization.Position(of(2022, Month.SEPTEMBER), of(2022, Month.OCTOBER), "Spring для " +
                    "начинающих", ""),
            new Organization.Position(of(2021, Month.OCTOBER), of(2021, Month.DECEMBER), "Java(Джава) " +
                    "для начинающих: с нуля до сертификата Oracle", ""));


    private final Organization organization6 = new Organization("Московский государственный университет " +
            "природообустройства", "",
            new Organization.Position(of(2003, Month.SEPTEMBER), of(2008, Month.JULY), "Инженер-механик",
                    ""));

    //Собираем education
    protected AbstractSection education = new OrganizationSection(organization4, organization5, organization6);

    public ResumeTestData() {
    }

    public Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.setContact(ContactType.MOBILE_PHONE, "+7(909) 987-1946");
        resume.setContact(ContactType.SKYPE, "");
        resume.setContact(ContactType.EMAIL, "tarlanworkmail@gmail.com");
        resume.setContact(ContactType.LINKEDIN, "");
        resume.setContact(ContactType.GITHUB, "https://github.com/TarlanSB");
        resume.setContact(ContactType.STACKOVERFLOW, "");
        resume.setContact(ContactType.HOME_PAGE, "");

        resume.setSection(SectionType.PERSONAL, personal);
        resume.setSection(SectionType.OBJECTIVE, objective);
        resume.setSection(SectionType.ACHIEVEMENT, achievements);
        resume.setSection(SectionType.QUALIFICATIONS, qualifications);
        resume.setSection(SectionType.EXPERIENCE, experience);//
        resume.setSection(SectionType.EDUCATION, education);

        return resume;
    }
}
