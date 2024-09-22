package test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TagesTest {

    private final static String URL = "https://tages.ru/";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open(URL);
    }

    @DisplayName("Успешная отправка формы обратной связи")
    @Test
    public void test1_1_successfulSubmissionFeedbackForms() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка успешного уведомления", () -> {
                            mainPage.shouldNotificationSuccessfulText("Ваше обращение получено");
                        })
        );
    }

    @DisplayName("Успешная отправка формы обратной связи без указания компании")
    @Test
    public void test1_2_successfulSubmissionFeedbackFormsWithoutCompany() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, "", email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка успешного уведомления", () -> {
                            mainPage.shouldNotificationSuccessfulText("Ваше обращение получено");
                        })
        );
    }

    @DisplayName("Успешная отправка формы обратной связи без указания комментария")
    @Test
    public void test1_3_successfulSubmissionFeedbackFormsWithoutComment() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, "");
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка успешного уведомления", () -> {
                            mainPage.shouldNotificationSuccessfulText("Ваше обращение получено");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи без указания имени")
    @Test
    public void test1_4_unsuccessfulSubmissionFeedbackFormsWithoutName() {
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo("", phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи без указания номера телефона")
    @Test
    public void test1_5_unsuccessfulSubmissionFeedbackFormsWithoutPhoneNumber() {
        var name = DataHelper.getValidName();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, "", company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи без указания адреса эл.почты")
    @Test
    public void test1_6_unsuccessfulSubmissionFeedbackFormsWithoutEmail() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, "", comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле Имя(цифры)")
    @Test
    public void test1_7_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueNameNumbers() {
        var name = DataHelper.getInvalidNameNumbers();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле Имя(спецсимволы)")
    @Test
    public void test1_8_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueNameSpecialSymbols() {
        var name = DataHelper.getInvalidNameSpecialSymbols();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле телефон(менее 11 цифр)")
    @Test
    public void test1_9_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValuePhone() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getInvalidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле телефон(латиница/кириллица/спец.символы)")
    @Test
    public void test1_10_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValuePhoneLetters() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.generateInvalidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getValidEmail();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(кириллица в имени пользователя)")
    @Test
    public void test1_11_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailCyrillicName() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailCyrillicName();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(кириллица в доменной части)")
    @Test
    public void test1_12_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailCyrillicDomain() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailCyrillicDomain();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(без точки в доменной части)")
    @Test
    public void test1_13_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailNoDotDomain() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailNoDotDomain();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(отсутствие @)")
    @Test
    public void test1_14_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailAbsenceAt() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailAbsenceAt();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(пробел в имени пользователя)")
    @Test
    public void test1_15_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailSpaceName() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailSpaceName();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(пробел в доменной части)")
    @Test
    public void test1_16_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailSpaceDomain() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailSpaceDomain();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(отсутствие имени пользователя)")
    @Test
    public void test1_17_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailAbsenceName() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailAbsenceName();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(отсутствие доменной части)")
    @Test
    public void test1_18_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailAbsenceDomain() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailAbsenceDomain();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(точка первым символом имени пользователя)")
    @Test
    public void test1_19_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailFirstPointName() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailFirstPointName();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(точка последним символом имени пользователя)")
    @Test
    public void test1_20_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailLastPointName() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailLastPointName();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(точка первым символом доменной части)")
    @Test
    public void test1_21_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailFirstPointDomain() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailFirstPointDomain();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Неуспешная попытка отправки формы обратной связи с невалидным значением в поле почта(точка первым символом доменной части)")
    @Test
    public void test1_22_unsuccessfulSubmissionFeedbackFormsWithoutInvalidValueEmailFirstPointDomain() {
        var name = DataHelper.getValidName();
        var phone = DataHelper.getValidPhoneNumber();
        var company = DataHelper.getValidCompany();
        var email = DataHelper.getInvalidEmailLastPointDomain();
        var comment = DataHelper.getValidComment();
        var formInfo = new DataHelper.FeedbackFormInfo(name, phone, company, email, comment);
        var mainPage = new MainPage();

        step("Отправляем запрос обратной связи", () -> {
            mainPage.sendFeedbackRequest(formInfo);
        });

        assertAll(
                () ->
                        step("Проверка уведомления об ошибке", () -> {
                            mainPage.checkInputInvalid("Невалидное значение");
                        })
        );
    }

    @DisplayName("Успешное открытие всех ссылок и разделов, в т.ч. вызовы email и tel")
    @Test
    public void test1_23_successfulOpenLinks() {
        var mainPage = new MainPage();

        assertAll(
                () ->
                        step("Получение и открытие всех ссылок из коллекции", () -> {
                            mainPage.getListLinks().forEach(Selenide::open);
                        })
        );
    }
}
