package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    // Elements
    private final SelenideElement monthSelect = $("select.react-datepicker__month-select");
    private final SelenideElement yearSelect = $("select.react-datepicker__year-select");

    // Actions
    public void setDate (String day, String month, String year){

        monthSelect.selectOption(month);
        yearSelect.selectOption(year);

        // Форматирование дня прямо здесь (например, "5" превратится в "005")
        String formattedDay = String.format("%03d", Integer.parseInt(day));


        // Базовая строка локатора для дня (так как она динамическая)
        String dayCssPrefix = "div.react-datepicker__day--";
        $(dayCssPrefix + formattedDay).click();

    }

}
