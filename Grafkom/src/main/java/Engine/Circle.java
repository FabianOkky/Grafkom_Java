package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Circle extends Object2d{
    float centerx;
    float centery;
    float radius;

    int pilihan;
    public Circle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerx, float centery, float radius, int pilihan) {
        super(shaderModuleDataList, vertices, color);
        this.centerx = centerx;
        this.centery = centery;
        this.radius = radius;
        this.pilihan = pilihan;
//        if (pilihan == 1){
//            createCircle();
//        } else if (pilihan == 2){
//            createTriangle();
//        } else if (pilihan == 3){
//            createRectangle();
//        }
//        createRectangle();
        createCircle();
//        createTriangle();
        setupVAOVBO();
    }

    public void createCircle(){
        //vertices -> clear dlu
        vertices.clear();
        // batas kiri kanan -1 sm 1
        // int degree = 45;
        // i += biar loopnya 4 kali untuk persegi
        // kalo segitiga loopnya 3 kali
        // kalo elipse radius yang x sendiri terus radius y  juga sendiri beda2
        for (double i=0;i<360;i+=0.01f){
             float x = (float) (centerx + radius *  Math.cos(Math.toRadians(i)));
             float y = (float) (centery + radius * Math.sin(Math.toRadians(i)));
             vertices.add(new Vector3f(x,y,0.0f));
            // setiap loop degree += 90 kalo rectangle
        }
    }

    public void createRectangle(){
        vertices.clear();
        for (double i=45;i<360;i+=90f){
            float x = (float) (centerx + radius *  Math.cos(Math.toRadians(i)));
            float y = (float) (centery + radius * Math.sin(Math.toRadians(i)));
            vertices.add(new Vector3f(x,y,0.0f));
        }
    }

    public void createTriangle(){
        vertices.clear();
        for (double i=-45;i<360;i+=135f){
            float x = (float) (centerx + radius *  Math.cos(Math.toRadians(i)));
            float y = (float) (centery + radius * Math.sin(Math.toRadians(i)));
//            System.out.println(x + " " + y);
            vertices.add(new Vector3f(x,y,0.0f));
        }
    }
    @Override
    public void draw()
    {
        drawSetup();
        glLineWidth(10);
        glPointSize(10);
        glDrawArrays(GL_POLYGON, 0, vertices.size());
        // kalo mau lingkaran tanpa fill ganti ke GL_LINES
    }
}
