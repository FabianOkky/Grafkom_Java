
import Engine.*;
import Engine.Rectangle;
import Engine.Window;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL20.*;


public class Main {


    private Window window = new Window(800,600,"Hello World");
    private Kotak DragKotak = null;
    private Object2d garis = null;
    private ArrayList<Object2d> objects = new ArrayList<>();
    private ArrayList<Object2d> objectsRectangle = new ArrayList<>();
    private ArrayList<Object2d> objectsCircle =  new ArrayList<>();
    private ArrayList<Object2d> Kotak = new ArrayList<>();
    private ArrayList<Object2d> Segitiga =  new ArrayList<>();
    private ArrayList<Object2d> Star =  new ArrayList<>();

    private ArrayList<Object2d> objectsPointsControl =  new ArrayList<>();
    private ArrayList<Kotak> KotakKhusus = new ArrayList<>();



    public void init(){
        window.init();
        GL.createCapabilities();

        List<ShaderProgram.ShaderModuleData> shader = Arrays.asList(
                //shaderFile lokasi menyesuaikan objectnya
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.vert"
                                , GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.frag"
                                , GL_FRAGMENT_SHADER)
        );
        List<Integer> starIndex = Arrays.asList(0,3,3,1,1,4,4,2,2,0);

        //code
        objectsPointsControl.add(new Object2d(
                shader,new ArrayList<>(),new Vector4f(0.0f,1.0f,1.0f,1.0f)
        ));

//        objects.add(new Object2d(
//                Arrays.asList(
//                        //shaderFile lokasi menyesuaikan objectnya
//                        new ShaderProgram.ShaderModuleData
//                                ("resources/shaders/sceneWithVerticesColor.vert"
//                                        , GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData
//                                ("resources/shaders/sceneWithVerticesColor.frag"
//                                        , GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(0.0f,0.5f,0.0f),
//                                new Vector3f(-0.5f,-0.5f,0.0f),
//                                new Vector3f(0.5f,-0.5f,0.0f)
//                        )
//                ), new ArrayList<>(
//                List.of(
//                        new Vector3f(1.0f,0.0f,0.0f),
//                        new Vector3f(0.0f,1.0f,0.0f),
//                        new Vector3f(0.0f,0.0f,1.0f)
//                )
//        )
//        ));

        //Background Biru
        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(1.0f,1.0f,0.0f),
                                new Vector3f(-1.0f,1.0f,0.0f),
                                new Vector3f(-1.0f,-1.0f,0.0f),
                                new Vector3f(1.0f,-1.0f,0.0f)

                        )
                ), new Vector4f(0.0f,0.0235f,0.7176f,1.0f),
                Arrays.asList(0,1,2,1,2,3)

        ));


        //Background Hijau
        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(1.0f,-0.5f,0.0f),
                                new Vector3f(-1.0f,-0.5f,0.0f),
                                new Vector3f(-1.0f,-1.0f,0.0f),
                                new Vector3f(1.0f,-1.0f,0.0f)

                        )
                ), new Vector4f(0.0f,0.5019f,0.0f,1.0f),
                Arrays.asList(0,1,2,1,2,3)

        ));

        //Body Rumah Kuning
        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.65f,0.0f,0.0f), // Top right
                                new Vector3f(-0.65f,0.0f,0.0f),// Top Left
                                new Vector3f(-0.65f,-0.6f,0.0f), // Bot left
                                new Vector3f(0.65f,-0.6f,0.0f) //Bot Right

                        )
                ), new Vector4f(0.5019f,0.5019f,0.0f,1.0f),
                Arrays.asList(0,1,2,1,2,3)

        ));

        //Trapesium Merah
        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.6f,0.2f,0.0f),//Top Right
                                new Vector3f(-0.6f,0.2f,0.0f),//Top Left
                                new Vector3f(-0.73f,-0.2f,0.0f),//Bot Left
                                new Vector3f(0.7f,-0.2f,0.0f)// Bot Right

                        )
                ), new Vector4f(1.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0,1,2,3)

        ));

        //Segitiga Atap Kuning
        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.565f,0.15f,0.0f), // Top
                                //new Vector3f(-0.65f,0.0f,0.0f),// Top Left
                                new Vector3f(-0.65f,-0.1f,0.0f), // Bot left
//                                new Vector3f(-0.4f,-0.1f,0.0f), //Bot Right
                                new Vector3f(-0.65f,-0.2f,0.0f), //Bot bot Left
                                new Vector3f(-0.45f,-0.2f,0.0f) //Bot bot Right




                        )
                ), new Vector4f(0.5019f,0.5019f,0.0f,1.0f),
                Arrays.asList(0,1,2,3)

        ));


        //Cerobong Asap
        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.5f,0.3f,0.0f),//Top Right
                                new Vector3f(0.4f,0.3f,0.0f),//Top Left
                                new Vector3f(0.4f,0.1f,0.0f),//Bot Left
                                new Vector3f(0.5f,0.1f,0.0f)// Bot Right

                        )
                ), new Vector4f(1.0f,1.0f,0.4f,1.0f),
                Arrays.asList(0,1,2,3)

        ));

        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.525f,0.4f,0.0f),//Top Right
                                new Vector3f(0.375f,0.4f,0.0f),//Top Left
                                new Vector3f(0.375f,0.3f,0.0f),//Bot Left
                                new Vector3f(0.525f,0.3f,0.0f)// Bot Right

                        )
                ), new Vector4f(1.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0,1,2,3)

        ));
        objectsCircle.add(new Circle(
                shader, new ArrayList<>(List.of()),
                new Vector4f(1.0f,1.0f,0.0f,1.0f),
                0.15,-0.7,0.7,0,0
        ));

        objectsRectangle.add(new Rectangle(
                shader,
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.525f,0.4f,0.0f),//Top Right
                                new Vector3f(0.375f,0.4f,0.0f),//Top Left
                                new Vector3f(0.375f,0.3f,0.0f),//Bot Left
                                new Vector3f(0.525f,0.3f,0.0f)// Bot Right

                        )
                ), new Vector4f(1.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0,1,2,3)

        ));
        objectsCircle.add(new Circle(
                shader, new ArrayList<>(List.of()),
                new Vector4f(0.0f,0.0235f,0.7176f,1.0f),
                0.15,-0.65,0.7,0,0
        ));

