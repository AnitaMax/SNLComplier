package cn.jlu.edu.ccst.WordsAnalyse.Model;

public class Token{
    private int row;
    private int col;
    private String type;
    private String value;

    public Token(int row, int col, String type, String value) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "row=" + row +
                ", col=" + col +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}