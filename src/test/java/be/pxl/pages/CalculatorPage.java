package be.pxl.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CalculatorPage {
    private static final String URL = System.getProperty("base.url");
    private static final String PAGE = "calculation.html";
    // Locators
    private final Locator inputNumber1;
    private final Locator inputNumber2;
    private final Locator radioSum;
    private final Locator radioMultiply;
    private final Locator checkboxDouble;
    private final Locator buttonCalculate;
    private final Locator resultParagraph;
    private final Page page;

    // Constructor
    public CalculatorPage(Page page) {
        this.page = page;
        this.inputNumber1 = page.locator("[data-testid=\"inputNumber1\"]");
        this.inputNumber2 = page.locator("[data-testid=\"inputNumber2\"]");
        this.radioSum = page.locator("[data-testid=\"radioSum\"]");
        this.radioMultiply = page.locator("[data-testid=\"radioMultiply\"]");
        this.checkboxDouble = page.locator("[data-testid=\"checkboxDouble\"]");
        this.buttonCalculate = page.locator("[data-testid=\"buttonCalculate\"]");
        this.resultParagraph = page.locator("[data-testid=\"result\"]");
    }

    public void navigateTo() {
        page.navigate(URL + PAGE);
    }

    // Page Actions
    public void setNumber1(String value) {
        inputNumber1.fill(value);
    }

    public void setNumber2(String value) {
        inputNumber2.fill(value);
    }

    public void selectSum() {
        radioSum.check();
    }

    public void selectMultiply() {
        radioMultiply.check();
    }

    public void enableDoubleFirstNumber(boolean enable) {
        // Check or uncheck the checkbox depending on 'enable'
        if (enable) {
            if (!checkboxDouble.isChecked()) {
                checkboxDouble.check();
            }
        } else {
            if (checkboxDouble.isChecked()) {
                checkboxDouble.uncheck();
            }
        }
    }

    public void clickCalculate() {
        buttonCalculate.click();
    }

    public String getResultText() {
        return resultParagraph.innerText().trim();
    }
}