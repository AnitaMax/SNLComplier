package cn.jlu.edu.ccst.Parsing.Model;



import java.util.ArrayList;
import java.util.List;


public class Node {
    private String name;	//该结点名字
    private int layer = 0;	//该结点层级

    private List<Node> childs = null;	//保存该结点的孩子

    public Node(String name){
        this.name = name;
    }


    public void add(Node n){
        if(childs == null)
            childs = new ArrayList<Node>();
        n.setLayer(layer+1);
        setChildLayout(n);
        childs.add(n);
    }


    private void setChildLayout(Node n){
        if(n.hasChild()){
            List<Node> c = n.getChilds();
            for(Node node : c){
                node.setLayer(node.getLayer()+1);
                setChildLayout(node);
            }
        }
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getLayer() {
        return layer;
    }


    public void setLayer(int layer) {
        this.layer = layer;
    }


    public List<Node> getChilds() {
        return childs;
    }


    public boolean hasChild(){
        return childs == null ? false : true;
    }


    public void printAllNode(Node n){
        System.out.println(n);
        if(n.hasChild()){
            List<Node> c = n.getChilds();
            for(Node node : c){
                printAllNode(node);
            }
        }
    }

    public String getAllNodeName(Node n){
        String s = n.toString()+"/n";
        if(n.hasChild()){
            List<Node> c = n.getChilds();
            for(Node node : c){
                s+=getAllNodeName(node)+"/n";
            }
        }
        return s;
    }

    public String toString(){
        return "  "+name;
    }
}
