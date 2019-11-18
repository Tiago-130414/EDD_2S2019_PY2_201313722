
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author santi
 */
public class Progress extends SwingWorker<Integer, String>{

    
    private JProgressBar jpbar;
    
    public Progress(JProgressBar jpbar) {
        this.jpbar = jpbar;
    }
    
    
    
    @Override
    protected Integer doInBackground() throws Exception {
        getJpbar().setIndeterminate(true);
        //proceso
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
        }
        getJpbar().setIndeterminate(false);
        return 0;
    }

    
    /**
     * @return the jpbar
     */
    public JProgressBar getJpbar() {
        return jpbar;
    }
    
    public void setJpbar(JProgressBar jpbar) {
        this.jpbar = jpbar;
    }


}
