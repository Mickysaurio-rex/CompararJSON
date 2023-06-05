package apiTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectTest {

    @Test
    public void verifyCreateProject(){
        given()
                .auth()
                .preemptive()
                .basic("upb2023@upb.com","12345")
                .header("Content-type","application/json")
                .body("{\n" +
                        "   \"Content\":\"API Miguel\", \n" +
                        "   \"Icon\":4\n" +
                        "}")
                .log().all()
        .when()
                .post("https://todo.ly/api/projects.json")
        .then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo("API Miguel"))
                .body("Icon",equalTo(4));
    }
}
