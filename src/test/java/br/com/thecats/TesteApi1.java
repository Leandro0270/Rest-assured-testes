package br.com.thecats;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class TesteApi1 {

    String favourite_id;

    public void favoritar() {
        String url = "https://api.thecatapi.com/v1/favourites";
        String corpo = "{\"image_id\": \"2OsQRQDnM\", \"sub_id\": \"demo-40bf29\"}";

        Response response =
                 given()
                         .contentType("application/json")
                         .header("x-api-key", "cc1c7ac7-fa24-4911-b89e-ca1f1a039e3f")
                         .body(corpo)
                .when()
                         .post(url);
        response.then()
                         .statusCode(200).body("message", containsString("SUCCESS"));

        String id = response.jsonPath().getString("id");
        favourite_id = id;
    }
    public void deletafav() {
        String url = "https://api.thecatapi.com/v1/favourites/{favourite_id}";

        Response response =
                given().contentType("application/json")
                    .header("x-api-key", "cc1c7ac7-fa24-4911-b89e-ca1f1a039e3f")
                    .pathParam("favourite_id", favourite_id)
                .when().
                    delete(url);
        response.then()
                    .body("message", containsString("SUCCESS"));
        System.out.println("Retorno 2 =>" + response.body().asString());
    }
    @Test
    public void Criaedeleta(){
        favoritar();
        deletafav();
    }
}

//b1fdd175-93a2-4716-ae98-7d04fa67ebd5