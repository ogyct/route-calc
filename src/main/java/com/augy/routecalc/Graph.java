package com.augy.routecalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, Node> nodes = new HashMap<>();

    public Graph() {
    }

    public void addEdge(String nodeName1, String nodeName2) {
        Node node1 = nodes.get(nodeName1);
        if (node1 == null) {
            node1 = new Node(nodeName1);
        }

        Node node2 = nodes.get(nodeName2);
        if (node2 == null) {
            node2 = new Node(nodeName2);
        }

        node1.addNeighbor(node2);
        node2.addNeighbor(node1);

        nodes.put(nodeName1, node1);
        nodes.put(nodeName2, node2);
    }

    public List<String> shortestPath(String startNodeName, String endNodeName) {
        // key node, value parent
        Map<String, String> parents = new HashMap<>();
        List<Node> temp = new ArrayList<>();

        Node start = nodes.get(startNodeName);
        temp.add(start);
        parents.put(startNodeName, null);

        while (temp.size() > 0) {
            Node currentNode = temp.get(0);
            if (currentNode == null) {
                throw new BusinessException();
            }
            List<Node> neighbors = currentNode.getNeighbors();

            for (Node neighbor : neighbors) {
                String nodeName = neighbor.getName();

                // a node can only be visited once if it has more than one parents
                boolean visited = parents.containsKey(nodeName);
                if (visited) {
                    continue;
                } else {
                    temp.add(neighbor);

                    // parents map can be used to get the path
                    parents.put(nodeName, currentNode.getName());

                    // return the shortest path if end node is reached
                    if (nodeName.equals(endNodeName)) {
                        return getPath(parents, endNodeName);
                    }
                }
            }

            temp.remove(0);
        }

        return null;
    }

    private List<String> getPath(Map<String, String> parents, String endNodeName) {
        List<String> path = new ArrayList<>();
        String node = endNodeName;
        while (node != null) {
            path.add(0, node);
            node = parents.get(node);
        }
        return path;
    }
}
