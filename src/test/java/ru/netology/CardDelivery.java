package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDelivery {

    public LocalDate dateNow = LocalDate.now();
    public String setDateDefault = dateNow.plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));


    @Test
    public void shouldRegByAcc() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Калининград");
        $("[data-test-id='date'] input").doubleClick().setValue(String.valueOf(setDateDefault));
        $("[data-test-id='name'] input").val("Максим Салтыков-Щедрин");
        $("[data-test-id='phone'] input").val("+79127775566");
        $("span.checkbox__box").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(12));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + setDateDefault));


    }


}
