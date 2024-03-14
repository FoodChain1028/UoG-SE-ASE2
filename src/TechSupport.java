package src;
import java.util.ArrayList;
import java.util.List;

public class TechSupport extends User{
    
    private String currentVersion;
    private List<String> updateHistory = new ArrayList<>();
    
    public TechSupport(String name) {
    	super(name);
    	this.currentVersion = "1.0";
    }
    
    public void update(String newVersion) {
        currentVersion = newVersion;
        logUpdateActivity("Updated to " + newVersion);
        updateHistory.add(newVersion);
    }
    
        
    public void logUpdateActivity(String updateDetails) {
        System.out.println(updateDetails);
    }
    
    public void revertUpdate(String toVersion){
        currentVersion = toVersion;
        logUpdateActivity("Reverted to " + toVersion);
        updateHistory.add(toVersion);
    }

    public List<String> getUpdateHistory() {
        return new ArrayList<>(updateHistory); 
    }

    public void notifyUsers() {
        System.out.println("New version " + currentVersion + " is available. Please update your system.");
            
    }

    public void BugFix() {
        System.out.println("Bug fixed in version " + currentVersion);
    }

// This skeleton need to be concised and implemented

}
