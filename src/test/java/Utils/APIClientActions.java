package Utils;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIClientActions {
	
	private final APIScenarioContext context;

    public APIClientActions(APIScenarioContext context) {
        this.context = context;
    }
    
    static {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    public Response sendRequest(String endpointKey, String payloadKey, boolean useAuth,
                                 String authContextKey, Map<String, String> pathParams) {

        String method = APIEndPointManager.getMethod(endpointKey);
        String path = APIEndPointManager.getPath(endpointKey, pathParams);

        RequestSpecification request = RestAssured.given()
                .baseUri(APIConfigReader.getBaseUrl())
                .basePath(path)
                .contentType(ContentType.JSON);

        // Token sent as-is, no "Bearer " prefix - matches the Postman collection.
        if (useAuth) {
            request = request.header("Authorization", context.getString(authContextKey));
        }

        if (payloadKey != null) {
            request = request.body(APIPayloadManager.getPayload(payloadKey, context));
        }

        Response response = fire(request, method);
        context.set("lastResponse", response);
        return response;
    }

    private Response fire(RequestSpecification request, String method) {
        switch (method) {
            case "POST": return request.post();
            case "GET": return request.get();
            case "PUT": return request.put();
            case "DELETE": return request.delete();
            default: throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

}
