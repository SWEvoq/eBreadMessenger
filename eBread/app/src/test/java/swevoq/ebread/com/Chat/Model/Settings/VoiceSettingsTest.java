package swevoq.ebread.com.Chat.Model.Settings;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marco on 21/04/17.
 */
public class VoiceSettingsTest {
    private VoiceSettings v=new VoiceSettings();

    @Test
    public void getVoiceName() throws Exception {
        String expected="istc-speaker_internazionale-hsmm";
        String actual=v.getVoiceName();
        assertEquals(expected,actual);
    }

    @Test
    public void setVoiceName() throws Exception {
        String expected="random";
        v.setVoiceName(expected);
        String actual=v.getVoiceName();
        assertEquals(expected,actual);
    }

    @Test
    public void getVoiceLanguage() throws Exception {
        String expected="it";
        String actual=v.getVoiceLanguage();
        assertEquals(expected,actual);
    }

    @Test
    public void setVoiceLanguage() throws Exception {
        String expected="en";
        v.setVoiceLanguage(expected);
        String actual=v.getVoiceLanguage();
        assertEquals(expected,actual);
    }

    @Test
    public void getVoiceRate() throws Exception {
        double expected=1.0;
        double actual=v.getVoiceRate();
        assertEquals(expected,actual,0.0);
    }

    @Test
    public void setVoiceRate() throws Exception {
        double expected=2.0;
        v.setVoiceRate(expected);
        double actual=v.getVoiceRate();
        assertEquals(expected,actual,0.0);
    }

    @Test
    public void isForwardHighlight() throws Exception {
        boolean expected=true;
        boolean actual=v.isForwardHighlight();
        assertEquals(expected,actual);
    }

    @Test
    public void setForwardHighlight() throws Exception {
        boolean expected=false;
        v.setForwardHighlight(expected);
        boolean actual=v.isForwardHighlight();
        assertEquals(expected,actual);
    }

    @Test
    public void isWordHighlight() throws Exception {
        boolean expected=true;
        boolean actual=v.isWordHighlight();
        assertEquals(expected,actual);
    }

    @Test
    public void setWordHighlight() throws Exception {
        boolean expected=false;
        v.setWordHighlight(expected);
        boolean actual=v.isWordHighlight();
        assertEquals(expected,actual);
    }

    @Test
    public void isShowHighlight() throws Exception {
        boolean expected=true;
        boolean actual=v.isShowHighlight();
        assertEquals(expected,actual);
    }

    @Test
    public void setShowHighlight() throws Exception {
        boolean expected=false;
        v.setShowHighlight(expected);
        boolean actual=v.isShowHighlight();
        assertEquals(expected,actual);
    }

    @Test
    public void isPlayVoice() throws Exception {
        boolean expected=true;
        boolean actual=v.isPlayVoice();
        assertEquals(expected,actual);
    }

    @Test
    public void setPlayVoice() throws Exception {
        boolean expected=false;
        v.setPlayVoice(expected);
        boolean actual=v.isPlayVoice();
        assertEquals(expected,actual);
    }

    @Test
    public void toStringTest() throws Exception {
        String expected="VoiceSettings{voiceName='istc-speaker_internazionale-hsmm', voiceLanguage='it', voiceRate=1.0, forwardHighlight=true, wordHighlight=true, showHighlight=true, playVoice=true}";
        String actual=v.toString();
        assertEquals(expected,actual);
    }

}