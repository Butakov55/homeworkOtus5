package services;

import dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetNewApi {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
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
