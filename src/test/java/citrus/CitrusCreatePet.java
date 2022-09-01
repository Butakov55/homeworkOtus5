package citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestRunner;
import org.junit.Test;


public class CitrusCreatePet extends JUnit4CitrusTestRunner {

    public TestContext context;

    @Test
    @CitrusTest
    public void getTest() {
        int id = 165;
        String name = "Elza";
        String status = "available";

        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client("urlAnimal")
                .send()
                .post()
                .payload("{\n" +
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
