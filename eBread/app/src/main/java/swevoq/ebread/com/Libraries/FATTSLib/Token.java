package swevoq.ebread.com.Libraries.FATTSLib;

/**
 * Created by Teslaru Nicolae on 27/04/2017.
 */

public class Token {
    private double start,end;
    private int char_start,char_end;

    public Token(double start, double end, int char_start, int char_end) {
        this.start = start;
        this.end = end;
        this.char_start = char_start;
        this.char_end = char_end;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public int getChar_start() {
        return char_start;
    }

    public void setChar_start(int char_start) {
        this.char_start = char_start;
    }

    public int getChar_end() {
        return char_end;
    }

    public void setChar_end(int char_end) {
        this.char_end = char_end;
    }

    @Override
    public String toString() {
        return "Token{" +
                "start=" + start +
                ", end=" + end +
                ", char_start=" + char_start +
                ", char_end=" + char_end +
                '}';
    }
}
