package Pages.APIv1;

import Utils.PropFileHandler;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.Random;


public class PayinTransaction {
    static PropFileHandler propFileHandler = new PropFileHandler();
    String apiBaseURI  = propFileHandler.readProperty("apiBaseURI");

    static String token;
    static String txnId;

    public void getToken() {
        RestAssured.baseURI = apiBaseURI;
        RequestSpecification request = RestAssured.given();
        // Adding headers
        request.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("salt", "B9TDPIcBfviCTrH0qLvLPVz7kKaEzQE9");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");

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

    public void sendPayRequest() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "PMZeTyBhp8JAwnw7AY3KHbdKhyYlfaaOLusfdQMyeVmLcESoIqOULZupo6S62NzS64AXD7erttcCZ1pCig5vXYycLMbmAB51fotPtjUWZIJvxod4vbCdfhvDubJBf6CR");


        Random random = new Random();

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        Response response = request.post("/pay-request");

        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        txnId = jsonPathEvaluator.get("data.txn_id");
        String apiStatus = jsonPathEvaluator.get("status");
        String txnStatus= jsonPathEvaluator.get("data.status");
        System.out.println("TxnId: " + txnId);

        Assert.assertEquals(apiStatus,"Success");
        Assert.assertEquals(txnStatus,"Pending");
        String redirectUrl = jsonPathEvaluator.get("data.redirect_url");
        System.out.println("redirectUrl: " + redirectUrl);

    }

    public void statusCheck() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "PMZeTyBhp8JAwnw7AY3KHbdKhyYlfaaOLusfdQMyeVmLcESoIqOULZupo6S62NzS64AXD7erttcCZ1pCig5vXYycLMbmAB51fotPtjUWZIJvxod4vbCdfhvDubJBf6CR");

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
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
        Assert.assertEquals(txnStatus,"Pending");
        String redirectUrl = jsonPathEvaluator.get("data.redirect_url");
        System.out.println("Extracted Token: " + token);

    }

    public void makeTransactionSuccessFail(){
        RestAssured.baseURI = "https://spayin.finsol.group";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get("/acq/lt5minute");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Boolean sts = jsonPathEvaluator.get("sts");
        Assert.assertTrue(sts);

    }

    public void verifyTransactionStatus(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "PMZeTyBhp8JAwnw7AY3KHbdKhyYlfaaOLusfdQMyeVmLcESoIqOULZupo6S62NzS64AXD7erttcCZ1pCig5vXYycLMbmAB51fotPtjUWZIJvxod4vbCdfhvDubJBf6CR");

        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
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
    //Required fields
    public void verifyRequiredFields() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "PMZeTyBhp8JAwnw7AY3KHbdKhyYlfaaOLusfdQMyeVmLcESoIqOULZupo6S62NzS64AXD7erttcCZ1pCig5vXYycLMbmAB51fotPtjUWZIJvxod4vbCdfhvDubJBf6CR");


        Random random = new Random();

        JSONObject requestParams = new JSONObject();// Cast
