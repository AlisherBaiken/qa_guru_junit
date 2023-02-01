package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import guru.qa.data.Locale;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.data.Locale.EN;

public class SelenideTes {

    static Stream<Arguments>siteSelenideShowAllOffBottomLocale(){
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")), /// argumentov mojet byt stolko skolko yazykov
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
        );

    }
    @MethodSource
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {0}")
    @Tag("BLOCKER")
    void siteSelenideShowAllOffBottomLocale(
            Locale locale,
            List<String> buttons
    ){
        open("https://selenide.org/");
        $$("#languages a").find(Condition.text(locale.name())).click();
        $$(".main-menu-pages a").filter(visible).shouldHave(CollectionCondition.texts(buttons));

    }
}
