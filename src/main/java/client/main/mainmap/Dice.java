package client.main.mainmap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Dice {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Timer rollTimer;
    private int currentDiceResult; // 현재 주사위 결과를 저장할 변수
    private JLabel[] diceLabels; // 주사위 이미지를 담을 배열

    public Dice() {
        frame = new JFrame("Dice Roller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // 주사위 이미지 경로 배열 (1부터 6까지)
        String[] imagePaths = {
                "src/main/java/client/main/mainmap/img/dice1.png",
                "src/main/java/client/main/mainmap/img/dice2.png",
                "src/main/java/client/main/mainmap/img/dice3.png",
                "src/main/java/client/main/mainmap/img/dice4.png",
                "src/main/java/client/main/mainmap/img/dice5.png",
                "src/main/java/client/main/mainmap/img/dice6.png"
        };

        diceLabels = new JLabel[6]; // 주사위 이미지를 담을 배열 초기화

        for (int i = 0; i < 6; i++) {
            ImageIcon imageIcon = new ImageIcon(imagePaths[i]);
            JLabel label = new JLabel(imageIcon);
            diceLabels[i] = label; // 배열에 주사위 이미지 저장
            cardPanel.add(label, String.valueOf(i + 1));
        }

        frame.add(cardPanel, BorderLayout.CENTER);

        JButton rollButton = new JButton("Roll Dice");
        frame.add(rollButton, BorderLayout.SOUTH);

        // 타이머 초기화
        rollTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        // 스페이스바를 눌렀을 때 타이머 시작 또는 멈춤
        frame.addKeyListener(new KeyListener() {
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
        frame.setFocusable(true);

        // "Roll Dice" 버튼 클릭 시 주사위 굴리기 시작
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollTimer.start();
            }
        });

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    private void rollDice() {
        // 주사위 결과에 따라 해당 카드를 보여줌
        int diceResult = (int) (Math.random() * 6) + 1;
        cardLayout.show(cardPanel, String.valueOf(diceResult));
    }

    // 현재 보여지고 있는 카드를 기반으로 현재 주사위 결과를 가져오는 메서드
    private int getCurrentDiceResult() {
        for (int i = 0; i < diceLabels.length; i++) {
            // 배열에서 현재 보여지고 있는 JLabel을 찾아 인덱스 반환
            if (diceLabels[i].isVisible()) {
                return i + 1;
            }
        }
        return -1; // 에러 처리를 위해 -1 반환
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dice();
            }
        });
    }
}
