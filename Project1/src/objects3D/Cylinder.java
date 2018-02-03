package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0; i < nSegments; i += 1.0)
		{ /* a loop around circumference of a tube */
		float angle = (float) (Math.PI * i * 2.0 / nSegments) ;
		float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);
		/* compute sin & cosine */
		float x1 = (float) Math.sin(angle)*radius, y1 = (float) Math.cos(angle)*radius;
		float x2 = (float) Math.sin(nextAngle)*radius, y2 = (float) Math.cos(nextAngle)*radius;
		Point4f vertices[] = { 	new Point4f(x1, y1, 0.0f,0.0f), 
				new Point4f(x2, y2, height,0.0f),
				new Point4f(x1, y1, height,0.0f)};
		//This is the normal of the aside
		Vector4f v = vertices[0].MinusPoint(vertices[1]); 
		Vector4f w = vertices[2].MinusPoint(vertices[0]);
		Vector4f normal = v.cross(w).Normal();
		GL11.glNormal3f(normal.x, normal.y, normal.z);
		/* draw top (green) triangle */
		GL11.glVertex3f(x1, y1, 0.0f);
		GL11.glVertex3f(x2, y2, height);
		GL11.glVertex3f(x1, y1, height);
		/* draw bottom (red) triangle */
		GL11.glVertex3f(x1, y1, 0.0f);
		GL11.glVertex3f(x2, y2, 0.0f);
		GL11.glVertex3f(x2, y2, height);
		//this is the normal of the top and bottom
		Point4f vertice[] = { 	new Point4f(0, 0, 0,0.0f), 
				new Point4f(x2, y2, 0,0.0f),
				new Point4f(x1, y1, 0,0.0f)};
		Vector4f v1 = vertice[0].MinusPoint(vertice[1]); 
		Vector4f w1 = vertice[2].MinusPoint(vertice[0]);
		Vector4f normal1 = v1.cross(w1).Normal();
		GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
		/* draw top cover*/
		GL11.glVertex3f(0, 0, height);
		GL11.glVertex3f(x2, y2, height);
		GL11.glVertex3f(x1, y1, height);
		/*draw bottom cover*/
		GL11.glVertex3f(x1, y1, 0.0f);
		GL11.glVertex3f(x2, y2, 0.0f);
		GL11.glVertex3f(0, 0, 0.0f);
		} /* a loop around circumference of a tube */
		GL11.glEnd();
	}
}
