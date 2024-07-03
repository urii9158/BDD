package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement sumField = $("[data-test-id=amount] .input__control");
    private final SelenideElement fromField = $("[data-test-id=from] .input__control");
    private final SelenideElement button = $("[data-test-id=action-transfer]");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMassage = $("[data-test-id = error-notification] .notification__content");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo, DataHelper.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMassage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
