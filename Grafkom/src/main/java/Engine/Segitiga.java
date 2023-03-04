package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Segitiga extends Object2d{
    double alas,tinggi,cx,cy;
    double x,y;


    public Segitiga(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color,
                    double alas,double tinggi,double cx, double cy) {
        super(shaderModuleDataList, vertices, color);
        this.cx = cx;
        this.cy = cy;
        this.alas = alas;
        this.tinggi = tinggi;
        CreateSegitiga();
        setupVAOVBO();
    }

    public void CreateSegitiga(){
        vertices.clear();


        for (float i = 315; i <= 675; i+=135) {
            x = cx + ((alas) * Math.cos(Math.toRadians(i)));
            y = cy + ((tinggi) * Math.sin(Math.toRadians(i)));
            vertices.add(new Vector3f((float) x, (float) y, 0.0f));
        }

    }

    public void draw(){
        drawSetup();

        glDrawArrays(GL_TRIANGLE_FAN,0,vertices.size());
    }
}
