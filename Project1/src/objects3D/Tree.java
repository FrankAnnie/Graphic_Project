package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Tree {
	float radius1;
	float radius2;
	float heightr;
	float heightb;
	int nSegments;
	
	public Tree(float radius1,float radius2, float heightr, float heightb,int nSegments){
		this.radius1 = radius1;
		this.radius2 = radius2;
		this.heightr = heightr;
		this.heightb = heightb;
		this.nSegments = nSegments;
	}
	public void DrawTree() 
	{
		//Here is the code of the root
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0; i < nSegments; i += 1.0)
		{ /* a loop around circumference of a tube */
		float angle = (float) (Math.PI * i * 2.0 / nSegments) ;
		float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);
		/* compute sin & cosine */
		float x1 = (float) Math.sin(angle)*radius2, y1 = (float) Math.cos(angle)*radius2;
		float x2 = (float) Math.sin(nextAngle)*radius2, y2 = (float) Math.cos(nextAngle)*radius2;
		float x3 = (float) Math.sin(angle)*radius1, y3 = (float) Math.cos(angle)*radius1;
		float x4 = (float) Math.sin(nextAngle)*radius1, y4 = (float) Math.cos(nextAngle)*radius1;
		Point4f vertices[] = { 	new Point4f(x1, y1, 0.0f,0.0f), 
				new Point4f(x2, y2, heightr,0.0f),
				new Point4f(x1, y1, heightr,0.0f)};
		//This is the normal of the aside
		Vector4f v = vertices[0].MinusPoint(vertices[1]); 
		Vector4f w = vertices[2].MinusPoint(vertices[0]);
		Vector4f normal = v.cross(w).Normal();
		GL11.glNormal3f(normal.x, normal.y, normal.z);
		
		/* draw top (green) triangle */
		GL11.glTexCoord2f(i/100,0);
		GL11.glVertex3f(x1, y1, 0.0f);
		GL11.glTexCoord2f(i+1/100,heightr);
		GL11.glVertex3f(x3, y3, heightr);
		GL11.glTexCoord2f(i+1/100,heightr);
		GL11.glVertex3f(x4, y4, heightr);
		/* draw bottom (red) triangle */
		GL11.glTexCoord2f(i/100,0);
		GL11.glVertex3f(x1, y1, 0.0f);
		GL11.glTexCoord2f(i+1/100,0);
		GL11.glVertex3f(x2, y2, 0.0f);
		GL11.glTexCoord2f(i+1/100,heightr);
		GL11.glVertex3f(x4, y4, heightr);
		
		//this is the normal of the top and bottom
		Point4f vertice[] = { 	new Point4f(0, 0, 0,0.0f), 
				new Point4f(x2, y2, 0,0.0f),
				new Point4f(x1, y1, 0,0.0f)};
		Vector4f v1 = vertice[0].MinusPoint(vertice[1]); 
		Vector4f w1 = vertice[2].MinusPoint(vertice[0]);
		Vector4f normal1 = v1.cross(w1).Normal();
		GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
		/* draw top cover*/
		GL11.glVertex3f(0, 0, heightr);
		//GL11.glTexCoord2f(0.0f,0.0f);
		GL11.glVertex3f(x4, y4, heightr);
		//GL11.glTexCoord2f(x2,y2);
		GL11.glVertex3f(x3, y3, heightr);
		//GL11.glTexCoord2f(x1,y1);
		/*draw bottom cover*/
		GL11.glVertex3f(x1, y1, 0.0f);
		//GL11.glTexCoord2f(x1,y1);
		GL11.glVertex3f(x2, y2, 0.0f);
		//GL11.glTexCoord2f(x2,y2);
		GL11.glVertex3f(0, 0, 0.0f);
		//GL11.glTexCoord2f(0.0f,0.0f);
		} /* a loop around circumference of a tube */
		GL11.glEnd();
		
		
		//Here is the code of the body
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0; i < nSegments; i += 1.0)
		{ /* a loop around circumference of a tube */
		float angle = (float) (Math.PI * i * 2.0 / nSegments) ;
		float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);
		/* compute sin & cosine */
		float x1 = (float) Math.sin(angle)*radius1, y1 = (float) Math.cos(angle)*radius1;
		float x2 = (float) Math.sin(nextAngle)*radius1, y2 = (float) Math.cos(nextAngle)*radius1;
		Point4f vertices[] = { 	new Point4f(x1, y1, heightr,0.0f), 
				new Point4f(x2, y2, heightb+heightr,0.0f),
				new Point4f(x1, y1, heightb+heightr,0.0f)};
		//This is the normal of the aside
		Vector4f v = vertices[0].MinusPoint(vertices[1]); 
		Vector4f w = vertices[2].MinusPoint(vertices[0]);
		Vector4f normal = v.cross(w).Normal();
		GL11.glNormal3f(normal.x, normal.y, normal.z);
		
		/* draw top (green) triangle */
		GL11.glTexCoord2f(i/100,heightr);
		GL11.glVertex3f(x1, y1, heightr);
		GL11.glTexCoord2f(i/100,heightb+heightr);
		GL11.glVertex3f(x2, y2, heightb+heightr);
		GL11.glTexCoord2f((i+1)/100,heightb+heightr);
		GL11.glVertex3f(x1, y1, heightb+heightr);
		/* draw bottom (red) triangle */
		GL11.glTexCoord2f(i/100,heightr);
		GL11.glVertex3f(x1, y1, heightr);
		GL11.glTexCoord2f((i+1)/100,heightr);
		GL11.glVertex3f(x2, y2, heightr);
		GL11.glTexCoord2f((i+1)/100,heightb+heightr);
		GL11.glVertex3f(x2, y2, heightb+heightr);
		
		//this is the normal of the top and bottom
		Point4f vertice[] = { 	new Point4f(0, 0, 0,0.0f), 
				new Point4f(x2, y2, 0,0.0f),
				new Point4f(x1, y1, 0,0.0f)};
		Vector4f v1 = vertice[0].MinusPoint(vertice[1]); 
		Vector4f w1 = vertice[2].MinusPoint(vertice[0]);
		Vector4f normal1 = v1.cross(w1).Normal();
		GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
		/* draw top cover*/
		GL11.glVertex3f(0, 0, heightb+heightr);
		//GL11.glTexCoord2f(0.0f,0.0f);
		GL11.glVertex3f(x2, y2, heightb+heightr);
		//GL11.glTexCoord2f(x2,y2);
		GL11.glVertex3f(x1, y1, heightb+heightr);
		//GL11.glTexCoord2f(x1,y1);
		/*draw bottom cover*/
		GL11.glVertex3f(x1, y1, heightr);
		//GL11.glTexCoord2f(x1,y1);
		GL11.glVertex3f(x2, y2, heightr);
		//GL11.glTexCoord2f(x2,y2);
		GL11.glVertex3f(0, 0, heightr);
		//GL11.glTexCoord2f(0.0f,0.0f);
		} /* a loop around circumference of a tube */
		GL11.glEnd();
	}
}

