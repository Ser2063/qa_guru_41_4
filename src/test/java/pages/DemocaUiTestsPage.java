package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemocaUiTestsPage {


    // Elements
    CalendarComponent calendar = new CalendarComponent();

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("[id=userEmail]");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement submitButton = $("button#submit");
    private final SelenideElement currentAddressInput = $("textarea#currentAddress");
    private final SelenideElement genderContainer =  $("#genterWrapper");
    private final SelenideElement calendarElement = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final ElementsCollection hobbiesList = $$("#hobbiesWrapper label");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private final SelenideElement stateSelect = $("#state");
    private final SelenideElement citySelect = $("#city");

    private final SelenideElement modalContent = $(".modal-content");
    private final SelenideElement resultsTable = $(".table-responsive");
    private final SelenideElement closeModalButton = $("#closeLargeModal");


    // Actions

    public void setPicture (String value){
        uploadPicture.uploadFromClasspath(value);
    }

    public void typeFirstName (String value){
    firstNameInput.setValue(value);
    }

    public void typeLastName (String value){
        lastNameInput.setValue(value);
    }

    public void typeUserEmail(String value){
        userEmailInput.setValue(value);

    }

    public void typeUserNumber (String value){
        userNumberInput.setValue(value);
    }

    public void typeCurrentAddress (String value){
        currentAddressInput.setValue(value);

    }

    public void typeSubjects (String value){
        subjectsInput.setValue(value).pressEnter();

    }

    public void setGender (String value){
        genderContainer.$(byText(value)).click();
    }

    public void setHobby (String value){
        hobbiesList.filterBy(text(value)).first().click();
    }

    public void setDateOfBirth (String day, String month, String year){
        calendarElement.click();
        calendar.setDate(day, month, year);
    }


    public void openPage(){
        open("/automation-practice-form");

    }

    public void submitForm(){
        submitButton.click();

    }


    public void setState (String value){
        stateSelect.click();
        stateCityContainer.$(byText(value)).click();
    }

    public void setCity (String value){
        citySelect.click();
        stateCityContainer.$(byText(value)).click();

    }


    public DemocaUiTestsPage checkModalVisible() {
        modalContent.shouldBe(visible);
        return this;
    }

    public void checkModalNotVisible() {
        modalContent.shouldNotBe(visible);
    }

    public DemocaUiTestsPage checkResult(String key, String value) {
        resultsTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    public void closeModal() {
        closeModalButton.click();
    }

    // Вариант проверки негативного теста  для конкретного поля name
    public void firstNameShouldBeRed() {
        firstNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    public void checkUserNumberIsInvalid() {
        userNumberInput.shouldHave(cssClass("form-control"));
    }

}
