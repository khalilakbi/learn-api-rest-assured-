package book_store;


import data.RandomData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DeleteUser {


    String user = RandomData.firstName();
    String password = RandomData.password();
    Response deleteResponse;

    Response responseCreate;
    Response responseGenerateToken;

    @BeforeMethod
    public void createGenerateToken() {
        // arrange act assert

        // request body (will the same for create user and generate token)
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", user);
        requestBody.put("password", password);

        // create user request
        responseCreate = RestAssured.given().baseUri("https://bookstore.toolsqa.com").contentType(ContentType.JSON).body(requestBody).
                when().post("/Account/v1/User").
                then().extract().response();
        // generate token request
        responseGenerateToken = RestAssured.given().baseUri("https://bookstore.toolsqa.com").contentType(ContentType.JSON).body(requestBody)
                .when().post("/Account/v1/GenerateToken")
                .then().extract().response();


    }


    @Test
    public void testStatusCode() {
        // joson path get userId from create user request
        JsonPath jsonPath = responseCreate.jsonPath();
        String id = jsonPath.get("userID");
        System.out.println(id);
        // json path to get token from generate token request
        JsonPath jsonPath1 = responseGenerateToken.jsonPath();
        String token = jsonPath1.get("token");
        System.out.println(token);
        String parameter = "/Account/v1/User/" + id;

        // delete request
        deleteResponse = RestAssured.given().baseUri("https://bookstore.toolsqa.com").
                auth().oauth2(token).
                when().delete(parameter).
                then().extract().response();
        System.out.println(deleteResponse.getStatusCode());
        System.out.println(deleteResponse.body().asString());


    }
}
