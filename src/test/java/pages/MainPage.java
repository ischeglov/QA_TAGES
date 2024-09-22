package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private SelenideElement name = $x("//input[@placeholder='Имя*']");
    private SelenideElement phone = $x("//input[@placeholder='Телефон*']");
    private SelenideElement company = $x("//input[@placeholder='Компания']");
    private SelenideElement email = $x("//input[@placeholder='Почта*']");
    private SelenideElement comment = $x("//textarea[@placeholder='Комментарий']");
    private SelenideElement inputInvalid = $(".form__inputs-container");
    private SelenideElement buttonSubmit = $("form > div.form__button-container > button");
    private SelenideElement successfulNotification = $x("//h4[@class='form__success-badge-title']");

    public void checkInputInvalid(String expectedText) {
        inputInvalid.shouldHave(exactText(expectedText)).shouldBe(visible, Duration.ofSeconds(5));
    }

    public void shouldNotificationSuccessfulText(String expectedText) {
        successfulNotification.shouldHave(exactText(expectedText)).shouldBe(visible, Duration.ofSeconds(20));
    }

    public void sendFeedbackRequest(DataHelper.FeedbackFormInfo formInfo) {
        name.setValue(formInfo.getName());
        phone.setValue(formInfo.getPhone());
        company.setValue(formInfo.getCompany());
        email.setValue(formInfo.getEmail());
        comment.setValue(formInfo.getComment());
        buttonSubmit.click();
    }
}
