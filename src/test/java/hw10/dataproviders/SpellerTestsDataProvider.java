package hw10.dataproviders;

import org.testng.annotations.DataProvider;

import java.util.List;

import static constants.Code.INCORRECT_WORD_ERROR;
import static constants.Language.ENGLISH;
import static constants.Option.*;

public class SpellerTestsDataProvider {

    public static final String CORRECT_TEXT = "Hope this one is correct";
    public static final String TEXT_WITH_MISSPELLED_WORD = "Hello Wurld";
    public static final Integer EXPECTED_MISSPELLED_WORD_LENGTH = 5;
    public static final Integer EXPECTED_MISSPELLED_WORD_POSITION = 6;
    public static final String[] TEXTS_ARRAY = {"misteke"};

    @DataProvider
    public static Object[][] textsDataSet() {
        return new Object[][] {
            {TEXTS_ARRAY, ENGLISH, List.of("mistake", "mistaken", "mistakes")},
        };
    }

    @DataProvider
    public static Object[][] incorrectTextDataSet() {
        return new Object[][] {
            {"applicetion", ENGLISH, DEFAULT_OPTION, "application", "applications", "application'"},
            {"pragramming", ENGLISH, DEFAULT_OPTION, "programming", "programing", "programming'"},
            {"intarface", ENGLISH, DEFAULT_OPTION, "interface", "interfaces", "interfaced", "interface'"}
        };
    }

    @DataProvider
    public static Object[][] errorCodeDataSet() {
        return new Object[][] {
            {"misteke", ENGLISH, DEFAULT_OPTION, INCORRECT_WORD_ERROR},
            {"pragramming", ENGLISH, DEFAULT_OPTION, INCORRECT_WORD_ERROR},
            {"wendows", ENGLISH, DEFAULT_OPTION, INCORRECT_WORD_ERROR},
        };
    }

    @DataProvider
    public static Object[][] textWithDigitsDataSet() {
        return new Object[][] {
            {"hello666", ENGLISH, IGNORE_DIGITS_OPTION, "hello 666", "halo666", "hell 666"},
            {"666world", ENGLISH, IGNORE_DIGITS_OPTION, "666 world", "666 word", "666 words"}
        };
    }

    @DataProvider
    public static Object[][] textWithLettersInUpperCaseDataSet() {
        return new Object[][] {
            {"mIstEKe", ENGLISH, IGNORE_CASE_OPTION, "mistake", "mistaken", "mistakes"},
            {"ApPlecaTIOn", ENGLISH, IGNORE_CASE_OPTION, "Application", "Applications", "Appreciation"}
        };
    }
}
