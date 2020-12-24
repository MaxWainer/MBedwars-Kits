package de.marcely.bedwarsaddon.kits.gui.buttons;

public class ConditionButton extends Button {

    private Button falseButton;
    private Button trueButton;
    private boolean condition;

    @Override
    public ConditionButton symbol(char icon) {
        super.symbol(icon);
        return this;
    }

    public ConditionButton falseButton(Button button) {
        this.falseButton = button;
        return this;
    }

    public ConditionButton trueButton(Button button) {
        this.trueButton = button;
        return this;
    }

    public ConditionButton setCondition(boolean condition) {
        this.condition = condition;
        return this;
    }

    public Button get() {
        if(condition)
            return trueButton;
        else
            return falseButton;
    }

}
