package mw.cdc.consumer;

import io.restassured.RestAssured;
import org.assertj.core.api.Fail;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContractTest {

    public static final String BASEURL = "http://192.168.0.116:9876";

    @DisplayName("Should return Yes when service is alive")
    @Test 
    void shouldReturnYesWhenServiceIsAlive() {
        //RestAssured.given().when().get("http://www.google.com").then().statusCode(200);
        RestAssured.given().when().param("question","Are you alive?").param("foo","foo").get(BASEURL + "/askme").then().statusCode(200).body(
            Matchers.equalTo("Yes"));
     }

     @DisplayName("Should answer Child when get on /askmeaboutage and age 10")
     @Test
     void shouldAnswerChildWhenGetOnAskmeaboutageAndAge10() {
      // given
         RestAssured.given().when().param("age","10").get(BASEURL + "/askmeaboutage").then().statusCode(200).body(
             Matchers.equalTo("Child"));
         ;
      }
}
