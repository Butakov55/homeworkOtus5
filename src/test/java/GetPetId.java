
import dto.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import services.PetNewApi;

import static org.hamcrest.Matchers.equalTo;

@Component
public class GetPetId {
    /*
        кейс: получить данные по корректному id питомца
        проверить: 200ок, инфу, которая пришли.
         */
    @Autowired
    public PetNewApi petNewApi;

    @Test
    public void getPetByCorrectId(){
        //создаем питомца
        Pet pet = Pet.builder()
                .id(165)
                .name("elza")
                .status("available")
                .build();

        PetNewApi petApi = new PetNewApi();
        petApi.createPet(pet);

        //проверяем данные по созданному питомцу
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
