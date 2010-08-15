package info.tmhedberg.android.frackdroid.fractal;

public class Mandelbrot
extends Fractal {
	
	private static int DEFAULT_CONVERGENCE_THRESHOLD = 50;
	private static int DEFAULT_DIVERGENCE_THRESHOLD = 8;
	
	private int convergenceThreshold, divergenceThreshold;
	
	public Mandelbrot() {
		convergenceThreshold = DEFAULT_CONVERGENCE_THRESHOLD;
		divergenceThreshold = DEFAULT_DIVERGENCE_THRESHOLD;
	}
	public Mandelbrot(int convergenceThreshold, int divergenceThreshold) {
		this.convergenceThreshold = convergenceThreshold;
		this.divergenceThreshold = divergenceThreshold;
	}
	
	@Override
	public int[][] render() {
		
		int[][] buffer = new int[viewportPixelHeight][viewportPixelWidth];
		
		// Iterate over pixels
		int sqDivergenceThreshold = divergenceThreshold * divergenceThreshold;
		for (int row = 0; row < viewportPixelHeight; row++) {
			for (int col = 0; col < viewportPixelWidth; col++) {
				
				// Calculate complex coordinate value of pixel
				double cRe = viewportOriginX + (double)col / (double)viewportPixelWidth * viewportCoordWidth;
				double cIm = viewportOriginY + (double)row / (double)viewportPixelHeight * viewportCoordHeight;
				
				// Iterate on Mandelbrot operation until result converges or diverges
				double z1Re = 0, z1Im = 0;
				double z2Re, z2Im;
				int iterations;
				for (iterations = 1; iterations < convergenceThreshold && z1Re * z1Re + z1Im * z1Im < sqDivergenceThreshold; iterations++) {
					z2Re = z1Re * z1Re - z1Im * z1Im;
					z2Im = 2 * z1Re * z1Im;
					z1Re = z2Re + cRe;
					z1Im = z2Im + cIm;
				}
				
				// Determine loop break condition and set pixel color accordingly
				if (iterations >= convergenceThreshold)	// Result is convergent
					buffer[row][col] = colorSet;
				else	// Result is divergent
					buffer[row][col] = getIntermediateColor((double)iterations / (double)convergenceThreshold);
				
			}
		}
		
		return buffer;
		
	}
	
}