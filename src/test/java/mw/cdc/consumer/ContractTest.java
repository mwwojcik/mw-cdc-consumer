package mw.cdc.consumer;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContractTest {
    
    @DisplayName("Should return Yes when service is alive")
    @Test 
    void shouldReturnYesWhenServiceIsAlive() {
        //RestAssured.given().when().get("http://www.google.com").then().statusCode(200);
        RestAssured.given().when().param("question","Are you alive?").get("http://192.168.0.116:9876/askme").then().statusCode(200).body(
            Matchers.equalTo("Yes"));
     }

}
