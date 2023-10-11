import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BookingTests {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://hotel-test.equalexperts.io"; // Replace with your API base URL
    }


    @Test(description = "As an API user I want to book for hotel")
    public void postBookingTest() throws IOException, ParseException {

        Helpers helpers = new Helpers();
        String guest1List = helpers.readJsonObjectFile("src/test/resources/testData/guest1.json");
        System.out.println(guest1List);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(guest1List)
                .post("/booking")
                .then()
                .statusCode(200);
    }
    @Test
    public void getBookingTest(){
        Response response=given().when().get("/booking/108836").then().extract().response();
        System.out.println(response.contentType());
        String firstname=response.path("firstname");
        System.out.println(firstname);
        Assertions.assertEquals("FERHAT",firstname);
        int totalprice =response.path("totalprice");
        System.out.println(totalprice);
        Assertions.assertEquals(1232,totalprice);



    }
    @Test
    public void deleteBookingTest(){


    }


}





