package info.tmhedberg.android.frackdroid.ui;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FractalView
extends SurfaceView
implements SurfaceHolder.Callback {
	
	public FractalView(Context context) {
		super(context);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	
}