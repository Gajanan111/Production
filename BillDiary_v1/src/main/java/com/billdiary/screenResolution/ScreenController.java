package com.billdiary.screenResolution;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class ScreenController {

	static GraphicsDevice graphicDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int getScreenWidth() {
		int width = graphicDevice.getDisplayMode().getWidth();
		return width;
	}
	public static int getScreenHeight() {
		int height = graphicDevice.getDisplayMode().getHeight();
		return height-70;
	}
	
}
