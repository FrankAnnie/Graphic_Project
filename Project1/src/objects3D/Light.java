package objects3D;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import GraphicsObjects.Utils;


public class Light {
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };
	public  Light() {
		// TODO Auto-generated constructor stub
	}

	
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawLight() {

		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(1).put(1).put(400).put(1).flip();
		FloatBuffer lightSpo = BufferUtils.createFloatBuffer(4);
		lightPos.put(-1).put(-1).put(0).put(0).flip();
		GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LIGHT1);
			int a = GL11.GL_EMISSION;
			GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos);
			GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPOT_DIRECTION, lightSpo);
			GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));
	}
}
