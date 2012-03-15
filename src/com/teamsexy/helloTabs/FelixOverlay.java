package com.teamsexy.helloTabs;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * Based on tutorial from: 
 * http://www.vogella.de/articles/AndroidLocationAPI/article.html#locationapi
 * @author genia / Lars Vogel
 *
 */
public class FelixOverlay extends ItemizedOverlay {

	private static int maxNum = 8;
	private OverlayItem overlays[] = new OverlayItem[maxNum];
	private int index = 0;
	private boolean full = false;
	private FelixOverlay itemizedoverlay;
	
	public FelixOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlays[i];
	}

	@Override
	public int size() {
		if (full) {
			return overlays.length;
		} else {
			return index;
		}

	}
	
	public void addOverlay(OverlayItem overlay) {
		if (index < maxNum) {
			overlays[index] = overlay;
		} else {
			index = 0;
			full = true;
			overlays[index] = overlay;
		}
		index++;
		populate();
	}
}
