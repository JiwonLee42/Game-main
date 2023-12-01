package client.mini3;

import client.main.mainmap.Dice;
import client.main.object.PlanetNode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MainFrame extends JFrame {
    JScrollPane scrollPane;

    // 이미지를 받을 변수를 BufferedImage 타입으로 선언
    BufferedImage backgroundImage;
    BufferedImage imageIcon1;
    BufferedImage imageIcon2;
    BufferedImage imageIcon3;
    BufferedImage planetImages[] = new BufferedImage[16];
    int gridSize = 70; // 각 이미지의 크기
    int gridCount = 5;  // 격자의 행과 열 개수
    int gap = 70;       // 이미지 간격

    MyPanel myPanel; // 내부 클래스 변수 선언

    // JPanel을 상속받은 내부 클래스 선언
    private class MyPanel extends JPanel {
        Dice dice = new Dice();
        List<PlanetNode> planets = new ArrayList<>();

        String[] planetImagePaths = {
                "src/main/java/client/main/object/img/planet1.png",
                "src/main/java/client/main/object/img/planet2.png",
                "src/main/java/client/main/object/img/planet3.png",
                "src/main/java/client/main/object/img/planet4.png",
                "src/main/java/client/main/object/img/planet5.png",
                "src/main/java/client/main/object/img/planet6.png",
                "src/main/java/client/main/object/img/planet7.png",
                "src/main/java/client/main/object/img/planet8.png",
                "src/main/java/client/main/object/img/planet9.png",
                "src/main/java/client/main/object/img/planet4.png",
                "src/main/java/client/main/object/img/planet7.png",
                "src/main/java/client/main/object/img/planet6.png",
                "src/main/java/client/main/object/img/planet3.png",
                "src/main/java/client/main/object/img/planet1.png",
                "src/main/java/client/main/object/img/planet9.png",
                "src/main/java/client/main/object/img/planet4.png",
        };

        public MyPanel() {
            // 행성 노드 생성 및 추가
            // 각 노드별 코인 정보 저장 배열
            int[] coinInfo = {3, -3, 3, 3, -3, 3, 3, 3, -3, 3, 3, 3, -3, 3, -3, 3};

            for (int i = 1; i <= 16; i++) {
                PlanetNode node = new PlanetNode(i, coinInfo[i - 1], planetImagePaths[i - 1]); // 각 노드에 ID와 코인 정보 넣어서 생성
                planets.add(node);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 배경 이미지 그리기
            g.drawImage(backgroundImage, 0, 0, 800, 800, null);

            int gridSize = 70;
            int gap = 70;
            int totalSize = gridSize * gridCount + gap * (gridCount - 1);
            int startX = (800 - totalSize) / 2;
            int startY = (800 - totalSize) / 2;

            // 기존 코드에서 행성 이미지 추가
            for (int row = 0; row < gridCount; row++) {
                for (int col = 0; col < gridCount; col++) {
                    int index = row * gridCount + col;
                    if (index < 5) {
                        int x = startX + col * (gridSize + gap);
                        int y = startY + row * (gridSize + gap);
                        planets.get(index).draw(g, x, y, gridSize, gridSize);
                    }
                }
            }

            int index = 5;
            for (int row = 1; row < 4; row++) {
                for (int col = 0; col < 2; col++) {
                    if (index % 2 == 1) {
                        int x = startX;
                        int y = startY + row * (gridSize + gap);
                        planets.get(index).draw(g, x, y, gridSize, gridSize);
                    } else {
                        int x = startX + 4 * (gridSize + gap);
                        int y = startY + row * (gridSize + gap);
                        planets.get(index).draw(g, x, y, gridSize, gridSize);
                    }
                    index++;
                }
            }

            int dist = 0;
            for (int col = 11; col < 16; col++) {
                int x = startX + dist++ * (gridSize + gap);
                int y = startY + 4 * (gridSize + gap);
                planets.get(index).draw(g, x, y, gridSize, gridSize);
            }


            // 주사위 이미지 그리기
            g.drawImage(dice.getImage(), 400, 400, 64, 64, null);
            this.add(dice);
        }
    }

    public MainFrame() {
        initData();
        setInitLayout();
    }

    private void initData() {
        setTitle("Main"); // 프레임 제목
        setSize(800, 800); // 프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 창 닫기

        try { // 해당 경로에서 이미지 읽어와 변수에 대입
            backgroundImage = ImageIO.read(new File("src/main/java/client/main/object/img/bg0.png"));
        } catch (IOException e) {
            System.out.println("해당 이미지가 존재하지 않습니다.");
            System.exit(0); // 프로그램 종료
        }

        myPanel = new MyPanel(); // 내부 클래스 객체 생성, 객체를 생성하지 않으면 이미지 표시X
    }

    private void setInitLayout() {
        setVisible(true); // 프레임을 화면에 표시
        this.add(myPanel); // 내부 클래스 객체를 프레임에 추가

    }


    public static void main(String[] args) {
        new MainFrame();
    }
}
