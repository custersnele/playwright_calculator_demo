package be.pxl.tests;

import be.pxl.pages.CalculatorPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationIT {
    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeAll
    public static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();

    }

    @Test
    public void testMultiplyWithoutDoubling() {
        CalculatorPage calcPage = new CalculatorPage(page);
        calcPage.navigateTo();
        calcPage.setNumber1("5");
        calcPage.setNumber2("7");
        calcPage.enableDoubleFirstNumber(false);
        calcPage.selectMultiply();
        calcPage.clickCalculate();
        assertEquals("Result: 35", calcPage.getResultText());
    }

    @Test
    public void testMultiplyWithDoubling() {
        CalculatorPage calcPage = new CalculatorPage(page);
        calcPage.navigateTo();
        calcPage.setNumber1("5");
        calcPage.setNumber2("7");
        calcPage.enableDoubleFirstNumber(true);
        calcPage.selectMultiply();
        calcPage.clickCalculate();
        assertEquals("Result: 70", calcPage.getResultText());
    }

    @AfterEach
    public void closeContext() {
        context.close();
    }

    @AfterAll
    public static void tearDown() {
        browser.close();
        playwright.close();
    }
}