package edu.fmi.ai.minimax;

//import com.sun.javaws.exceptions.InvalidArgumentException;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
    public static void main(String[] args) throws InvalidArgumentException, IOException {
        Field n = new Field();
        Node node = new Node(n, 0, -1);
        node = getBestChild(node);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(!node.isLeaf()) {

            node.print();
            System.out.println("Your move, smart ass!");
            int a = 0, b = 0;
            String input = br.readLine();
            if(input != null) {
                a = Integer.parseInt(input.split(" ")[0]);
                b = Integer.parseInt(input.split(" ")[1]);
            }
            char[][] field = node.getField().getField();
            if(field[a][b] != Utils.EMPTY_FIELD) {
                System.out.println("GTFO NOOB, BYE!!!");
                return;
            }
            field[a][b] = Utils.O_FIELD;
            Field newField = new Field(field);
            node = new Node(newField, newField.calculateTurns(), 1);
            if(!node.isLeaf()) {
                node = getBestChild(node);
            }
        }


        System.out.println("AAAAND THE WINNER IS: " + node.getBest() + " (1:X -1:O O:draw)");
        node.print();







//        while(node.getAnchestors().size() > 0) {
//            node.print();
//            for(int i = 0; i < node.getAnchestors().size(); i++) {
//                if(node.getAnchestors().get(i).getBest() == node.getBest()) {
//                    node = node.getAnchestors().get(i); break;
//                }
//            }
//
//
//        }
//        System.out.println(node.getBest());
    }

    static Node getBestChild(Node node) {
        Node bestNode = node.getAnchestors().get(0);
        for(Node child: node.getAnchestors()) {
            if(child.getBest() > bestNode.getBest()) {
                bestNode = child;
            }
        }
        return bestNode;
    }
}
