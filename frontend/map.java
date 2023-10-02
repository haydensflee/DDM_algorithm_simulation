import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.geom.*;
import java.awt.Graphics;

public class map extends JPanel {
    // PA = Plot Area, as fraction of Panel size.
    final static double PA_XO = 0.15, PA_YO = 0.10; // Origin
    final static double PA_XS = 0.80, PA_YS = 0.80; // Size

    final static double dotSize = 5.; // Plotted point size, in pixels
    int nFcnPts = 100; // Number of points used to plot function

    // Input data
    int nPts_cowboys;
    double[] x_cowboys, y_cowboys;
    int nPts_aliens;
    double[] x_aliens, y_aliens;
    int nPts_base;
    double[] x_base, y_base;
    // Plot area in user coordinates (x-y, not pixel, space)
    double xMin = 0, xMax = 100, yMin = 0, yMax = 100;

    // Constructor with user-defined plot area
    public map(double[] xdata_cowboys, double[] ydata_cowboys, double[] xdata_aliens, double[] ydata_aliens,
            double[] xdata_base, double[] ydata_base) {

        setPreferredSize(new Dimension(500, 500));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder("Agent Map"));
        setBackground(Color.white);

        nPts_cowboys = xdata_cowboys.length;
        x_cowboys = new double[nPts_cowboys];
        y_cowboys = new double[nPts_cowboys];
        for (int i = 0; i < nPts_cowboys; i++) {
            x_cowboys[i] = xdata_cowboys[i];
            y_cowboys[i] = ydata_cowboys[i];
        }

        nPts_aliens = xdata_aliens.length;
        x_aliens = new double[nPts_aliens];
        y_aliens = new double[nPts_aliens];
        for (int i = 0; i < nPts_aliens; i++) {
            x_aliens[i] = xdata_aliens[i];
            y_aliens[i] = ydata_aliens[i];
        }

        nPts_base = xdata_base.length;
        x_base = new double[nPts_base];
        y_base = new double[nPts_base];
        for (int i = 0; i < nPts_base; i++) {
            x_base[i] = xdata_base[i];
            y_base[i] = ydata_base[i];
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g); // paint background
        Graphics2D g2 = (Graphics2D) g; // Cast Graphics object to Graphics2D

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getSize();

        // Define the corners of the plot area in pixels
        int iMin = (int) (PA_XO * d.width);
        int iMax = (int) ((PA_XO + PA_XS) * d.width);
        int jMin = (int) (PA_YO * d.height);
        int jMax = (int) ((PA_YO + PA_YS) * d.height);
        // Define the scaling between the plot area in x-y and the plot area in pixels
        double xScale = (double) (iMax - iMin) / (xMax - xMin);
        double yScale = (double) (jMax - jMin) / (yMax - yMin);

        // Draw axes.
        g2.setColor(Color.black);
        g2.draw(new Rectangle2D.Double(PA_XO * d.width, PA_YO * d.height, PA_XS * d.width, PA_YS * d.height));

        // Draw points.
        double ix_cowboys = iMin + ((x_cowboys[0] - xMin) * xScale);
        double iy_cowboys = jMax - ((y_cowboys[0] - yMin) * yScale); // JPanel y starts at TOP

        for (int i = 0; i < nPts_cowboys; i++) {
            ix_cowboys = iMin + ((x_cowboys[i] - xMin) * xScale);
            iy_cowboys = jMax - ((y_cowboys[i] - yMin) * yScale);

            g2.setColor(Color.blue);
            g2.fill(new Ellipse2D.Double(ix_cowboys - dotSize / 2., iy_cowboys - dotSize / 2., dotSize, dotSize));
        }

        double ix_aliens = iMin + ((x_aliens[0] - xMin) * xScale);
        double iy_aliens = jMax - ((y_aliens[0] - yMin) * yScale); // JPanel y starts at TOP

        for (int i = 0; i < nPts_aliens; i++) {
            ix_aliens = iMin + ((x_aliens[i] - xMin) * xScale);
            iy_aliens = jMax - ((y_aliens[i] - yMin) * yScale);

            g2.setColor(Color.red);
            g2.fill(new Ellipse2D.Double(ix_aliens - dotSize / 2., iy_aliens - dotSize / 2., dotSize, dotSize));
        }

        double ix_base = iMin + ((x_base[0] - xMin) * xScale);
        double iy_base = jMax - ((y_base[0] - yMin) * yScale); // JPanel y starts at TOP

        for (int i = 0; i < nPts_base; i++) {
            ix_base = iMin + ((x_base[i] - xMin) * xScale);
            iy_base = jMax - ((y_base[i] - yMin) * yScale);

            g2.setColor(Color.green);
            g2.fill(new Ellipse2D.Double(ix_base - dotSize / 2., iy_base - dotSize / 2., dotSize, dotSize));
        }

        // Add lables of xMin, xMax, yMin, yMax to drawing
        g2.setColor(Color.black);
        g2.drawString(String.valueOf(xMin), (float) (PA_XO * d.width - 10.),
                (float) ((PA_YO + PA_YS) * d.height + 15.));
        g2.drawString(String.valueOf(xMax), (float) ((PA_XO + PA_XS) * d.width - 20.),
                (float) ((PA_YO + PA_YS) * d.height + 15.));
        g2.drawString(String.valueOf(yMin), (float) (PA_XO * d.width - 50.), (float) ((PA_YO + PA_YS) * d.height + 5.));
        g2.drawString(String.valueOf(yMax), (float) (PA_XO * d.width - 50.), (float) (PA_YO * d.height + 5.));

    }

}
