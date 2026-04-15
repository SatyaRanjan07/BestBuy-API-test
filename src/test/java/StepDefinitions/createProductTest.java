package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static org.hamcrest.Matchers.*;

public class createProductTest {
    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse valRes;

    File file = new File(System.getProperty("user.dir")
            + "/src/test/resources/json_Payloads/createProductReqPayload.json");

    @Given("I set base URI as {string} and I have valid payload with required headers")
    public void i_set_base_uri_as_and_i_have_valid_payload_with_required_headers(String string) {
        RestAssured.baseURI = "http://localhost:3030";
        reqSpec = RestAssured.given().baseUri("http://localhost:3030")
                .contentType("application/json")
                .body(file);
    }
    @When("I send post request to {string}")
    public void i_send_post_request_to(String string) {
        response = reqSpec.when().post(string);
        System.out.println(response.asPrettyString());

    }
    @Then("I should get status code as {int}")
    public void i_should_get_status_code_as(Integer int1) {
        valRes = response.then();
        valRes.statusCode(int1);

    }
    @Then("response should contains {string}")
    public void response_should_contains(String string) {
            valRes.body(containsString(string));
    }
    @Then("response should have product name as {string}")
    public void response_should_have_product_name_as(String prodName) {
        valRes.body("name",equalTo(prodName));
    }
    @Then("response should have product model as {string}")
    public void response_should_have_product_model_as(String model) {
        valRes.body("model",equalTo(model));
    }

    @Then("response payload should have correct schema")
    public void response_payload_should_have_correct_schema() {
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateProduct_SCHEMA.json"));
    }
}
