package helper;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.JUnit4CitrusSupport;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import dto.Pet;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class RESTHelper extends JUnit4CitrusSupport {

    public TestContext context;

    @Test
    @CitrusTest
    public void getRestTest() {

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
                        "    \"name\": \"Elza\",\n" +
                        "    \"photoUrls\": null, \n" +
                        "    \"tags\": null, \n" +
                        "    \"status\": \"available\"\n" +
                        "}")

        );

        context.setVariable("value", "superValue");
        $(echo("Property value " + context.getVariable("value")));

        $(echo("Property value " + context.getVariable("idAnimal")));

        $(http().client("urlAnimal")
                .send()
                .get(context.getVariable("idAnimal"))
        );

        $(http()
                .client("urlAnimal")
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
        pet.setName("Elza");
        pet.setStatus("available");
        return pet;

    }
}