//        objectsCircle.add(
//                new Circle(
//                shader,
//                new ArrayList<>(),
//                new Vector4f(1.0f,0.0f,0.0f,1.0f),
//                0.4f,0.5f,0.0f,0
//        ));
//
        Kotak.add(new Kotak(
                shader,
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,0.0f,1.0f),
                0.0f,0.0f,0.5f,0.5f
        ));
//
        Segitiga.add(new Segitiga(
                shader,
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,0.0f,1.0f),
                0.2,0.3,0.0,0.0
        ));

        Star.add(new Star(shader,
                new ArrayList<>(),
                new Vector4f((204f/255f),(204f/255f),0.0f,1.0f),
                starIndex,-0.3f,0.5f,0.05f));

        Star.add(new Star(shader,
                new ArrayList<>(),
                new Vector4f((204f/255f),(204f/255f),0.0f,1.0f),
                starIndex,-0.1f,0.7f,0.03f));

        Star.add(new Star(shader,
                new ArrayList<>(),
                new Vector4f((204f/255f),(204f/255f),0.0f,1.0f),
                starIndex,0.5f,0.85f,0.03f));

        objectsCircle.add(new Circle(
                shader, new ArrayList<>(List.of()),
                new Vector4f((128f/255f),(128f/255f),(128f/255f),1.0f),
                0.05,0.58,0.55,1,0
        ));
        objectsCircle.add(new Circle(
                shader, new ArrayList<>(List.of()),
                new Vector4f((128f/255f),(128f/255f),(128f/255f),1.0f),
                0.03,0.52,0.51,1,0.02
        ));
        objectsCircle.add(new Circle(
                shader, new ArrayList<>(List.of()),
                new Vector4f((128f/255f),(128f/255f),(128f/255f),1.0f),
                -0.01,0.46,0.48,1,0.04
        ));


    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();
            input();

            //code
//            for(Object2d object: objects){
//                object.draw();
//            }

//            for(Object2d object: objects){
//                object.drawWithVerticesColor();
//            }
//            for(Object2d object: objectsRectangle){
//                object.draw();
//            }
//            for(Object2d object: objectsCircle){
//                object.draw();
//            }
//            for(Object2d object: Segitiga){
//                object.draw();
//            }

//            for(Object2d object: Kotak){
//                object.draw();
//            }

//            for(Object2d object: Star){
//                object.draw();
//            }

            for(Object2d object: objectsPointsControl){
                object.drawLine();
            }
            for(Object2d object: KotakKhusus){
                object.draw();
            }





            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public void input(){
        Kotak h;
        boolean found = false;
        List<Vector3f> vec ;
        int save = 0;
        List<ShaderProgram.ShaderModuleData> shader = Arrays.asList(
                //shaderFile lokasi menyesuaikan objectnya
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.vert"
                                , GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.frag"
                                , GL_FRAGMENT_SHADER)
        );
        if(window.isKeyPressed(GLFW_KEY_W)){
            System.out.println("W");
        }
        if(window.getMouseInput().isLeftButtonPressed()){
            Vector2f pos = window.getMouseInput().getCurrentPos();
//            System.out.println("x : "+ pos.x + " y : "+pos.y);
            pos.x = (pos.x - (window.getWidth())/2.0f)/(window.getWidth()/2.0f);
            pos.y = (pos.y - (window.getHeight())/2.0f)/(-window.getHeight()/2.0f);
            if((!(pos.x > 1 || pos.x < -0.97)&&!(pos.y >0.97 || pos.y < -1))){
                System.out.println("x : "+ pos.x + " y : "+pos.y);

                for (Kotak Kotak : KotakKhusus){
                    if (Kotak.contains(pos.x,pos.y)){
                        DragKotak = Kotak;
                        found = true;


                        break;
                    }
                    save += 1;
                }
                if(!found && DragKotak == null){
                    KotakKhusus.add(new Kotak(shader,new ArrayList<>(List.of())
                            ,new Vector4f(1.0f,0.0f,0.0f,1.0f),
                            pos.x,pos.y,0.05,0.05));
                    objectsPointsControl.get(0).addVertices(new Vector3f(pos.x, pos.y,0));
                }
                if(DragKotak != null){
                    objectsPointsControl.get(0).move(pos.x,pos.y,save);
                    DragKotak.move(pos.x,pos.y);

                }



            }

        }
        else if(window.getMouseInput().isleftButtonRelease()){
            DragKotak = null;
            save = 0;
        }


    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}