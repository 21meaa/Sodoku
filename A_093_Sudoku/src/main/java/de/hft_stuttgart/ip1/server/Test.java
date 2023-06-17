package de.hft_stuttgart.ip1.server;

public class Test {

    public static void main(String[] args) {
        int size = 9;
        int[] grid  = new int[size * size];


        for(int  i = 1; i <= 9; i++){
            Node node = new Node(i);
        }

        for(int i = 0; i < 39062500; i++){
            Node node = new Node(1);
        }
    }


    public static class row{
        Node start;
        Node end;
    }
    public static class collum{
        Node start;
        Node end;
    }
    public static class Node {
        int value;
        Node left;
        Node right;
        Node top;
        Node bottom;

        public Node(int value){
            this.value = value;
            left = null;
            right = null;
            top = null;
            bottom = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getTop() {
            return top;
        }

        public void setTop(Node top) {
            this.top = top;
        }

        public Node getBottom() {
            return bottom;
        }

        public void setBottom(Node bottom) {
            this.bottom = bottom;
        }
    }
}
