package kr.dev.parktrio.template;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import bayaba.engine.lib.ButtonObject;
import bayaba.engine.lib.ButtonType;
import bayaba.engine.lib.GameInfo;
import bayaba.engine.lib.GameObject;
import bayaba.engine.lib.Sprite;

public class GameMain
{
	public GL10 mGL = null;
	private Context MainContext;
	public GameInfo gInfo;
	public float TouchX, TouchY;
	public int CurLayer = 0;
	
	private Sprite plusSpr = new Sprite();
	private Sprite minusSpr = new Sprite();
	
	public ButtonObject plusBtn = new ButtonObject();
	public ButtonObject minusBtn = new ButtonObject();

	private Sprite normalGomSpr = new Sprite();
	private GameObject normalGom = new GameObject();
	
	private Sprite flyingGomSpr = new Sprite();
	private GameObject flyingGom = new GameObject();
	
	public float speed = 0.2f;

	public GameMain( Context context, GameInfo info )
	{
		MainContext = context;
		gInfo = info;
	}
	
	public void LoadGom() {
		normalGomSpr.LoadSprite( mGL, MainContext, R.drawable.gom01, "normal.spr" );
		normalGom.SetObject( normalGomSpr, 0, 0, 240, 300, 0, 0 );

		flyingGomSpr.LoadSprite( mGL, MainContext, R.drawable.gom01, "flying.spr" );
		flyingGom.SetObject( flyingGomSpr, 0, 0, 240, 300, 0, 0 );
	}
	
	public void LoadGameData()
	{				
		LoadGom();
		
		plusSpr.LoadSprite( mGL, MainContext, R.drawable.button, "plus.spr" );
		plusBtn.SetButton( plusSpr, ButtonType.TYPE_KEEP_CLICK, 0, 360, 600, 0 );
		
		minusSpr.LoadSprite( mGL, MainContext, R.drawable.button, "minus.spr" );
		minusBtn.SetButton( minusSpr, ButtonType.TYPE_KEEP_CLICK, 0, 120, 600, 0 );
	}
	
	public void PushButton( boolean flag, float x, float y )
	{
		plusBtn.CheckButton( gInfo, flag, x, y );
		if ( plusBtn.click == ButtonType.STATE_DOWN_BUTTON ) {
			if ( speed < 2.5f ) speed += 0.1f;
		}
		
		minusBtn.CheckButton( gInfo, flag, x, y );
		if ( minusBtn.click == ButtonType.STATE_DOWN_BUTTON ) {
			if ( speed > 0.2f ) speed -= 0.1f;
		}
	}
	
	public void DoGame()
	{	
		if ( speed < 1.5f ) {
			normalGom.AddFrameLoop( speed );
			normalGom.DrawSprite( gInfo );
		} else {
			flyingGom.AddFrameLoop( speed - 1.3f );
			flyingGom.DrawSprite( gInfo );
		}
		plusBtn.DrawSprite( mGL, gInfo, null );
		minusBtn.DrawSprite( mGL, gInfo, null );
	}
}
