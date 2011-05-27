package se.mah.k3.guack;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class Mole implements Drawable {
	
	private float _posX, _posY;
	private int alpha;
	private Paint _p;
	private Paint _p2;
	private Paint _p3;
	private float _radius = 10;
	private boolean alive = false;
	private	Random random = new Random();
	
	
	public  Mole(float posX, float posY){
		_posX = posX;
		_posY = posY;
		alpha = 0;
		_p = new Paint(Paint.ANTI_ALIAS_FLAG);
		_p.setARGB(255, 234, 231, 128);
		_p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		_p2.setARGB(255, 162, 189, 70);
		_p3 = new Paint(Paint.ANTI_ALIAS_FLAG);
		_p3.setARGB(255, 45, 53, 16);
		alive = false;
	}

	@Override
	public void update() {
		Log.i("k3", "askhfakfaksdjfhkajs");
		if (random.nextInt(100) > 95){
			Log.i("k3", "askhfakfaksdjfhkajsdhfskajdfhaksfhaksjd");
			alpha = 255;
			alive = true;
		}
	}

	@Override
	public void draw(Canvas c) {

		if (alpha > 0){
			alpha = alpha - 20;
		}
		else if (alpha <= 0){
			alpha = 0;
			alive = false;
		}
		if (alive){
			c.drawCircle(_posX, _posY, _radius+17, _p3);
			c.drawCircle(_posX, _posY, _radius+15, _p2);
			c.drawCircle(_posX, _posY+5, _radius, _p);
			
		}		
	}
	
	

	@Override
	public boolean pressed(MotionEvent m) {

		Log.i("k3", "Pressed");
		
		boolean hit = false;
		float dx = (_posX - m.getX());
		float dy = (_posY - m.getY());
		float dist = (float) Math.sqrt(dx * dx + dy * dy);
		
		if (dist < _radius+15 && alive == true) {
			alive = false;
			alpha = 0;
			hit = true;
		}
		return hit;
	}
}