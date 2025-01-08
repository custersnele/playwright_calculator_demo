package be.pxl.steps;

import be.pxl.pages.CalculatorPage;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorSteps {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;
    private CalculatorPage calculatorPage;

    @Before
    public void beforeScenario() {
        if (playwright == null) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        }
        context = browser.newContext();
        page = context.newPage();
        calculatorPage = new CalculatorPage(page);
    }

    @After
    public void afterScenario() {
        context.close();
    }

    @Given("I open the calculator page")
    public void iOpenTheCalculatorPage() {
        calculatorPage.navigateTo();
    }

    @When("I set the first number to {string}")
    public void iSetTheFirstNumberTo(String number) {
        calculatorPage.setNumber1(number);
    }

    @When("I set the second number to {string}")
    public void iSetTheSecondNumberTo(String number) {
        calculatorPage.setNumber2(number);
    }

    @When("I check {string}")
    public void iCheck(String checkboxLabel) {
        // For simplicity, we assume it's the "Double the first number" checkbox
        calculatorPage.enableDoubleFirstNumber(true);
    }

    @When("I select {string}")
    public void iSelect(String operation) {
        if ("Multiply".equalsIgnoreCase(operation)) {
            calculatorPage.selectMultiply();
        } else {
            calculatorPage.selectSum();
        }
    }

    @When("I click on {string}")
    public void iClickOn(String buttonLabel) {
        // For simplicity, we assume there's only a "Calculate" button
        calculatorPage.clickCalculate();
    }

    @Then("the result should be {string}")
    public void theResultShouldBe(String expected) {
        assertEquals(expected, calculatorPage.getResultText());
    }
}