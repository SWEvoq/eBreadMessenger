package swevoq.ebread.com.Chat.Model.Settings;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 21/04/17.
 */
public class TextSettingsTest {
    private TextSettings settings=new TextSettings();

    @Test
    public void getTextColor() throws Exception {
        String expected=" ";
        String actual=settings.getTextColor();
        assertEquals(expected,actual);
    }

    @Test
    public void setTextColor() throws Exception {
        String expected="blue";
        settings.setTextColor(expected);
        String actual=settings.getTextColor();
        assertEquals(expected,actual);
    }

    @Test
    public void getBubbleColor() throws Exception {
        String expected=" ";
        String actual=settings.getBubbleColor();
        assertEquals(expected,actual);
    }

    @Test
    public void setBubbleColor() throws Exception {
        String expected="blue";
        settings.setBubbleColor(expected);
        String actual=settings.getBubbleColor();
    }

    @Test
    public void getFontSize() throws Exception {
        String expected=" ";
        String actual=settings.getFontSize();
        assertEquals(expected,actual);
    }

    @Test
    public void setFontSize() throws Exception {
        String expected="10";
        settings.setFontSize(expected);
        String actual=settings.getFontSize();
        assertEquals(expected,actual);
    }

    @Test
    public void getFontSpacing() throws Exception {
        String expected=" ";
        String actual=settings.getFontSpacing();
        assertEquals(expected,actual);
    }

    @Test
    public void setFontSpacing() throws Exception {
        String expected="11";
        settings.setFontSpacing(expected);
        String actual=settings.getFontSpacing();
        assertEquals(expected,actual);
    }

    @Test
    public void getTextFont() throws Exception {
        String expected=" ";
        String actual= settings.getTextFont();
        assertEquals(expected,actual);
    }

    @Test
    public void setTextFont() throws Exception {
        String expected="12";
        settings.setTextFont(expected);
        String actual=settings.getTextFont();
        assertEquals(expected,actual);
    }

    @Test
    public void getHighlightColor() throws Exception {
        String expected=" ";
        String actual=settings.getHighlightColor();
        assertEquals(expected,actual);
    }

    @Test
    public void setHighlightColor() throws Exception {
        String expected="blue";
        settings.setHighlightColor(expected);
        String actual=settings.getHighlightColor();
        assertEquals(expected,actual);
    }

    @Test
    public void toStringTest() throws Exception {
        String expected="TextSettings{textColor=' ', bubbleColor=' ', fontSize=' ', fontSpacing=' ', textFont=' ', highlightColor=' '}";
        String actual=settings.toString();
        assertEquals(expected,actual);
    }

}