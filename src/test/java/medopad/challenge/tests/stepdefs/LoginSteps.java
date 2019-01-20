package medopad.challenge.tests.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginSteps extends BaseSteps {

    private static final String USER_NAME_LABEL = "userName";

    @FindBy(how = How.NAME, using = "userName")
    private WebElement userName;

    @FindBy(how = How.NAME, using = "password")
    private WebElement password;

    @FindBy(how = How.NAME, using = "login")
    private WebElement login;

    public LoginSteps() throws IOException {
        openBrowser();
    }

    @Given("^I am on medopad login page$")
    public void iAmOnMedopadLoginPage() {
        PageFactory.initElements(chromeDriver, this);
        chromeDriver.get(getPropertyValue("homepage"));
    }

    @When("^I enter valid username$")
    public void iEnterValidUsername() {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 20);
        wait.until(ExpectedConditions.visibilityOf(userName));
        userName.sendKeys(getPropertyValue("email"));
    }


    @And("^I enter valid password$")
    public void iEnterValidPassword() {
        password.sendKeys(getPropertyValue("password"));
    }

    @When("^I click on login$")
    public void iClickOnLogin() {
        login.click();
    }

    @Then("^I should see my userName (.*) on home page$")
    public void iShouldSeeMyUserNameOnHomePage(String userName) {
        WebElement userNameLabel = chromeDriver.findElement(By.xpath(USER_NAME_LABEL));
        assertEquals(userNameLabel.getText(), userName);
    }
}
