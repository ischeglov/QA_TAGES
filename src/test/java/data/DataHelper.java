package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private static final Faker FAKER = new Faker(new Locale("en"));
    private static final Random RANDOM = new Random();

    private DataHelper() {
    }

    @Value
    public static class FeedbackFormInfo {
        String name;
        String phone;
        String company;
        String email;
        String comment;
    }

    /** Имя **/

    public static String getValidName() {
        return FAKER.name().name();
    }

    public static String generateInvalidName() {
        String[] invalidName = {"123456", "@#$%&*()"};
        var name = invalidName[RANDOM.nextInt(invalidName.length)];
        return name;
    }

    public static String getInvalidNameNumbers() {
        return "123456";
    }

    public static String getInvalidNameSpecialSymbols() {
        return "@#$%&*()";
    }

    /** Номер телефона **/

    public static String getValidPhoneNumber() {
        return FAKER.phoneNumber().phoneNumber();
    }

    public static String getInvalidPhoneNumber() {
        return FAKER.phoneNumber().subscriberNumber(9);
    }

    public static String generateInvalidPhoneNumber() {
        String[] invalidPhoneNumber = {"Номер", "Number", "@#$%&*()"};
        var phone = invalidPhoneNumber[RANDOM.nextInt(invalidPhoneNumber.length)];
        return phone;
    }

    /** Компания **/

    public static String getValidCompany() {
        return FAKER.company().name();
    }

    public static String getValidEmail() {
        return FAKER.internet().emailAddress();
    }

    /** Почта **/

    public static String generateInvalidEmail() {
        String[] invalidEmail = {
                "Иван@mail.ru",
                "Ivan@майл.ru",
                "Ivan@mailru",
                "Ivanmail.ru",
                "Ivan shcheglov@mail.ru",
                "Ivan@mail mail.ru",
                "@mail.ru", "Ivan@",
                ".Ivan@mail.ru",
                "Ivan.@mail.ru",
                "Ivan@.mail.ru",
                "Ivan@mail.ru."
        };
        var email = invalidEmail[RANDOM.nextInt(invalidEmail.length)];
        return email;
    }

    public static String getInvalidEmailCyrillicName() {
        return "Иван@mail.ru";
    }

    public static String getInvalidEmailCyrillicDomain() {
        return "Ivan@майл.ru";
    }

    public static String getInvalidEmailNoDotDomain() {
        return "Ivan@mailru";
    }

    public static String getInvalidEmailAbsenceAt() {
        return "Ivanmail.ru";
    }

    public static String getInvalidEmailSpaceName() {
        return "Ivan shcheglov@mail.ru";
    }

    public static String getInvalidEmailSpaceDomain() {
        return "Ivan@mail mail.ru";
    }

    public static String getInvalidEmailAbsenceName() {
        return "@mail.ru";
    }

    public static String getInvalidEmailAbsenceDomain() {
        return "Ivan@";
    }

    public static String getInvalidEmailFirstPointName() {
        return ".Ivan@mail.ru";
    }

    public static String getInvalidEmailLastPointName() {
        return "Ivan.@mail.ru";
    }

    public static String getInvalidEmailFirstPointDomain() {
        return "Ivan@.mail.ru";
    }

    public static String getInvalidEmailLastPointDomain() {
        return "Ivan@mail.ru.";
    }

    /** Комментарий **/

    public static String getValidComment() {
        return "Доброго дня! Выполняется тестовое задание, перезванивать не нужно, спасибо за понимание";
    }
}
