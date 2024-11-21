package api_request;

import data.RandomData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestUpdateRequest {

    Response response;
    String name = RandomData.firstName();
    String job = RandomData.job();
    @BeforeMethod
    public void requestUpdateUser(){

        Map<String ,String > deleteBody = new HashMap<>();
        deleteBody.put("name",name);
        deleteBody.put("job",job);
        response = RestAssured.given().baseUri("https://reqres.in/").contentType(ContentType.JSON).body(deleteBody).
                when().put("/api/users/2").
                then().extract().response();
    }

    @Test
    public void testStatusCode(){
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testResponseBody(){
        JsonPath jsonPath = response.jsonPath();
        // actual name will hold the name from response body
        String actualName = jsonPath.get("name");
        // name will be the name randomly generated in global variable
        Assert.assertEquals(actualName,name);
    }


}
