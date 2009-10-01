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
//        g.drawRect(7,3,85,9);
//        g.drawString("0",2,23,false);
//        g.drawString("100",81,23,false);
//        g.drawString("ENERGY",35,23,false);
//        for(int i = 5; i < 10; i++){
//            //g.drawLine(9,i,(int)(10.00+0.08*energy),i);
//            g.drawLine(9,i,(int)(10.00+1*energy),i);
//        }
        g.drawString("WOMBOT",40,50);
        g.drawRect(5,5,90,20);
        g.drawString("0",2,30,false);
        g.drawString("100",81,30,false);
        g.drawString("ENERGY",30,30,false);
        g.fillRect(5, 5, (int)(energy*90), 20);
    }

    public static void drawNeutral(double energy){
        drawEnergy(energy);
        g.drawLine(10,59,19,59);
        g.drawRect(5,47,6,6);
        g.drawRect(19,47,6,6);
    }

    public static void drawSmile(double energy){
        drawEnergy(energy);
        g.drawLine(10,59,19,59);
        g.drawLine(6,55,9,58);
        g.drawLine(20,58,23,55);
        g.drawRect(5,47,6,6);
        g.drawRect(19,47,6,6);
    }

    public static void drawFrown(double energy){
        drawEnergy(energy);
        g.drawLine(10,59,19,59);
        g.drawLine(6,63,9,60);
        g.drawLine(20,60,23,63);
        g.drawLine(5,47,10,52);
        g.drawLine(19,52,24,47);
    }

    public static void drawSleep(double energy){
        drawEnergy(energy);
        g.drawLine(10,59,19,59);
        g.drawLine(5,52,10,52);
        g.drawLine(19,52,24,52);
        g.drawString("z",23,43,false);
        g.drawString("Z",26,40,false);
    }

    public static void drawCrazy(double energy){
        drawEnergy(energy);
        g.drawRect(5,47,6,6);
        g.drawRect(19,52,4,4);
        g.drawRect(4,55,19,8);
    }

}
