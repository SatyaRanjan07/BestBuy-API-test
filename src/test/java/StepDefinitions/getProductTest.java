package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class getProductTest {

    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse valRes;

    @Given("I have valid payload with required headers")
    public void i_have_valid_payload_with_required_headers() {
        reqSpec = RestAssured.given().baseUri("http://localhost:3030")
                .queryParam("id","9999679");
    }
    @When("I send get request with {int}")
    public void i_send_get_request_with(Integer id) {
        response = reqSpec.when().get("/products");
        System.out.println(response.asPrettyString());
    }
    @Then("I should get the product id as {int}")
    public void i_should_get_product_id_as(Integer id) {
        response.then().body("data[0].id",equalTo(id));
    }
    @Then("I should get the status code as {int}")
    public void i_should_get_the_status_code_as(Integer statusCode){
        response.then().statusCode(statusCode);
    }

    @Then("the response should have product name as {string}")
    public void the_response_should_have_product_name_as(String prodName){
        response.then().body("data[0].name",equalTo(prodName));
    }

    @Then("the response should have product model as {string}")
    public void the_response_should_have_product_model_as(String model) {
        response.then().body("data[0].model",equalTo(model));
    }
}
