package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class AppCardDeliveryTaskOneTest {

    @BeforeEach
    public void setupTest() {
        open("http://localhost:9999");
    }

    private String generetionDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).
                format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void shouldBeSuccessfullyCompleted(){

        $("[data-test-id='city'] input").setValue("Красноярск");
        String planningDate = generetionDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Иванов-Иваныч Иван");
        $("[data-test-id='phone'] input").setValue("+79278586565");
        $("[data-test-id='agreement'] span.checkbox__box").click();
        $("button.button").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }

}
