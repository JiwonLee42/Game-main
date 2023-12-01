package client.main.social;

import javax.swing.*;
import java.awt.*;

public class readyMain extends JFrame {

    // 전역변수 필드

    public readyMain() {

            L
            // 프레임 설정
            setTitle("로그인 화면");
            setSize(800, 800);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 배경 이미지 설정 (가정)
            JLabel backgroundLabel = new JLabel(new ImageIcon("assets/minigame2/bg0.png"));
            backgroundLabel.setLayout(null);
            setContentPane(backgroundLabel);
            JPanel panel = new JPanel();
            backgroundLabel.add(panel);
            Dimension size = panel.getPreferredSize();
            panel.setBounds((getWidth() - size.width) / 2, (getHeight() - size.height) / 2, size.width, size.height);
    }


    // 방 생성 및 참여 버튼, 프로필 등 표시
    public void displayMain() {

        JPanel main = new JPanel(new GridLayout(4,1));
        main.setPreferredSize(new Dimension(400, 400));
        main.setBackground(Color.BLACK);
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Solar System");
        titlePanel.add(title);

        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel imgLabel = new JLabel(new ImageIcon("assets/minigame2/player.png"));


    }


    // db로부터 프로필 받아오기

    // 방 생성 1명당 1개의 방을 생성할 수 있음
    /*
    1. 방장으로 된 방이 있는지 확인
     */
    public void createRoom() {

    }

    // 방 참여
    public void joinRoom() {

    }


    // playerRoom table
    // roomId, 방장 userId, 초대코드(랜덤생성), 방 이름, 상태(playing/readyfull/readyleft), 생성일자 -> 게임 끝나거나 펑 하면 사라짐
    // joinRoom table
    // roomID, userId

    public static void main(String[] args) {

    }


}
