//package client.main.mainmap;
//
//import client.main.object.PlanetNode;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class MainMap {
//    private ArrayList<PlanetNode> nodes;
//
//    public MainMap() {
//        // 각 노드별 코인 정보 저장 배열
//        int[] coinInfo = {3, -3, 3, 3, -3, 3, 3, 3, -3, 3, 3, 3, -3, 3, -3, 3};
//
//        nodes = new ArrayList<>();
//
//        for (int i = 0; i < 16; i++) {
//            PlanetNode node = new PlanetNode(i, coinInfo[i]); // 각 노드에 ID와 코인 정보 넣어서 생성
//            nodes.add(node);
//        }
//
//        // 랜덤하게 하나의 노드의 sun을 true로 설정
//        setRandomSunNode();
//    }
//
//    private void setRandomSunNode() {
//        Random random = new Random();
//        int randomIndex = random.nextInt(nodes.size());
//
//        // 기존에 태양이 할당되어 있던 노드의 sun을 false로 설정
//        for (PlanetNode node : nodes) {
//            if (node.isSun()) {
//                node.setSun(false);
//                break;
//            }
//        }
//
//        // 랜덤한 인덱스의 노드의 sun을 true로 설정
//        nodes.get(randomIndex).setSun(true);
//
//        System.out.println("Randomly selected node with sun: " + nodes.get(randomIndex).getId());
//    }
//}
//
