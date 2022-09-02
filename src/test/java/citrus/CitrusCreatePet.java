package citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestRunner;
import com.consol.citrus.junit.JUnit4CitrusSupport;
import org.junit.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;


public class CitrusCreatePet extends JUnit4CitrusSupport {

    public TestContext context;

    @Test
    @CitrusTest
    public void getTest() {
        int id = 165;
        String name = "Elza";
        String status = "available";

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

    }

}
