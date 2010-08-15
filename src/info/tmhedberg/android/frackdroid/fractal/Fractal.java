package info.tmhedberg.android.frackdroid.fractal;

import android.graphics.Color;

public abstract class Fractal {

	protected int viewportPixelWidth, viewportPixelHeight;
	protected double viewportCoordWidth, viewportCoordHeight;
	protected double viewportOriginX, viewportOriginY;
	protected int colorStart, colorEnd, colorSet;
	
	public abstract Color[][] render();
	
	public void setViewportPixelDimensions(int width, int height) {
		viewportPixelWidth = width;
		viewportPixelHeight = height;
	}
	public double getViewportCoordWidth() {
		return viewportCoordWidth;
	}
	public double getViewportCoordHeight() {
		return viewportCoordHeight;
	}
	public void setViewportCoordDimensions(double width, double height) {
		viewportCoordWidth = width;
		viewportCoordHeight = height;
	}
	public double getViewportOriginX() {
		return viewportOriginX;
	}
	public double getViewportOriginY() {
		return viewportOriginY;
	}
	public void setViewportOrigin(double x, double y) {
		viewportOriginX = x;
		viewportOriginY = y;
	}
	public void setColors(int start, int end, int set) {
		colorStart = start;
		colorEnd = end;
		colorSet = set;
	}
	
	protected int getIntermediateColor(double distanceFromStart) {
		
		if (distanceFromStart < 0 || distanceFromStart > 1)
			throw new IllegalArgumentException("distanceFromStart must be between 0 and 1, inclusive");
		
		int RDiff = Color.red(colorEnd) - Color.red(colorStart);
		int GDiff = Color.green(colorEnd) - Color.green(colorStart);
		int BDiff = Color.blue(colorEnd) - Color.blue(colorStart);
		
		int newR = (int)(Color.red(colorStart) + distanceFromStart * RDiff);
		int newG = (int)(Color.green(colorStart) + distanceFromStart * GDiff);
		int newB = (int)(Color.blue(colorStart) + distanceFromStart * BDiff);
		
		return Color.rgb(newR, newG, newB);
		
	}
	
}