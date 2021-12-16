package hw10.tests;

import hw10.dataproviders.SpellerTestsDataProvider;
import dto.SpellerResponseDto;
import org.testng.annotations.Test;
import service.RestSpellerAssertions;

import java.util.List;

import static hw10.dataproviders.SpellerTestsDataProvider.*;
import static constants.Language.ENGLISH;
import static constants.Option.DEFAULT_OPTION;

public class SpellerTests extends BaseTest {

    @Test(description = "Text doesn't contain error and response is empty")
    public void testCorrectText() {
        SpellerResponseDto[] spellerResponse =
            restSpellerService.checkText(CORRECT_TEXT, ENGLISH, DEFAULT_OPTION);
        new RestSpellerAssertions(spellerResponse)
            .verifyEmptyResponse();
    }

    @Test(description = "Text length is equal to expected")
    public void testTextHasCorrectLength() {
        SpellerResponseDto[] spellerResponse =
            restSpellerService.checkText(TEXT_WITH_MISSPELLED_WORD, ENGLISH, DEFAULT_OPTION);
        new RestSpellerAssertions(spellerResponse)
            .verifyTextLength(EXPECTED_MISSPELLED_WORD_LENGTH);
    }

    @Test(description = "Misspelled word position is equal to expected")
    public void testMisspelledWordHasCorrectPosition() {
        SpellerResponseDto[] spellerResponse =
            restSpellerService.checkText(TEXT_WITH_MISSPELLED_WORD, ENGLISH, DEFAULT_OPTION);
        new RestSpellerAssertions(spellerResponse)
            .verifyMisspelledWordPosition(EXPECTED_MISSPELLED_WORD_POSITION);
    }

    @Test(description = "Response contains error code",
          dataProvider = "errorCodeDataSet",
          dataProviderClass = SpellerTestsDataProvider.class)
    public void testResponseContainsErrorCode(String actualText, String lang, Integer option, Integer expectedCode) {
        SpellerResponseDto[] spellerResponse =
            restSpellerService.checkText(actualText, lang, option);
        new RestSpellerAssertions(spellerResponse)
            .verifyErrorCode(expectedCode);
    }

    @Test(description = "Text contains error",
          dataProvider = "incorrectTextDataSet",
          dataProviderClass = SpellerTestsDataProvider.class)
    public void testIncorrectText(String actualText, String lang, Integer option, String... expectedText) {
        verifyTestResult(actualText, lang, option, expectedText);
    }


    @Test(description = "Ignore digits in text",
          dataProvider = "textWithDigitsDataSet",
          dataProviderClass = SpellerTestsDataProvider.class)
    public void testIgnoreDigitsOption(String actualText, String lang, Integer option, String... expectedText) {
        verifyTestResult(actualText, lang, option, expectedText);
    }

    @Test(description = "Ignore case in text with errors",
          dataProvider = "textWithLettersInUpperCaseDataSet",
          dataProviderClass = SpellerTestsDataProvider.class)
    public void testIgnoreCaseOption(String actualText, String lang, Integer option, String... expectedText) {
        verifyTestResult(actualText, lang, option, expectedText);
    }

    private void verifyTestResult(String actualText, String lang, Integer option, String... expectedText) {
        SpellerResponseDto[] spellerResponse =
            restSpellerService.checkText(actualText, lang, option);
        new RestSpellerAssertions(spellerResponse)
            .verifyCorrectTextSuggestions(List.of(expectedText));
    }
}