package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.*;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class ResumeTestData {

    private final AbstractSection personal = new TextSection("Аналитический склад ума, быстрая обучаемость, " +
            "целеустремленность, стрессоустойчивость, аккуратность, без вредных привычек");

    private final AbstractSection objective = new TextSection("Ведущий инженер проектировщик");

    private final AbstractSection achievements = new ListSection(Arrays.asList("РАЗРАБОТКА тома \"МЕРОПРИЯТИЯ ПО ЗАЩИТЕ" +
                    " ОТ ШУМА И ВИБРАЦИИ\"",
            "Расчет акустическог воздействия от точечных, линейных, полигональных источников шума",
            "Расчет проникающего внешнего шума в помещение",
            "Построение шумовых карт воздействия источников шума на оприлегающию территрию",
            "ОПРЕДЕЛЕНИЕ размеров границ расчетной САНИТАРНО-ЗАЩИТНОЙ ЗОНЫ",
            "Расчет уровней зувукового давления в помещениях",
            "РАЗРАБОТКА МЕРОПРИЯТИЙ по защите от шума",
            "ПРОХОЖДЕНИЕ ГГЭ и ГЭЭ")
    );
    private final AbstractSection qualifications = new ListSection(Arrays.asList("АРМ Акустика, AutoCAD",
            "Java 8, IntelliJ IDEA, GitHub/Git, Сервлеты, JSP, JSTL, Tomcat, JUnit, PostgreSQL, GSON, JAXB")
    );

    private final Organization organization1 = new Organization("АО ГСПИ", "https://www.aogspi.ru//",
            new Organization.Position(of(2013, Month.SEPTEMBER), NOW, "Инженер-проектировщик.",
                    "РАЗРАБОТКА тома \"МЕРОПРИЯТИЯ ПО ЗАЩИТЕ ОТ ШУМА И ВИБРАЦИИ\""));

    private final Organization organization2 = new Organization("ООО ДТ-ФОТО", "",
            new Organization.Position(of(2010, Month.JANUARY), of(2013, Month.SEPTEMBER), "Менеджер",
                    ""));

    private final Organization organization3 = new Organization("ООО КПаскаль", "",
            new Organization.Position(of(2010, Month.MARCH), of(2013, Month.JANUARY), "Помошник менеджера",
                    ""));

    //Собираем experience
    protected AbstractSection experience = new OrganizationSection(organization1, organization2, organization3);

    private final Organization organization4 = new Organization("java online project",
            "https://javaops.ru/view/baejava",
            new Organization.Position(of(2023, Month.SEPTEMBER), of(2024, Month.FEBRUARY), "BASEJAVA",
                    ""));

    private final Organization organization5 = new Organization("UDEMY", "https://www.udemy.com/",
            new Organization.Position(of(2024, Month.FEBRUARY), of(2024, Month.FEBRUARY), "Beginner's " +
                    "Guide to SQL and PostgreSQL", ""));

    private final Organization organization6 = new Organization("java online project",
            "https://javaops.ru/view/startjava",
            new Organization.Position(of(2023, Month.MAY), of(2023, Month.SEPTEMBER), "STARTJAVA",
                    "Mastered the syntax of Java, arrays, OOP, working with the console, the Git basics, SQL and" +
                            " PostgreSQL"));

    private final Organization organization7 = new Organization("UDEMY", "https://www.udemy.com/",
            new Organization.Position(of(2023, Month.JANUARY), of(2023, Month.JANUARY), "JDBC",
                    ""));

    private final Organization organization8 = new Organization("UDEMY", "https://www.udemy.com/",
            new Organization.Position(of(2022, Month.SEPTEMBER), of(2022, Month.OCTOBER), "Spring для " +
                    "начинающих", ""));

    private final Organization organization9 = new Organization("UDEMY", "https://www.udemy.com/",
            new Organization.Position(of(2021, Month.OCTOBER), of(2021, Month.DECEMBER), "Java(Джава) " +
                    "для начинающих: с нуля до сертификата Oracle", ""));

    private final Organization organization10 = new Organization("Московский государственный университет " +
            "природообустройства", "",
            new Organization.Position(of(2003, Month.SEPTEMBER), of(2008, Month.JULY), "Инженер-механик",
                    ""));

    //Собираем education
    protected AbstractSection education = new OrganizationSection(organization4, organization5, organization6,
            organization7, organization8, organization9, organization10);

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
