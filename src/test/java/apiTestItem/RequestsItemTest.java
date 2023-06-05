package apiTestItem;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestsItemTest {
    public int idProject;

    @Test
    public void verifyCreateItem(){
        JSONObject body = new JSONObject();
        body.put("Content", "New Item Miguel");
        Response response = given()
                .auth()
                .preemptive()
                .basic("miguel123@upb.com","12345")
                .header("Content-type","application/json")
                .body(body.toString())
                .log().all()
        .when()
                .post("https://todo.ly/api/items.json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")));

        idProject = response.then().extract().path("Id");

        given()
                .auth()
                .preemptive()
                .basic("miguel123@upb.com", "12345")
                .log().all()
        .when()
                .get("https://todo.ly/api/items/" + idProject + ".json")
        .then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo("New Item Miguel"));


        body.put("Content", "Miguel Update Intento");
        given()
                .auth()
                .preemptive()
                .basic("miguel123@upb.com", "12345")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
        .when()
                .put("https://todo.ly/api/items/" + idProject + ".json")

        .then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo("Miguel Update Intento"));


        given()
                .auth()
                .preemptive()
                .basic("miguel123@upb.com", "12345")
                .log().all()
                .when()
                .delete("https://todo.ly/api/items/" + idProject + ".json");

        response.then()
                .log().all()
                .statusCode(200);


    }


}


