package StepDefinitions;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import Utils.APIClientActions;
import Utils.APIScenarioContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;


public class APICommonSteps {

    private final APIScenarioContext context;
    private final APIClientActions apiClient;
    private final Map<String, String> pendingPathParams = new HashMap<>();
	
    public APICommonSteps(APIScenarioContext context)
    {
    	this.context = context;
    	this.apiClient = new APIClientActions(context);
    }
    
    //The sendRequest method has internal steps where it saves the response of the hit in context as lastResponse
    @When("I send a {string} request to {string} with payload {string}")
    public void sendRequestWithPayload(String method, String endpointKey, String payloadKey)
    {
    	apiClient.sendRequest(endpointKey, payloadKey, false, null, fetchPathParams());
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedCode)
    {
    	Assert.assertEquals("Body: " + lastResponse().asPrettyString(), expectedCode, lastResponse().getStatusCode());
    }
    
    @Then("the response should contain key {string}")
    public void verifyKeyExists(String jsonPath) {
        Assert.assertNotNull("Expected key '" + jsonPath + "'", lastResponse().jsonPath().get(jsonPath));
    }
    @And("I save the response field {string} as {string}")
    public void saveField(String jsonPath, String contextKey) {
        context.set(contextKey, lastResponse().jsonPath().get(jsonPath));
    }
    
	// Path params are set on the line(s) before the request, then
    // consumed and cleared here so the next request starts clean.
    private Map<String, String> fetchPathParams() {
        Map<String, String> params = new HashMap<>(pendingPathParams);
        pendingPathParams.clear();
        return params;
    }
    
    //Method fetches latest response that was stored in context right after the api was requested
    private Response lastResponse() {
        return (Response) context.get("lastResponse");
    }
    
    
}
