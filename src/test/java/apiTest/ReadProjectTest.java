package apiTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadProjectTest {
    private int idProject;

    @BeforeEach
    public void requirementProject() {
        JSONObject body = new JSONObject();
        body.put("Content", "EynarJSON");
        body.put("Icon", 1);
        Response response = given()
                .auth()
                .preemptive()
                .basic("miguel123@upb.com", "12345")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Icon", equalTo(body.get("Icon")))
                .body("Content", equalTo(body.get("Content")));

        //Extraer datos de la respuesta
        idProject = response.then().extract().path("Id");
    }

    @Test
    public void verifyReadProject() {
        Response response = given()
                .auth()
                .preemptive()
                .basic("miguel123@upb.com", "12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/projects/" + idProject + ".json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Icon", equalTo(1))
                .body("Content", equalTo("EynarJSON"));
    }
}
