package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Star extends Object2d{
    float centerx;
    float centery;
    float radius;
    List<Integer> index;
    int ibo;
    public Star(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Integer> index, float centerx, float centery, float radius) {
        super(shaderModuleDataList, vertices, color);
        this.centerx = centerx;
        this.centery = centery;
        this.radius = radius;
        this.index = index;
        createStar();
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index), GL_STATIC_DRAW);
        setupVAOVBO();
    }

    public void createStar(){
        //vertices -> clear dlu
        vertices.clear();
        // batas kiri kanan -1 sm 1
        // int degree = 45;
        // i += biar loopnya 4 kali untuk persegi
        // kalo segitiga loopnya 3 kali
        // kalo elipse radius yang x sendiri terus radius y  juga sendiri beda2
        for (float i=36;i<=360;i+=72.0f){
            float x = (float) (centerx + radius * Math.cos(Math.toRadians(i)));
            float y = (float) (centery + radius * Math.sin(Math.toRadians(i)));
            vertices.add(new Vector3f(x,y,0.0f));
            // setiap loop degree += 90 kalo rectangle
        }
    }
    @Override
    public void draw()
    {
        drawSetup();
        glLineWidth(1); // ketebalan garis
        glPointSize(0);
        // Bind IBO dan draw
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glDrawElements(GL_LINES, index.size(), GL_UNSIGNED_INT, 0);
    }
}
