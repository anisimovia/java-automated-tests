package com.aqa.course.ui.pages.elementspages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPage {

    // --- Блок результата ---
    private final SelenideElement resultBlock = $("#result");

    // --- Проверка, что мы на нужной странице ---
    public CheckBoxPage checkOpened() {
        $("h1").shouldHave(text("Check Box"));
        return this;
    }

    // --- Раскрыть узел по имени ---
    public CheckBoxPage expandNode(String nodeName) {
        SelenideElement node = getTreeNode(nodeName);
        if ("false".equals(node.getAttribute("aria-expanded"))) {
            node.$x(".//span[contains(@class,'rc-tree-switcher')]").click();
        }
        node.shouldHave(attribute("aria-expanded", "true"));
        return this;
    }

    // --- Свернуть узел по имени ---
    public CheckBoxPage collapseNode(String nodeName) {
        SelenideElement node = getTreeNode(nodeName);
        if ("true".equals(node.getAttribute("aria-expanded"))) {
            node.$x(".//span[contains(@class,'rc-tree-switcher')]").click();
        }
        node.shouldHave(attribute("aria-expanded", "false"));
        return this;
    }

    // --- Поставить/снять галочку на узле ---
    public CheckBoxPage toggleNode(String nodeName) {
        getTreeNode(nodeName)
                .$x(".//span[contains(@class,'rc-tree-checkbox')]")
                .click();
        return this;
    }

    // --- Проверить, что узел отмечен ---
    public CheckBoxPage shouldBeChecked(String nodeName) {
        getTreeNode(nodeName)
                .$x(".//span[contains(@class,'rc-tree-checkbox')]")
                .shouldHave(attribute("aria-checked", "true"));
        return this;
    }

    // --- Проверить, что узел не отмечен ---
    public CheckBoxPage shouldNotBeChecked(String nodeName) {
        getTreeNode(nodeName)
                .$x(".//span[contains(@class,'rc-tree-checkbox')]")
                .shouldHave(attribute("aria-checked", "false"));
        return this;
    }

    // --- Проверить, что в блоке результатов есть нужный текст ---
    public CheckBoxPage shouldHaveResult(String expectedText) {
        resultBlock.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    // --- Вспомогательный метод: находит div[role='treeitem'] по имени узла ---
    private SelenideElement getTreeNode(String nodeName) {
        return $x(String.format(
                "//div[@role='treeitem'][.//span[@title='%s']]", nodeName
        ));
    }
}