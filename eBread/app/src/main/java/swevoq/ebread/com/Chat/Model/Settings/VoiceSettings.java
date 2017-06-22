package swevoq.ebread.com.Chat.Model.Settings;

/**
 * Created by Teslaru Nicolae on 20/04/2017.
 */

public class VoiceSettings {
    private String voiceName;
    private String voiceLanguage;
    private double voiceRate;
    private int highlightDelay;
    private boolean wordHighlight;
    private boolean showHighlight;
    private boolean playVoice;
    private boolean persistentHighlight;

    public VoiceSettings() {
        voiceName = "istc-speaker_internazionale-hsmm";
        voiceLanguage = "it";
        voiceRate = 1.0;
        highlightDelay = 0;
        wordHighlight = true;
        showHighlight = true;
        playVoice = true;
        persistentHighlight = false;
    }

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public String getVoiceLanguage() {
        return voiceLanguage;
    }

    public void setVoiceLanguage(String voiceLanguage) {
        this.voiceLanguage = voiceLanguage;
    }

    public double getVoiceRate() {
        return voiceRate;
    }

    public void setVoiceRate(double voiceRate) {
        this.voiceRate = voiceRate;
    }

    public int getHighlightDelay() {
        return highlightDelay;
    }

    public void setHighlightDelay(int highlightDelay) {
        this.highlightDelay = highlightDelay;
    }

    public boolean isWordHighlight() {
        return wordHighlight;
    }

    public void setWordHighlight(boolean wordHighlight) {
        this.wordHighlight = wordHighlight;
    }

    public boolean isShowHighlight() {
        return showHighlight;
    }

    public void setShowHighlight(boolean showHighlight) {
        this.showHighlight = showHighlight;
    }

    public boolean isPlayVoice() {
        return playVoice;
    }

    public void setPlayVoice(boolean playVoice) {
        this.playVoice = playVoice;
    }

    public boolean isPersistentHighlight(){
        return persistentHighlight;
    }

    public void setPersistentHighlight(boolean persistentHighlight){
        this.persistentHighlight = persistentHighlight;
    }

    @Override
    public String toString() {
        return "VoiceSettings{" +
                "voiceName='" + voiceName + '\'' +
                ", voiceLanguage='" + voiceLanguage + '\'' +
                ", voiceRate=" + voiceRate +
                ", highlightDelay=" + highlightDelay +
                ", wordHighlight=" + wordHighlight +
                ", showHighlight=" + showHighlight +
                ", playVoice=" + playVoice +
                ", persistentHighlight=" + persistentHighlight +
                '}';
    }
}
