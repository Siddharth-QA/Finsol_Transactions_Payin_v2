package Pages.APIv2;

import Utils.PropFileHandler;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.Random;

public class PayinNs2s {
    static String token;
    static String txnId;
    PropFileHandler propFileHandler = new PropFileHandler();
    String apiBaseURI = propFileHandler.readProperty("apiBaseURI");
    public void getTokenns2s() {
        RestAssured.baseURI = apiBaseURI;
        RequestSpecification request = RestAssured.given();
        // Adding headers
        request.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("salt", "LtOizbJOpPu88nQvE3Eu2BIFsVlIBR6K");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");

        request.body(requestParams.toString());
        Response response = request.post("/get-token");


        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        token = jsonPathEvaluator.get("data.token");
        System.out.println("Extracted Token: " + token);

    }

    public void verifyRequiredFieldsnS2S(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "MNBD9Mvk3Dj5G9DJhHsmsqPGns3o2VSl6NNOvWYVjUrnIxMSA9cqK8BOjuQAcwX3m3f4MGBxnYTmyobKzaQyo8ScDVKfSqpRmnzcE0ZSt85g75JzD4cUF6AzgD89VM4T");


        Random random = new Random();

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("amount", "10");
        requestParams.put("currency", "INR");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("cust_country", "India");
        requestParams.put("cust_state", "Uttar Pradesh");
        requestParams.put("cust_city", "Noida");
        requestParams.put("cust_pincode", "201301");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");
        requestParams.put("remarks", "1234-ASDFG-345-GHJK-54");

        request.body(requestParams.toString());
        Response response = request.post("/ns2srequest");

        int statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        String remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without amt: " + remarks);

//        Assert.assertEquals(remarks,"Something went wrong.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("cust_country", "India");
        requestParams.put("cust_state", "Uttar Pradesh");
        requestParams.put("cust_city", "Noida");
        requestParams.put("cust_pincode", "201301");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");
        requestParams.put("remarks", "1234-ASDFG-345-GHJK-54");

        request.body(requestParams.toString());
        response = request.post("/ns2srequest");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without cur: " + remarks);

        Assert.assertEquals(remarks,"currency: This field is required.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("currency", "INR");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000000));
//        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("cust_country", "India");
        requestParams.put("cust_state", "Uttar Pradesh");
        requestParams.put("cust_city", "Noida");
        requestParams.put("cust_pincode", "201301");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");
        requestParams.put("remarks", "1234-ASDFG-345-GHJK-54");

        request.body(requestParams.toString());
        response = request.post("/ns2srequest");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without cust_name: " + remarks);

        Assert.assertEquals(remarks,"cust_name: This field is required.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("currency", "INR");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_phone", "9856745682");
//        requestParams.put("cust_country", "India");
        requestParams.put("cust_state", "Uttar Pradesh");
        requestParams.put("cust_city", "Noida");
        requestParams.put("cust_pincode", "201301");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");
        requestParams.put("remarks", "1234-ASDFG-345-GHJK-54");

        request.body(requestParams.toString());
        response = request.post("/ns2srequest");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without cust_country: " + remarks);

        Assert.assertEquals(remarks,"cust_country: This field is required.");

        requestParams.clear();


    }
    public void sendPayRequestnS2S(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "MNBD9Mvk3Dj5G9DJhHsmsqPGns3o2VSl6NNOvWYVjUrnIxMSA9cqK8BOjuQAcwX3m3f4MGBxnYTmyobKzaQyo8ScDVKfSqpRmnzcE0ZSt85g75JzD4cUF6AzgD89VM4T");


        Random random = new Random();

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("amount", "10");
        requestParams.put("currency", "INR");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("cust_country", "India");
        requestParams.put("cust_state", "Uttar Pradesh");
        requestParams.put("cust_city", "Noida");
        requestParams.put("cust_pincode", "201301");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");
        requestParams.put("remarks", "1234-ASDFG-345-GHJK-54");

        request.body(requestParams.toString());
        Response response = request.post("/ns2srequest");

        int statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        txnId = jsonPathEvaluator.get("data.txn_id");
        String apiStatus = jsonPathEvaluator.get("status");
        String txnStatus= jsonPathEvaluator.get("data.status");
        String remarks= jsonPathEvaluator.get("data.remarks");
        System.out.println("TxnId: " + txnId);

