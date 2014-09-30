
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;


public class Driver extends PApplet{

	double c = .1;
	int w = 150;
	int h = 150;
	float t = 0;
	int scale = 5;
	GridSpace[][] array = new GridSpace[w][h];
	ArrayList<Point> roomLocations = new ArrayList<Point>();
	ArrayList<Room> roomsList = new ArrayList<Room>();
	
	public void setup() {
		size(w* scale, h * scale);
		for(int i = 0; i < w;i++){
			for(int j = 0; j < h; j++){
				array[i][j] = new GridSpace(i, j, scale,255);
			}
		}
		generateRooms(20,5,80);
	}
	
	public void draw(){
		point(0,0);
		fill(0);
	
		for(int i = 0; i < w;i++){
			for(int j = 0; j < h; j++){
				array[i][j].drawPoint();	
			}
		}
		t++;
	}
	
	public void mousePressed(){
		array[mouseX*w/width][mouseY*h/height].mousePressed();
	}
	
	private void generateRooms(int posRange, int num, int percent){
		Random rand = new Random();
		for(int i = 1; i <= num; i++){
			for(int j = 1; j <= num; j++){
				if(rand.nextDouble() <= percent/100.0){					
					array[i*width/(num+1)*w/width - 1 + (int) (posRange/2 - rand.nextDouble() * posRange)]
						 [j*height/(num+1)*h/height - 1 + (int) (posRange/2 - rand.nextDouble() * posRange)].color = 0;
					
					roomLocations.add(new Point(i*width/(num+1)*w/width,j*height/(num+1)*h/height));
					roomsList.add(new Room(new Point(i*width/(num+1)*w/width,j*height/(num+1)*h/height)));
				}
			}
		}
	}

	public class GridSpace {

	private int x;
	private int y;
	private int color;
	private int scale;
	
	public GridSpace(int x,int y, int scale, int color){
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.color = color;
	}
	
	private void drawPoint(){
		fill(color);
		rect(x*scale,y*scale,scale,scale);

	}
	
	public void mousePressed(){
		switch (color){
		case 0: color = 150; break;
		case 150: color = 250; break;
		case 250: color = 0; break;
		default: 
			color = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getColor() {
		return color;
	}

	boolean overEvent() {
	    if (mouseX*w/width > x*scale && mouseX*w/width <= x*scale + scale &&
	    	mouseY*h/height < -y*scale && mouseY*h/height >= -y*scale - scale) {
	      return true;
	    } else {
	      return false;
	    }
	}
	
}
	
	public class Edge implements Comparable{
		Point a;
		Point b;
		double dist;
		
		public Edge(Point a, Point b) {
			this.a = a;
			this.b = b;
			dist = a.distance(b);
		}

		public double getDist(){
			return dist;
		}
		
		@Override
		public int compareTo(Object b) {

			if(dist < ((Edge) b).getDist()){
				return 1;
			} else if (dist == ((Edge) b).getDist()){
				return 0;
			} else {
				return -1;
			}
			
		}
		
		
		
		
		
		
		
	}
	
}
