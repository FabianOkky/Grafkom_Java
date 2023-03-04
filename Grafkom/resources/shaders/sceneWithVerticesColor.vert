#version 330
layout (location=0) in vec3 position;
layout (location=1) in vec3 color;

out vec4 out_color;

void main()
{
    //vec4(position,alpha)
    gl_Position = vec4(position, 1.0);
    out_color = vec4(color,1.0);
}