        Assert.assertEquals(apiStatus,"Success");
        Assert.assertEquals(txnStatus,"Accepted");
        Assert.assertEquals(remarks,"Request Accepted");
        redirectUrl = jsonPathEvaluator.get("data.redirect_url");
        System.out.println("redirectUrl: " + redirectUrl);

    }
    String redirectUrl;

    public  void statusChecknS2S(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "MNBD9Mvk3Dj5G9DJhHsmsqPGns3o2VSl6NNOvWYVjUrnIxMSA9cqK8BOjuQAcwX3m3f4MGBxnYTmyobKzaQyo8ScDVKfSqpRmnzcE0ZSt85g75JzD4cUF6AzgD89VM4T");

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("txn_id", txnId);

        request.body(requestParams.toString());
        Response response = request.post("/get-status");

        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.txn_id");

        String apiStatus = jsonPathEvaluator.get("status");
        String txnStatus= jsonPathEvaluator.get("data.status");
        String statusCodeData= jsonPathEvaluator.get("data.status_code");
        String remarks= jsonPathEvaluator.get("data.remarks");
        System.out.println("TxnId: " + txnId);

        Assert.assertEquals(apiStatus,"Success");
        Assert.assertEquals(txnStatus,"Accepted");
        Assert.assertEquals(remarks,"Request Accepted");
        Assert.assertEquals(statusCodeData,"0000");
        System.out.println("Extracted Token: " + token);
    }
    WebDriver driver;
    public void openUrl(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("disable-gpu");
        options.addArguments("no-sandbox");
        options.addArguments("disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get(redirectUrl);
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public  void statusChecknS2SPending(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "MNBD9Mvk3Dj5G9DJhHsmsqPGns3o2VSl6NNOvWYVjUrnIxMSA9cqK8BOjuQAcwX3m3f4MGBxnYTmyobKzaQyo8ScDVKfSqpRmnzcE0ZSt85g75JzD4cUF6AzgD89VM4T");

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("txn_id", txnId);

        request.body(requestParams.toString());
        Response response = request.post("/get-status");

        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.txn_id");

        String apiStatus = jsonPathEvaluator.get("status");
        String txnStatus= jsonPathEvaluator.get("data.status");
        String statusCodeData= jsonPathEvaluator.get("data.status_code");
        String remarks= jsonPathEvaluator.get("data.remarks");
        System.out.println("TxnId: " + txnId);

        Assert.assertEquals(apiStatus,"Success");
        Assert.assertEquals(txnStatus,"Pending");
        Assert.assertEquals(remarks,"Payment Under Processing");
        Assert.assertEquals(statusCodeData,"2222");
        System.out.println("Extracted Token: " + token);
    }

    public void verifyTransactionStatusnS2S(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "MNBD9Mvk3Dj5G9DJhHsmsqPGns3o2VSl6NNOvWYVjUrnIxMSA9cqK8BOjuQAcwX3m3f4MGBxnYTmyobKzaQyo8ScDVKfSqpRmnzcE0ZSt85g75JzD4cUF6AzgD89VM4T");

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("txn_id", txnId);

        request.body(requestParams.toString());
        Response response = request.post("/get-status");

        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.txn_id");

        String apiStatus = jsonPathEvaluator.get("status");
        String txnStatus= jsonPathEvaluator.get("data.status");
        System.out.println("TxnId: " + txnId);

        Assert.assertEquals(apiStatus,"Success");
        Assert.assertTrue(txnStatus.equals("Success") || txnStatus.equals("Failed"));
        String redirectUrl = jsonPathEvaluator.get("data.redirect_url");
        System.out.println("Extracted Token: " + token);
    }

    public void verifyOrderIdExistsnS2S(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "MNBD9Mvk3Dj5G9DJhHsmsqPGns3o2VSl6NNOvWYVjUrnIxMSA9cqK8BOjuQAcwX3m3f4MGBxnYTmyobKzaQyo8ScDVKfSqpRmnzcE0ZSt85g75JzD4cUF6AzgD89VM4T");


        Random random = new Random();
        int randNum = random.nextInt(10000000);
        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("amount", "10");
        requestParams.put("currency", "INR");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("order_id", "ORD000087654"+randNum);
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("cust_country", "India");
        requestParams.put("cust_state", "Uttar Pradesh");
        requestParams.put("cust_city", "Noida");
        requestParams.put("cust_pincode", "201301");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");
        requestParams.put("remarks", "1234-ASDFG-345-GHJK-54");

        request.body(requestParams.toString());
        Response response = request.post("/ns2srequest");

        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(jsonPathEvaluator.get("data.status"),"Accepted");
        Assert.assertEquals(jsonPathEvaluator.get("data.status_code"),"0000");
        Assert.assertEquals(jsonPathEvaluator.get("data.remarks"),"Request Accepted");

        request.body(requestParams.toString());
        Response response1 = request.post("/ns2srequest");
        int statusCode1 = response1.getStatusCode();
        System.out.println("The status code recieved: " + statusCode1);

        System.out.println("Response body: " + response1.getBody().asString());

        JsonPath jsonPathEvaluator1 = response1.jsonPath();

        Assert.assertEquals(jsonPathEvaluator1.get("data.status"),"Rejected");
        Assert.assertEquals(jsonPathEvaluator1.get("data.status_code"),"3333");
        Assert.assertEquals(jsonPathEvaluator1.get("data.remarks"),"Order Id Already Exists");

    }

    public void verifyInvalidTokennS2S(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");


        Random random = new Random();
        int randNum = random.nextInt(10000000);
        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("amount", "10");
        requestParams.put("currency", "INR");
        requestParams.put("merchant_id", "UMTZNOPHBH5K8C1");
        requestParams.put("order_id", "ORD000087654"+randNum);
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("cust_country", "India");
        requestParams.put("cust_state", "Uttar Pradesh");
        requestParams.put("cust_city", "Noida");
        requestParams.put("cust_pincode", "201301");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");
        requestParams.put("remarks", "1234-ASDFG-345-GHJK-54");

        request.body(requestParams.toString());
        Response response = request.post("/ns2srequest");

        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 401);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(jsonPathEvaluator.get("status"),"Error");
        Assert.assertEquals(jsonPathEvaluator.get("data.message"),"Token Invalid");
    }




}
