package kr.dev.parktrio.template;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GLView extends GLSurfaceView
{
	Context mContext;	
	public GameMain sImg;

	public GLView( Context context, GameMain img )
	{
		super( context );
		setFocusable( true );
		
		mContext = context;
		sImg = img;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
    {
		final int action = event.getAction();
		
		synchronized ( sImg.mGL )
		{
			sImg.TouchX = event.getX() * sImg.gInfo.ScalePx;
			sImg.TouchY = event.getY() * sImg.gInfo.ScalePy;
			
			switch ( action & MotionEvent.ACTION_MASK )
			{
				case	MotionEvent.ACTION_DOWN	:
						{	
						}
				case	MotionEvent.ACTION_MOVE :
						{
						}
				case	MotionEvent.ACTION_POINTER_DOWN	:
						{
							sImg.PushButton( true, sImg.TouchX, sImg.TouchY );
						}
						break;
		
				case	MotionEvent.ACTION_UP :
				case	MotionEvent.ACTION_POINTER_UP :
						{
							sImg.PushButton( false, sImg.TouchX, sImg.TouchY );
						}
						break;
			}
		}
      	return true;
    }

}
