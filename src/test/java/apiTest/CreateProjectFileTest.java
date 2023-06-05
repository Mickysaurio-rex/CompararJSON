package apiTest;

import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectFileTest {

    @Test
    public void verifyCreateProject(){
        String filePath = new File("").getAbsolutePath();
        given()
                .auth()
                .preemptive()
                .basic("upb2023@upb.com","12345")
                .header("Content-type","application/json")
                .body(new File(filePath + "/src/test/resources/createProjectBody.json"))
                .log().all()
        .when()
                .post("https://todo.ly/api/projects.json")
        .then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("Miguel Example File"))
                .body("Icon",equalTo(9));
    }
}
