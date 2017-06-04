package pl.cssprime;

import java.util.HashMap;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.UI;

import pl.cssprime.client.CssPrimeState;

// This is the server-side UI component that provides public API 
// for CssPrime
public class CssPrime extends AbstractExtension {

	private static final long serialVersionUID = 1L;
	private UI targetUI;
	private final String DEFAULT_KEY="default";
    public CssPrime(UI targetUI) {
    	super.extend(targetUI);
    	setTargetUI(targetUI);
    	getState().cssMap = new HashMap<>();
    }

	public void setStyle(String style) {
		setStyle(DEFAULT_KEY,style);
	}
	public void setStyle(String key,String style) {
		if(key==null || key.isEmpty()){
			setStyle(style);
		}else{
			getState().cssMap.put(key, style);
			getState().key=generateUniqeRequestKey(key);
		}
	}

    public void removeStyle(String key){
    	getState().cssMap.remove(key);
    	getState().key=generateUniqeRequestKey(key);
    }
    public void removeStyle(){
    	removeStyle(DEFAULT_KEY);
    }
    public String getStyle() {
    	return getState().cssMap.get(DEFAULT_KEY);
    }
    public String getStyle(String key) {
    	return getState().cssMap.get(key);
    }
    private String generateUniqeRequestKey(String key) {
    	return key+"("+System.currentTimeMillis()+")";
    }
    @Override
    protected CssPrimeState getState() {
        return (CssPrimeState) super.getState();
    }

	public UI getTargetUI() {
		return targetUI;
	}

	public void setTargetUI(UI targetUI) {
		this.targetUI = targetUI;
	}
	public void setUrlResource(String url){
		getState().urlResource=url;
	}
   
}
