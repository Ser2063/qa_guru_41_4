import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.TestBase;

import static testdata.TestData.*;

public class TestBoxTests extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();


    @Test
    void fillFormTest() {


        textBoxPage.openPage();
        textBoxPage.typeUserName(userName);
        textBoxPage.typeUserEmail(userEmail);
        textBoxPage.typeCurrentAddress(currentAddress);
        textBoxPage.typePublicAddress(permanentAddress);
        textBoxPage.submitForm();
        textBoxPage.checkField("name", userName);
        textBoxPage.checkField("email", userEmail);
        textBoxPage.checkField("currentAddress", currentAddress);
        textBoxPage.checkField("permanentAddress", permanentAddress);
    }


    // Негативные тесты
    @Test
    void invalidEmailTest() {
        textBoxPage.openPage();
        textBoxPage.typeUserEmail(userEmailNegNotAnEmail); // "not-an-email"
        textBoxPage.submitForm();
        textBoxPage.userEmailInputShouldHaveErrorClass(); // Проверяем наличие CSS-класс ошибки у поля
        textBoxPage.userEmailInputShouldNotBeVisible();
    }

    @Test
    void emptyFormSubmitTest() {
        textBoxPage.openPage();
        textBoxPage.submitForm();
        textBoxPage.userEmailInputShouldNotBeVisible();
        textBoxPage.outputNameShouldNotExist();
    }

    @Test
    void incompleteEmailTest() {
        textBoxPage.openPage();
        textBoxPage.typeUserName(userName);
        textBoxPage.typeUserEmail(userEmailNeg); // sergey@missingdomain Без .ru/.com и т.д.
        textBoxPage.submitForm();
        textBoxPage.userEmailInputShouldHaveErrorClass(); // Проверяем наличие CSS-класс ошибки у поля
        textBoxPage.userEmailInputShouldNotBeVisible(); // Проверяем, что блок с результатом не отобразился
    }

}
