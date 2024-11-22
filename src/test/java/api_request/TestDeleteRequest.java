package api_request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestDeleteRequest {
   Response response;


    @BeforeMethod
    public void testDeleteUser(){
        response = RestAssured.given().baseUri("https://reqres.in/").
                when().delete("/api/users/2").
                then().extract().response();
    }

    @Test
    public void testStatusCode(){
        Assert.assertEquals(response.getStatusCode(),204);
    }
}
