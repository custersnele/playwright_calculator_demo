package be.pxl.tests;

import be.pxl.pages.CalculatorPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private static Playwright playwright;
    private static Browser browser;
    private Page page;
    private CalculatorPage calculatorPage;

    @BeforeAll
    static void beforeAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void afterAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        BrowserContext context = browser.newContext();
        page = context.newPage();
        calculatorPage = new CalculatorPage(page);
        calculatorPage.navigateTo();
    }

    @AfterEach
    void cleanup() {
        page.context().close();
    }

    @Test
    void testSumWithoutDoubling() {
        calculatorPage.setNumber1("4");
        calculatorPage.setNumber2("3");
        calculatorPage.selectSum();
        calculatorPage.enableDoubleFirstNumber(false);

        calculatorPage.clickCalculate();
        assertEquals("Result: 7", calculatorPage.getResultText());
    }
}