package Engine;

import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {

    private Vector2f currentPos;
    private Vector2f displVec;
    private Vector2f scroll;
    private boolean inWindow;
    private boolean leftButtonPressed;
    private Vector2f previousPos;
    private boolean rightButtonPressed;

    private boolean leftButtonReleased;
    private boolean rightButtonReleased;

    public MouseInput(long windowHandle) {
        previousPos = new Vector2f(-1, -1);
        currentPos = new Vector2f();
        scroll = new Vector2f();
        displVec = new Vector2f();
        leftButtonPressed = false;
        rightButtonPressed = false;
        leftButtonReleased = false;
        rightButtonReleased = false;
        inWindow = false;

        // yang perlu diperhatiin yang tiga ini untuk mouse inputnya
        // set cursor ini tiap kali pencet di layar dia deteksi koordinatnya
        glfwSetCursorPosCallback(windowHandle, (handle, xpos, ypos) -> {
            currentPos.x = (float) xpos;
            currentPos.y = (float) ypos;
        });
        // setiap kali masuk ke window ini yg dipanggil
        glfwSetCursorEnterCallback(windowHandle, (handle, entered) -> inWindow = entered);
        glfwSetScrollCallback(windowHandle, (handle, xoffset, yoffset)->{
            scroll.x = (float)xoffset;
            scroll.y = (float)yoffset;
        });
        // ini untuk dideteksi yang diklik yg mana (right click, left click, release dll)
        // yang dibuatin yg press
        glfwSetMouseButtonCallback(windowHandle, (handle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
            leftButtonReleased = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_RELEASE;
            rightButtonReleased = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_RELEASE;
        });
    }

    public Vector2f getCurrentPos() {
        return currentPos;
    }

    public Vector2f getScroll() {
        return scroll;
    }

    public Vector2f getDisplVec() {
        return displVec;
    }

    public void input() {
        displVec.x = 0;
        displVec.y = 0;
        if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
            double deltax = currentPos.x - previousPos.x;
            double deltay = currentPos.y - previousPos.y;
            boolean rotateX = deltax != 0;
            boolean rotateY = deltay != 0;
            if (rotateX) {
                displVec.y = (float) deltax;
            }
            if (rotateY) {
                displVec.x = (float) deltay;
            }
        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public boolean isLeftButtonReleased(){return leftButtonReleased;}
    public boolean isRightButtonReleased(){return rightButtonReleased;}
}
