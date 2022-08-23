package services;


import dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;
@Component
public class PetNewApi {
    private static final String BASE_URL = System.getProperty("base.url");

    public static final String PET = "/pet";
    private RequestSpecification rspec;


    public PetNewApi(){
        rspec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }


    public ValidatableResponse createPet (Pet pet) {
        return given(rspec)
                .basePath(PET)
                .body(pet)
                .log().all()
                .when()
                .post()
                .then()
                .log().all();
    }



    public ValidatableResponse getPetId (String id) {
        return given(rspec)
                .basePath(PET + "/" + id)
                .log().all()
                .when()
                .get()
                .then()
                .log().all();
    }
}
