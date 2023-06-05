package apiTest;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectJsonTest {

    @Test
    public void verifyCreateProject(){
        JSONObject body = new JSONObject();
        body.put("Content","Miguel Json");
        body.put("Icon",4);


        given()
                .auth()
                .preemptive()
                .basic("upb2023@upb.com","12345")
                .header("Content-type","application/json")
                .body(body.toString())
                .log().all()
        .when()
                .post("https://todo.ly/api/projects.json")
        .then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")))
                .body("Icon",equalTo(body.get("Icon")));
    }
}
