
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author santi
 */
public class visorImagen extends JPanel {

    private Image ima;

    @Override
    public void paint(Graphics g) {
      
        
        if (getClass().getResource("/Reportes/bit.png")!=null) {
            
            ima = new ImageIcon(getClass().getResource("/Reportes/bit.png")).getImage();
            g.drawImage(ima, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
        
      
    }

}
