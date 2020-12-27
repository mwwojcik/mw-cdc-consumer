package mw.cdc.consumer;

import io.restassured.RestAssured;
import javax.annotation.PostConstruct;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
//@EnableStubRunnerServer
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "mw:cdc-producer")
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
public class LocalContractTest {

    @StubRunnerPort("mw:cdc-producer")
    private int PORT;

    private String BASEURL;

    @BeforeEach
    void setUp(){
        BASEURL="http://localhost:"+PORT;
    }

    @DisplayName("Should return Yes when service is alive")
    @Test
    void shouldReturnYesWhenServiceIsAlive()
        throws Exception {
        RestAssured.given()
                   .when()
                   .param("question", "Are you alive?")
                   .param("foo", "foo")
                   .get(BASEURL+"/askme")
                   .then()
                   .statusCode(200)
                   .body(Matchers.equalTo("Yes"));
    }
}

