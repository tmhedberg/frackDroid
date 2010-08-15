package info.tmhedberg.android.frackdroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import info.tmhedberg.android.frackdroid.fractal.Fractal;
import info.tmhedberg.android.frackdroid.fractal.Mandelbrot;

public class FractalView
extends SurfaceView
implements SurfaceHolder.Callback {
	
	private static final Class<? extends Fractal> DEFAULT_FRACTAL = Mandelbrot.class;
	private static final double DEFAULT_FRACTAL_COORD_DIM_X = 3;
	private static final double DEFAULT_FRACTAL_COORD_DIM_Y = 5;
	private static final double DEFAULT_FRACTAL_ORIGIN_X = -2;
	private static final double DEFAULT_FRACTAL_ORIGIN_Y = -2.5;
	
	private final SurfaceHolder surfaceHolder = getHolder();
	private final DrawingThread thread = new DrawingThread(surfaceHolder);
	
	public FractalView(final Context context, final AttributeSet attrs) {
		
		super(context);
		
		surfaceHolder.addCallback(this);
		
	}
	
	@Override
	public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
		thread.setSurfaceSize(width, height);
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
	
	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		return thread.handleTouchEvent(event);
	}
	
	private class DrawingThread
	extends Thread {
		
		private boolean running = false;
		
		private int canvasWidth, canvasHeight;
		private float zoomCenterx, zoomCentery;
		
		private final SurfaceHolder surfaceHolder;
		
		public DrawingThread(final SurfaceHolder surfaceHolder) {
			this.surfaceHolder = surfaceHolder;
		}
		
		@Override
		public void run() {
			while (running) {
				final Canvas canvas = surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					doDraw(canvas);
				}
				if (canvas != null)
					surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		
		private void doDraw(Canvas canvas) {
			
		}
		
		public void setRunning(final boolean running) {
			this.running = running;
		}
		
		public void setSurfaceSize(final int width, final int height) {
			synchronized (surfaceHolder) {
				canvasWidth = width;
				canvasHeight = height;
			}
		}
		
		public boolean handleTouchEvent(final MotionEvent event) {
			zoomCenterx = event.getX();
			zoomCentery = event.getY();
			return true;
		}
		
	}
	
}