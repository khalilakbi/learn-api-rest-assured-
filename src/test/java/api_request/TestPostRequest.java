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

public class TestPostRequest {
    Response response;
    String name = RandomData.firstName();
    String job = RandomData.job();
    @BeforeMethod
    public void testCreateUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name",name);
        requestBody.put("job",job);

        response = RestAssured.given().baseUri("https://reqres.in/").contentType(ContentType.JSON).body(requestBody).
                when().post("/api/users").
                then().extract().response();
    }

    @Test
    public void testStatusCode(){
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),201);
    }
    @Test
    public void testContentType(){
        //test server name
        //Assert.assertEquals(response.header("Server"),"cloudflare");
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
    }
    @Test
    public void testResponseBody(){
        JsonPath jsonPath = response.jsonPath();
        String expectedName = jsonPath.get("name");
        System.out.println(expectedName);
        Assert.assertEquals(expectedName, name);
    }
}
