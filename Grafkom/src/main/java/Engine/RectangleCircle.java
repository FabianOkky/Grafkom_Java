package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class RectangleCircle extends Object2d{
    float centerx;
    float centery;
    float radius;
    public RectangleCircle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerx, float centery, float radius) {
        super(shaderModuleDataList, vertices, color);
        this.centerx = centerx;
        this.centery = centery;
        this.radius = radius;
        createRectangle();
        setupVAOVBO();
    }

    public void createRectangle(){
        vertices.clear();
        for (double i=45;i<360;i+=90f){
            float x = (float) (centerx + radius *  Math.cos(Math.toRadians(i)));
            float y = (float) (centery + radius * Math.sin(Math.toRadians(i)));
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

    public void move(float centerx, float centery){
//        vertices.clear();
        this.centerx = centerx;
        this.centery = centery;
        createRectangle();
        setupVAOVBO();
    }

    public float getCenterx(){
        return centerx;
    }

    public float getCentery(){
        return centery;
    }

    public boolean checkOverlap(float x1, float y1){
        float jarak = (float) Math.sqrt((centerx - x1) * (centerx - x1) + (centery - y1) * (centery - y1));
//        System.out.println("Jarak Dua Titik: " + jarak);
        if (jarak > 0.2f) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkKotak(float x1, float y1){
        float jarak = (float) Math.sqrt((centerx - x1) * (centerx - x1) + (centery - y1) * (centery - y1));
//        System.out.println("Jarak Dua Titik: " + jarak);
        if (jarak < 0.1f) {
            return true;
        } else {
            return false;
        }
    }
}
