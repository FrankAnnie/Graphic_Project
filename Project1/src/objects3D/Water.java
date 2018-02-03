package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Water {
	float x,y,z;
	int n = 150;
	float cos,sin;//This is the cos value and the sin value
	public Water(float tx,float ty,float y) {
		this.y = y;
		this.cos = tx;
		this.sin = ty;
	}
	public void DrawWater(){
		z = 0;
		x = 0;
		Point4f  vertices[] = {  new Point4f(0, 0, 0, 0.0f), 
								 new Point4f(1, -y, 0, 0.0f),
								 new Point4f(0, -y, 1, 0.0f),
								 new Point4f(1,0,1,0.0f)};
		
		//This is the code of light
		GL11.glNormal3f(cos, 0, sin);

		for(z = 0;z<n+150;z++){
			
			if(z%2!=1){
			for( x = 0;x<n;x++){
				GL11.glBegin(GL11.GL_QUADS);

				
				if(x%2!=1){
					GL11.glTexCoord2f(x/n,z/n);
					GL11.glVertex3f(x, 0, z);
					GL11.glTexCoord2f((x+1)/n,z/n);
					GL11.glVertex3f(x+1, -y, z);
					GL11.glTexCoord2f((x+1)/n,(z+1)/n);
					GL11.glVertex3f(x+1, 0, z+1);
					GL11.glTexCoord2f(x/n,(z+1)/n);
					GL11.glVertex3f(x, -y, z+1);
					GL11.glEnd();
				}
				if(x%2 == 1){
					GL11.glTexCoord2f(x/n,z/n);
					GL11.glVertex3f(x, -y, z);
					GL11.glTexCoord2f((x+1)/n,z/n);
					GL11.glVertex3f(x+1, 0, z);
					GL11.glTexCoord2f((x+1)/n,(z+1)/n);
					GL11.glVertex3f(x+1, -y, z+1);
					GL11.glTexCoord2f(x/n,(z+1)/n);
					GL11.glVertex3f(x, 0, z+1);
					GL11.glEnd();
				}
			}
			}
			else{
				for( x = 0;x<n;x++){
					GL11.glBegin(GL11.GL_QUADS);
			

					if(x%2==1){
						GL11.glTexCoord2f(x/n,z/n);
						GL11.glVertex3f(x, 0, z);
						GL11.glTexCoord2f((x+1)/n,z/n);
						GL11.glVertex3f(x+1, -y, z);
						GL11.glTexCoord2f((x+1)/n,(z+1)/n);
						GL11.glVertex3f(x+1, 0, z+1);
						GL11.glTexCoord2f(x/n,(z+1)/n);
						GL11.glVertex3f(x, -y, z+1);
						GL11.glEnd();
					}
					if(x%2 != 1){
						GL11.glTexCoord2f(x/n,z/n);
						GL11.glVertex3f(x, -y, z);
						GL11.glTexCoord2f((x+1)/n,z/n);
						GL11.glVertex3f(x+1, 0, z);
						GL11.glTexCoord2f((x+1)/n,(z+1)/n);
						GL11.glVertex3f(x+1, -y, z+1);
						GL11.glTexCoord2f(x/n,(z+1)/n);
						GL11.glVertex3f(x, 0, z+1);
						GL11.glEnd();
					}
				}
			}
		}
	}
}