//        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        Response response = request.post("/pay-request");

        int statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();
        String remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without amt: " + remarks);

        Assert.assertEquals(remarks,"Something went wrong.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without paymode: " + remarks);

        Assert.assertEquals(remarks,"Something went wrong.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
//        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without sub_paymode: " + remarks);

        Assert.assertEquals(jsonPathEvaluator.get("data.status"),"Rejected");
        Assert.assertEquals(jsonPathEvaluator.get("data.status_code"),"3333");
        Assert.assertEquals(remarks,"sub_pay_mode: This field is required.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
//        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without curr: " + remarks);

        Assert.assertEquals(jsonPathEvaluator.get("data.status"),"Rejected");
        Assert.assertEquals(jsonPathEvaluator.get("data.status_code"),"3333");
        Assert.assertEquals(remarks,"currency: This field is required.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
//        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without merchant_id: " + remarks);

        Assert.assertEquals(remarks,"Something went wrong.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
//        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without order_id: " + remarks);

        Assert.assertEquals(remarks,"Something went wrong.");

        requestParams.clear();

        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
//        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without cust_name: " + remarks);

        Assert.assertEquals(jsonPathEvaluator.get("data.status"),"Rejected");
        Assert.assertEquals(jsonPathEvaluator.get("data.status_code"),"3333");
        Assert.assertEquals(remarks,"cust_name: This field is required.");

        requestParams.clear();
        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
//        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without cust_email: " + remarks);

        Assert.assertEquals(remarks,"Something went wrong.");



        requestParams.clear();
        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
//        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without cust_country: " + remarks);

        Assert.assertEquals(jsonPathEvaluator.get("data.status"),"Rejected");
        Assert.assertEquals(jsonPathEvaluator.get("data.status_code"),"3333");
        Assert.assertEquals(remarks,"cust_country: This field is required.");

        requestParams.clear();
        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
//        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without cust_phone: " + remarks);

        Assert.assertEquals(remarks,"Something went wrong.");

        requestParams.clear();
        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+random.nextInt(10000));
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
//        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        response = request.post("/pay-request");

        statusCode = response.getStatusCode();

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        jsonPathEvaluator = response.jsonPath();
        remarks = jsonPathEvaluator.get("data.remarks");
        System.out.println("Remarks without callbackUrl: " + remarks);

        Assert.assertEquals(remarks,"Payment Under Processing");
        Assert.assertEquals(jsonPathEvaluator.get("data.status"),"Pending");
        Assert.assertEquals(jsonPathEvaluator.get("data.status_code"),"2222");

    }

    public void verifyOrderIdExists(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Token " + token);
        request.header("Secret-Key", "PMZeTyBhp8JAwnw7AY3KHbdKhyYlfaaOLusfdQMyeVmLcESoIqOULZupo6S62NzS64AXD7erttcCZ1pCig5vXYycLMbmAB51fotPtjUWZIJvxod4vbCdfhvDubJBf6CR");


        Random random = new Random();
        int randNum = random.nextInt(10000);
        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+randNum);
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        Response response = request.post("/pay-request");

        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved: " + statusCode);

        System.out.println("Response body: " + response.getBody().asString());

        //Assertion
        Assert.assertEquals(statusCode, 200);
        // Extracting token
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(jsonPathEvaluator.get("data.status"),"Pending");
        Assert.assertEquals(jsonPathEvaluator.get("data.status_code"),"2222");
        Assert.assertEquals(jsonPathEvaluator.get("data.remarks"),"Payment Under Processing");

        request.body(requestParams.toString());
        Response response1 = request.post("/pay-request");
        int statusCode1 = response1.getStatusCode();
        System.out.println("The status code recieved: " + statusCode1);

        System.out.println("Response body: " + response1.getBody().asString());

        JsonPath jsonPathEvaluator1 = response1.jsonPath();

        Assert.assertEquals(jsonPathEvaluator1.get("data.status"),"Rejected");
        Assert.assertEquals(jsonPathEvaluator1.get("data.status_code"),"3333");
        Assert.assertEquals(jsonPathEvaluator1.get("data.remarks"),"Order Id Already Exists");

    }

    public void verifyInvalidToken(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");


        Random random = new Random();
        int randNum = random.nextInt(10000);
        JSONObject requestParams = new JSONObject();// Cast
        requestParams.put("amount", "10");
        requestParams.put("pay_mode", "UP");
        requestParams.put("sub_pay_mode", "UPQR");
        requestParams.put("currency", "INR");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("merchant_id", "UM1PGA1CZ25YW7E");
        requestParams.put("order_id", "ORD000087654"+randNum);
        requestParams.put("cust_name", "Siddharth");
        requestParams.put("cust_email", "siddharth@gmail.com");
        requestParams.put("cust_country", "India");
        requestParams.put("return_url", "https://pgtesting.csgtech.in/callback%22");
        requestParams.put("cust_phone", "9856745682");
        requestParams.put("callback_url", "https://pgtesting.csgtech.in/callback");

        request.body(requestParams.toString());
        Response response = request.post("/pay-request");

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
