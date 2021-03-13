package com.augy.routecalc;

import java.util.ArrayList;
import java.util.List;

class Node {
    String name;
    List<Node> neighbors = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public String toString() {
        return this.name;
    }
}
