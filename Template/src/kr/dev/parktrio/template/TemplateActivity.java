package kr.dev.parktrio.template;

import bayaba.engine.lib.GameInfo;
import android.graphics.Point;
import android.media.AudioManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class TemplateActivity extends Activity {

	private GLView play;
	private GameMain sImg;
	public GameInfo gInfo;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream( AudioManager.STREAM_MUSIC );

        Point size = new Point();

        gInfo = new GameInfo( 480, 800 );
        //gInfo.ScreenXsize = super.getWindowManager().getDefaultDisplay().getWidth();
        //gInfo.ScreenYsize = super.getWindowManager().getDefaultDisplay().getHeight();
        super.getWindowManager().getDefaultDisplay().getSize( size );
        gInfo.ScreenXsize = size.x;
        gInfo.ScreenYsize = size.y;
        gInfo.SetScale();

        sImg = new GameMain( this, gInfo );
        play = new GLView( this, sImg );
        play.setRenderer( new SurfaceClass(sImg) );

        setContentView( play );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.template, menu);
		return true;
	}

}
