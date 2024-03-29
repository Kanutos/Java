/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio018;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;


public class FroggyCrossTheRoad extends JFrame implements Runnable{
    int velocidad = 10;
    Thread animacion;
    Image imagen; 
    Graphics noseve;
    Rana rana;
    boolean gameOver = false;
    boolean win = false;
    boolean jugando;
    List<Coche> coches = new ArrayList<>();
    int cantidadCoches = 100;
    int score;
    int scoreMax;
    
   public static void main(String arg[]){
       FroggyCrossTheRoad app = new FroggyCrossTheRoad();
    }
    public FroggyCrossTheRoad(){
        init();
        start();
    }
    public void init(){
        score = 0;
        scoreMax = 0;
        pack();
        setSize(600, 600);
        setVisible(true);
        imagen = this.createImage(600, 600); 
        noseve = imagen.getGraphics(); 
        rana = new Rana();
    }
    public void start(){
        animacion = new Thread(this);
         animacion.start();
    }
    
     public void paint(Graphics g){
       paintBoard();
       rana.paint(noseve);
       pintarCoches();
       paintScore();
       paintGameOver();
       paintWin();
       tituloInicio();
       g.drawImage(imagen, 0, 0, this);
    }

     public void update(Graphics g){ //override, lo sobreescribimos eliminando la linea de borrar
        paint(g);
    }
    public void run(){
        while(true){
            loDeLaRana();   
            loDeLosCoches();
            repaint();
            try {
                Thread.sleep(velocidad);
            } catch (InterruptedException ex){
            }
        }
    }
    
    public boolean keyDown(Event ev, int tecla){
       if(!win || !gameOver){
            rana.update(tecla);
       }
        if(tecla == 10){//intro
            startNewGame();
            return true;
            }
        return false;
    }
    public void startNewGame(){
        if(scoreMax < score)
            scoreMax = score;
        score = 0;
        cantidadCoches = 100;
        rana = new Rana();
        coches = new ArrayList<Coche>();
        iniciarCoches();
        paintBoard();
        gameOver = false;
        win = false;
        if(!animacion.isAlive())
            animacion.start();  
        else
            animacion.interrupt();
    }
    private void tituloInicio() {
        if(!animacion.isAlive()){
            noseve.setColor(Color.BLACK);
            noseve.setFont(new Font("Arial", Font.BOLD, 20));
            noseve.drawString("Para jugar o para ", 205, 300);
            noseve.drawString("reiniciar pulsa ENTER", 200, 325);
        }
    }

    private void iniciarCoches() {
        for(int i = 0; i < 6; i++)
            coches.add(new Coche(i));
    }
    
    private void loDeLaRana() {
        rana.update();
        if(rana.y < 0 - rana.height){
            rana.y = 590;
            score++;
            cantidadCoches -= 2;
        }
        if(cantidadCoches <= 10)
            win();
    }
    
    private void pintarCoches() {
        if(!coches.isEmpty())
            for(Coche co: coches)
                co.paint(noseve);
    }
    private void loDeLosCoches() {
        if((int)(Math.random() * cantidadCoches) == 0)
            coches.add(new Coche((int)(Math.random()*6)));
        if(!coches.isEmpty()){
            for(Coche co : coches){
                co.update();
                if(co.intersects(rana)){
                    rana.vida--;
                    gameOver();
                }
                if(co.x <= -250 || co.x >=850){
                    coches.remove(co);
                    break;
                }
                co.cocheChocon(coches);
            }
        }
    }
    private void paintBoard() {
        this.setSize(600, 600);
        imagen = this.createImage(600, 600); 
        noseve = imagen.getGraphics();
        noseve.setColor(Color.CYAN);
        noseve.fillRect(0, 0, 600, 600);
        noseve.setColor(Color.LIGHT_GRAY);
        noseve.fillRect(0, 75, 600, 200);
        noseve.fillRect(0, 350, 600, 180);
        noseve.setColor(Color.WHITE);
        noseve.fillRect(0, 75, 600, 15);
        noseve.fillRect(0, 260, 600, 15);
        noseve.fillRect(0, 340, 600, 15);
        noseve.fillRect(0, 520, 600, 15);
        for(int i = 0; i < 10; i++){
            noseve.fillRect((i*60)+5, 135, 50, 15);
            noseve.fillRect((i*60)+5, 200, 50, 15);
            noseve.fillRect((i*60)+5, 400, 50, 15);
            noseve.fillRect((i*60)+5, 465, 50, 15);
        }
        noseve.setColor(Color.BLACK);
        noseve.drawRect(0, 75, 600, 15);
        noseve.drawRect(0, 260, 600, 15);
        noseve.drawRect(0, 340, 600, 15);
        noseve.drawRect(0, 520, 600, 15);
    }
   private void paintScore() {
        noseve.setColor(Color.BLACK);
        noseve.setFont(new Font("Arial", Font.BOLD, 14));
        noseve.drawString("SCORE: " + Integer.toString(score), 20, 580);
        noseve.drawString("MAX: " + Integer.toString(scoreMax), 20, 560);
    }
    private void gameOver() {
          gameOver = true;
          repaint();
         
    }
    private void paintGameOver() {
        if(gameOver){
            noseve.setFont(new Font("Arial", Font.BOLD, 20));
            noseve.setColor(Color.BLACK);
            noseve.drawString("GAME OVER ", 250, 300);
            noseve.drawString("Para reiniciar pulsa ENTER", 225, 325);
        }
    }
    private void win() {
        win = true;
        repaint();
        animacion.interrupt();
    }
    private void paintWin() {
        if(win){
            noseve.setFont(new Font("Arial", Font.BOLD, 20));
            noseve.setColor(Color.BLACK);
            noseve.drawString("HAS GANADO ", 250, 300);
            noseve.drawString("Para reiniciar pulsa ENTER", 225, 325);
        }
    }
}
