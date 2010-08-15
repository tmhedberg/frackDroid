package info.tmhedberg.android.frackdroid.fractal;

import java.awt.Color;

public abstract class Fractal {

	protected int viewportPixelWidth, viewportPixelHeight;
	protected double viewportCoordWidth, viewportCoordHeight;
	protected double viewportOriginX, viewportOriginY;
	protected Color colorStart, colorEnd, colorSet;
	
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
	public void setColors(Color start, Color end, Color set) {
		colorStart = start;
		colorEnd = end;
		colorSet = set;
	}
	
	protected Color getIntermediateColor(double distanceFromStart) {
		
		if (distanceFromStart < 0 || distanceFromStart > 1)
			throw new IllegalArgumentException("distanceFromStart must be between 0 and 1, inclusive");
		
		int RDiff = colorEnd.getRed() - colorStart.getRed();
		int GDiff = colorEnd.getGreen() - colorStart.getGreen();
		int BDiff = colorEnd.getBlue() - colorStart.getBlue();
		
		int newR = (int)(colorStart.getRed() + distanceFromStart * RDiff);
		int newG = (int)(colorStart.getGreen() + distanceFromStart * GDiff);
		int newB = (int)(colorStart.getBlue() + distanceFromStart * BDiff);
		
		return new Color(newR, newG, newB);
		
	}
	
}