package com.lj9.subcutaneousexplorer;

import com.lj9.mjEngine.mjGame;
import com.lj9.mjEngine.mjRenderer;
import com.lj9.mjEngine.mjScene;
import com.lj9.mjEngine.mjSceneManager;
import com.lj9.mjEngine.mjSurfaceView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class SubcutaneousExplorer extends Activity {

		private mjSurfaceView mGLView;
		MediaPlayer mediaPlayer;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			 super.onCreate(savedInstanceState);
		        
		        // Remove the title bar
		        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		        // Request only landscape orientations
		        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		        
		        // Initialise the game
		        mjGame.Initialize(this);
		        
		        // Create startup GameScene
		        mjScene scene = new MenuUniverseScene();//new TakkaTakkaUniverseScene();
		        mjSceneManager.currentScene = scene;
		        // Create glView and attach it
		        mjRenderer renderer = new mjRenderer(true);
		        
		        //mGLView = new mjSurfaceView(this, scene, renderer);
		        
		        mGLView = new mjSurfaceView(this, scene, renderer);
		        String androidVersion = android.os.Build.VERSION.RELEASE;
		        if (androidVersion.startsWith("4.4"))
		        {
		        	mGLView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN);
		        } else
		        {
		        	//mGLView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		        }
		        setContentView(mGLView);
		        
		        
		}

		
		@Override
	    public boolean dispatchGenericMotionEvent(MotionEvent e) {
	    	InputDevice myDevice = e.getDevice();
	        if ((myDevice.getSources() & InputDevice.SOURCE_CLASS_JOYSTICK) != 0) {
	            
	        	mjSceneManager.currentScene.OnProcessMotion(e);
	            return true;
	        }
	        return super.dispatchGenericMotionEvent(e);
	    }
		@Override
		public boolean onTouchEvent(MotionEvent e) {
			return mjSceneManager.currentScene.OnTouchEvent(e);
		}
		
		@Override
	    public void onPause() {
	        super.onPause();  // Always call the superclass method first

	        if (mjGame.musicPlayer != null)
	        {
	        	mjGame.musicPlayer.pause();
	        }
	    }
		
}
