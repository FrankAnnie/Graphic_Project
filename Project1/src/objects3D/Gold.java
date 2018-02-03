package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Gold {
	public void draw(){
		Point4f  vertices[] = {  new Point4f(0, 0, 0, 0.0f), 
				 new Point4f(100, 0, 0, 0.0f),
				 new Point4f(100, 100, 0, 0.0f),
				 new Point4f(0, 100, 0,0.0f)};
		
		
		GL11.glBegin(GL11.GL_QUADS);
		Vector4f v = vertices[3].MinusPoint(vertices[2]);
		Vector4f w = vertices[3].MinusPoint(vertices[0]);
		Vector4f normal = v.cross(w).Normal();
		GL11.glNormal3f(normal.x, normal.y, normal.z);
		GL11.glTexCoord2f(0.0f,0.0f);
		GL11.glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
		GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);
		GL11.glTexCoord2f(1.0f,1.0f);
		GL11.glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);
		GL11.glTexCoord2f(0.0f,1.0f);
		GL11.glVertex3f(vertices[3].x, vertices[3].y, vertices[3].z);
		GL11.glEnd();
	}
}
