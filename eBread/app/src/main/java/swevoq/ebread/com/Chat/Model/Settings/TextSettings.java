package swevoq.ebread.com.Chat.Model.Settings;

import android.graphics.Color;

import java.util.HashMap;

/**
 * Created by Teslaru Nicolae on 20/04/2017.
 */

public class TextSettings {
    private String textColor;
    private String bubbleColor;
    private int fontSize;
    private int fontSpacing;
    private String textFont;
    private String highlightColor;

    public TextSettings() {
        textColor = "Black";
        bubbleColor = "Yellow";
        fontSize = 18;
        fontSpacing = 0;
        textFont = "Roboto";
        highlightColor = "Light Blue";
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

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSpacing() {
        return fontSpacing;
    }

    public void setFontSpacing(int fontSpacing) {
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

    public int getColorByName(String colorName){
        HashMap<String,Integer> result = new HashMap<>();
        result.put("Blue Grey", Color.parseColor("#78909c"));
        result.put("Grey", Color.parseColor("#bdbdbd"));
        result.put("Deep Orange", Color.parseColor("#ff7043"));
        result.put("Yellow", Color.parseColor("#ffee58"));
        result.put("Light Green", Color.parseColor("#9ccc65"));
        result.put("Light Blue", Color.parseColor("#81d4fa"));
        result.put("Red", Color.parseColor("#ef9a9a"));
        result.put("Black", Color.BLACK);
        result.put("White", Color.WHITE);
        return result.get(colorName);
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
