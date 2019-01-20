package medopad.challenge.tests.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WebPageSteps extends BaseSteps {

    private static final String SEARCH_BOX_LOCATOR = "searchBox";
    private static final String SEARCH_BUTTON_LOCATOR = "searchButton";
    private static final String PATIENT_NAME_LABEL_LOCATOR = "//*[contains(@class,'patientNameLabel')]";
    private static final String PATIENTS_RECORDS = "li/label/span[contains(@class,'patientsList')]";
    private static final String ADD_NOTES_BUTTON = "addbutton";
    private static final String NOTES_AREA = "notesTextArea";
    private static final String SAMPLE_NOTES = "prescription for diabetes provided. All other tests were normal.";
    private static final String SAVE_BUTTON = "saveButton";
    private static final String NOTES = "notes";

    public WebPageSteps() throws IOException {
    }

    @When("^I enter a (.*) in the search box$")
    public void iEnterAPatientIdInTheSearchBox(String patientId) {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 20);
        WebElement searchElement = chromeDriver.findElement(By.id(SEARCH_BOX_LOCATOR));
        wait.until(ExpectedConditions.visibilityOf(searchElement));
        searchElement.sendKeys(patientId);
    }

    @And("^I click on search button$")
    public void iClickOnSearchButton() {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 20);
        WebElement searchButtonElement = chromeDriver.findElement(By.id(SEARCH_BUTTON_LOCATOR));
        wait.until(ExpectedConditions.visibilityOf(searchButtonElement));
        searchButtonElement.click();
    }

    @Then("^I should see the (.*) in results page$")
    public void iShouldSeeThePatientInResultsPage(String patientid) {
        explicitWait(2000);
        List<WebElement> patientRecords = chromeDriver.findElements(By.xpath(PATIENTS_RECORDS));
        List<String> patientIds = new ArrayList<>();
        for (WebElement patientRecord : patientRecords) {
            patientIds.add(patientRecord.getText());
        }
        assertTrue(patientIds.contains(patientid));
    }

    @When("^I click on (.*) in results$")
    public void iClickOnAPatientIdInResults(String patientId) {
        List<WebElement> patientRecords = chromeDriver.findElements(By.xpath(PATIENTS_RECORDS));
        for (WebElement patientRecord : patientRecords) {
            if (patientRecord.getText().equals(patientId)) {
                patientRecord.click();
            }
        }
    }

    @Then("^I should see the profile page of the patient with (.*)")
    public void iShouldSeeTheProfilePageOfThePatientWithRecords(String patientName) {
        explicitWait(2000);
        WebElement patientNameElement = chromeDriver.findElement(By.xpath(PATIENT_NAME_LABEL_LOCATOR));
        assertEquals(patientName, patientNameElement.getText());
    }

    @When("^I click on add notes$")
    public void iClickOnAddNotes() {
        WebDriverWait wait = new WebDriverWait(chromeDriver, 20);
        WebElement addNotes = chromeDriver.findElement(By.name(ADD_NOTES_BUTTON));
        wait.until(ExpectedConditions.visibilityOf(addNotes));
        addNotes.click();
    }

    @And("^I type the notes in the text area$")
    public void iTypeTheNotesInTheTextArea() {
        chromeDriver.findElement(By.id(NOTES_AREA)).sendKeys(SAMPLE_NOTES);
    }

    @And("^I click on save$")
    public void iClickOnSave() {
        chromeDriver.findElement(By.name(SAVE_BUTTON)).click();
    }

    @Then("^I should see the notes added to the patients page$")
    public void iShouldSeeTheNotesAddedToThePatientsPage() {
        explicitWait(2000); //wait for the added notes to appear in the page
        WebElement notesElement = chromeDriver.findElement(By.id(NOTES));
        assertNotNull(notesElement.getText());
    }
}
