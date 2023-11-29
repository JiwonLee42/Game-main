package client.main.mainmap;

import client.main.object.PlanetNode;

import java.util.ArrayList;

public class MainMap {
    //private int id;
    private ArrayList<PlanetNode> nodes;

    public MainMap() {
        for (int i = 0; i < 16; i++) {
            PlanetNode node = new PlanetNode(i);
            nodes.add(node);
        }
    }
}
