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
    public TextBoxPage typeUserName(String value){
        userNameInput.setValue(value);
        return this;
    }
    public TextBoxPage typeUserEmail(String value){
        userEmailInput.setValue(value);
        return this;
    }

    public TextBoxPage typeCurrentAddress (String value){
        currentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage typePublicAddress (String value){
        permanentAddressInput.setValue(value);
        return this;
    }
    public TextBoxPage openPage(){
    open("/text-box");
        return this;
    }

    public TextBoxPage submitForm(){
        submitButton.click();
        return this;
    }

    public TextBoxPage checkField (String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));
        return this;
    }

    // МЕТОД: проверка, что поле email имеет класс ошибки
    public TextBoxPage userEmailInputShouldHaveErrorClass() {
        userEmailInput.shouldHave(cssClass("field-error"));
        return this;
    }
    // Проверяем, что имя "Sergey" не появилось в итоговом блоке, так как форма не отправилась

    public TextBoxPage userEmailInputShouldNotBeVisible() {
        outputResults.shouldNot(be(visible));
        return this;
    }

    // Вариант проверки негативного теста  для конкретного поля name
    public void outputNameShouldNotExist() {
        outputName.shouldNot(exist);
    }

}
