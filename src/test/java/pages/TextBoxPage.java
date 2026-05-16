package pages;

import com.codeborne.selenide.SelenideElement;
import testdata.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage extends TestBase {

    // Elements

    private final SelenideElement userNameInput =  $("#userName");
    private final SelenideElement userEmailInput = $("[id=userEmail]");
    private final SelenideElement currentAddressInput = $("[id=currentAddress]");
    private final SelenideElement permanentAddressInput = $("[id=permanentAddress]");
    private final SelenideElement submitButton = $("[id=submit]");
    private final SelenideElement outputResults = $("[id=output]");

    // Элементы key внутри output (для проверок)
    private final SelenideElement outputName = outputResults.$("#name");


    // Actions
    public void typeUserName(String value){
        userNameInput.setValue(value);
    }
    public void typeUserEmail(String value){
        userEmailInput.setValue(value);
    }

    public void typeCurrentAddress (String value){
        currentAddressInput.setValue(value);
    }

    public void typePublicAddress (String value){
        permanentAddressInput.setValue(value);
    }
    public void openPage(){
    open("/text-box");
    }

    public void submitForm(){
        submitButton.click();
    }

    public void checkField (String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));
    }

    // МЕТОД: проверка, что поле email имеет класс ошибки
    public void userEmailInputShouldHaveErrorClass() {
        userEmailInput.shouldHave(cssClass("field-error"));
    }
    // Проверяем, что имя "Sergey" не появилось в итоговом блоке, так как форма не отправилась

    public void userEmailInputShouldNotBeVisible() {
        outputResults.shouldNot(be(visible));
    }

    // Вариант проверки негативного теста  для конкретного поля name
    public void outputNameShouldNotExist() {
        outputName.shouldNot(exist);
    }

}
