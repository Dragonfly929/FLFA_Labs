package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // create a graph
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        g.addVertex("q0");
        g.addVertex("q1");
        g.addVertex("q2");
        g.addEdge("q0", "q1");
        g.addEdge("q1", "q2");
        g.addEdge("q2", "q0");

        // create a JGraphX graph
        mxGraph mxGraph = new mxGraph();
        mxGraph.getModel().beginUpdate();
        try {
            // add vertices to the JGraphX graph
            Object[] vertexObjects = new Object[g.vertexSet().size()];
            int i = 0;
            for (String vertex : g.vertexSet()) {
                vertexObjects[i] = mxGraph.insertVertex(mxGraph.getDefaultParent(), null, vertex, 0, 0, 40, 40, "shape=ellipse");
                i++;
            }

            // add edges to the JGraphX graph
            for (DefaultEdge edge : g.edgeSet()) {
                String source = g.getEdgeSource(edge);
                String target = g.getEdgeTarget(edge);
                Object sourceVertex = null;
                Object targetVertex = null;
                for (Object vertex : vertexObjects) {
                    if (mxGraph.getLabel(vertex).equals(source)) {
                        sourceVertex = vertex;
                    }
                    if (mxGraph.getLabel(vertex).equals(target)) {
                        targetVertex = vertex;
                    }
                }
                mxGraph.insertEdge(mxGraph.getDefaultParent(), null, "a", sourceVertex, targetVertex);
            }

            // layout the graph using the mxCircleLayout
            mxCircleLayout layout = new mxCircleLayout(mxGraph);
            layout.execute(mxGraph.getDefaultParent());
        } finally {
            mxGraph.getModel().endUpdate();
        }

        // create a JGraphX component and display the graph
        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        graphComponent.setEnabled(false);
        JFrame frame = new JFrame();
        frame.getContentPane().add(graphComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }

}
