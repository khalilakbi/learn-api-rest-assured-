package book_store;

import data.RandomData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestRegisterUser {

    Response response;
    String user = RandomData.firstName();
    String password = RandomData.password();

    @BeforeMethod
    public void registerUserRequest(){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userName",user);
        requestBody.put("password",password);
       response =  RestAssured.given().baseUri("https://bookstore.toolsqa.com").contentType(ContentType.JSON).body(requestBody).
                when().post("/Account/v1/User").
                then().extract().response();

    }
    @Test
    public void testStatusCode(){
        System.out.println(response.getStatusCode());
        System.out.println(response.body().asString());
    }
}
