package client.main.mainmap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Dice extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Timer rollTimer;
    private JLabel[] diceLabels;
    private int currentDiceResult;

    public Dice() {
        setLayout(new BorderLayout());

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        String[] imagePaths = {
                "src/main/java/client/main/mainmap/img/dice1.png",
                "src/main/java/client/main/mainmap/img/dice2.png",
                "src/main/java/client/main/mainmap/img/dice3.png",
                "src/main/java/client/main/mainmap/img/dice4.png",
                "src/main/java/client/main/mainmap/img/dice5.png",
                "src/main/java/client/main/mainmap/img/dice6.png"
        };

        diceLabels = new JLabel[6];

        for (int i = 0; i < 6; i++) {
            ImageIcon imageIcon = new ImageIcon(imagePaths[i]);
            JLabel label = new JLabel(imageIcon);
            diceLabels[i] = label;
            cardPanel.add(label, String.valueOf(i + 1));
        }

        add(cardPanel, BorderLayout.CENTER);

        rollTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (rollTimer.isRunning()) {
                        rollTimer.stop();
                        currentDiceResult = getCurrentVisibleDiceResult();
                        System.out.println("주사위 멈춤. 결과: " + currentDiceResult);
                    } else {
                        rollTimer.start();
                        startDiceRollThread(); // 주사위 굴리는 스레드 시작
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

        setFocusable(true);

        rollTimer.setInitialDelay(0); // Timer 초기 지연 시간을 0으로 설정

        setSize(400, 400);
        setVisible(true);
    }

    private void rollDice() {
        int diceResult = (int) (Math.random() * 6) + 1;
        cardLayout.show(cardPanel, String.valueOf(diceResult));
    }

    private int getCurrentVisibleDiceResult() {
        for (int i = 0; i < diceLabels.length; i++) {
            if (diceLabels[i].isVisible()) {
                return i + 1;
            }
        }
        return -1;
    }

    // 주사위 굴리는 스레드
    private void startDiceRollThread() {
        Thread diceRollThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (rollTimer.isRunning()) {
                    rollDice(); // 주사위 이미지 변경
                    try {
                        Thread.sleep(100); // 이미지 변경 속도
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        diceRollThread.start();
    }

    public int getCurrentDiceResult() {
        return currentDiceResult;
    }
}
