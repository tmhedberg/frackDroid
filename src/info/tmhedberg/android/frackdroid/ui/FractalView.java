package info.tmhedberg.android.frackdroid.ui;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FractalView
extends SurfaceView
implements SurfaceHolder.Callback {
	
	public FractalView(final Context context) {
		super(context);
	}
	
	@Override
	public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
		
	}
	
	@Override
	public void surfaceCreated(final SurfaceHolder holder) {
		
	}
	
	@Override
	public void surfaceDestroyed(final SurfaceHolder holder) {
		
	}
	
}