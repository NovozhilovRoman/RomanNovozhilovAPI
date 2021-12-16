package service;

import com.google.gson.Gson;
import dto.SpellerResponseDto;

import java.util.HashMap;
import java.util.Map;

public class RestSpellerService extends CommonService {

    public SpellerResponseDto[] checkText(String text, String lang, Integer option) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", text);
        params.put("lang", lang);
        params.put("option", option);

        return
            new Gson().fromJson(
                getWithParams(URI.CHECK_TEXT, params)
                    .getBody().asString(), SpellerResponseDto[].class);
    }

    public SpellerResponseDto[] checkTexts(String[] text, String lang) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", text);
        params.put("lang", lang);

        return
            new Gson().fromJson(
                getWithParams(URI.CHECK_TEXTS, params)
                    .getBody().asString(), SpellerResponseDto[].class);
    }
}
