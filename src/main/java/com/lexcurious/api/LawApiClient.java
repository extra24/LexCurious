package com.lexcurious.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lexcurious.model.LawDetail;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

// API 클라이언트
public class LawApiClient {

    private final OkHttpClient httpClient;
    private final Gson gson;

    // API KEY
    private final String API_KEY;

    // API URL
    private final String LAW_LIST_API_BASE_URL;     // 법률 목록 API URL
    private final String LAW_DETAIL_API_BASE_URL;   // 법률 본문 API URL

    // 생성자
    public LawApiClient(OkHttpClient httpClient, Gson gson, String apiKey, String lawListApiBaseUrl, String lawDetailApiBaseUrl) {
        this.httpClient = httpClient;
        this.gson = gson;

        Properties prop = loadProperties();

        API_KEY = getProperty(prop, "law.api.key");
        LAW_LIST_API_BASE_URL = getProperty(prop, "law.api.list.base-ur");
        LAW_DETAIL_API_BASE_URL = getProperty(prop, "law.detail.api.base-url");
    }

    // config.properties 로드 메서드
    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null){
                System.err.println("config.properties를 찾을 수 없습니다.");
                throw new IOException("config.properties 파일을 찾을 수 없습니다.");
            }
            properties.load(input);
            return properties;
        } catch (IOException e) {
            System.err.println("설정 파일 로드 중 오류 발생" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("API 키를 로드할 수 없습니다.", e);
        }
    }

    // properties 값 존재 여부 확인하고 반환하는 메서드
    private String getProperty(Properties prop, String keyName) {
        String value = prop.getProperty(keyName);
        if (value == null || value.isEmpty()) {
            System.err.println("설정파일에 "+ keyName + "이 설정되지 않았습니다.");
            throw new IllegalArgumentException("설정 파일을 확인해주세요");
        }
        return value;
    }

    /**
     법률 목록 검색하는 API 호출하고 결과를 LawListResponse 객체로 반환
     * @param keyword 검색할 법률 키워드
     * @return LawListResponse 객체 (총 개수, 페이지, 법률 목록 포함)
     * @throws IOException API 통신 오류 발생 시
     * @throws JsonSyntaxException JSON 파싱 오류 발생 시
     */
    public LawListResponse searchLawList(String keyword) throws IOException, JsonSyntaxException {
        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.toString());
        String url = String.format("%s&query=%s", LAW_LIST_API_BASE_URL, encodedKeyword);

        System.out.println("법률 목록 API 호출 URL: " + url);

        Request request = new Request.Builder().url(url).build();

        try (Response response = httpClient.newCall(request).execute()){
            if (!response.isSuccessful()) {
                String errMessage = "법률 목록 API 호출 실패" + response.code() + "): " + response.message();
                System.err.println(errMessage);
                throw new IOException(errMessage);
            }
            String jsonResponse = response.body().string();
            System.out.println("법률 목록 API 응답 (일부): " + jsonResponse.substring(0, Math.min(jsonResponse.length(), 500)) + "...");

            return gson.fromJson(jsonResponse, LawListResponse.class);
        } catch (JsonSyntaxException e){
            System.err.println("법률 목록 API JSON 파싱 에러 " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     특정 법률의 상세 본문 정보를 조회하는 API를 호출하고 LawDetail 객체로 반환합니다.
     * @param lawId 조회할 법률의 일련번호 (법령키)
     * @return LawDetail 객체 (조문내용, 항내용 등 포함)
     * @throws IOException API 통신 오류 발생 시
     * @throws JsonSyntaxException JSON 파싱 오류 발생 시
     */
    public LawDetail getLawDetail(int lawId) throws IOException, JsonSyntaxException {
        String url = String.format("%s&ID=%d", LAW_DETAIL_API_BASE_URL, lawId);

        System.out.println("법률 본문 API 호출 URL: " + url);

        Request request = new Request.Builder().url(url).build();

        try (Response response = httpClient.newCall(request).execute()) {
            if(!response.isSuccessful()){
                String errMessage =  "법률 본문 API 호출 실패 (HTTP " + response.code() + "): " + response.message();
                System.err.println(errMessage);
                throw new IOException(errMessage);
            }
            String jsonResponse = response.body().string();
            System.out.println("법률 본문 API 응답 (일부): " + jsonResponse.substring(0, Math.min(jsonResponse.length(), 500)) + "...");

            return gson.fromJson(jsonResponse, LawDetail.class);
        } catch (JsonSyntaxException e) {
            System.err.println("법률 본문 API 응답 JSON 파싱 오류: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
