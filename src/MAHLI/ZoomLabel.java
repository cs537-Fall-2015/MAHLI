package MAHLI;

import javax.swing.*;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;

public class ZoomLabel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image img;
	private float scaleX, scaleY;

	public ZoomLabel() {
		scaleX = scaleY = 1;
		setOpaque(false);
	}

	public ZoomLabel(Image img) {
		// quick and 'dirty' way to load an image
		this.img = new ImageIcon(img).getImage();
		scaleX = scaleY = 1;
		setOpaque(false);
	}

	public ZoomLabel(ImageIcon icon) {
		this(icon.getImage());
	}

	public void setImage(Image img) {
		this.img = img;
		revalidate();
		repaint();
	}

	public void setScale(float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		revalidate();
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		int prefWidth = (int) (img == null ? 0 : img.getWidth(this) * scaleX);
		int prefHeight = (int) (img == null ? 0 : img.getHeight(this) * scaleY);

		return new Dimension(prefWidth, prefHeight);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (img == null)
			return;

		int w = (int) (img.getWidth(null) * scaleX);
		int h = (int) (img.getHeight(null) * scaleY);
		int x = (getWidth() - w) / 2;
		int y = (getHeight() - h) / 2;
		g.drawImage(img, x, y, w, h, null);
	}

}
