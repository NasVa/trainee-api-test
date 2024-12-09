package org.nasva.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification requestSpecifications(String url, String contentType){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(contentType)
                .build();
    }
    public static ResponseSpecification responseSpec(int expectedStatusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .build();
    }
}
