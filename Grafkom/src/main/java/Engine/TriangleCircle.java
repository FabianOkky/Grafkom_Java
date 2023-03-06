package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class TriangleCircle extends Object2d{
    float centerx;
    float centery;
    float radius;
    public TriangleCircle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerx, float centery, float radius) {
        super(shaderModuleDataList, vertices, color);
        this.centerx = centerx;
        this.centery = centery;
        this.radius = radius;
        createTriangle();
        setupVAOVBO();
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
        // kalo mau segitiga dari lingkaran tanpa fill ganti ke GL_LINE_LOOP
    }
}
