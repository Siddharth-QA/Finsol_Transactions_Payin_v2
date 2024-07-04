package StepDefs.APIv1;

import StepDefs.BaseStepDef;
import io.cucumber.java.en.When;

public class PayinTRansactionsStepDef extends BaseStepDef {

    @When("User hits the post api request 10 times")
    public void userHitsThePostApiRequestTimes() {
        for(int i=1;i<=10;i++){
            payinTransaction.getToken();
            payinTransaction.sendPayRequest();
            payinTransaction.statusCheck();
            payinTransaction.makeTransactionSuccessFail();
            payinTransaction.verifyTransactionStatus();
        }
    }
    @When("Verify required fields")
    public void verifyRequiredFields(){
        payinTransaction.getToken();
        payinTransaction.verifyRequiredFields();
    }

    @When("Verify OrderId already Exists")
    public void verifyOrderIdAlreadyExists() {
        payinTransaction.getToken();
        payinTransaction.verifyOrderIdExists();
    }

    @When("Verify Invalid Token")
    public void verifyInvalidToken() {
        payinTransaction.verifyInvalidToken();
    }


}
