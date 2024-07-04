package StepDefs.APIv2;

import StepDefs.BaseStepDef;
import io.cucumber.java.en.When;

public class PayinTransactionsStepDef extends BaseStepDef {

    @When("User hits the post api request 10 times")
    public void userHitsThePostApiRequestTimes() {

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


    @When("Verify post with subPayMode UPQR 10 times")
    public void verifyPostWithSubPayModeUPQRTimes() {
        for(int i=1;i<=10;i++){
            payinTransaction.getToken();
            payinTransaction.sendPayRequest();
            payinTransaction.statusCheck();
            payinTransaction.makeTransactionSuccessFail();
            payinTransaction.verifyTransactionStatus();
        }
    }

    @When("Verify post with subPayMode CLCT 10 times")
    public void verifyPostWithSubPayModeCLCTTimes() {
        for(int i=1;i<=10;i++){
            payinTransaction.getToken();
            payinTransaction.sendPayRequestCLCT();
            payinTransaction.statusCheck();
            payinTransaction.makeTransactionSuccessFail();
            payinTransaction.verifyTransactionStatus();
        }
    }

    @When("Verify post with subPayMode ITNT 10 times")
    public void verifyPostWithSubPayModeITNTTimes() {
        for(int i=1;i<=10;i++){
            payinTransaction.getToken();
            payinTransaction.sendPayRequestITNT();
            payinTransaction.statusCheck();
            payinTransaction.makeTransactionSuccessFail();
            payinTransaction.verifyTransactionStatus();
        }
    }

    @When("Verify non S2S required fields")
    public void verifyNonSSRequiredFields() {
        payinNs2s.getTokenns2s();
        payinNs2s.verifyRequiredFieldsnS2S();
    }

    @When("Verify non s2s pay request 10 times")
    public void verifyNonSSPayRequestTimes() {
        for(int i=1;i<=10;i++){
            payinNs2s.getTokenns2s();
            payinNs2s.sendPayRequestnS2S();
            payinNs2s.statusChecknS2S();
            payinNs2s.openUrl();
            payinNs2s.statusChecknS2SPending();
           payinTransaction.makeTransactionSuccessFail();
            payinNs2s.verifyTransactionStatusnS2S();
        }
    }

    @When("Verify OrderId already Exists non s2s")
    public void verifyOrderIdAlreadyExistsNonSS() {
        payinNs2s.getTokenns2s();
        payinNs2s.verifyOrderIdExistsnS2S();
    }

    @When("Verify Invalid Token non s2s")
    public void verifyInvalidTokenNonSS() {
        payinNs2s.verifyInvalidTokennS2S();

    }
}
