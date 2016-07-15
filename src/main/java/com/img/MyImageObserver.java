package com.img;

import java.awt.Image;
import java.awt.image.ImageObserver;

public class MyImageObserver implements ImageObserver {

	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		return false;
	}

}
