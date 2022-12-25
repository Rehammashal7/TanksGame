/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Textures.Example1;

import Textures.Example1.AnimGLEventListener3.Directions;

public class bullet {

    Directions direction;
    int x, y;

    boolean fired = true;

//    public bullet(Directions direction, int x, int y) {
//        this.direction = direction;
//        this.fired = true;
//        this.x = x;
//        this.y = y;
//    }

    bullet(Directions direction, int x, int y) {
          this.direction = direction;
        this.fired = true;
        this.x = x;
        this.y = y;

    }

}
