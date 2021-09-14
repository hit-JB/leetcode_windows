package com.company.bean;

public class RBTree {
    public static RBTree RILL =new RBTree();
    private RBTree p;
    private RBTree left;
    private RBTree right;
    private Integer key;
    public static void main(String[] args){

    }
    public void leftRotate(RBTree root,RBTree x){
        RBTree y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft()!=RILL){
           y.getLeft().setP(x);
        }
        y.setP(x.getP());
        if(x.getP()==RILL){
            root=y;
        }else if(x==x.getP().getLeft()){
            x.getP().setLeft(y);
        }else{
            x.getP().setRight(y);
        }
        y.setLeft(x);
        x.setP(y);
    }
    public RBTree(int key,RBTree p,RBTree left,RBTree right){
        this.key = key;
        this.p = p;
        this.left = left;
        this.right = right;
    }
    public RBTree(){

    };
    public RBTree getP() {
        return p;
    }

    public void setP(RBTree p) {
        this.p = p;
    }

    public RBTree getLeft() {
        return left;
    }

    public void setLeft(RBTree left) {
        this.left = left;
    }

    public RBTree getRight() {
        return right;
    }

    public void setRight(RBTree right) {
        this.right = right;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
