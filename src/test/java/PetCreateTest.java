import dto.Pet;
import org.junit.jupiter.api.Test;
import services.PetNewApi;

import static org.hamcrest.Matchers.equalTo;

public class PetCreateTest {

    /*
         кейс: отправить запрос для обновления инфы о питомце с несколькими парметрами
         проверить: статус 200ок, отправленные данные корректно создаются.
    */
    @Test
    public void petUpdateSomeParametres(){
        Pet pet = Pet.builder()
                .id(165)
                .name("elza")
                .status("available")
                .build();

        PetNewApi petApi = new PetNewApi();

        petApi.createPet(pet)
                .statusCode(200)
                .body("id", equalTo(165))
                .body("name", equalTo("elza"))
                .body("status", equalTo("available"));
    }

    /*
        кейс: отправить запрос для обновления инфы о питомце с одним параметром
        проверить: статус 200ок, отправленный id создался корректные, неотправленные параметры - null.
    */
    @Test
    public void petUpdateOnlyId(){
        Pet pet = Pet.builder()
                .id(165)
                .build();

        PetNewApi petApi = new PetNewApi();

        petApi.createPet(pet)
                .statusCode(200)
                .body("id", equalTo(165))
                .body("name", equalTo(null))
                .body("status", equalTo(null));
    }

}
