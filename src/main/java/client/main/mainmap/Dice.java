package client.main.mainmap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Dice extends JLabel {
    private Timer rollTimer;
    private ImageIcon[] diceIcons;
    private int currentDiceResult;

    public Dice() {
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        // 주사위 이미지 경로 배열 (1부터 6까지)
        String[] imagePaths = {
                "src/main/java/client/main/mainmap/img/dice1.png",
                "src/main/java/client/main/mainmap/img/dice2.png",
                "src/main/java/client/main/mainmap/img/dice3.png",
                "src/main/java/client/main/mainmap/img/dice4.png",
                "src/main/java/client/main/mainmap/img/dice5.png",
                "src/main/java/client/main/mainmap/img/dice6.png"
        };

        diceIcons = new ImageIcon[6];

        for (int i = 0; i < 6; i++) {
            ImageIcon imageIcon = new ImageIcon(imagePaths[i]);
            diceIcons[i] = imageIcon;
        }

        // 타이머 초기화
        rollTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        // 스페이스바를 눌렀을 때 타이머 시작 또는 멈춤
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (rollTimer.isRunning()) {
                        rollTimer.stop();
                        // 주사위 굴리기가 멈출 때 현재 주사위 결과를 가져옴
                        currentDiceResult = getCurrentDiceResult();
                        System.out.println("주사위 멈춤. 결과: " + currentDiceResult);
                    } else {
                        rollTimer.start();
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // 프레임이 키 이벤트를 받을 수 있도록 설정
        setFocusable(true);

        // "Roll Dice" 버튼 클릭 시 주사위 굴리기 시작
        rollTimer.setInitialDelay(0); // Timer 초기 지연 시간을 0으로 설정
    }

    public Image getImage() {
        // 현재 보여지고 있는 아이콘의 이미지를 가져와서 반환
        return diceIcons[0].getImage();
    }

    private void rollDice() {
        // 주사위 결과에 따라 해당 이미지를 보여줌
        int diceResult = (int) (Math.random() * 6) + 1;
        setIcon(diceIcons[diceResult - 1]);
    }

    // 현재 보여지고 있는 주사위 결과를 가져오는 메서드
    private int getCurrentVisibleDiceResult() {
        for (int i = 0; i < diceIcons.length; i++) {
            // 배열에서 현재 보여지고 있는 이미지를 찾아 인덱스 반환
            if (((ImageIcon)diceIcons[i]).getImage() == ((ImageIcon)getIcon()).getImage()) {
                return i + 1;
            }
        }
        return -1; // 에러 처리를 위해 -1 반환
    }


    public int getCurrentDiceResult() {
        return currentDiceResult;
    }
}
