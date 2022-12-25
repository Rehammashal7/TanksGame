/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Textures.Example1;

import java.util.Random;

/**
 *
 * @author TOSHIBA
 */
public class enemy {
   
     Random random = new Random();
    int x, y, direction , xbullet ,ybullet ,bulletDirection ;
    int bulletTrigger ;
    boolean isAlive;
    boolean collesion;

    public enemy() {
        x = 20;
        y = 10;
        isAlive = true;
        collesion =false;
        direction = random.nextInt(4);
        bulletTrigger = 1;
        
    }
    
   

}
