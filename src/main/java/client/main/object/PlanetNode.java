package client.main.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlanetNode extends Unit {
    private int id;
    private int coin; // 해당 행성에 할당되는 코인 수
    private boolean sun = false; // 해당 노드 태양 생성 여부
    private String name;
    private BufferedImage image; // 이미지 추가

    public PlanetNode(int id, int coin, String imagePath) {
        this.id = id;
        this.coin = coin;
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setSun(boolean change) {
        sun = true;
    }

    public boolean isSun() {
        return sun;
    }

    public void draw(Graphics g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }
}
