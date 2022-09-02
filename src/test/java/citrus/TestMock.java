package citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.JUnit4CitrusSupport;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import dto.Pet;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class TestMock extends JUnit4CitrusSupport {

    public TestContext context;

    @Test
    @CitrusTest
    public void getTest() {

        this.context = citrus.getCitrusContext().createTestContext();

        $(http()
                .client("urlAnimal")
                .send()
                .post()
                .message()
                .type("application/json")
                .body("{\n" +
                        "    \"id\": 165,\n" +
                        "    \"category\": null, \n" +
                        "    \"name\": \"Elzas\",\n" +
                        "    \"photoUrls\": null, \n" +
                        "    \"tags\": null, \n" +
                        "    \"status\": \"available\"\n" +
                        "}")

        );

        $(http().client("restClient")
                .send()
                .get(context.getVariable("idAnimal"))
                .fork(true)
        );

        $(http().server("restServer")
                .receive()
                .get()
        );

        $(http().server("restServer")
                .send()
                .response()
                .message()
                .type("application/json")
                .body("{\n" +
                        "    \"id\": 165,\n" +
                        "    \"name\": \"Elzas\",\n" +
                        "    \"status\": \"available\"\n" +
                        "}")
        );

        $(http()
                .client("restClient")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type("application/json")
                .body(new ObjectMappingPayloadBuilder(getJsonData(), "objectMapper"))
        );

    }

    public Pet getJsonData() {
        Pet pet = new Pet();
        pet.setId(165);
        pet.setName("Elzas");
        pet.setStatus("available");
        return pet;

    }

}
