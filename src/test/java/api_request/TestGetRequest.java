package api_request;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGetRequest {
    Response response;
    @BeforeMethod
    public void getAllUser() {

        response = RestAssured.given().baseUri("https://reqres.in/").
                when().get("/api/users?page=2").
                then().extract().response();
    }

    @Test
    public void testStatusCode(){
//        int statusCode = response.getStatusCode();
//        System.out.println(statusCode);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test
    public void testStatusLine(){
        System.out.println(response.getStatusLine());
        Assert.assertTrue(response.getStatusLine().contains("200 OK"));
    }

    @Test
    public void testContentType(){
        System.out.println(response.header("Content-Type"));
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");

    }
    @Test
    public void testResponseBody(){
       // System.out.println(response.body().asString());
        JsonPath jsonPath = response.jsonPath();
        String actualEmail = jsonPath.get("data[0].email");
//        int page = jsonPath.get("total");
//        String name = jsonPath.get("data[1].first_name");
//        System.out.println(page);
//        System.out.println(name);
//        String  url = jsonPath.get("support.url");
//        System.out.println(url);
        Assert.assertEquals(actualEmail,"michael.lawson@reqres.in");
    }
}
