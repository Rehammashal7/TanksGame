package Textures.Example1;

import Textures.AnimListener;
import Textures.Example1.AnimGLEventListener3.Directions;
import Textures.TextureReader;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.*;

import java.util.BitSet;
import java.util.Random;
import javax.media.opengl.glu.GLU;

public class AnimGLEventListener3 extends AnimListener {
    DrowBlocks block;
ArrayList<DrowBlocks>blocks=new ArrayList<DrowBlocks>();
    
    enum Directions{
            up,down,left,right,up_left,up_right,down_left,down_right
    } 
 Directions direction=Directions.up;// 0=up ,1=right,2=down ,3=left
  boolean isDead=false;
 
 int animationIndex = 0;
    int maxWidth = 100;
    int maxHeight = 100;
    int x = maxWidth -10, y = maxHeight / 99;
    int enx=0,eny=0;
    Directions enmdir=Directions.up;
   
ArrayList<bullet>bullets=new ArrayList<bullet>();
ArrayList<enemy>enemies=new ArrayList<enemy>();
    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {"tank up.png","tank up.png","tank up.png","tank up.png","bullet.png","break_brick.jpg","solid_brick.jpg", "enemy up.png","index.jpg"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

       DrowBlock();
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();

        DrawBackground(gl);
        handleKeyPress();
        animationIndex = animationIndex % 4;

//        DrawGraph(gl);
  
           
                
                DrawSprite(gl, x,y, 1, 1,direction);
             //    DrawSprite(gl, enx,eny, 7, 1,Directions[getRandomDirection()]);
         
       for (int i = 0; i < blocks.size(); i++) {
                        if (blocks.get(i).isAlive) {
                            DrawSprite1(gl, blocks.get(i).x, blocks.get(i).y, 5, .5f);
                        }
                    }
        // DrawSprite1(gl, 70, 80, 5, 0.7f);
//          DrawSprite1(gl, 80, 75, 5, 0.7f);
//         DrawSprite1(gl, 73, 75, 6, 0.7f);
//          DrawSprite1(gl, 66, 75, 5, 0.7f);
//           DrawSprite1(gl, 50, 75, 5, 0.7f);
//           DrawSprite1(gl, 43, 75, 5, 0.7f);
//            DrawSprite1(gl, 7, 75, 5, 0.7f);
//             DrawSprite1(gl, 80, 81, 5, 0.7f);
//            DrawSprite1(gl, 66, 81, 5, 0.7f);
//             DrawSprite1(gl, 50, 81, 5, 0.7f);
//           DrawSprite1(gl, 50, 87, 6, 0.7f);
//            DrawSprite1(gl, 30, 92, 6, 0.7f);
//           DrawSprite1(gl, 30, 86, 6, 0.7f);
//           DrawSprite1(gl, 20, 70, 5, 0.7f);
//           DrawSprite1(gl, 20, 64, 5, 0.7f);
//            DrawSprite1(gl, 20, 58, 5, 0.7f);
//              DrawSprite1(gl, 80, 30, 5, 0.7f);
//           DrawSprite1(gl, 80, 24, 5, 0.7f);
//            DrawSprite1(gl, 80, 18, 5, 0.7f);
//              DrawSprite1(gl, 80, 12, 5, 0.7f);
//           DrawSprite1(gl, 80, 6, 5, 0.7f);
//            DrawSprite1(gl, 80, 0, 5, 0.7f);
//             DrawSprite1(gl, 60, 30, 5, 0.7f);
//           DrawSprite1(gl, 60, 24, 5, 0.7f);
//            DrawSprite1(gl, 60, 18, 5, 0.7f);
//              DrawSprite1(gl, 60, 12, 5, 0.7f);
//           DrawSprite1(gl, 60, 6, 5, 0.7f);
//            DrawSprite1(gl, 60, 0, 5, 0.7f);
//            
//              DrawSprite1(gl, 20, 28, 5, 0.7f);
//           DrawSprite1(gl, 20, 24, 5, 0.7f);
//            DrawSprite1(gl, 20, 18, 5, 0.7f);
//              DrawSprite1(gl, 20, 12, 5, 0.7f);
//           DrawSprite1(gl, 20, 6, 5, 0.7f);
//            DrawSprite1(gl, 20, 0, 5, 0.7f);
//            
//            
//            
//              DrawSprite1(gl, 14, 43, 5, 0.7f);
//           DrawSprite1(gl, 7, 43, 5, 0.7f);
//            DrawSprite1(gl, 0, 43, 5, 0.7f);
//              DrawSprite1(gl, 20, 35, 6, 0.7f);
//             
//             DrawSprite1(gl, 20, 51, 6, 0.7f);
//             
//               DrawSprite1(gl,73, 20, 6, 0.7f);
//              DrawSprite1(gl, 50, 35, 6, 0.7f);
//              DrawSprite1(gl, 80, 51, 6, 0.7f);
//            
//              
//              DrawSprite1(gl, 64, 60,5, 0.7f);
//           DrawSprite1(gl, 50, 60, 5, 0.7f);
//            DrawSprite1(gl, 57, 60, 5, 0.7f);
              
//         DrawSprite1(gl, 100, 80, 5, 0.7f);
//          DrawSprite1(gl, 70, 80, 5, 0.7f);
//         DrawSprite1(gl, 70, 80, 5, 0.7f);
//          DrawSprite1(gl, 70, 80, 5, 0.7f);
//         DrawSprite1(gl, 30, 40, 13, 1f);
//            DrawSprite1(gl, 30, 40, 13, 1f);
//             DrawSprite1(gl, 30, 40, 13, 1f);
//              DrawSprite1(gl, 30, 40, 13, 1f);
//      
            for(bullet bullet:bullets)   {
             if(bullet.fired){
          switch(bullet.direction){
        
            case up:bullet.y++;break;
            case right:bullet.x++;break;
            case down:bullet.y--;break;
            case left:bullet.x--;break;
              case up_left:bullet.y++;bullet.x--;break;
            case up_right:bullet.y++;bullet.x++;break;
            case down_right:bullet.y--;bullet.x++;break;
            case down_left:bullet.y--;bullet.x--;break;
          
        }
        
         DrawSprite(gl, bullet.x, bullet.y, 4, 0.3f,Directions.up);
    }}
            
           
            }     
        
       

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, int x, int y, int index, float scale,Directions dir) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On
int angle=0;
        switch(dir){
        
            case up:angle=0;break;
            case right:angle=-90;break;
            case down:angle=180;break;
            case left:angle=90;break;
              case up_left:angle=45;break;
            case up_right:angle=-45;break;
            case down_right:angle=-135;break;
            case down_left:angle=135;break;
            default:angle=0;
            
        }
        
        
        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 0.9, y / (maxHeight / 2.0) - 0.9, 0);
        gl.glScaled(0.1 * scale, 0.1 * scale, 1);
        gl.glRotated(angle,0, 0, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    
    
    
    
       public void DrawSprite1(GL gl, int x, int y, int index, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On
int angle=0;
     
        
        gl.glPushMatrix();
     gl.glTranslated(x / (maxWidth / 2.0) - 0.9, y / (maxHeight / 2.0) - 0.9, 0);
      gl.glScaled(0.1 * scale, 0.1 * scale, 1);
    //    gl.glRotated(angle,0, 0, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
      //Drow blocks
    public void DrowBlock(){
      block = new DrowBlocks(41, 30);
        blocks.add(block);
     block = new DrowBlocks(41, 20);
        blocks.add(block);
         block = new DrowBlocks(46, 20);
        blocks.add(block);
         block = new DrowBlocks(51, 30);
        blocks.add(block);
         block = new DrowBlocks(51, 20);
        blocks.add(block);
         block = new DrowBlocks(20, 60);
        blocks.add(block);
        block = new DrowBlocks(20, 66);
        blocks.add(block);
         block = new DrowBlocks(20, 72);
        blocks.add(block);
         block = new DrowBlocks(60, 50);
        blocks.add(block);
         block = new DrowBlocks(66, 50);
        blocks.add(block);
         block = new DrowBlocks(72, 50);
        blocks.add(block);
     block = new DrowBlocks(30, 80);
        blocks.add(block);
         block = new DrowBlocks(30, 86);
        blocks.add(block);
         block = new DrowBlocks(30, 92);
        blocks.add(block);
         block = new DrowBlocks(40, 80);
        blocks.add(block);
         block = new DrowBlocks(22, 60);
        blocks.add(block);
    
    
    }
 
    
    //move Roundom enemy
    
    
    
    public int getRandomDirection() {
        int random = new Random().nextInt(4);
        return random;
    }
    
    
    
    
    
    

    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture.length-1]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    /*
     * KeyListener
     */
    
  
    public void handleKeyPress() {
         
        if(isKeyPressed(KeyEvent.VK_SPACE)){
            
           bullets.add(new bullet(direction,x,y)) ;
      
        
        }
        
        
 if (isKeyPressed(KeyEvent.VK_LEFT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
            if (x > 0) {
                x--;
            } if (y > 0) {
                y--;
            }
            direction=Directions.down_left;
            animationIndex++;
        }
       else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
            if (x < maxWidth - 10) {
                x++;
            }if (y > 0) {
                y--;
            }
             direction=Directions.down_right;
            animationIndex++;
        }
     
     else   if (isKeyPressed(KeyEvent.VK_UP)&&isKeyPressed(KeyEvent.VK_LEFT)) {
            if (y < maxHeight - 10) {
                y++;
            } if (x > 0) {
                x--;
            }
            direction=Directions.up_left;
            animationIndex++;
        }
       
       else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_UP)) {
            if (x < maxWidth - 10) {
                x++;
            } if (y < maxHeight - 10) {
                y++;
            }
             direction=Directions.up_right;
            animationIndex++;
        }
       else if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (y > 0) {
                y--;
            }
            direction=Directions.down;
            animationIndex++;
        }
     else   if (isKeyPressed(KeyEvent.VK_UP)) {
            if (y < maxHeight - 10) {
                y++;
            }
            direction=Directions.up;
            animationIndex++;
        }
    else if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < maxWidth - 10) {
                x++;
            } 
             direction=Directions.right;
            animationIndex++;
        }
  
     else   if (isKeyPressed(KeyEvent.VK_LEFT)) {
             if (x > 0) {
                x--;
            }
            direction=Directions.left;
            animationIndex++;
        }
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    public static void main(String[] args) {
        new Anim(new AnimGLEventListener3());
    }
}
