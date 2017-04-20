package swevoq.ebread.com.Chat.Model.Settings;

/**
 * Created by Teslaru Nicolae on 20/04/2017.
 */

public class TextSettings {
    private String textColor;
    private String bubbleColor;
    private String fontSize;
    private String fontSpacing;
    private String textFont;
    private String highlightColor;

    public TextSettings() {
        textColor = " ";
        bubbleColor = " ";
        fontSize = " ";
        fontSpacing = " ";
        textFont = " ";
        highlightColor = " ";
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBubbleColor() {
        return bubbleColor;
    }

    public void setBubbleColor(String bubbleColor) {
        this.bubbleColor = bubbleColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontSpacing() {
        return fontSpacing;
    }

    public void setFontSpacing(String fontSpacing) {
        this.fontSpacing = fontSpacing;
    }

    public String getTextFont() {
        return textFont;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public String getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    @Override
    public String toString() {
        return "TextSettings{" +
                "textColor='" + textColor + '\'' +
                ", bubbleColor='" + bubbleColor + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", fontSpacing='" + fontSpacing + '\'' +
                ", textFont='" + textFont + '\'' +
                ", highlightColor='" + highlightColor + '\'' +
                '}';
    }
}