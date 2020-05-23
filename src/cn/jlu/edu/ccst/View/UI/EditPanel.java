package cn.jlu.edu.ccst.View.UI;

import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class EditPanel extends JTextPane {
    /**
     * 是否实现行号，默认不显示
     */
    private boolean showLineNumber = false;
    private int fontSize = 14;//默认为14号字体
    private int fontLineHeight;
    private FontMetrics fontMetrics;

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        fontMetrics = getFontMetrics(getFont());
        fontLineHeight = fontMetrics.getHeight();
    }
    public int getLineHeight() {
       return fontLineHeight;
    }
    public EditPanel() {
        super();
        Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, fontSize);
        setFont(DEFAULT_FONT);
    }

    //设置不自动换行
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public void setShowLineNumber(boolean isShow) {
        this.showLineNumber = isShow;
    }
    public boolean getShowLineNumber() {
        return this.showLineNumber;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        StyleConstants.setFontSize(getInputAttributes(), getFontSize());
        if (getShowLineNumber()) {
            drawLineNumber(g);
        }
    }
    protected void drawLineNumber(Graphics g) {
        setMargin(new Insets(0, 35, 0, 0));
        // 绘制行号的背景色
        g.setColor(new Color(180, 180, 180));
        g.fillRect(0, 0, 30, getHeight());
        // 获得有多少行
        StyledDocument docu = getStyledDocument();
        Element element = docu.getDefaultRootElement();
        int rows = element.getElementCount();
        // 绘制行号的颜色
        g.setColor(new Color(90, 90, 90));
        g.setFont(new Font(getFont().getName(), getFont().getStyle(), getFontSize()));
        for (int row = 0; row < rows; row++) {
            g.drawString((row + 1)+"",2,  (getLineHeight()*(row+1)-4));
        }
    }

    public int getFontSize() {
        return fontSize;
    }

}