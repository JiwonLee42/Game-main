package client.main.object;

import java.awt.*;

public class Unit {
    int posX;
    int posY;
    int width;
    int height;
    Image img;
    // 이미지를 불러오는 역할.
    Toolkit tk = Toolkit.getDefaultToolkit();

    // ==== getter, setter ====


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Toolkit getTk() {
        return tk;
    }

    public void setTk(Toolkit tk) {
        this.tk = tk;
    }
}
