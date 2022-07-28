import dto.Pet;
import org.junit.jupiter.api.Test;
import services.PetNewApi;

import static org.hamcrest.Matchers.equalTo;

public class GetPetId {

    /*
    кейс: получить данные по корректному id питомца
    проверить: 200ок, инфу, которая пришли.
     */
    @Test
    public void getPetByCorrectId(){
        PetNewApi petApi = new PetNewApi();

        petApi.getPetId("165")
                .statusCode(200)
                .body("id", equalTo(165))
                .body("name", equalTo("elza"))
                .body("status", equalTo("available"));
    }

    /*
    кейс: отправить запрос с некорректным id питомца
    проверить: 200ок, сообщение о ошибке
     */
    @Test
    public void getPetByNotCorrectId(){
        PetNewApi petApi = new PetNewApi();

        petApi.getPetId("165000000")
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Pet not found"));
    }
}
