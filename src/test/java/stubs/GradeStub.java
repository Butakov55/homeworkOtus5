package stubs;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.JUnit4CitrusSupport;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import dto.GradeData;
import dto.UserData;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class GradeStub extends JUnit4CitrusSupport {

    public TestContext context;

    @Test
    @CitrusTest
    public void getGradeTest() {

        this.context = citrus.getCitrusContext().createTestContext();

        $(http().client("restClientUser")
                .send()
                .get("user/get/2")
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
                        "    \"score\": 78\n" +
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
    public GradeData getJsonData() {
        GradeData gradeData = new GradeData();
        gradeData.setName("Test User");
        gradeData.setScore(78);
        return gradeData;
    }


}
