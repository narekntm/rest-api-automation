package utils.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class RestService {
    public static final String API_TOKEN = "46be7aa2ff9da11b7c0e856a56510029bc81dfde5dc9150e3a0fef63136dc642";
    public static final String BASE_URL = "https://gorest.co.in/public/v1";
    protected RequestSpecification REQ_SPEC;

    protected abstract String getBasePath();

    public RestService() {
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(getBasePath())
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .setContentType(ContentType.JSON)
                .build();
    }
}
