package se.mah.k3.guack;

import android.os.Handler;
import android.util.Log;

public class GameUpdateThread extends Thread {
	Handler _handler;
	private boolean _running = true;
	
	public void stopCountDown (){
		_running = false;
	}
	
	public GameUpdateThread(Handler handler){
		_handler = handler; 
	}
	
	@Override
	public void run() {
		super.run();
		while (_running){
			try {
				Log.i("k3", "Thread Running");
				_handler.sendEmptyMessage(MAX_PRIORITY);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.i("k3", e.getMessage());
			}
		}
	}
	

}