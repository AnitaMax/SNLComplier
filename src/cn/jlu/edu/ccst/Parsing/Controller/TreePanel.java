package cn.jlu.edu.ccst.Parsing.Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;


public class TreePanel extends JPanel {

    private Node tree;				//保存整棵树
    private int gridWidth = 120;		//每个结点的宽度
    private int gridHeight = 20;	//每个结点的高度
    private int vGap = 100;			//每2个结点的垂直距离
    private int hGap = 30;			//每2个结点的水平距离

    private int startY = 10;		//根结点的Y，默认距离顶部10像素
    private int startX = 0;			//根结点的X，默认水平居中对齐

    private int childAlign;						//孩子对齐方式
    public static int CHILD_ALIGN_ABSOLUTE = 0;	//相对Panel居中
    public static int CHILD_ALIGN_RELATIVE = 1;	//相对父结点居中

    private Font font = new Font("微软雅黑",Font.BOLD,14);	//描述结点的字体

    private Color gridColor = Color.BLACK;		//结点背景颜色
    private Color linkLineColor = Color.BLACK;	//结点连线颜色
    private Color stringColor = Color.WHITE;	//结点描述文字的颜色

    public void setLayerHashMap(HashMap<Integer, Integer> layerHashMap) {

        this.layerHashMap = layerHashMap;
    }

    private HashMap<Integer, Integer> layerHashMap;
    private HashMap<Integer, Integer> layerStartPosHashMap=new HashMap<>();
    public TreePanel(){
        this(null,CHILD_ALIGN_ABSOLUTE);
    }


    public TreePanel(Node n){
        this(n,CHILD_ALIGN_ABSOLUTE);
    }


    public TreePanel(int childAlign){
        this(null,childAlign);
    }


    public TreePanel(Node n, int childAlign){
        super();
        setTree(n);
        this.childAlign = childAlign;
    }


    public void setTree(Node n) {
        tree = n;
    }

    public void paintComponent(Graphics g){
        startX = (getWidth()-gridWidth)/2;
        super.paintComponent(g);
        g.setFont(font);
        drawAllNode(tree, startX, g);
    }


    public void drawAllNode(Node n, int x, Graphics g){
        if(n.getName()=="Program"){
            layerStartPosHashMap=new HashMap<>();
        }
        int y = n.getLayer()*(vGap+gridHeight)+startY;
        int fontY = y + gridHeight - 5;		//5为测试得出的值，你可以通过FM计算更精确的，但会影响速度

        g.setColor(gridColor);
        g.fillRect(x, y, gridWidth, gridHeight);	//画结点的格子

        g.setColor(stringColor);
        g.drawString(n.toString(), x, fontY);		//画结点的名字

        if(n.hasChild()){
            List<Node> c = n.getChilds();
            int layerNum=c.get(0).getLayer();
            int total_size=layerHashMap.get(layerNum);
            int tempStartPosx;
            if(layerStartPosHashMap.get(layerNum)==null){
                tempStartPosx = startX+gridWidth/2 - (total_size*(gridWidth+hGap)-hGap)/2;
                layerStartPosHashMap.put(layerNum,tempStartPosx);
            }else{
                tempStartPosx=layerStartPosHashMap.get(layerNum);

            }

            int tempPosx = tempStartPosx;


            int i = 0;
            for(Node node : c){
                int newX = tempPosx+(gridWidth+hGap)*i;	//孩子结点起始X
                g.setColor(linkLineColor);
                g.drawLine(x+gridWidth/2, y+gridHeight, newX+gridWidth/2, y+gridHeight+vGap);	//画连接结点的线
                drawAllNode(node, newX, g);
                i++;
            }

            Iterator<Map.Entry<Integer, Integer>> it = layerStartPosHashMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Integer, Integer> entry = it.next();
                Integer key = entry.getKey();
                if(key==layerNum){
                    it.remove();
                }

            }
            tempStartPosx =tempStartPosx+(gridWidth+hGap)*c.size();
            layerStartPosHashMap.put(layerNum,tempStartPosx);
        }
    }

    public Color getGridColor() {
        return gridColor;
    }


    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public Color getLinkLineColor() {
        return linkLineColor;
    }

    public void setLinkLineColor(Color gridLinkLine) {
        this.linkLineColor = gridLinkLine;
    }

    public Color getStringColor() {
        return stringColor;
    }


    public void setStringColor(Color stringColor) {
        this.stringColor = stringColor;
    }

    public int getStartY() {
        return startY;
    }


    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }


    public void setStartX(int startX) {
        this.startX = startX;
    }


}
