package objects3D;

import java.io.IOException;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;

public class Human {

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 0.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 0.0f };
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
	
	
	public Human() {

	}
	
	// Implement using notes  in Animation lecture  
	public void DrawHuman(float delta,boolean GoodAnimation,Texture texture) throws IOException //add the texture here
 { 
		 
		 float theta = (float) (delta * 2 * Math.PI*5);
		  float LimbRotation;
		  
		  
		 if (GoodAnimation)
		 {
			 LimbRotation = (float) Math.cos(theta)*45;
		 }else
		 {
			 LimbRotation = 0;
		 } 
		  
		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();
		TexSphere test = new TexSphere();
		
 
 
		 GL11.glPushMatrix(); 
		 
		 {
			 GL11.glColor3f(white[0], white[1], white[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
			  GL11.glTranslatef(0.0f,1.0f,0.0f);
			  texture.bind();
			  GL11.glEnable(GL11.GL_TEXTURE_2D);
              test.DrawTexSphere(0.5f, 32, 32);
              GL11.glDisable(GL11.GL_TEXTURE_2D);
			 
		        //  chest
			 GL11.glColor3f(white[0], white[1], white[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
			 GL11.glPushMatrix(); {
		     GL11.glTranslatef(0.0f,1.0f,0.0f);
		     texture.bind();
		     GL11.glEnable(GL11.GL_TEXTURE_2D);
             test.DrawTexSphere(0.5f, 32, 32); 
             GL11.glDisable(GL11.GL_TEXTURE_2D);
					//waist
					GL11.glColor3f(white[0], white[1], white[2]);
					GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
					GL11.glPushMatrix(); {
					GL11.glTranslatef(0.0f,-0.5f,0.0f);
					sphere.DrawSphere(0.3f, 32, 32);
					}GL11.glPopMatrix();
		            // neck
		       	 	GL11.glColor3f(white[0], white[1], white[2]);
		            GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		            GL11.glPushMatrix(); {
		            GL11.glTranslatef(0.0f,0.0f, 0.0f);
		            GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
		                //                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f); 
		                cylinder.DrawCylinder(0.15f,0.7f,32);


		                // head
		           	   GL11.glColor3f(white[0], white[1], white[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		               GL11.glPushMatrix(); {
		               GL11.glTranslatef(0.0f,0.0f,1.0f);
		               texture.bind();
		               GL11.glEnable(GL11.GL_TEXTURE_2D);
		               test.DrawTexSphere(0.5f, 32, 32);
		               GL11.glDisable(GL11.GL_TEXTURE_2D);
		              //left eye      
		                    GL11.glColor3f(white[0], white[1], white[3]);
		                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		                    GL11.glPushMatrix(); {
		                    GL11.glTranslatef(-0.18f,0.3f,0.1f);
		                    sphere.DrawSphere(0.15f, 32, 32);
		                    }GL11.glPopMatrix();
		              //right eye
		                     GL11.glColor3f(white[0], white[1], white[3]);
				             GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
				             GL11.glPushMatrix(); {
				             GL11.glTranslatef(0.18f,0.3f,0.1f);
				             sphere.DrawSphere(0.15f, 32, 32);
				             }GL11.glPopMatrix();
				      //hair
				             GL11.glColor3f(black[0], black[1], black[3]);
				             GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
				             GL11.glPushMatrix(); {
				             GL11.glTranslatef(0.0f,0.1f,0.23f);
				             sphere.DrawSphere(0.3f, 32, 32);
				             }GL11.glPopMatrix();
				             GL11.glPopMatrix();   
		                } GL11.glPopMatrix();


		                // left shoulder
		           	 GL11.glColor3f(grey[0],grey[1], grey[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.5f,0.4f,0.0f);
		                    sphere.DrawSphere(0.25f, 32, 32); 
		                    

		                    // left arm
		               	 GL11.glColor3f(white[0], white[1], white[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		                    GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.0f);
		                        GL11.glRotatef(40.0f,1.0f,2.0f,0.0f);
		                        
		                        
		                        GL11.glRotatef(LimbRotation,1.0f,0.0f,0.0f); 
		                     //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);  
		                        cylinder.DrawCylinder(0.15f,0.7f,32);


		                        // left elbow
		                   	 GL11.glColor3f(grey[0], grey[1], grey[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.75f);
		                            sphere.DrawSphere(0.2f, 32, 32); 
		                            
		                            //left forearm
		                       	 GL11.glColor3f(grey[0], grey[1], grey[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.0f);
		                                GL11.glRotatef(90.0f,1.0f,0.2f,0.0f);
		                             //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f); 
		                                cylinder.DrawCylinder(0.1f,0.7f,32);

		                                // left hand
		                           	 GL11.glColor3f(grey[0], grey[1], grey[2]);
		                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		                                GL11.glPushMatrix(); {
		                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                    sphere.DrawSphere(0.2f, 32, 32); 
		                                    


		                                } GL11.glPopMatrix();
		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix ();
		                } GL11.glPopMatrix ();
		                // to chest
		                // right shoulder
		                GL11.glColor3f(grey[0],grey[1], grey[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(-0.5f,0.4f,0.0f);
			                    sphere.DrawSphere(0.25f, 32, 32); 

		                    // right arm
			                    GL11.glColor3f(white[0], white[1], white[2]);
				                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
				                    GL11.glPushMatrix(); {
				                        GL11.glTranslatef(0.0f,0.0f,0.0f);
				                        GL11.glRotatef(20.0f,1.0f,-2.0f,0.0f);
				                        
				                        
				                        GL11.glRotatef(-LimbRotation,1.0f,0.0f,0.0f); 
				                     //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);  
				                        cylinder.DrawCylinder(0.15f,0.7f,32);
		                        // right elbow
				                        GL11.glColor3f(grey[0], grey[1], grey[2]);
					                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
					                        GL11.glPushMatrix(); {
					                            GL11.glTranslatef(0.0f,0.0f,0.75f);
					                            sphere.DrawSphere(0.2f, 32, 32);      
		                            //right forearm
					                            GL11.glColor3f(white[0], white[1], white[2]);
						                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
						                            GL11.glPushMatrix(); {
						                                GL11.glTranslatef(0.0f,0.0f,0.0f);
						                                GL11.glRotatef(90.0f,1.0f,-0.2f,0.0f);
						                             //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f); 
						                                cylinder.DrawCylinder(0.1f,0.7f,32);
		                                // right hand
						                                GL11.glColor3f(grey[0], grey[1], grey[2]);
							                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
							                                GL11.glPushMatrix(); {
							                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
							                                    sphere.DrawSphere(0.2f, 32, 32);    
		                         
							                                } GL11.glPopMatrix();
						                            } GL11.glPopMatrix();
						                        } GL11.glPopMatrix();
						                    } GL11.glPopMatrix ();
						                } GL11.glPopMatrix ();
		                //chest


		            } GL11.glPopMatrix();


		            // pelvis

		            // left hip
		       	 GL11.glColor3f(grey[0], grey[1], grey[2]);
		           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		            GL11.glPushMatrix(); {
		                GL11.glTranslatef(-0.5f,-0.2f,0.0f);
		               
		                sphere.DrawSphere(0.25f, 32, 32); 


		                // left high leg
		           	 GL11.glColor3f(white[0], white[1], white[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                
		                    
		                    GL11.glRotatef((LimbRotation)+120,1.0f,0.0f,0.0f); 
		                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
		                    cylinder.DrawCylinder(0.15f,0.7f,32);


		                    // left knee
		               	 GL11.glColor3f(grey[0], grey[1], grey[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		                    GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.75f);
		                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                        GL11.glRotatef((LimbRotation)-50,1.0f,0.0f,0.0f);
		                        sphere.DrawSphere(0.25f, 32, 32); 

		                        //left low leg
		                   	 GL11.glColor3f(white[0], white[1], white[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.0f);
		                           // GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                            //GL11.glRotatef((-LimbRotation/2)+90,1.0f,0.0f,0.0f); 
		                           // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
		                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
		                            cylinder.DrawCylinder(0.15f,0.7f,32);

		                            // left foot
		                       	 GL11.glColor3f(grey[0], grey[1], grey[2]);
		                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
		                            GL11.glPushMatrix(); {
		                                GL11.glTranslatef(0.0f,0.0f,0.75f);
		                                sphere.DrawSphere(0.3f, 32, 32);  

		                            } GL11.glPopMatrix();
		                        } GL11.glPopMatrix();
		                    } GL11.glPopMatrix();
		                } GL11.glPopMatrix();
		            } GL11.glPopMatrix();

		            // pelvis


		            // right hip
		            GL11.glColor3f(grey[0], grey[1], grey[2]);
			           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
			            GL11.glPushMatrix(); {
			                GL11.glTranslatef(0.5f,-0.2f,0.0f);
			               
			                sphere.DrawSphere(0.25f, 32, 32); 


			                // right high leg
			           	 GL11.glColor3f(white[0], white[1], white[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(0.0f,0.0f,0.0f);
			                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
			                
			                    
			                    GL11.glRotatef((-LimbRotation)+120,1.0f,0.0f,0.0f); 
			                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
			                    cylinder.DrawCylinder(0.15f,0.7f,32);


			                    // right knee
			               	 GL11.glColor3f(grey[0], grey[1], grey[2]);
			                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
			                    GL11.glPushMatrix(); {
			                        GL11.glTranslatef(0.0f,0.0f,0.75f);
			                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
			                        GL11.glRotatef((-LimbRotation)-50,1.0f,0.0f,0.0f);
			                        sphere.DrawSphere(0.25f, 32, 32); 

			                        //right low leg
			                   	 GL11.glColor3f(white[0], white[1], white[2]);
			                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
			                        GL11.glPushMatrix(); {
			                            GL11.glTranslatef(0.0f,0.0f,0.0f);
			                           // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
			                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
			                            cylinder.DrawCylinder(0.15f,0.7f,32);

			                            // right foot
			                       	 GL11.glColor3f(grey[0], grey[1], grey[2]);
			                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
			                            GL11.glPushMatrix(); {
			                                GL11.glTranslatef(0.0f,0.0f,0.75f);
			                                sphere.DrawSphere(0.3f, 32, 32);  

			                            } GL11.glPopMatrix();
			                        } GL11.glPopMatrix();
			                    } GL11.glPopMatrix();
			                } GL11.glPopMatrix();
			            } GL11.glPopMatrix();
		        
		        } GL11.glPopMatrix();
		         
		    } 

		

	}
	
	
	
	
}
 
	/*
	 
	 
}

	*/
	 