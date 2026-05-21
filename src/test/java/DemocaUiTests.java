import org.junit.jupiter.api.Test;
import pages.DemocaUiTestsPage;
import testdata.TestBase;

import static testdata.TestData.*;

public class DemocaUiTests extends TestBase {

    DemocaUiTestsPage democaUiTestsPage = new DemocaUiTestsPage();

    @Test
    void fillingFormFieldsCSS() {

        democaUiTestsPage.openPage();
        democaUiTestsPage.typeFirstName(firstName);
        democaUiTestsPage.typeLastName(lastName);
        democaUiTestsPage.typeUserEmail(userEmail);
        democaUiTestsPage.typeUserNumber(userNumber);
        democaUiTestsPage.setGender(genterWrapper);

        // Дата рождения
        democaUiTestsPage.setDateOfBirth(daySelect, monthSelect, yearSelect);

        // Предметы
        democaUiTestsPage.typeSubjects(subjectsInput);

        //Хобби
        democaUiTestsPage.setHobby(hobbiesWrapperReading);
        democaUiTestsPage.setHobby(hobbiesWrapperSports);

        //Адрес
        democaUiTestsPage.typeCurrentAddress(currentAddress);

        //Картинка
        democaUiTestsPage.setPicture(uploadPicture);

        //штат и город
        democaUiTestsPage.setState(state);
        democaUiTestsPage.setCity(city);

        //нажимаем кнопку
        democaUiTestsPage.submitForm();

        // Реконсиляция (Проверка по таблице)
        democaUiTestsPage.checkModalVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", genterWrapper)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", daySelect + " " + monthSelect + "," + yearSelect)
                .checkResult("Subjects", subjectsInput)
                .checkResult("Hobbies", hobbiesWrapperReading + ", " + hobbiesWrapperSports)
                .checkResult("Picture", uploadPicture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);

        // Закрытие модального окна
        democaUiTestsPage.closeModal();

    }

    @Test
    void submitEmptyFormTest() {
        democaUiTestsPage.openPage();
        democaUiTestsPage.submitForm();
        democaUiTestsPage.checkModalNotVisible(); // Проверка отсутствия модалки через существующий элемент UI
        democaUiTestsPage.firstNameShouldBeRed(); // Проверка валидации (красная рамка)
    }


    @Test
    void shortMobileNumberTest() {
        democaUiTestsPage.openPage();
        democaUiTestsPage.typeFirstName(firstName);
        democaUiTestsPage.typeLastName(lastName);
        democaUiTestsPage.setGender(genterWrapper);
        democaUiTestsPage.typeUserNumber(userNumberNeg);
        democaUiTestsPage.submitForm();
        democaUiTestsPage.checkModalNotVisible();
        democaUiTestsPage.checkUserNumberIsInvalid();
    }


    @Test
    void alphabeticMobileNumberTest() {
        democaUiTestsPage.openPage();
        democaUiTestsPage.typeFirstName(firstName);
        democaUiTestsPage.typeLastName(lastName);
        democaUiTestsPage.setGender(genterWrapper);
        democaUiTestsPage.typeUserNumber(userNumberNega);
        democaUiTestsPage.submitForm();
        democaUiTestsPage.checkModalNotVisible();
    }

    @Test
    void missingGenderTest() {
        democaUiTestsPage.openPage();
        democaUiTestsPage.typeFirstName(firstName);
        democaUiTestsPage.typeLastName(lastName);
        democaUiTestsPage.typeUserNumber(userNumber); // Пропускаем клик по Gender
        democaUiTestsPage.submitForm();
        democaUiTestsPage.checkModalNotVisible();
    }
}
