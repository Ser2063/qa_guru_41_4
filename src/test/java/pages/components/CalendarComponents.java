package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponents {

    public void setDate (String day, String month, String year){

        $("select.react-datepicker__month-select").selectOption(month);
        $("select.react-datepicker__year-select").selectOption(year);
        // Форматирование дня прямо здесь (например, "5" превратится в "005")
        String formattedDay = String.format("%03d", Integer.parseInt(day));
        $("div.react-datepicker__day--" + formattedDay).click();

    }

}
