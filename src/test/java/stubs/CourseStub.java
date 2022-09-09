package stubs;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.junit.JUnit4CitrusSupport;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static com.consol.citrus.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;

public class CourseStub extends JUnit4CitrusSupport {
    public TestContext context;

    @Test
    @CitrusTest
    public void getCourseTest() {

        this.context = citrus.getCitrusContext().createTestContext();

        $(http().client("restClientUser")
                .send()
                .get("course/get/all")
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
                .body("[\n" +
                        "{\n" +
                        "    \"name\": \"QA Java\",\n" +
                        "    \"score\": 15000\n" +
                        "},\n" +
                                "{\n" +
                                "    \"name\": \"Java\",\n" +
                                "    \"score\": 20000\n" +
                                "}\n" +
                        "]"
                        )
        );

        $(http()
                .client("restClientUser")
                .receive()
                .response(HttpStatus.OK)
                        .message()
                .validate(jsonPath()
                        .expression("$..name", "QA Java,Java")
                        .expression("$..score", "15000,20000")
                )
        );
    }
}