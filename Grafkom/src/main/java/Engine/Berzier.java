package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Berzier extends Object2d{
    public Berzier(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        this.vertices = new ArrayList<>(
                List.of(
                        new Vector3f(),
                        new Vector3f(),
                        new Vector3f()
                )
        );
    }
}
