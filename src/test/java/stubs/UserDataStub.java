package stubs;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.JUnit4CitrusSupport;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import dto.Pet;
import dto.UserData;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class UserDataStub extends JUnit4CitrusSupport {

    public TestContext context;

    @Test
    @CitrusTest
    public void getUserTest() {

        this.context = citrus.getCitrusContext().createTestContext();

        $(http().client("restClientUser")
                .send()
                .get("user/get/all")
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
                        "    \"name\": \"Test User\",\n" +
                        "    \"cource\": \"QA\",\n" +
                        "    \"email\": \"test@test.test\",\n" +
                        "    \"age\": 23\n" +
                        "}")
        );

        $(http()
                .client("restClientUser")
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type("application/json")
                .body(new ObjectMappingPayloadBuilder(getJsonData(), "objectMapper"))
        );

    }
    public UserData getJsonData() {
        UserData userData = new UserData();
        userData.setName("Test User");
        userData.setCource("QA");
        userData.setEmail("test@test.test");
        userData.setAge(23);
        return userData;
    }
}
