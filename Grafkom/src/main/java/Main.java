import Engine.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {

    private Window window = new Window(800, 800, "Hello World");

    ArrayList<Object2d> objects = new ArrayList<>();

    ArrayList<Object2d> objectsRectangle = new ArrayList<>();

    ArrayList<Object2d> objectsCircle = new ArrayList<>();

    ArrayList<Object2d> objectsElips = new ArrayList<>();

    ArrayList<Object2d> objectsStar = new ArrayList<>();

    ArrayList<Object2d> objectsRectCircle = new ArrayList<>();

    ArrayList<Object2d> objectsTriangleCircle = new ArrayList<>();

    ArrayList<Object2d> objectsPointsControl = new ArrayList<>();

    ArrayList<Object2d> titikBerzier = new ArrayList<>();

    int index;
    int indexKotak;

    int saveindex=0;

    boolean overlap;

    boolean kotakTerpilih;

    boolean drag;

    boolean newKotak;

    int tes;

    public void init() {
        window.init();
        GL.createCapabilities();
        overlap = false;
        kotakTerpilih = false;
        index = 0;


        objectsPointsControl.add(new Object2d(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(
//                List.of(
//                        new Vector3f(-1.0f, 0.0f, 0.0f),
//                        new Vector3f(0.0f,1.0f,0.0f),
//                        new Vector3f(1.0f, 0.0f, 0.0f)
//                )
        ),
                new Vector4f(0.0f, 1.0f, 0.0f, 1.0f) // ini untuk warna garisnya
        ));

        titikBerzier.add(new Object2d(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ), new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f) // ini untuk warna garisnya
        ));
    }

    public boolean cekJarakDuaTitik(float x1, float y1, float x2, float y2) {
        float jarak = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        System.out.println("Jarak Dua Titik: " + jarak);
        if (jarak > 0.2f) {
            return false;
        } else {
            return true;
        }
    }

    public boolean cekJarakTitikKotak(float x1, float y1, float x2, float y2) {
        float jarak = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        System.out.println("Jarak Kotak: " + jarak);
        if (jarak < 0.1f) {
            return true;
        } else {
            return false;
        }
    }

    public void berzier(ArrayList<Object2d> objects){
        int indexBerzier = 0;
        for (float t=0;t<=1;t+=0.01f){
            float x = 0;
            float y = 0;
            int n = objects.size()-1;
            for (int i=0;i<=n;i++){
                int koefisien = kombinasi(n, i);
                float term = koefisien * (float) Math.pow(1-t, n-i) * (float) Math.pow(t, i);
//                System.out.println("cek: " + objects.get(i).getCenterx() + " " + objects.get(i).getCentery());
                x += term * objects.get(i).getCenterx();
                y += term * objects.get(i).getCentery();
            }
//            System.out.println(x + " " + y);
//            System.out.println(titikBerzier.get(0).getCenterx() + " " + titikBerzier.get(0).getCentery());
//            System.out.println(titikBerzier.get(titikBerzier.size()-1).getCenterx() + " " + titikBerzier.get(titikBerzier.size()-1).getCentery());
            if (tes == 0){
                titikBerzier.get(0).addVertices(new Vector3f(x, y, 0));
            }
            if (tes == 1){
                titikBerzier.get(0).changeVertices(indexBerzier,new Vector3f(x, y, 0));
                indexBerzier += 1;
            }
        }
        if (tes == 0){
            tes = 1;
        }
//        titikBerzier.get(0).addVertices(new Vector3f(objects.get(objects.size()-1).getCenterx(), objects.get(objects.size()-1).getCentery(), 0));
//        System.out.println(titikBerzier.toString());
    }

    public int factorial(int angka){
        int hasil = 1;
        for (int i=2;i<=angka;i++){
            hasil = hasil * i;
        }
        return hasil;
    }

    public int kombinasi(int n, int r){
        if (r < 0 || r > n) {
            return 0;
        }return factorial(n)/(factorial(r)*factorial(n-r));
    }

    public int koefSegitigaPascal(int n, int k){
        if (k < 0 || k > n) {
            return 0;
        }
        int koef = 1;
        for (int i = 0; i < k; i++) {
            koef *= (n - i);
            koef /= (i + 1);
        }
        return koef;
    }


    public void input() {
        if (window.isKeyPressed(GLFW_KEY_W)) {
            System.out.println("w");
//            berzier(objectsRectCircle);
        }

        // ini buat fungsi klo is leftbuttonreleased, indexnya diplus terus tambah object baru. di atas bikin variable index
        if (window.getMouseInput().isLeftButtonReleased()) {
//            System.out.println("ahoy");
            kotakTerpilih = false;
            drag = false;
//            System.out.println(kotakTerpilih);
//            System.out.println(drag);
        }

        if (window.getMouseInput().isLeftButtonPressed()) {
            Vector2f pos = window.getMouseInput().getCurrentPos();
//            System.out.println("x : " + pos.x + "y : " + pos.y);

            // dinormalisasi biar titik 0,0 itu di tengah
            pos.x = (pos.x - (window.getWidth()) / 2.0f) / (window.getWidth() / 2.0f);
            pos.y = (pos.y - (window.getHeight()) / 2.0f) / (-window.getHeight() / 2.0f);
//            System.out.println("x : " + pos.x + "y : " + pos.y);

            indexKotak = 0;
            drag = false;
            newKotak = true;
            // ini supaya printnya cuma sekali
            if ((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1))) {
                for (Object2d objects : objectsRectCircle) {
//                    if (objects.checkOverlap(pos.x, pos.y) == false) {
//                        overlap = false;
//                    }
                    if (objects.checkKotak(pos.x, pos.y)) {
                        kotakTerpilih = true;
                        saveindex=indexKotak;
                        break;
                    }
                    indexKotak += 1;
                }
//                for (int i = 0; i < titik.size(); i++) {
//                    kotakTerpilih = cekJarakTitikKotak(pos.x, pos.y, titik.get(i).x, titik.get(i).y);
//                    overlap = cekJarakDuaTitik(pos.x, pos.y, titik.get(i).x, titik.get(i).y);
//                    if (kotakTerpilih){
//                        indexKotak = i;
//                        break;
//                    }
//                    indexKotak = i;
//                    if (kotakTerpilih && click) {
//                        System.out.println(i);
//                        System.out.println(kotakTerpilih);
//                        objectsPointsControl.get(0).clearVertices(i);
//                        objectsPointsControl.get(0).addVertices(i, new Vector3f(pos.x, pos.y, 0));
//                        objectsRectCircle.remove(i);
//                        objectsRectCircle.add(i, new RectangleCircle(Arrays.asList(
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                        ), new ArrayList<>(
//                                List.of(
//                                        new Vector3f(),
//                                        new Vector3f(),
//                                        new Vector3f(),
//                                        new Vector3f()
//                                )
//                        ), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
//                                pos.x, pos.y, 0.1f
//                        ));
//                    }
//                System.out.println(overlap);
//                System.out.println(kotakTerpilih);
//                System.out.println(drag);
//                System.out.println(kombinasi(5,2));

            if (kotakTerpilih) {
//                System.out.println(indexKotak);
                drag = true;
            }

            if (drag) {
                Vector3f position = new Vector3f(pos, 0.0f);
                objectsRectCircle.get(saveindex).move(pos.x, pos.y);
                objectsPointsControl.get(0).changeVertices(saveindex, position);
                berzier(objectsRectCircle);
//                    objectsRectCircle.remove(indexKotak);
//                        objectsRectCircle.add(indexKotak, new RectangleCircle(Arrays.asList(
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                        ), new ArrayList<>(
//                                List.of(
//                                        new Vector3f(),
//                                        new Vector3f(),
//                                        new Vector3f(),
//                                        new Vector3f()
//                                )
//                        ), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
//                                pos.x, pos.y, 0.1f
//                        ));
            }

//                overlap = false;
//                System.out.println("x : " + pos.x + "y : " + pos.y);
//                if (index > 0) {
//                    for (int j = 0; j < titik.size(); j++) {
//                        overlap = cekJarakDuaTitik(pos.x, pos.y, titik.get(j).x, titik.get(j).y);
//                        System.out.println("overlap: " + overlap);
//                        if (overlap) {
//                            break;
//                        }
//                    }
//                }
            if (!overlap && !drag && !kotakTerpilih) {
//                index = 1;
//                titik.add(new Vector2f(pos.x, pos.y));
                for (Object2d objects : objectsRectCircle) {
                    if (objects.checkOverlap(pos.x, pos.y) == true) {
                        newKotak = false;
                        break;
                    }
                }
                if (newKotak) {
                    objectsPointsControl.get(0).addVertices(new Vector3f(pos.x, pos.y, 0));
                    objectsRectCircle.add(new RectangleCircle(Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ), new ArrayList<>(
                            List.of(
                                    new Vector3f(),
                                    new Vector3f(),
                                    new Vector3f(),
                                    new Vector3f()
                            )
                    ), new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                            pos.x, pos.y, 0.1f
                    ));
                    berzier(objectsRectCircle);
                }
            }



            // addVertices ini panggil fungsi yg di object2d
            // dalam getnya diganti index
//                objectsPointsControl.get(0).addVertices(new Vector3f(pos.x, pos.y, 0));
//                objectsRectCircle.add(new RectangleCircle(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ), new ArrayList<>(
//                        List.of(
//                                new Vector3f(),
//                                new Vector3f(),
//                                new Vector3f(),
//                                new Vector3f()
//                        )
//                ), new Vector4f(1.0f,0.0f,0.0f,1.0f),
//                        pos.x,pos.y,0.1f
//                ));
        }
    }

}

    public void loop() {
        while (window.isOpen()) {
            window.update();
            // ini untuk background colornya
            glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // direset, tapi hanya 1 frame maka perlu diloop
            GL.createCapabilities();
            input();
            // code di bawah createcapabilities dan di atas restore state
            for (Object2d object : objects) {
                object.drawWithVerticesColor();
            }
            for (Object2d object : objectsRectangle) {
                object.draw();
            }
            for (Object2d object : objectsCircle) {
                object.draw();
            }
            for (Object2d object : objectsElips) {
                object.draw();
            }
            for (Object2d object : objectsStar) {
                object.draw();
            }
            for (Object2d object : objectsRectCircle) {
                object.draw();
            }
            for (Object2d object : objectsTriangleCircle) {
                object.draw();
            }
            for (Object2d object : objectsPointsControl) {
                object.drawLine();
            }
            for (Object2d object : titikBerzier){
                object.drawLineBerzier();
            }
            // restore state
            glDisableVertexAttribArray(0);
            // poll fow window events
            // the key callback above will only be invoked during this call
            glfwPollEvents();
        }
    }

    public void run() {
        init();
        loop();

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}