package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class Elips extends Object2d{
    float centerx;
    float centery;
    float radiusx;
    float radiusy;
    public Elips(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerx, float centery, float radiusx, float radiusy) {
        super(shaderModuleDataList, vertices, color);
        this.centerx = centerx;
        this.centery = centery;
        this.radiusx = radiusx;
        this.radiusy = radiusy;
        createEclipse();
        setupVAOVBO();
    }

    public void createEclipse(){
        //vertices -> clear dlu
        vertices.clear();
        // batas kiri kanan -1 sm 1
        // int degree = 45;
        // i += biar loopnya 4 kali untuk persegi
        // kalo segitiga loopnya 3 kali
        // kalo elipse radius yang x sendiri terus radius y  juga sendiri beda2
        for (double i=0;i<360;i+=0.01f){
            float x = (float) (centerx + radiusx * Math.cos(i));
            float y = (float) (centery + radiusy * Math.sin(i));
            vertices.add(new Vector3f(x,y,0.0f));
            // setiap loop degree += 90 kalo rectangle
        }
    }
    @Override
    public void draw()
    {
        drawSetup();
        glLineWidth(10);
        glPointSize(10);
        glDrawArrays(GL_POLYGON, 0, vertices.size());
    }
}
