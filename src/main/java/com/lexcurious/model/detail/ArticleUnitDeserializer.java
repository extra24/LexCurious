package com.lexcurious.model.detail;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Custom Deserializer : ArticleUnit 파싱할 때 항 필드를 리스트인지 단일 객체인지 판별해서 처리하기 위한 클래스
public class ArticleUnitDeserializer implements JsonDeserializer<ArticleUnit> {

    @Override
    public ArticleUnit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // 다른 필드들도 파싱
        String articleKey = jsonObject.has("조문키") ? jsonObject.get("조문키").getAsString() : null;
        String articleContent = jsonObject.has("조문내용") ? jsonObject.get("조문내용").getAsString() : null;

        // 항 필드를 처리할 리스트 : clauseList
        List<Clause> clauseList = new ArrayList<>();

        if (jsonObject.has("항")) {
            JsonElement hangElement = jsonObject.get("항");

            // 항이 배열(Array)인지 확인
            if (hangElement.isJsonArray()) {
                JsonArray hangArray = hangElement.getAsJsonArray();
                for (JsonElement element : hangArray) {
                    // 배열의 각 요소를 Clause 객체로 변환하여 리스트에 추가
                    clauseList.add(context.deserialize(element, Clause.class));
                }
            }
            // 항이 단일 객체인지 확인
            else if (hangElement.isJsonObject()) {
                JsonObject hangObject = hangElement.getAsJsonObject();
                List<Ho> hoList = new ArrayList<>();

                // 호 배열이 있는지 확인
                if (hangObject.has("호") && hangObject.get("호").isJsonArray()) {
                    JsonArray hoArray = hangObject.get("호").getAsJsonArray();
                    for (JsonElement hoElement : hoArray) {
                        // 호 배열의 각 요소를 Ho 객체로 변환
                        hoList.add(context.deserialize(hoElement, Ho.class));
                    }
                }
                // 호 리스트를 포함하는 하나의 항객체 생성하여 추가
                clauseList.add(new Clause(null, null, hoList));
            }
        }
        // 최종적으로 ArticleUnit 생성하여 반환
        return new ArticleUnit(articleKey, articleContent, clauseList);
    }
}
