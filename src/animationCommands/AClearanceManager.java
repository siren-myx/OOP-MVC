package animationCommands;

import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.Row;
import util.annotations.StructurePattern;
@StructurePattern("No Pattern")
public class AClearanceManager implements ClearanceManager{
    @Row(0)
    @ComponentWidth(210)
    @ComponentHeight(34)
    public synchronized void waitForProceed() {
        try { wait(); }
        catch (Exception e) { e.printStackTrace();}
    }
    
    @Row(1)
    @ComponentWidth(210)
    @ComponentHeight(34)
    public synchronized void proceed() {
        notify();
    }
    
    @Row(2)
    @ComponentWidth(210)
    @ComponentHeight(34)
    public synchronized void proceedAll() {
        notifyAll();
    }

}
