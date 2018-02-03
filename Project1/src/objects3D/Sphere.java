package objects3D;

import org.lwjgl.opengl.GL11;

public class Sphere {

	
	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawSphere(float radius,float nSlices,float nSegments) {
		float inctheta = (float) ((2.0f*Math.PI)/(nSlices));
		float incphi =(float) (Math.PI/(nSegments));
		float x1,y1,z1,x2,y2,z2,x3,y3,x4,y4;
		
		GL11.glBegin(GL11.GL_QUADS);
			for(float theta=(float) -Math.PI; theta<Math.PI; theta+=inctheta){
				for(float phi=(float) -(Math.PI/2.0f); phi<(Math.PI/2.0f); phi+=incphi){
					//Those are the coordinates of the small square 
					x1 = (float) (radius*Math.cos(theta)*Math.cos(phi));
					y1 = (float) (radius*Math.cos(theta)*Math.sin(phi));
					z1 = (float) (radius*Math.sin(theta));
					x4 = (float) (radius*Math.cos(theta+inctheta)*Math.cos(phi));
					y4 = (float) (radius*Math.cos(theta+inctheta)*Math.sin(phi));
					z2 = (float) (radius*Math.sin(theta+inctheta));
					x3 = (float) (radius*Math.cos(theta+inctheta)*Math.cos(phi+incphi));
					y3 = (float) (radius*Math.cos(theta+inctheta)*Math.sin(phi+incphi));
					x2 = (float) (radius*Math.cos(theta)*Math.cos(phi+incphi));
					y2 = (float) (radius*Math.cos(theta)*Math.sin(phi+incphi));
					//draw the small square
					GL11.glNormal3f(x1, y1, z1);
					GL11.glVertex3f(x1, y1, z1);
					GL11.glNormal3f(x2, y2, z1);
					GL11.glVertex3f(x2, y2, z1);
					GL11.glNormal3f(x3, y3, z2);
					GL11.glVertex3f(x3, y3, z2);
					GL11.glNormal3f(x4, y4, z2);
					GL11.glVertex3f(x4, y4, z2);
				}
			}
			GL11.glEnd();
	}
}

 