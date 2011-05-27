package se.mah.k3.guack;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameEngine extends View implements Callback{
	
	Paint background;
	Handler myHandler;
	int score = 0;
	
	private List<Drawable> myMoles = new ArrayList<Drawable>();

	public GameEngine(Context context) {
		super(context);

		myHandler = new Handler(this);
		GameUpdateThread gT = new GameUpdateThread(myHandler);
		gT.start();
		
		createMoles();

	}

	private void createMoles(){
		
		for (int r = 0; r < 3; r++){
			for (int c = 1; c < 4; c++){
				myMoles.add(new Mole(80 * c, 86 * r + 60));
			}
		}
	}
	
@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		
		Paint background = new Paint(Paint.ANTI_ALIAS_FLAG);
		background.setColor(Color.parseColor("#5a4b0f"));
		canvas.drawPaint(background);

		Paint moleNestColor = new Paint(Paint.ANTI_ALIAS_FLAG);
		moleNestColor.setColor(Color.parseColor("#a2bd46"));

		float width = getWidth()/4;
		float height = getHeight()/5;

		/*for (int r = 0; r < 3; r++){
			for (int c = 1; c < 4; c++){
				canvas.drawCircle(width * c, height * r + 68, 35, moleNestColor);
			}
		}*/

		/*canvas.drawLine(0, getHeight()-getHeight()/4, getWidth(), getHeight()-getHeight()/4, moleNestColor);
*/
		Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(Color.parseColor("#a2bd46"));
		textPaint.setTextSize(35);
		canvas.drawText("Guack a Mole", 55, 350, textPaint);
		
		String scoreString = Integer.toString(score);
		canvas.drawText(scoreString, 55, 400, textPaint);
		
	
		for (Drawable d : myMoles){
			d.draw(canvas);
		}
		
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("k3", "Touchy-touch!");
		
		for (Drawable d : myMoles){
			if (d.pressed(event)){
				score++;
			}
				
		}
		return super.onTouchEvent(event);
	}
	
	private void superUpdate(){
		for (Drawable d : myMoles){
			d.update();
		}
		this.invalidate();
	}

	@Override
	public boolean handleMessage(Message msg) {
		Log.i("k3", "Mess: " + msg.what);
		superUpdate();
		return false;
	}
	


}