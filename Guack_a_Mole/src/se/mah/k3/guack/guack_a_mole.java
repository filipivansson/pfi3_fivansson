package se.mah.k3.guack;

import android.app.Activity;
import android.os.Bundle;

public class guack_a_mole extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameEngine(this));
    }
    
}