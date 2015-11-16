package mahli;

import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class ZoomLabel extends JPanel {
    private Image img;
    private float scaleX, scaleY;

    public ZoomLabel() {
        scaleX = scaleY = 1;
        setOpaque(false);
    }
    public  ZoomLabel(Image img) {
         //quick and 'dirty' way to load an image
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
        int prefWidth  = (int) (img == null?0:img.getWidth(this) * scaleX);
        int prefHeight = (int) (img == null?0:img.getHeight(this) * scaleY);

        return new Dimension(prefWidth,prefHeight);
    }
    @Override
    public void paintComponent(Graphics g) {
        if(img == null)
            return;

        int w = (int) (img.getWidth(null) * scaleX);
        int h = (int) (img.getHeight(null) * scaleY);

        int x = (getWidth()-w)/2;
        int y = (getHeight()-h)/2;

        g.drawImage(img,x,y,w,h, null);
    }

    public static void main(String[] args) {
        final ZoomLabel label = new ZoomLabel();
        try{
            Image img = javax.imageio.ImageIO.read(new File("images/1.jpg"));
            label.setImage(img);
        }catch(Exception e) {
            System.err.println("Couldn't load image for demonstration");
            e.printStackTrace();
            System.exit(0);
        }

        JFrame frame = new JFrame();

        frame.add(new JScrollPane(label));

        JSlider slider = new JSlider(0,1000,100);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int val = ((JSlider) e.getSource()).getValue();
                label.setScale(val * .01f, val * .01f);
            }
        });

        frame.add(slider, java.awt.BorderLayout.SOUTH);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}