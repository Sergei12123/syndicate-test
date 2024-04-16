package com.cmtrc48239a0helloworldtest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "DemoJavaLambda",
	roleName = "DemoJavaLambda-role",
	isPublishVersion = true,
	aliasName = "${lambdas_alias_name}",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
public class Demojavalambda implements RequestHandler<APIGatewayProxyRequestEvent, Map<String, Object>> {

	public Map<String, Object> handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		if(request.getPath().equals("/hello") && request.getHttpMethod().equals("GET")){
			System.out.println("Hello from lambda");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", 200);
			resultMap.put("message", "Hello from Lambda");
			return resultMap;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("statusCode", 200);
		return resultMap;
	}
}
