package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Star extends Object2d{
    double cx,cy,size;
    double x,y;
    List<Integer> index;
    int ibo;

    public Star(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color,
                List<Integer>index,double cx,double cy,double size) {
        super(shaderModuleDataList, vertices, color);
        this.cx = cx;
        this.cy = cy;
        this.size = size;
        this.index =index;
        CreateStar();
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index),GL_STATIC_DRAW);
        setupVAOVBO();
    }

    public void CreateStar(){
        vertices.clear();

        for (float i = 36; i < 396; i+=72)
        {
            x = cx + ((size) * Math.cos(Math.toRadians(i)));
            y = cy + ((size) * Math.sin(Math.toRadians(i)));
            vertices.add(new Vector3f((float) x, (float) y, 0.0f));

        }
    }

    public void draw(){
        drawSetup();

        glLineWidth(1);
        glPointSize(1);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_LINES,index.size(),GL_UNSIGNED_INT,0);
//        glDrawArrays(GL_LINE_LOOP,0,vertices.size());

        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT

    }
}
