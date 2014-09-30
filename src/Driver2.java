import processing.core.PApplet;


public class Driver2 extends PApplet{

 double c = .1;
 int square = 20;
 int l = square;
 int h = square;
 float t = 0;
 
 public void setup() {
  size(1000, 1000);
 }
 
 public void draw(){
  pushMatrix();
  translate(500, 500);
  point(0,0);
  fill(0);

  //frameRate(10);
  
  for(int i = -square; i <= square;i++){
   for(int j = -square; j <= square; j++){
    double height = calculateHeight(i,j,t);
    fill((int) height);
    drawIsometricPoint(i,j,1,1,height);
    //drawPoint(i,j,1,1);
   }
  }
  t++;
  popMatrix();
 }
 
 private double calculateHeight(float x, float y, float t){
  double u = 0;
  double lam1 = 2.5;
  double lam2 = .5;
  int a = 2;
  int b = 1;
  int d = 4;
  int e = 2;
  int f = 1;
  int g = 2;
  u = f*Math.cos(c*Math.sqrt(lam1)*t)* Math.sin(a*Math.PI*x/(l)) * Math.sin(b * Math.PI*y/h) + g * Math.cos(c*Math.sqrt(lam2)*t)*Math.sin(d*Math.PI*x/l)*Math.sin(e*Math.PI*y/h);
  u += 15;
  u = 255 * (u)/(25);
  return u;
 }
 
 private void drawPoint(float x, float y, float width, float height){
  rect(x*15,-y*15,width*15,height*-15);

 }
 
 private void drawIsometricPoint(float x, float y, float width, float height, double color){
  y = (float) (y + 8 * color/255);
  x = (float) (x - 8 * color/255);
  int scale = 15;
  float x1 = x;
  float x2 = (x + width);
  float y1 = y ;
  float y2 = (y + height);
  float srt = .9f;//(Math.sqrt(3)/2.0);
  float half = (float) .5;//.5;
  
  quad((x1+y1)*srt*scale,(y1-x1)*half*-scale,(x1+y2)*srt*scale,(y2-x1)*half*-scale,(x2+y2)*srt*scale,(y2-x2)*half*-scale,(x2+y1)*srt*scale,(y1-x2)*half*-scale);
 }

}