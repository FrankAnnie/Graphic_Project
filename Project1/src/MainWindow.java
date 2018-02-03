
import java.io.IOException;
import java.nio.FloatBuffer;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;
import objects3D.TexCylinder;
import objects3D.TexSphere;
import objects3D.Tree;
import objects3D.Velamen;
import objects3D.Water;
import objects3D.Gold;
import objects3D.Cylinder;
import objects3D.Light;
import objects3D.Grid;
import objects3D.Human;
import objects3D.Ship;
import objects3D.Sphere;
import objects3D.Tetrahedron;
import objects3D.TexCube; 

//Main windows class controls and creates the 3D virtual world , plhease do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {
	private boolean forward = false;
	private boolean back = false;
	private boolean right = false;
	private boolean left = false;
	private boolean lightSwitch = false;
	private  boolean MouseOnepressed = true;
	private boolean  dragMode=false;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	long  myDelta =0 ; //to use for animation
	float Alpha =0 ; //to use for animation
	long StartTime; // beginAnimiation 

	Arcball MyArcball= new Arcball();
	
	boolean DRAWGRID =false;
	boolean waitForKeyrelease= true;
	/** Mouse movement */
	int LastMouseX = -1 ;
	int LastMouseY= -1;
	
	 float pullX = 0.0f; // arc ball  X cord. 
	 float pullY = 0.0f; // arc ball  Y cord. 

	 
	int OrthoNumber = 2000; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project 
	
	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process 
	

	public void start() {
		
		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		 //rotate quad
		rotation += 0.01f * delta;
		  
		  
		int MouseX= Mouse.getX();
		  int MouseY= Mouse.getY();
		  int WheelPostion = Mouse.getDWheel();
	  
		  
		  boolean  MouseButonPressed= Mouse.isButtonDown(0);
		  
		 
		  
		  if(MouseButonPressed && !MouseOnepressed )
		  {
			  MouseOnepressed =true;
			//  System.out.println("Mouse drag mode");
			  MyArcball.startBall( MouseX, MouseY, 1200, 800);
			  dragMode=true;
				
				
		  }else if( !MouseButonPressed)
		  { 
				// System.out.println("Mouse drag mode end ");
			  MouseOnepressed =false;
			  dragMode=false;
		  }
		  
		  if(dragMode)
		  {
			  MyArcball.updateBall( MouseX  , MouseY  , 1200, 800);
		  }
		  
		  if(WheelPostion>0)
		  {
			  OrthoNumber += 10;
			 
		  }
		  
		  if(WheelPostion<0)
		  {
			  OrthoNumber -= 10;
			  if( OrthoNumber<610)
			  {
				  OrthoNumber=610;
			  }
			  
			//  System.out.println("Orth nubmer = " +  OrthoNumber);
			  
		  }
		  
		  /** rest key is R*/
		  if (Keyboard.isKeyDown(Keyboard.KEY_R))
			  MyArcball.reset();
		  
		  
		if (Keyboard.isKeyDown(Keyboard.KEY_W)){
			forward = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			back = true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			lightSwitch = !lightSwitch;
			initGL();
			
		}
			

		
		if(forward = true){
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				forward = true;
			}
			else{
				forward = false;
			}
		}
		if(back = true){
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				back = true;
			}
			else{
				back = false;
			}
		}
		if(right = true){
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				right = true;
			}
			else{
				right = false;
			}
		}
		if(left = true){
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				left = true;
			}
			else{
				left = false;
			}
		}

		
		
		 if(waitForKeyrelease) // check done to see if key is released 
		 {
		if (Keyboard.isKeyDown(Keyboard.KEY_G))
		{
			
			DRAWGRID = !DRAWGRID;
			Keyboard.next();
			if(Keyboard.isKeyDown(Keyboard.KEY_G))
			{
				waitForKeyrelease=true;
			}else
			{
				waitForKeyrelease=false;
				
			}
		}
		 }
		 
		 /** to check if key is released */
		 if(Keyboard.isKeyDown(Keyboard.KEY_G)==false)
			{
				waitForKeyrelease=true;
			}else
			{
				waitForKeyrelease=false;
				
			}
		 
		
		 
		 
		
		 
			

		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter
		
		LastMouseX= MouseX;
		LastMouseY= MouseY ;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200,800); 
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();//That controls the sea light

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();
		//my light tower
		FloatBuffer lightPos5 = BufferUtils.createFloatBuffer(4);
		lightPos5.put(1200).put(1000).put(1500).put(1).flip();
		FloatBuffer lightSpo = BufferUtils.createFloatBuffer(4);
		lightSpo.put(-1).put(-1).put(-1).put(0).flip();
		FloatBuffer lightSpo1 = BufferUtils.createFloatBuffer(4);
		lightSpo1.put(70).put(0).put(0).put(0).flip();

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos5); 
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPOT_DIRECTION, lightSpo);	
		//GL11.glLight(GL11.GL_LIGHT0, GL11.GL_EMISSION, lightPos5);//angle
																	
		if(lightSwitch){
			// light
			GL11.glEnable(GL11.GL_LIGHT0); 
		}
		else{
			GL11.glDisable(GL11.GL_LIGHT0);
		}

		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		 GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
											// on
	 	GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
	 	GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		
		   GL11.glEnable(GL11.GL_BLEND);
       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
          try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load in texture
          

	}

	 

	public void changeOrth() {

		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity();
		  GL11.glOrtho(1200 -  OrthoNumber , OrthoNumber, (800 - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000);
		 	GL11.glMatrixMode(GL11.GL_MODELVIEW); 
		 	
		 	FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16); 
		 	GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);
		 
		 //	if(MouseOnepressed)
		 //	{
		  
		 	MyArcball.getMatrix(CurrentMatrix); 
		 //	}
		 	
		    GL11.glLoadMatrix(CurrentMatrix);
		 	GLU.gluLookAt(-100, -200, 500, -100, 0, 0, 0, 1, 0);
	}

	/*
	 * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load 
	 * 
	 */
	float count = 1;
	float positionx = 0;
	float positionz = 0;
	float cpositionx = -500;
	float cpositionz = -600;
	float v = 0;
	float vmax = 13;
	float av = 0.1f;
	float cv = 5;//speed of the chasing boat
	float suprise = 1;
	double angle = 0;
	double cangle = 0;
	boolean gold1 = true,gold2 = true,gold3 = true,gold4 = true,gold5 = true;
	public void renderGL() { 
		changeOrth();
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glColor3f(0.5f, 0.5f, 1.0f); 
		 
		 myDelta =   getTime() - StartTime; 
		 float delta =((float) myDelta)/10000;
		 float time = ((float) myDelta)/30000; 
		  // code to aid in animation 
		 float theta = (float) (delta * 2 * Math.PI);
		 float thetaDeg = delta * 360; 
		  float posn_x = (float) Math.cos(theta); // same as your circle code in your notes 
		  float posn_y  = (float) Math.sin(theta);
		  float thetatime = (float) (time * 2 * Math.PI);
		  float timecos = (float) Math.cos(thetatime);  
		  float timesin  = (float) Math.sin(thetatime);
		  /*
		   * This code draws a grid to help you view the human models movement 
		   *  You may change this code to move the grid around and change its starting angle as you please 
		   */
		if(DRAWGRID)
		{
		GL11.glPushMatrix();
		Grid MyGrid = new Grid();
		GL11.glTranslatef(600, 400, 0); 
		GL11.glScalef(200f, 200f,  200f); 
		 MyGrid.DrawGrid();
		GL11.glPopMatrix();
		}
		
		//This is the ocean code
		GL11.glPushMatrix();
		Water test = new Water(timecos,timesin,posn_y);
		GL11.glTranslatef(-900, 400, -2800);
		GL11.glScalef(20f, 20f,  20f); 
		
		Color.white.bind();
		water.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);      
		test.DrawWater();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		//This is the ship code
		GL11.glPushMatrix();{
		Ship ship = new Ship();
		GL11.glTranslatef(positionx, 400, positionz);
		GL11.glScalef(suprise, suprise, suprise);
		GL11.glRotated(angle, 0, 1, 0);
		if(forward==true){
			if(v<vmax){
				v = v + av; 
			}
			if(positionx>-900&&positionx<1800&&positionz<2400&&positionz>-2250){//make sure the ship not out of the range
				positionx = (float) (positionx + v*Math.cos(-angle/(180/Math.PI)));
				positionz =	(float) (positionz + v*Math.sin(-angle/(180/Math.PI)));
			}
			else{
				if(positionx<=-900){
					v = 0;//hit the wall reset the v
					positionx = -899;
				}
				if(positionx>=1800){
					v = 0;
					positionx = 1799;
				}
				if(positionz>=2400){
					v = 0;
					positionz = 2399;
				}
				if(positionz<=-2250){
					v = 0;
					positionz = -2249;
				}
			}
			
		}
		if(forward == false){//reset the velocity
			if(v>0){
				v = v - av;
				if(back==false){
					if(positionx>-900&&positionx<1800&&positionz<2400&&positionz>-2250){
						positionx = (float) (positionx + v*Math.cos(-angle/(180/Math.PI)));
						positionz =	(float) (positionz + v*Math.sin(-angle/(180/Math.PI)));
					}
					else{
						if(positionx<=-900){
							v = 0;
							positionx = -899;
						}
						if(positionx>=1800){
							v = 0;
							positionx = 1799;
						}
						if(positionz>=2400){
							v = 0;
							positionz = 2399;
						}
						if(positionz<=-2250){
							v = 0;
							positionz = -2249;
						}
					}
				}
			}
			
		}
		if(back==true){
			if(v>-vmax){
				v = v - av;
			}
			if(positionx>-900&&positionx<1800&&positionz<2400&&positionz>-2250){
				positionx = (float) (positionx + v*Math.cos(-angle/(180/Math.PI)));
				positionz =	(float) (positionz + v*Math.sin(-angle/(180/Math.PI)));
			}
			else{
				if(positionx<=-900){
					v = 0;
					positionx = -899;
				}
				if(positionx>=1800){
					v = 0;
					positionx = 1799;
				}
				if(positionz>=2400){
					v = 0;
					positionz = 2399;
				}
				if(positionz<=-2250){
					v = 0;
					positionz = -2249;
				}
			}
			
		}
		if(back==false){
			if(v<0){
				v = v + av;
				if(forward==false){
					if(positionx>-900&&positionx<1800&&positionz<2400&&positionz>-2250){
						positionx = (float) (positionx + v*Math.cos(-angle/(180/Math.PI)));
						positionz =	(float) (positionz + v*Math.sin(-angle/(180/Math.PI)));
					}
					else{
						if(positionx<=-900){
							v = 0;
							positionx = -899;
						}
						if(positionx>=1800){
							v = 0;
							positionx = 1799;
						}
						if(positionz>=2400){
							v = 0;
							positionz = 2399;
						}
						if(positionz<=-2250){
							v = 0;
							positionz = -2249;
						}
					}
				}
			}
		}
		if(right==true){
			angle = angle+1;
		}
		if(left==true){
			angle = angle-1;
		}
		
		GL11.glPushMatrix();{
		TexCylinder sail = new TexCylinder();
		GL11.glTranslatef(10, 150, 0);
		GL11.glRotatef(90, 1, 0, 0);
		sail.DrawCylinder(3, 150, 100);
		
		GL11.glPushMatrix();
		Velamen velamen = new Velamen();
		GL11.glTranslatef(0, 0, 50);
		r.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		velamen.DrawVelament(50, 100, 100);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		}GL11.glPopMatrix();
		
		GL11.glScalef(30f, 30f, 30f);
		
		Color.white.bind();
		iron.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		ship.DrawShip();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		}GL11.glPopMatrix();
		
		//This is the code of the gold
		if(gold1 == true){
			GL11.glPushMatrix();
			TexCube gold = new TexCube();
			GL11.glTranslatef(600, 420, 0); 
			GL11.glRotatef(45, 1, 0, 1);
			GL11.glRotatef(rotation, 0, 0f, 1f); 
			GL11.glScalef(20f, 20f,  20f);
			
			if(Math.abs(positionx-600)<20&&Math.abs(positionz)<20){
				gold1 = false;
				v = v-2;
				vmax = vmax-2;
			}
			gold.DrawTexCube();
			GL11.glPopMatrix();
		}
		
		if(gold2 == true){
			GL11.glPushMatrix();
			TexCube gold = new TexCube();
			GL11.glTranslatef(1500, 420, -800); 
			GL11.glRotatef(45, 1, 0, 1);
			GL11.glRotatef(rotation, 0, 0f, 1f); 
			GL11.glScalef(20f, 20f,  20f);
			
			if(Math.abs(positionx-1500)<20&&Math.abs(positionz+800)<20){
				gold2 = false;
				v = v-2;
				vmax = vmax-2;
			}
			gold.DrawTexCube();
			GL11.glPopMatrix();
		}
		
		if(gold3 == true){
			GL11.glPushMatrix();
			TexCube gold = new TexCube();
			GL11.glTranslatef(300, 420, -800); 
			GL11.glRotatef(45, 1, 0, 1);
			GL11.glRotatef(rotation, 0, 0f, 1f); 
			GL11.glScalef(20f, 20f,  20f);
			
			if(Math.abs(positionx-300)<20&&Math.abs(positionz+800)<20){
				gold3 = false;
				v = v-2;
				vmax = vmax-2;
			}
			gold.DrawTexCube();
			GL11.glPopMatrix();
		}
		
		if(gold4 == true){
			GL11.glPushMatrix();
			TexCube gold = new TexCube();
			GL11.glTranslatef(100, 420, 800); 
			GL11.glRotatef(45, 1, 0, 1);
			GL11.glRotatef(rotation, 0, 0f, 1f); 
			GL11.glScalef(20f, 20f,  20f);
			
			if(Math.abs(positionx-100)<20&&Math.abs(positionz-800)<20){
				gold4 = false;
				v = v-2;
				vmax = vmax-2;
			}
			gold.DrawTexCube();
			GL11.glPopMatrix();
		}
		
		if(gold5 == true){
			GL11.glPushMatrix();
			TexCube gold = new TexCube();
			GL11.glTranslatef(600, 420, -420); 
			GL11.glRotatef(45, 1, 0, 1);
			GL11.glRotatef(rotation, 0, 0f, 1f); 
			GL11.glScalef(20f, 20f,  20f);
			
			if(Math.abs(positionx-600)<20&&Math.abs(positionz+420)<20){
				gold5 = false;
				v = v-2;
				vmax = vmax-2;
			}
			gold.DrawTexCube();
			GL11.glPopMatrix();
		}
		//This is the code of the chasing ship
		if(gold1==false||gold2==false||gold3==false||gold4==false||gold5==false){
			GL11.glPushMatrix();{
				Ship ship = new Ship();
				GL11.glTranslatef(cpositionx, 400, cpositionz);
				double distance = (positionz - cpositionz)*(positionz - cpositionz)+(positionx - cpositionx)*(positionx - cpositionx);
				distance = Math.sqrt(distance);
				if(positionx-cpositionx>0){
				cangle = -Math.asin((positionz - cpositionz)/distance)*180/Math.PI;
				}
				else{
					cangle = Math.asin((positionz - cpositionz)/distance)*180/Math.PI+180;
				}
				
				cpositionx = (float) (cpositionx + cv*Math.cos(-cangle/(180/Math.PI)));
				cpositionz =	(float) (cpositionz + cv*Math.sin(-cangle/(180/Math.PI)));
				GL11.glRotated(cangle, 0, 1, 0);
				GL11.glPushMatrix();{
					TexCylinder sail = new TexCylinder();
					GL11.glTranslatef(10, 150, 0);
					GL11.glRotatef(90, 1, 0, 0);
					sail.DrawCylinder(3, 150, 100);
					
					GL11.glPushMatrix();
					Velamen velamen = new Velamen();
					GL11.glTranslatef(0, 0, 50);
					r.bind();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					velamen.DrawVelament(50, 100, 100);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glPopMatrix();
					}GL11.glPopMatrix();
					
					GL11.glScalef(30f, 30f, 30f);
					
					Color.white.bind();
					iron.bind();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					ship.DrawShip();
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					}GL11.glPopMatrix();
		}
		if(Math.abs(positionx-cpositionx)<20&&Math.abs(positionz-cpositionz)<20){
			positionx = 0;
			positionz = 0;
			v = 0;
			vmax = 13;
			gold1 = true;
			gold2 = true;
			gold3 = true;
			gold4 = true;
			gold5 = true;
		}
		if(gold1==false&&gold2==false&&gold3==false&&gold4==false&&gold5==false){
			suprise = 20;
		}
		//This is the code of the island
		GL11.glPushMatrix();
		TexSphere island = new TexSphere();
		GL11.glTranslatef(1500, 400, 2300);
		GL11.glScalef(500, 50, 2000);
		mountain.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		island.DrawTexSphere(1, 15, 15);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		//This is the code of the light tower
		GL11.glPushMatrix();
		Tree tree = new Tree(30, 50, 100, 200,50);//The name is tree but now it's the light tower
		GL11.glTranslatef(1200, 400, 1500);
		GL11.glRotatef(-90, 1, 0, 0);
		wood.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		tree.DrawTree();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix();{
		TexSphere test1 = new TexSphere();
		GL11.glTranslatef(1200, 700, 1500);
		
		light.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_EMISSION);//here is the code to make it like a light but there seems to be a bug
//	    GL11.glEnable(GL11.GL_COLOR_MATERIAL);//don't understand why this line is useless
		test1.DrawTexSphere(50, 20, 20);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
//		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		}GL11.glPopMatrix();
		

	}
		  
	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}
	 
	Texture texture;
	Texture cube;
	Texture mountain;
	Texture water;
	Texture iron;
	Texture wood;
	Texture r;
	Texture sand;
	Texture light;
	 
	/*
	 * Any additional textures for your assignment should be written in here. 
	 * Make a new texture variable for each one so they can be loaded in at the beginning 
	 */
	public void init() throws IOException {
	         
	  texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/superman.png"));
	  //System.out.println("Texture loaded okay ");
	  cube = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/dota21.png"));
	  mountain = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/mountain.png"));
	  water = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/water.png"));
	  iron = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/iron.png"));
	  wood = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/wood.png"));
	  r = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/2016.png"));
	  sand = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/beach.png"));
	  light = TextureLoader.getTexture("PNG",ResourceLoader.getResourceAsStream("res/white.png"));

	}
	
}
