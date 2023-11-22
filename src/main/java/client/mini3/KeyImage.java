package client.mini3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MainFrame extends JFrame {
    JScrollPane scrollPane;

    //이미지를 받을 변수를 BufferedImage 타입으로 선언
    BufferedImage backgroundImage;
    BufferedImage imageIcon1;
    BufferedImage imageIcon2;
    BufferedImage imageIcon3;
    BufferedImage planetImages[] = new BufferedImage[16];
    int gridSize = 70; // 각 이미지의 크기
    int gridCount = 5;  // 격자의 행과 열 개수
    int gap = 70;       // 이미지 간격


    MyPanel myPanel; //내부 클래스 변수 선언

    //JPanel을 상속받은 내부 클래스 선언
    private class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //이미지 위치 및 크기 설정
            g.drawImage(backgroundImage, 0, 0, 800, 800, null);
            g.drawImage(imageIcon1, 700, 50, 35, 35, null);
            g.drawImage(imageIcon2, 650, 50, 35, 35, null);

            // 테두리 이미지 배치
            int totalSize = gridSize * gridCount + gap * (gridCount - 1);
            int startX = (800 - totalSize) / 2;
            int startY = (800 - totalSize) / 2;
            int distY = 0;

            for (int row = 0; row < gridCount; row++) {
                for (int col = 0; col < gridCount; col++) {
                    int index = row * gridCount + col;
                    if (index < 5) {
                        int x = startX + col * (gridSize + gap);
                        int y = startY + row * (gridSize + gap);
                        g.drawImage(planetImages[index], x, y, gridSize, gridSize, null);
                    }
                }
            }
            // index 5 ~ 10 56 78 910
            int index = 5;
            for (int row = 1; row < 4; row++) {
                for (int col = 0; col < 2; col++) {
                    if (index % 2 == 1) {
                        int x = startX;
                        int y = startY + row * (gridSize + gap);
                        g.drawImage(planetImages[index], x, y, gridSize, gridSize, null);
                    }
                    else {
                        int x = startX + 4 * (gridSize + gap);
                        int y = startY + row * (gridSize + gap);
                        g.drawImage(planetImages[index], x, y, gridSize, gridSize, null);
                    }
                    index++;
                }
            }

            int dist = 0;
            for (int col = 11; col < 16; col++) {
                int x = startX + dist++ * (gridSize + gap);
                int y = startY + 4 * (gridSize + gap);
                g.drawImage(planetImages[col], x, y, gridSize, gridSize, null);

            }

        }

    }

    public MainFrame() {
        initData();
        setInitLayout();
        //addEventListener();
    }

    private void initData() {
        setTitle("Image add"); //프레임 제목
        setSize(800, 800); //프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 창 닫기

        try { //해당 경로에서 이미지 읽어와 변수에 대입
            backgroundImage = ImageIO.read(new File("SOURCE/bg0.png"));
            imageIcon1 = ImageIO.read(new File("SOURCE/sun1.png"));
            for (int i = 0; i < 16; i++) {
                String path = "SOURCE/planet" + ((i % 9) + 1)+ ".png";
                planetImages[i] = ImageIO.read(new File(path));
            }
        } catch (IOException e) {
            System.out.println("해당 이미지가 존재하지 않습니다.");
            System.exit(0); //프로그램 종료
        }

        myPanel = new MyPanel(); //내부 클래스 객체 생성, 객체를 생성하지 않으면 이미지 표시X
    }

    private void setInitLayout() {
        setVisible(true); //프레임을 화면에 표시
        this.add(myPanel); //내부 클래스 객체를 프레임에 추가
    }
}

public class KeyImage {
    public static void main(String[] args) {
        new MainFrame();
    }
}
