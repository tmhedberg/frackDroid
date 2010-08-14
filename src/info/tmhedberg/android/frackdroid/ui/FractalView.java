package info.tmhedberg.android.frackdroid.ui;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FractalView
extends SurfaceView
implements SurfaceHolder.Callback {
	
	private final DrawingThread thread = new DrawingThread();
	
	public FractalView(final Context context) {
		super(context);
	}
	
	@Override
	public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
		
	}
	
	@Override
	public void surfaceCreated(final SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}
	
	@Override
	public void surfaceDestroyed(final SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {}
		}
	}
	
	private class DrawingThread
	extends Thread {
		
		private boolean running = false;
		
		public void setRunning(final boolean running) {
			this.running = running;
		}
		
	}
	
}