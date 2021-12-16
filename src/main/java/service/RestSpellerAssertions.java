package service;

import dto.SpellerResponseDto;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestSpellerAssertions {

    public SpellerResponseDto[] spellerResponse;
    public SpellerResponseDto[][] spellerResponseDtos;

    public RestSpellerAssertions(SpellerResponseDto[] response) {
        this.spellerResponse = response;
    }

    public void verifyEmptyResponse() {
        var result = List.of(spellerResponse);
        assertTrue(result.isEmpty(), "Result should be empty");
    }

    public RestSpellerAssertions verifyErrorCode(Integer code) {
        assertEquals(spellerResponse[0].getCode(), code,
            "Incorrect error code");
        return this;
    }

    public RestSpellerAssertions verifyTextLength(Integer length) {
        assertEquals(spellerResponse[0].getLen(), length,
            "Incorrect text length");
        return this;
    }

    public RestSpellerAssertions verifyMisspelledWordPosition(Integer position) {
        assertEquals(spellerResponse[0].getPos(), position,
            "Incorrect misspelled word position");
        return this;
    }

    public RestSpellerAssertions verifyCorrectTextSuggestions(List<String> expectedText) {
        assertEquals(spellerResponse[0].getS(), expectedText,
            "Incorrect text suggestions");
        return this;
    }

    public RestSpellerAssertions verifyCorrectTextsSuggestions(List<List<String>> expectedTexts) {
        List<List<String>> list = new ArrayList<>();
        for (SpellerResponseDto spellerResponseDto : spellerResponse) {
            List<String> s = spellerResponseDto.getS();
            list.add(s);
        }
        assertEquals(
            list, expectedTexts,
            "Incorrect texts suggestions");
        return this;
    }
}
