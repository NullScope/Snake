import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends Canvas implements KeyListener{
	private BufferedImage grassIMG = loadImage("grass3.png");
	
	//PLAYER BODY ANGLES
	private BufferedImage bodyLEFTIMG = loadImage("bodyLEFT.png");
	private BufferedImage bodyRIGHTIMG = loadImage("bodyRIGHT.png");
	private BufferedImage bodyDOWNIMG = loadImage("bodyDOWN.png");
	private BufferedImage bodyUPIMG = loadImage("bodyUP.png");
	
	//ENEMY BODY ANGLES
	private BufferedImage enemyLEFTIMG = loadImage("enemyLEFT.png");
	private BufferedImage enemyRIGHTIMG = loadImage("enemyRIGHT.png");
	private BufferedImage enemyDOWNIMG = loadImage("enemyDOWN.png");
	private BufferedImage enemyUPIMG = loadImage("enemyUP.png");
	
	//PLAYER HEAD ANGLES
	private BufferedImage headLEFTIMG = loadImage("headLEFT.png");
	private BufferedImage headRIGHTIMG = loadImage("headRIGHT.png");
	private BufferedImage headDOWNIMG = loadImage("headDOWN.png");
	private BufferedImage headUPIMG = loadImage("headUP.png");
	
	//ENEMY HEAD ANGLES
	private BufferedImage enemyHeadLEFTIMG = loadImage("enemyHEADLEFT.png");
	private BufferedImage enemyHeadRIGHTIMG = loadImage("enemyHEADRIGHT.png");
	private BufferedImage enemyHeadDOWNIMG = loadImage("enemyHEADDOWN.png");
	private BufferedImage enemyHeadUPIMG = loadImage("enemyHEADUP.png");
	
	//PLAYER TAIL ANGLES
	private BufferedImage tailLEFTIMG = loadImage("tailLEFT.png");
	private BufferedImage tailRIGHTIMG = loadImage("tailRIGHT.png");
	private BufferedImage tailDOWNIMG = loadImage("tailDOWN.png");
	private BufferedImage tailUPIMG = loadImage("tailUP.png");
	
	//ENEMY TAIL ANGLES
	private BufferedImage enemyTailLEFTIMG = loadImage("enemyTAILLEFT.png");
	private BufferedImage enemyTailRIGHTIMG = loadImage("enemyTAILRIGHT.png");
	private BufferedImage enemyTailDOWNIMG = loadImage("enemyTAILDOWN.png");
	private BufferedImage enemyTailUPIMG = loadImage("enemyTAILUP.png");
	
	//PLAYER BODY CURVES
	private BufferedImage bodyCurveUpLeftIMG = loadImage("bodyCURVEUPLEFT.png");
	private BufferedImage bodyCurveUpRightIMG = loadImage("bodyCURVEUPRIGHT.png");
	private BufferedImage bodyCurveDownLeftIMG = loadImage("bodyCURVEDOWNLEFT.png");
	private BufferedImage bodyCurveDownRightIMG = loadImage("bodyCURVEDOWNRIGHT.png");
	
	//ENEMY BODY CURVES
	private BufferedImage enemyBodyCurveUpLeftIMG = loadImage("enemyCURVEUPLEFT.png");
	private BufferedImage enemyBodyCurveUpRightIMG = loadImage("enemyCURVEUPRIGHT.png");
	private BufferedImage enemyBodyCurveDownLeftIMG = loadImage("enemyCURVEDOWNLEFT.png");
	private BufferedImage enemyBodyCurveDownRightIMG = loadImage("enemyCURVEDOWNRIGHT.png");
	
	private BufferedImage powerIMG = loadImage("speed.png");
	private BufferedImage vinesIMG = loadImage("vines.png");
	private BufferedImage limaoIMG = loadImage("limao.png");
	private BufferedImage melanIMG = loadImage("melancia.png");
	private BufferedImage macaIMG = loadImage("maca.png");
	
	private static final int largura = 40;
	private static final int altura = 40;
	private static final int escala = 15;
	private static final int default_speed = 50;
	private static int score = 0;
	private static int points = 0;
	private static int campaignLevel = 1;
	private static int speed = default_speed;
	private static int move = 4;
	private static int enemyMove = 4;
	private static int check_move = 0;
	private static int enemyScore = 0;
	private static int count = 0;
	private static int revert = 0;
	private static int enemyCount = 0;
	private static int enemyRevert = 0;
	private static int paused = 0;
	private static int i = 0;
	private static int last = 0;
	private static int enemyLast = 0;
	private static int percentage = 0;
	private static int initial_percentage = 5;
	private static int counter = 0;
	private static int markedI = 0;
	private static int option = 0;
	private static int enemyFound = 0;
	private static int loadType = 0;
	private static int gameType = 0;
	private static JOptionPane menu = new JOptionPane();
	private static JButton play = new JButton("Play Campaign");
	private static JButton custom = new JButton("Custom Levels");
	private static File level;
	
	private static LinkedList<Body> cobra = new LinkedList<Body>();
	private static LinkedList<Body> enemy = new LinkedList<Body>();

	private static char[][] matrix = new char[largura][altura];
	private static Object[] options = {"Campaign","Custom Levels", "Exit"};
	private static Vector<Fruit> vecFruit = new Vector<Fruit>();
	
	private static JFrame janela = new JFrame();
	private static JPanel painel = (JPanel)janela.getContentPane();
	private static JPanel painel2 = new JPanel();

	public Main(){
		optionMenu("Welcome to Snake!");
		options[0] = "Continuar Campanha";
		janela.setTitle("SNAKE || PLAYER SCORE: "+score+" || ENEMY SCORE: "+enemyScore);
		painel.setBounds(0, 0, largura, altura);
		painel.add(this);
		janela.setBounds(0,0,((escala*largura)+escala),(int) (escala*(altura+2.5)));
		janela.setVisible(true);
		addKeyListener(this);

		requestFocus();	
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@SuppressWarnings("static-access")
	private void optionMenu(String message) {
		option = menu.showOptionDialog(null, message, "Snake", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
		cobra.removeAll(cobra);
		enemy.removeAll(enemy);
		vecFruit.removeAllElements();
		enemyFound = 0;
		enemyCount = 0;
		count = 0;
		score = 0;
		if(option == JOptionPane.YES_OPTION){
			if(gameType == 0){
				loadType = 0;
			}else{
				loadType = 1;
			}

			loadLevel();
		}else{
			if(option == JOptionPane.NO_OPTION){
				gameType = 1;
				loadType = 1;
				JFileChooser fc = new JFileChooser();
				
				option = fc.showOpenDialog(Main.this);
				if(option == JFileChooser.APPROVE_OPTION){
					level = fc.getSelectedFile();
					loadLevel();
				}
			}else{
				if(option == JOptionPane.CANCEL_OPTION){
					System.exit(0);
				}else{
					System.exit(0);
				}
			}
		}
		enemyScore = 0;
	}

	public void game() {
		while (isVisible()) {
			if(paused == 0){
				if(score < points){
					if (!moveBody() || cobra.size() < 2 || enemyScore >= points) {
						if(cobra.size() < 2){
							options[0] = "Try again";
							optionMenu("Gameover! \nSCORE: "+score);
						}else{
							
							options[0] = "Try again";
							if(enemyScore >= points){
								optionMenu("Gameover! The enemy snake won\nSCORE: "+score);
							}else{
								optionMenu("Gameover! You hit a wall or the enemy snake\nSCORE: "+score);
							}
						}
					}else{
						if(enemyFound == 1){
							moveEnemy();
						}
					}
				}else{
					options[0] = "Next Level";
					loadType = 0;
					campaignLevel += 1;
					optionMenu("Congratualations");
				}
				paint(getGraphics());
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean moveBody() {

		Body head = cobra.getFirst();
		Body tail = cobra.getLast();
		
		matrix[head.getX()][head.getY()] = 'S';
		switch(move){
			case 1:{
				//UP
				if(head.getY() != 0){
					head = new Body(cobra.getFirst().getX(), cobra.getFirst().getY()-1);
					cobra.addFirst(head);
				}else{
					head = new Body(cobra.getFirst().getX(), 39);
					cobra.addFirst(head);
				}
				break;
			}
			case 2:{
				//DOWN
				if(head.getY() != 39){
					head = new Body(cobra.getFirst().getX(), cobra.getFirst().getY()+1);
					cobra.addFirst(head);
				}else{
					head = new Body(cobra.getFirst().getX(), 0);
					cobra.addFirst(head);
				}
				break;
			}
			case 3:{
				//RIGHT
				if(head.getX() != 39){
					head = new Body(cobra.getFirst().getX()+1, cobra.getFirst().getY());
					cobra.addFirst(head);
				}else{
					head = new Body(0, cobra.getFirst().getY());
					cobra.addFirst(head);
				}
				break;
			}
			case 4:{
				//LEFT
				if(head.getX() != 0){
					head = new Body(cobra.getFirst().getX()-1, cobra.getFirst().getY());
					cobra.addFirst(head);
				}else{
					head = new Body(39, cobra.getFirst().getY());
					cobra.addFirst(head);
				}
				break;
			}
		}

		if(matrix[cobra.getFirst().getX()][cobra.getFirst().getY()] != 'w' && matrix[cobra.getFirst().getX()][cobra.getFirst().getY()] != 'S' && matrix[cobra.getFirst().getX()][cobra.getFirst().getY()] != 'T' && matrix[cobra.getFirst().getX()][cobra.getFirst().getY()] != 'K' && matrix[cobra.getFirst().getX()][cobra.getFirst().getY()] != 'B' && matrix[cobra.getFirst().getX()][cobra.getFirst().getY()] != 'D'){
			switch(matrix[cobra.getFirst().getX()][cobra.getFirst().getY()]){
				case('F'):{
					createFruit(1);
					deleteFruit(cobra.getFirst().getX(), cobra.getFirst().getY());
					count += 1;
					score += 1;
					janela.setTitle("SNAKE || PLAYER SCORE: "+score+" || ENEMY SCORE: "+enemyScore);
					break;
				}
				case('M'):{
					final Timer clock=new javax.swing.Timer((int) Math.floor(3000), null);
					
					clock.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							counter += 1;
							percentage = counter * initial_percentage;
							if(Math.floor(Math.random()*100) <= percentage){
								
								createFruit(2);
								clock.stop();
								clock.removeActionListener(null);
								counter = 0;
							}else{
								clock.start();
							}
						}
					});
					clock.start();
					deleteFruit(cobra.getFirst().getX(), cobra.getFirst().getY());
					count += 2;
					score += 2;
					janela.setTitle("SNAKE || PLAYER SCORE: "+score+" || ENEMY SCORE: "+enemyScore);
					break;
				}
				case('L'):{
					final Timer clock=new javax.swing.Timer((int) Math.floor(4000), null);
					
					clock.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							counter += 1;
							percentage = counter * initial_percentage;
							if(Math.floor(Math.random()*100) <= percentage){
								createFruit(3);
								clock.stop();
								clock.removeActionListener(null);
								counter = 0;
							}else{
								clock.start();
							}
						}
					});
					clock.start();
					revert += 1;
					score -= 1;
					janela.setTitle("SNAKE || PLAYER SCORE: "+score+" || ENEMY SCORE: "+enemyScore);
					break;
				}
				case('P'):{

					break;
				}
			}
			if(count == 0){
				cobra.removeLast();
			}else
				count -= 1;
			
			if(revert > 0){
				matrix[tail.getX()][tail.getY()]='_';
				tail = cobra.getLast();
				cobra.removeLast();
				revert -= 1;
			}
			
			matrix[head.getX()][head.getY()]='H';
			matrix[tail.getX()][tail.getY()]='_';
			paintBackground(getGraphics(), tail.getX(), tail.getY());
			
			tail = cobra.getLast();
			
			matrix[tail.getX()][tail.getY()]='T';
			
			check_move = 0;
			return true;
		}else return false;
	}

	private void moveEnemy() {
		Body head = enemy.getFirst();
		Body tail = enemy.getLast();
		
		matrix[head.getX()][head.getY()] = 'B';
		switch(enemyMove){
			case 1:{
				//UP
				if(head.getY() != 0){
					head = new Body(enemy.getFirst().getX(), enemy.getFirst().getY()-1);
					enemy.addFirst(head);
				}else{
					head = new Body(enemy.getFirst().getX(), 39);
					enemy.addFirst(head);
				}
				break;
			}
			case 2:{
				//DOWN
				if(head.getY() != 39){
					head = new Body(enemy.getFirst().getX(), enemy.getFirst().getY()+1);
					enemy.addFirst(head);
				}else{
					head = new Body(enemy.getFirst().getX(), 0);
					enemy.addFirst(head);
				}
				break;
			}
			case 3:{
				//RIGHT
				if(head.getX() != 39){
					head = new Body(enemy.getFirst().getX()+1, enemy.getFirst().getY());
					enemy.addFirst(head);
				}else{
					head = new Body(0, enemy.getFirst().getY());
					enemy.addFirst(head);
				}
				break;
			}
			case 4:{
				//LEFT
				if(head.getX() != 0){
					head = new Body(enemy.getFirst().getX()-1, enemy.getFirst().getY());
					enemy.addFirst(head);
				}else{
					head = new Body(39, enemy.getFirst().getY());
					enemy.addFirst(head);
				}
				break;
			}
		}

		if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()] != 'D'){
			switch(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()]){
				case('F'):{
					createFruit(1);
					deleteFruit(enemy.getFirst().getX(), enemy.getFirst().getY());
					enemyCount += 1;
					enemyScore += 1;
					janela.setTitle("SNAKE || PLAYER SCORE: "+score+" || ENEMY SCORE: "+enemyScore);
					break;
				}
				case('M'):{
					final Timer clock=new javax.swing.Timer((int) Math.floor(3000), null);
					
					clock.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							counter += 1;
							percentage = counter * initial_percentage;
							if(Math.floor(Math.random()*100) <= percentage){
								
								createFruit(2);
								clock.stop();
								clock.removeActionListener(null);
								counter = 0;
							}else{
								clock.start();
							}
						}
					});
					clock.start();
					deleteFruit(enemy.getFirst().getX(), enemy.getFirst().getY());
					
					enemyCount += 2;
					enemyScore += 2;
					janela.setTitle("SNAKE || PLAYER SCORE: "+score+" || ENEMY SCORE: "+enemyScore);
					break;
				}
				case('L'):{
					final Timer clock=new javax.swing.Timer((int) Math.floor(4000), null);
					
					clock.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							counter += 1;
							percentage = counter * initial_percentage;
							if(Math.floor(Math.random()*100) <= percentage){
								createFruit(3);
								clock.stop();
								clock.removeActionListener(null);
								counter = 0;
							}else{
								clock.start();
							}
						}
					});
					clock.start();
					enemyRevert += 1;
					enemyScore -= 1;
					janela.setTitle("SNAKE || PLAYER SCORE: "+score+" || ENEMY SCORE: "+enemyScore);
					break;
				}
			}
			if(enemyCount == 0){
				enemy.removeLast();
			}else
				enemyCount -= 1;
			
			if(enemyRevert > 0){
				matrix[tail.getX()][tail.getY()]='_';
				tail = enemy.getLast();
				enemy.removeLast();
				enemyRevert -= 1;
			}
			
			matrix[head.getX()][head.getY()]='K';
			matrix[tail.getX()][tail.getY()]='_';
			paintBackground(getGraphics(), tail.getX(), tail.getY());
			tail = enemy.getLast();

			
			matrix[tail.getX()][tail.getY()]='D';
			closestFruit();
			return;
		}
	}

	private void closestFruit() {
		int currentX = 0;
		int currentY = 0;
		int total = 20000000;
		@SuppressWarnings("unused")
		int temp = (int) Math.floor((Math.random()*2)+1);
		
		for (int i = 0; i < vecFruit.size(); i++) {
			currentX = enemy.getFirst().getX()-vecFruit.get(i).getX();
			currentY = enemy.getFirst().getY()-vecFruit.get(i).getY();
			if(currentX < 0){
				currentX = currentX*-1;
			}
			if(currentY < 0){
				currentY = currentY*-1;
			}
			if(currentX+currentY < total){
				total = currentX+currentY;
				markedI = i;
			}
		}
		
		currentX = enemy.getFirst().getX()-vecFruit.get(markedI).getX();
		currentY = enemy.getFirst().getY()-vecFruit.get(markedI).getY();
		if(currentY > 0){
			if(currentX == 0){
				switch(enemyMove){
					case(1):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}
							}
						}
					break;
					}
					case(2):{
						if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
							enemyMove = 4;
						}else{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 3;
								}
							}
						}
					break;
					}
					case(3):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 3;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 2;
								}
							}
						}
					break;
					}
					case(4):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 2;
								}
							}
						}
					break;
					}
				}
			}
			if(currentX > 0){
				switch(enemyMove){
					case(1):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}
							}
						}
					break;
					}
					case(2):{
						if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
							enemyMove = 4;
						}else{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}
							}
						}
					break;
					}
					case(3):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 3;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 2;
								}
							}
						}
					break;
					}
					case(4):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 2;
								}
							}
						}
					break;
					}
				}
			}
			if(currentX < 0){
				switch(enemyMove){
					case(1):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}
							}
						}
					break;
					}
					case(2):{
						if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
							enemyMove = 3;
						}else{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}
							}
						}
					break;
					}
					case(3):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 3;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 2;
								}
							}
						}
					break;
					}
					case(4):{
						if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
							enemyMove = 1;
						}else{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 2;
								}
							}
						}
					break;
					}
				}
			}
		}else{
			if(currentY < 0){
				if(currentX == 0){
					switch(enemyMove){
						case(1):{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
									enemyMove = 1;
								}else{
									if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 3;
									}
								}
							}
						break;
						}
						case(2):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}else{
									if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 3;
									}
								}
							}
						break;
						}
						case(3):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
										enemyMove = 1;
									}
								}
							}
						break;
						}
						case(4):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
										enemyMove = 1;
									}
								}
							}
						break;	
						}
					}
				}
				if(currentX < 0){
					switch(enemyMove){
						case(1):{
							if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 3;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
									enemyMove = 1;
								}else{
									if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 4;
									}
								}
							}
						break;
						}
						case(2):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}else{
									if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 3;
									}
								}
							}
						break;
						}
						case(3):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
										enemyMove = 1;
									}
								}
							}
						break;
						}
						case(4):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
										enemyMove = 1;
									}
								}
							}
						break;	
						}
					}
				}
				if(currentX > 0){
					switch(enemyMove){
						case(1):{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D'){
									enemyMove = 1;
								}else{
									if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D'){
										enemyMove = 3;
									}
								}
							}
						break;
						}
						case(2):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}else{
									if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 3;
									}
								}
							}
						break;
						}
						case(3):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
										enemyMove = 1;
									}
								}
							}
						break;
						}
						case(4):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
								enemyMove = 2;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
										enemyMove = 1;
									}
								}
							}
						break;	
						}
					}
				}
			}
			if(currentY == 0){
				if(currentX > 0){
					switch(enemyMove){
						case(1):{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
									enemyMove = 1;
								}else{
									if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 4;
									}
								}
							}
						break;
						}
						case(2):{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 2;
								}else{
									if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 3;
									}
								}
							}
						break;
						}
						case(3):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
								enemyMove = 1;
							}else{
								if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 3;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
										enemyMove = 2;
									}
								}
							}
						break;
						}
						case(4):{
							if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 4;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
									enemyMove = 1;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
										enemyMove = 2;
									}
								}
							}
						break;	
						}
					}
				}
				if(currentX < 0){
					switch(enemyMove){
						case(1):{
							if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 3;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
									enemyMove = 1;
								}else{
									if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 4;
									}
								}
							}
						break;
						}
						case(2):{
							if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 3;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
									enemyMove = 2;
								}else{
									if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
										enemyMove = 4;
									}
								}
							}
						break;
						}
						case(3):{
							if(matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()+1][enemy.getFirst().getY()] != 'T'){
								enemyMove = 3;
							}else{
								if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
									enemyMove = 1;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
										enemyMove = 2;
									}
								}
							}
						break;
						}
						case(4):{
							if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()-1] != 'T'){
								enemyMove = 1;
							}else{
								if(matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'w' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'B' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'D' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'H' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'S' && matrix[enemy.getFirst().getX()-1][enemy.getFirst().getY()] != 'T'){
									enemyMove = 4;
								}else{
									if(matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'w' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'B' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'D' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'H' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'S' && matrix[enemy.getFirst().getX()][enemy.getFirst().getY()+1] != 'T'){
										enemyMove = 2;
									}
								}
							}
						break;	
					}
				}
				}
			}
		}
	}

	private void deleteFruit(int x, int y) {
		for (int i = 0; i < vecFruit.size(); i++) {
			if(x == vecFruit.get(i).getX() && y == vecFruit.get(i).getY()){
				vecFruit.remove(i);
			}
		}
	}
	
	private void createFruit(int i) {
		int temp;
		int temp2;
		temp = (int) Math.floor((Math.random()*38)+1);
		temp2 = (int) Math.floor((Math.random()*38)+1);
		
		if(matrix[temp][temp2]=='w' || matrix[temp][temp2]== 'S' || matrix[temp][temp2]== 'H' || matrix[temp][temp2]== 'T' || matrix[temp][temp2]== 'K' || matrix[temp][temp2]== 'B' || matrix[temp][temp2]== 'D' || matrix[temp][temp2]== 'F' || matrix[temp][temp2]== 'M' || matrix[temp][temp2]== 'L' ){
			createFruit(i);
		}else{
			switch(i){
				case(1):{
					matrix[temp][temp2] = 'F';
					vecFruit.add(new Fruit(temp, temp2));
					break;
				}
				case(2):{
					matrix[temp][temp2] = 'M';
					vecFruit.add(new Fruit(temp, temp2));
					break;
				}
				case(3):{
					matrix[temp][temp2] = 'L';
					vecFruit.add(new Fruit(temp, temp2));
					break;
				}
			}
		}
	}

	private void loadLevel() {
		String ultimoSTR = "";
		String penultimoSTR = "";
		String antepenultimoSTR = "";
		BufferedReader temp = null;
		int y = 0;
		try {
			
			if(loadType == 0){
				temp = new BufferedReader(new FileReader(campaignLevel+".txt"));
			}else{
				temp = new BufferedReader(new FileReader(level));
			}

	        String str;
	        while((str = temp.readLine()) != null){
	        	antepenultimoSTR = penultimoSTR;
	        	penultimoSTR = ultimoSTR;
	        	ultimoSTR = str;
	        	if(y < 40){
		        	for(int x = 0; x<largura; x++){	
		    			matrix[x][y] = str.charAt(x);
		    			if(matrix[x][y] == 'H'){
		    				cobra.addFirst(new Body(x, y));
		    			}else{
		    				if(matrix[x][y]=='S'){
		    					cobra.addLast(new Body(x, y));
		    				}else{
		    					if(matrix[x][y]=='T'){
		    						cobra.addLast(new Body(x, y));
		    					}
		    				}
		    			}
		    			if(matrix[x][y] == 'K'){
		    				enemy.addFirst(new Body(x, y));
		    				enemyFound = 1;
		    			}else{
		    				if(matrix[x][y]=='B'){
		    					enemy.addLast(new Body(x, y));
		    				}else{
		    					if(matrix[x][y]=='D'){
		    						enemy.addLast(new Body(x, y));
		    					}
		    				}
		    			}
		    			if(matrix[x][y] == 'F'){
		    				vecFruit.add(new Fruit(x, y));
		    			}else{
		    				if(matrix[x][y]=='M'){
		    					vecFruit.add(new Fruit(x, y));
		    				}
		    			}
		        	}
		        	
		        	y+=1;
	        	}
	        }
	        try{
	        	if(enemyFound == 0){
	        		 points = Integer.parseInt(penultimoSTR);
	 		        switch(ultimoSTR){
	 		        	case("^"):{
	 		        		move = 1;
	 		        		break;
	 		        	}
	 		        	case("/"):{
	 		        		move = 2;
	 		        		break;
	 		        	}
	 		        	case(">"):{
	 		        		move = 3;
	 		        		break;
	 		        	}
	 		        	case("<"):{
	 		        		move = 4;
	 		        		break;
	 		        	}
	 		        }
	        	}else{
	        		 points = Integer.parseInt(antepenultimoSTR);
	        		switch(penultimoSTR){
	 		        	case("^"):{
	 		        		move = 1;
	 		        		break;
	 		        	}
	 		        	case("/"):{
	 		        		move = 2;
	 		        		break;
	 		        	}
	 		        	case(">"):{
	 		        		move = 3;
	 		        		break;
	 		        	}
	 		        	case("<"):{
	 		        		move = 4;
	 		        		break;
	 		        	}
	        		}
	        		
	        		switch(ultimoSTR){
	 		        	case("^"):{
	 		        		enemyMove = 1;
	 		        		break;
	 		        	}
	 		        	case("/"):{
	 		        		enemyMove = 2;
	 		        		break;
	 		        	}
	 		        	case(">"):{
	 		        		enemyMove = 3;
	 		        		break;
	 		        	}
	 		        	case("<"):{
	 		        		enemyMove = 4;
	 		        		break;
	 		        	}
	        		}
	        	}
	        }catch (Exception e) {
	        	
			}
		        temp.close();
	    } 
	    catch (IOException e){
	    	e.printStackTrace();
	    }
	}


	public void paint(Graphics g) {
		last = cobra.size()-1;
		enemyLast = enemy.size()-1;
		
		//PAINT PLAYER
		for (i = 0; i < cobra.size(); i++) {
			
			if(i == 0){
				switch(move){
					case(1):{
						paintHeadUP(g, cobra.get(i).getX(), cobra.get(i).getY());
					break;
					}
					case(2):{
						paintHeadDOWN(g, cobra.get(i).getX(), cobra.get(i).getY());
					break;
					}
					case(3):{
						paintHeadRIGHT(g, cobra.get(i).getX(), cobra.get(i).getY());
					break;
					}
					case(4):{
						paintHeadLEFT(g, cobra.get(i).getX(), cobra.get(i).getY());
					break;
					}
				}
			}
			if(i >= 1 && i < last){
				if(((cobra.get(i).getX() - cobra.get(i-1).getX() == 1 && cobra.get(i).getY() - cobra.get(i-1).getY() == 0) && (cobra.get(i).getX() - cobra.get(i+1).getX() == 0 && cobra.get(i).getY() - cobra.get(i+1).getY() == -1)) || ((cobra.get(i).getX() - cobra.get(i-1).getX() == 0 && cobra.get(i).getY() - cobra.get(i-1).getY() == -1) && (cobra.get(i).getX() - cobra.get(i+1).getX() == 1 && cobra.get(i).getY() - cobra.get(i+1).getY() == 0))){
					paintBodyCurveUPLEFT(g, cobra.get(i).getX(), cobra.get(i).getY());
				}else{
					if(((cobra.get(i).getX() - cobra.get(i-1).getX() == -1 && cobra.get(i).getY() - cobra.get(i-1).getY() == 0) && (cobra.get(i).getX() - cobra.get(i+1).getX() == 0 && cobra.get(i).getY() - cobra.get(i+1).getY() == -1)) || ((cobra.get(i).getX() - cobra.get(i-1).getX() == 0 && cobra.get(i).getY() - cobra.get(i-1).getY() == -1) && (cobra.get(i).getX() - cobra.get(i+1).getX() == -1 && cobra.get(i).getY() - cobra.get(i+1).getY() == 0))){
						paintBodyCurveUPRIGHT(g, cobra.get(i).getX(), cobra.get(i).getY());
					}else{
						if(((cobra.get(i).getX() - cobra.get(i-1).getX() == 1 && cobra.get(i).getY() - cobra.get(i-1).getY() == 0) && (cobra.get(i).getX() - cobra.get(i+1).getX() == 0 && cobra.get(i).getY() - cobra.get(i+1).getY() == 1)) || ((cobra.get(i).getX() - cobra.get(i-1).getX() == 0 && cobra.get(i).getY() - cobra.get(i-1).getY() == 1) && (cobra.get(i).getX() - cobra.get(i+1).getX() == 1 && cobra.get(i).getY() - cobra.get(i+1).getY() == 0))){
							paintBodyCurveDOWNLEFT(g, cobra.get(i).getX(), cobra.get(i).getY());
						}else{
							if(((cobra.get(i).getX() - cobra.get(i-1).getX() == -1 && cobra.get(i).getY() - cobra.get(i-1).getY() == 0) && (cobra.get(i).getX() - cobra.get(i+1).getX() == 0 && cobra.get(i).getY() - cobra.get(i+1).getY() == 1)) || ((cobra.get(i).getX() - cobra.get(i-1).getX() == 0 && cobra.get(i).getY() - cobra.get(i-1).getY() == 1) && (cobra.get(i).getX() - cobra.get(i+1).getX() == -1 && cobra.get(i).getY() - cobra.get(i+1).getY() == 0))){
								paintBodyCurveDOWNRIGHT(g, cobra.get(i).getX(), cobra.get(i).getY());
							}else{
								if(cobra.get(i).getX() - cobra.get(i-1).getX() == 1){
									paintBodyLEFT(g, cobra.get(i).getX(), cobra.get(i).getY());
								}else{
									if(cobra.get(i).getX() - cobra.get(i-1).getX() == -1){
										paintBodyRIGHT(g, cobra.get(i).getX(), cobra.get(i).getY());
									}else{
										if(cobra.get(i).getY() - cobra.get(i-1).getY() == 1){
											paintBodyUP(g, cobra.get(i).getX(), cobra.get(i).getY());
										}else{
											if(cobra.get(i).getY() - cobra.get(i-1).getY() == -1){
												paintBodyDOWN(g, cobra.get(i).getX(), cobra.get(i).getY());
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(i == last){
				if(cobra.get(last).getX() - cobra.get(i-1).getX() == 1){
					paintTailLEFT(g, cobra.get(i).getX(), cobra.get(i).getY());
				}else{
					if(cobra.get(last).getX() - cobra.get(i-1).getX() == -1){
					paintTailRIGHT(g, cobra.get(i).getX(), cobra.get(i).getY());
					}else{
						if(cobra.get(last).getY() - cobra.get(i-1).getY() == 1){
							paintTailUP(g, cobra.get(i).getX(), cobra.get(i).getY());
						}else{
							if(cobra.get(last).getY() - cobra.get(i-1).getY() == -1){
								paintTailDOWN(g, cobra.get(i).getX(), cobra.get(i).getY());
							}
						}
					}
				}
			}
		}
		
		//PAINT ENEMY
		for (i = 0; i < enemy.size(); i++) {
			
			if(i == 0){
				switch(enemyMove){
					case(1):{
						paintEnemyHeadUP(g, enemy.get(i).getX(), enemy.get(i).getY());
					break;
					}
					case(2):{
						paintEnemyHeadDOWN(g, enemy.get(i).getX(), enemy.get(i).getY());
					break;
					}
					case(3):{
						paintEnemyHeadRIGHT(g, enemy.get(i).getX(), enemy.get(i).getY());
					break;
					}
					case(4):{
						paintEnemyHeadLEFT(g, enemy.get(i).getX(), enemy.get(i).getY());
					break;
					}
				}
			}
			if(i >= 1 && i < enemyLast){
				if(((enemy.get(i).getX() - enemy.get(i-1).getX() == 1 && enemy.get(i).getY() - enemy.get(i-1).getY() == 0) && (enemy.get(i).getX() - enemy.get(i+1).getX() == 0 && enemy.get(i).getY() - enemy.get(i+1).getY() == -1)) || ((enemy.get(i).getX() - enemy.get(i-1).getX() == 0 && enemy.get(i).getY() - enemy.get(i-1).getY() == -1) && (enemy.get(i).getX() - enemy.get(i+1).getX() == 1 && enemy.get(i).getY() - enemy.get(i+1).getY() == 0))){
					paintEnemyCurveUPLEFT(g, enemy.get(i).getX(), enemy.get(i).getY());
				}else{
					if(((enemy.get(i).getX() - enemy.get(i-1).getX() == -1 && enemy.get(i).getY() - enemy.get(i-1).getY() == 0) && (enemy.get(i).getX() - enemy.get(i+1).getX() == 0 && enemy.get(i).getY() - enemy.get(i+1).getY() == -1)) || ((enemy.get(i).getX() - enemy.get(i-1).getX() == 0 && enemy.get(i).getY() - enemy.get(i-1).getY() == -1) && (enemy.get(i).getX() - enemy.get(i+1).getX() == -1 && enemy.get(i).getY() - enemy.get(i+1).getY() == 0))){
						paintEnemyCurveUPRIGHT(g, enemy.get(i).getX(), enemy.get(i).getY());
					}else{
						if(((enemy.get(i).getX() - enemy.get(i-1).getX() == 1 && enemy.get(i).getY() - enemy.get(i-1).getY() == 0) && (enemy.get(i).getX() - enemy.get(i+1).getX() == 0 && enemy.get(i).getY() - enemy.get(i+1).getY() == 1)) || ((enemy.get(i).getX() - enemy.get(i-1).getX() == 0 && enemy.get(i).getY() - enemy.get(i-1).getY() == 1) && (enemy.get(i).getX() - enemy.get(i+1).getX() == 1 && enemy.get(i).getY() - enemy.get(i+1).getY() == 0))){
							paintEnemyCurveDOWNLEFT(g, enemy.get(i).getX(), enemy.get(i).getY());
						}else{
							if(((enemy.get(i).getX() - enemy.get(i-1).getX() == -1 && enemy.get(i).getY() - enemy.get(i-1).getY() == 0) && (enemy.get(i).getX() - enemy.get(i+1).getX() == 0 && enemy.get(i).getY() - enemy.get(i+1).getY() == 1)) || ((enemy.get(i).getX() - enemy.get(i-1).getX() == 0 && enemy.get(i).getY() - enemy.get(i-1).getY() == 1) && (enemy.get(i).getX() - enemy.get(i+1).getX() == -1 && enemy.get(i).getY() - enemy.get(i+1).getY() == 0))){
								paintEnemyCurveDOWNRIGHT(g, enemy.get(i).getX(), enemy.get(i).getY());
							}else{
								if(enemy.get(i).getX() - enemy.get(i-1).getX() == 1){
									paintEnemyLEFT(g, enemy.get(i).getX(), enemy.get(i).getY());
								}else{
									if(enemy.get(i).getX() - enemy.get(i-1).getX() == -1){
										paintEnemyRIGHT(g, enemy.get(i).getX(), enemy.get(i).getY());
									}else{
										if(enemy.get(i).getY() - enemy.get(i-1).getY() == 1){
											paintEnemyUP(g, enemy.get(i).getX(), enemy.get(i).getY());
										}else{
											if(enemy.get(i).getY() - enemy.get(i-1).getY() == -1){
												paintEnemyDOWN(g, enemy.get(i).getX(), enemy.get(i).getY());
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(i == enemyLast){
				if(enemy.get(enemyLast).getX() - enemy.get(i-1).getX() == 1){
					paintEnemyTailLEFT(g, enemy.get(i).getX(), enemy.get(i).getY());
				}else{
					if(enemy.get(enemyLast).getX() - enemy.get(i-1).getX() == -1){
					paintEnemyTailRIGHT(g, enemy.get(i).getX(), enemy.get(i).getY());
					}else{
						if(enemy.get(enemyLast).getY() - enemy.get(i-1).getY() == 1){
							paintEnemyTailUP(g, enemy.get(i).getX(), enemy.get(i).getY());
						}else{
							if(enemy.get(enemyLast).getY() - enemy.get(i-1).getY() == -1){
								paintEnemyTailDOWN(g, enemy.get(i).getX(), enemy.get(i).getY());
							}
						}
					}
				}
			}
		}
		for(int x = 0; x<largura; x++){
			for(int y = 0; y<altura; y++){
				switch(matrix[x][y]){
					case('w'):{
					//WALL
						paintWall(g, x, y);
						break;
					}
					case('F'):{
					//MAÇA
						paintMaca(g, x, y);
						break;
					}
					case('L'):{
					//LIMÃO
						paintLimao(g, x, y);
						break;
					}
					case('M'):{
					//MELANCIA
						paintMelancia(g, x, y);
						break;
					}
					case('P'):{
					//Powerup
						paintPowerup(g, x, y);
						break;
					}
					case('_'):{
						paintBackground(g, x, y);
						break;
					}
				}
			}
		}
	}
	//PAINT PLAYER BODY CURVES
	private void paintBodyCurveDOWNRIGHT(Graphics g, int x, int y) {
		g.drawImage(bodyCurveDownRightIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintBodyCurveDOWNLEFT(Graphics g, int x, int y) {
		g.drawImage(bodyCurveDownLeftIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintBodyCurveUPRIGHT(Graphics g, int x, int y) {
		g.drawImage(bodyCurveUpRightIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintBodyCurveUPLEFT(Graphics g, int x, int y) {
		g.drawImage(bodyCurveUpLeftIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF PAINT PLAYER BODY CURVES
	
	private void paintBackground(Graphics g, int x, int y) {
		g.drawImage(grassIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	
	
	//PAINT ENEMY BODY CURVES
	private void paintEnemyCurveDOWNRIGHT(Graphics g, int x, int y) {
		g.drawImage(enemyBodyCurveDownRightIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyCurveDOWNLEFT(Graphics g, int x, int y) {
		g.drawImage(enemyBodyCurveDownLeftIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyCurveUPRIGHT(Graphics g, int x, int y) {
		g.drawImage(enemyBodyCurveUpRightIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyCurveUPLEFT(Graphics g, int x, int y) {
		g.drawImage(enemyBodyCurveUpLeftIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF ENEMY BODY CURVES
	
	//PAINT PLAYER BODY ANGLES
	private void paintBodyUP(Graphics g, int x, int y) {	
		g.drawImage(bodyUPIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintBodyDOWN(Graphics g, int x, int y) {	
		g.drawImage(bodyDOWNIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintBodyRIGHT(Graphics g, int x, int y) {	
		g.drawImage(bodyRIGHTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintBodyLEFT(Graphics g, int x, int y) {
		g.drawImage(bodyLEFTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF PAINT BODY ANGLES
	
	//PAINT ENEMY BODY ANGLES
	private void paintEnemyUP(Graphics g, int x, int y) {	
		g.drawImage(enemyUPIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyDOWN(Graphics g, int x, int y) {	
		g.drawImage(enemyDOWNIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyRIGHT(Graphics g, int x, int y) {	
		g.drawImage(enemyRIGHTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyLEFT(Graphics g, int x, int y) {
		g.drawImage(enemyLEFTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF PAINT ENEMY BODY ANGLES
	
	//PAINT PLAYER HEAD ANGLES
	private void paintHeadUP(Graphics g, int x, int y) {	
		g.drawImage(headUPIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintHeadDOWN(Graphics g, int x, int y) {	
		g.drawImage(headDOWNIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintHeadRIGHT(Graphics g, int x, int y) {	
		g.drawImage(headRIGHTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintHeadLEFT(Graphics g, int x, int y) {	
		g.drawImage(headLEFTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF PAINT PLAYER HEAD ANGLES
	
	//PAINT ENEMY HEAD ANGLES
	private void paintEnemyHeadUP(Graphics g, int x, int y) {	
		g.drawImage(enemyHeadUPIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyHeadDOWN(Graphics g, int x, int y) {	
		g.drawImage(enemyHeadDOWNIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyHeadRIGHT(Graphics g, int x, int y) {	
		g.drawImage(enemyHeadRIGHTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyHeadLEFT(Graphics g, int x, int y) {	
		g.drawImage(enemyHeadLEFTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF PAINT ENEMY HEAD ANGLES
	
	//PAINT TAIL PLAYER ANGLES
	private void paintTailLEFT(Graphics g, int x, int y) {
		g.drawImage(tailLEFTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintTailRIGHT(Graphics g, int x, int y) {
		g.drawImage(tailRIGHTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintTailDOWN(Graphics g, int x, int y) {
		g.drawImage(tailDOWNIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintTailUP(Graphics g, int x, int y) {
		g.drawImage(tailUPIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF TAIL PLAYER ANGLES
	
	//PAINT TAIL ENEMY ANGLES
	private void paintEnemyTailLEFT(Graphics g, int x, int y) {
		g.drawImage(enemyTailLEFTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyTailRIGHT(Graphics g, int x, int y) {
		g.drawImage(enemyTailRIGHTIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyTailDOWN(Graphics g, int x, int y) {
		g.drawImage(enemyTailDOWNIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintEnemyTailUP(Graphics g, int x, int y) {
		g.drawImage(enemyTailUPIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	//END OF TAIL ENEMY ANGLES
	
	private void paintPowerup(Graphics g, int x, int y) {
		g.drawImage(powerIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintMaca(Graphics g, int x, int y) {
		g.drawImage(macaIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintLimao(Graphics g, int x, int y) {
		g.drawImage(limaoIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintMelancia(Graphics g, int x, int y) {
		g.drawImage(melanIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	private void paintWall(Graphics g, int x, int y) {
		g.drawImage(vinesIMG, escala*x, escala*y,Color.GREEN,  this);
	}
	
	public BufferedImage loadImage(String nome) {
		URL url= null;
		try {
			url = getClass().getClassLoader().getResource(nome);
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Não foi possivel carregar a imagem " + nome +" de "+url);
			System.out.println("O Erro foi : "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public static void main(String[] args) {
		Main screen = new Main();
		screen.game();
	}

	public void keyPressed(KeyEvent arg0) {
	   	switch (arg0.getKeyCode()) {
	   		case KeyEvent.VK_DOWN:{
	   			if(move != 1 && paused == 0 && check_move == 0){
		   			move = 2;
		   			check_move = 1;
	   			}
	   			break;
	   		}
	   		case KeyEvent.VK_UP:{
	   			if(move != 2 && paused == 0 && check_move == 0){
		   			move = 1;
		   			check_move = 1;
	   			}
	   			break;
	   		}
	   		case KeyEvent.VK_LEFT:{
	   			if(move != 3 && paused == 0 && check_move == 0){
		   			move = 4;
		   			check_move = 1;
	   			}
	   			break;
	   		}
	   		case KeyEvent.VK_RIGHT:{
	   			if(move != 4 && paused == 0 && check_move == 0){
		   			move = 3;
		   			check_move = 1;
	   			}
	   			break;
	   		}
	   		case KeyEvent.VK_ESCAPE:{
	   			if(paused == 0){
	   				paused = 1;
	   			}else{
	   				paused = 0;
	   			}
	   			break;
	   		}
		}
	}

	public void keyReleased(KeyEvent arg0) {
	
	}

	public void keyTyped(KeyEvent arg0) {
		
	}
}