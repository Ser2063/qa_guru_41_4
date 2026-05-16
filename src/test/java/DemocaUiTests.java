import org.junit.jupiter.api.Test;
import testdata.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class DemocaUiTests extends TestBase {


    @Test
    void fillingFormFieldsCSS() {
        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $$("#genterWrapper label").filterBy(text(genterWrapper)).first().click();
        $("#userNumber").setValue(userNumber);

        // Дата рождения
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOption(monthSelect);
        $("select.react-datepicker__year-select").selectOption(yearSelect);
        $("div.react-datepicker__day--" + paddedDay).click();

        // Предметы
        $("#subjectsInput").setValue(subjectsInput).pressEnter();

        //Хобби
        $$("#hobbiesWrapper label").filterBy(text(hobbiesWrapperReading)).first().click();
        $$("#hobbiesWrapper label").filterBy(text(hobbiesWrapperSports)).first().click();

        //Адрес
        $("textarea#currentAddress").setValue(currentAddress);

        //Картинка
        $("#uploadPicture").uploadFromClasspath(uploadPicture); //добавлена папка test/resources вложен файл img.png

        //штат и город
        $("#state").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text(state)).click();
        $("#city").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text(city)).click();

        //нажимаем кнопку
        $("button#submit").click();

        //реконсиляция

        $(".modal-content").shouldBe(visible);

        //  Проверка по таблице
        $(".table-responsive")
                .$(byText("Student Name")).parent().shouldHave(text(firstName+ " " +lastName));
        $(".table-responsive")
                .$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive")
                .$(byText("Gender")).parent().shouldHave(text(genterWrapper));
        $(".table-responsive")
                .$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive")
                .$(byText("Date of Birth")).parent().shouldHave(text(daySelect+ " " + monthSelect + "," + yearSelect));
        $(".table-responsive")
                .$(byText("Subjects")).parent().shouldHave(text(subjectsInput));
        $(".table-responsive")
                .$(byText("Hobbies")).parent().shouldHave(text(hobbiesWrapperReading + ", " + hobbiesWrapperSports));
        $(".table-responsive")
                .$(byText("Picture")).parent().shouldHave(text(uploadPicture));
        $(".table-responsive")
                .$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive")
                .$(byText("State and City")).parent().shouldHave(text(state+ " " + city));

        // Закрытие модального окна
        $("#closeLargeModal").click();

    }
}
