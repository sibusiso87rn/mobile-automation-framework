package starter.testing.core.util.rest;


import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import starter.testing.core.util.environment.EnvironmentConfig;
import starter.testing.core.util.environment.config.RestServiceConfig;



public class RestAssuredHelper {

    private static final Logger logger = LogManager.getLogger(RestAssuredHelper.class);

    public static ResponseSpecification defaultResponseSpecBuilder(){
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectContentType("application/json;charset=UTF-8");
        return builder.build();
    }

    public static RequestSpecification init(String servicekey){
        RestServiceConfig restServiceConfig =  EnvironmentConfig.getInstance().getRestServiceConfigByKey(servicekey);
        RestAssured.basePath = restServiceConfig.getBasePath();
        RestAssured.baseURI  = restServiceConfig.getBaseHost();

        String browserStackUsername = EnvironmentConfig.getInstance().getConfigValue("browserstack.username");
        String browserStackPassword = EnvironmentConfig.getInstance().getConfigValue("browserstack.password");

        logger.debug("RequestSpecification RestAssured.basePath "+ RestAssured.basePath);
        logger.debug("RequestSpecification RestAssured.baseURI "+ RestAssured.baseURI);
        logger.debug("RequestSpecificationReq Full Path "+ restServiceConfig.getFullPath());
        return RestAssured.given().auth().basic(browserStackUsername,browserStackPassword);
    }

    //Concatinates the base uri and path
    public static String resolveRequestURI(String input){
        return  RestAssured.baseURI+RestAssured.basePath+input;
    }

}
