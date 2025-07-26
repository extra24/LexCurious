package com.lexcurious.model.search;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;

// 개별 법률의 간략화된 정보 모델 : 법률의 데이터 구조
// *Record 사용 : 데이터만 담는 클래스
public record Law (
        @SerializedName("법령일련번호") int lawSerialId,
        @SerializedName("법령명한글") String lawName,
        @SerializedName("법령약칭명") String lawAbbreviation,
        @SerializedName("공포일자") int promulgationDate,
        @SerializedName("공포번호") int promulgationNumber,
        @SerializedName("시행일자") int effectDate,
        @SerializedName("소관부처명") String ministryName,
        @SerializedName("제개정구분명") String revisionType,
        @SerializedName("법령구분명") String divisionName,
        @SerializedName("법령상세링크") String detailLink
) {
    // 공포일자 파싱
    public LocalDate getPromulgationDate() {
        if(String.valueOf(promulgationDate).length() == 8) {
            return LocalDate.of(
                    promulgationDate / 10000,
                    (promulgationDate % 10000) / 100,
                    promulgationDate % 100
            );
        }
        return null;
    }
    // 시행일자 파싱
    public LocalDate getEffectDate() {
        if (String.valueOf(effectDate).length() == 8) {
            return LocalDate.of(
                    effectDate / 10000,
                    (effectDate % 10000) / 100,
                    effectDate % 100
            );
        }
        return null;
    }
}
