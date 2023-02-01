package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class JunitSimpleTest {
    @BeforeEach
    void setUP(){
        open("https://google.com");
    }
//    @Disabled("Временно отключен с номером дефекта на жире")
//    @DisplayName("Демонстрационный тест")
//    @Test
//    @CsvSource({
//        "Allure testops, https://qameta.io",
//        "Selenide, https://selenide.org"
//    })
    @CsvFileSource(resources = "/SetData.csv")
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0}")
    @Tags({@Tag("Blocker"),@Tag("UI_Test")})

    void simpleTest(String productName, String productUrl){
        $("[name=q]").setValue(productName).pressEnter();
        $("#search").shouldHave(text(productUrl));
    }

    @ValueSource(
            strings ={ "Allure testops,Selenide"}
    )
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0}")
    @Tags({@Tag("Blocker"),@Tag("UI_Test")})

    void simpleTestCount(String productName){
        $("[name=q]").setValue(productName).pressEnter();
        $$(".g").shouldHave(CollectionCondition.sizeGreaterThan(5));
    }

}
