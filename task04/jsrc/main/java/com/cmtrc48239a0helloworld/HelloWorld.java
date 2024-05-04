package com.cmtrc48239a0helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.lambda.LambdaUrlConfig;
import com.syndicate.deployment.model.Architecture;
import com.syndicate.deployment.model.DeploymentRuntime;
import com.syndicate.deployment.model.RetentionSetting;
import com.syndicate.deployment.model.lambda.url.AuthType;
import com.syndicate.deployment.model.lambda.url.InvokeMode;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "hello_world",
	roleName = "hello_world-role",
	isPublishVersion = true,
	runtime = DeploymentRuntime.JAVA11,
	architecture = Architecture.ARM64,
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@LambdaUrlConfig(
	authType = AuthType.NONE,
	invokeMode = InvokeMode.BUFFERED
)
public class HelloWorld implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		System.out.println("Hello from lambda");
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("statusCode", 200);
		responseBody.put("message", "Hello from Lambda");
		try {
			String jsonResponse = mapper.writeValueAsString(responseBody);
			response.setBody(jsonResponse);

		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		response.setStatusCode(200);
		return response;
	}
}
