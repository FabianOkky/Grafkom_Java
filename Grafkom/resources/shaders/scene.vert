#version 330
layout (location=0) in vec3 position;


void main()
{
    //vec4(position,alpha)
    gl_Position = vec4(position, 1.0);
}