/**
 * Code for the Wombot's LCD Display to be integrated later.
 *
 *@author Cenydd Stott
 *@version September 2009
 */


import javax.microedition.lcdui.Graphics;

public class WombotLCD
{

    static Graphics g = new Graphics();
    //int face = 1;

    /*
       private void drawDisplay(){
       g.drawRect(7,3,85,9);
       g.drawString("0",2,23,false);
       g.drawString("100",81,23,false);
       g.drawString("ENERGY",35,23,false);
       for(int i = 5; i < 10; i++){
       g.drawLine(9,i,(int)(10.00+0.08*(double)energyLevel.energy),i);
       }
       switch(face){
       case 1: this.drawNeutral();break;
       case 2: this.drawSmile();break;
       case 3: this.drawFrown();break;
       case 4: this.drawSleep();break;
       case 5: this.drawCrazy();break;
       default:break;
       }
       }
       */

    private static void drawEnergy(double energy) {
        g.drawRect(7,3,85,9);
        g.drawString("0",2,23,false);
        g.drawString("100",81,23,false);
        g.drawString("ENERGY",35,23,false);
        for(int i = 5; i < 11; i++){
            //g.drawLine(9,i,(int)(10.00+0.08*energy),i);
            g.drawLine(9,i,(int)(10.00+(85-7)*energy),i);
        }
    }

    public static void drawNeutral(double energy){
        drawEnergy(energy);
        g.drawLine(5,61,9,61);
        g.drawRect(3,55,3,3);
        g.drawRect(9,55,3,3);
    }

    public static void drawSmile(double energy){
        drawEnergy(energy);
        g.drawLine(5,61,9,61);
        g.drawLine(3,59,4,60);
        g.drawLine(11,59,10,60);
        g.drawRect(3,55,3,3);
        g.drawRect(9,55,3,3);
    }

    public static void drawFrown(double energy){
        drawEnergy(energy);
        g.drawLine(5,61,9,61);
        g.drawLine(3,62,4,63);
        g.drawLine(11,63,10,62);
        g.drawLine(3,55,5,57);
        g.drawLine(9,57,11,55);
    }

    public static void drawSleep(double energy){
        drawEnergy(energy);
        g.drawLine(5,60,9,60);
        g.drawLine(3,57,5,57);
        g.drawLine(9,57,11,57);
        g.drawString("z",10,56,false);
        g.drawString("Z",12,52,false);
    }

    public static void drawCrazy(double energy){
        drawEnergy(energy);
        g.drawRect(3,55,3,3);
        g.drawRect(10,55,2,2);
        g.drawRect(3,59,9,5);
    }

}
