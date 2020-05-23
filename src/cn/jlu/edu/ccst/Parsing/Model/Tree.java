package cn.jlu.edu.ccst.Parsing.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree {
    Node root;
    ArrayList<Node> LeafNode;
    HashMap<Integer, Integer> layerHashMap=new HashMap<>();

    public Node getRoot() {
        return root;
    }

    public Tree(Node root) {
        this.root = root;

    }
    public HashMap<Integer, Integer> getChildNumOfLayer(){
        depthSearch(root);
        return layerHashMap;


    }
    public void depthSearch(Node p){
        if(p.hasChild()){
            for(int i=0;i<p.getChilds().size();i++){
                Node curNode=p.getChilds().get(i);
                if(layerHashMap.get(curNode.getLayer())==null){
                    layerHashMap.put(curNode.getLayer(),1);
                }else{
                    int tempLayNum=layerHashMap.get(curNode.getLayer());
                    tempLayNum=tempLayNum+1;
                    layerHashMap.put(curNode.getLayer(),tempLayNum);
                }
                depthSearch(curNode);
            }
        }

    }

    public ArrayList<Node> getLeafNode(){
        LeafNode = new ArrayList<>();
        LeafNode.add(root);
        collectLeafNode(root);
        return LeafNode;
    }
    void collectLeafNode(Node n){
        if(n.hasChild()){
            List<Node> c=n.getChilds();
            for(Node node : c){
                if(node.getChilds()==null){
                    LeafNode.add(node);
                }else{
                    collectLeafNode(node);
                }
            }

        }


    }
}
