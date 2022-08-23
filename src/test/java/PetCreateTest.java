import com.github.javafaker.Faker;
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
        //создаем питомца
        Faker faker = new Faker();
        String randomNameBeforeUpdate = faker.name().firstName();

        Pet pet = Pet.builder()
                .id(165)
                .name(randomNameBeforeUpdate)
                .status("available")
                .build();

        PetNewApi petApi = new PetNewApi();

        petApi.createPet(pet);

        //проверяем данные
        petApi.getPetId("165")
                .statusCode(200)
                .body("id", equalTo(165))
                .body("name", equalTo(randomNameBeforeUpdate))
                .body("status", equalTo("available"));

        //обновляем данные
        String randomNameAfterUpdate = faker.name().firstName();
        Pet pet2 = Pet.builder()
                .id(165)
                .name(randomNameAfterUpdate)
                .status("available")
                .build();

        petApi.createPet(pet2);

        //проверяем обновление данных
        petApi.getPetId("165")
                .statusCode(200)
                .body("id", equalTo(165))
                .body("name", equalTo(randomNameAfterUpdate))
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


        //обновляем данные
        Pet pet2 = Pet.builder()
                .id(200)
                .build();

        petApi.createPet(pet2);

        //проверяем обновление данных
        petApi.getPetId("200")
                .statusCode(200)
                .body("id", equalTo(200))
                .body("name", equalTo(null))
                .body("status", equalTo(null));
    }

}
