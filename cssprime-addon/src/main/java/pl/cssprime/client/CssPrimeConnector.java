package pl.cssprime.client;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

import pl.cssprime.CssPrime;

@Connect(CssPrime.class)
public class CssPrimeConnector extends AbstractExtensionConnector {

	private static final long serialVersionUID = 1L;
	private static final String CLAZZ="css";

	@Override
    public CssPrimeState getState() {
        return (CssPrimeState) super.getState();
    }
    @OnStateChange("key")
	void setKey() {
    	if(getState().cssMap==null || getState().key==null){
    		return;
    	}
    	String key = getState().key.replaceAll("\\(.+\\)", "");
    	String value =getState().cssMap.get(key);
    	if(value==null || value.isEmpty()){
    		removeStyle(key,getConnectorId(),CLAZZ);
    	}else{
    		appendStyle(value,key,getConnectorId(),CLAZZ);
    	}
    }
    private native void removeStyle(String key,String id,String clazz)
    /*-{
        var cssRule=$doc.getElementById(clazz+id)    
        if(!cssRule) {
            return;
        }
        var cur;
        if(cssRule.styleSheet){
        	cur=cssRule.styleSheet.cssText;
        }else{
        	if(cssRule.firstChild){
        		cur=cssRule.firstChild.textContent;
        	}
        }
        if(cur){
        	var cm='/';
        	if(cur.indexOf(cm+'*'+key+'*'+cm)!==-1){
        		var regex=new RegExp('\\/\\*'+key+'\\*\\/[\\s\\S]+?\\/\\*\\*\\/');
        		cur=cur.replace(regex,'');
		        if(cssRule.styleSheet){
		        	cssRule.styleSheet.cssText=cur;
		        }else{
		        	if(cssRule.firstChild){
		        		cssRule.firstChild.textContent=cur;
		        	}else{
		        		cssRule.appendChild(document.createTextNode(cur));
		        	}
		        }
    		}
        }
    }-*/;
    private native void appendStyle(String value, String key,String id,String clazz)
    /*-{
        console.log(value+"    "+key+"     "+id+"     "+clazz);
        var cssRule=$doc.getElementById(clazz+id)    
        if(!cssRule) {
            cssRule = $doc.createElement("style");
            cssRule.type = "text/css";
            cssRule.id = clazz+id;
            $doc.getElementsByTagName("head")[0].appendChild(cssRule);
        }
        var cur;
        if(cssRule.styleSheet){
        	cur=cssRule.styleSheet.cssText;
        }else{
        	if(cssRule.firstChild){
        		cur=cssRule.firstChild.textContent;
        	}
        }
        console.log(cur);
    	var cm='/';
    	var val = cm+'*'+key+'*'+cm+'\n'+value+'\n'+cm+'**'+cm;
    	console.log('val '+val);
        if(cur){
        	if(cur.indexOf(cm+'*'+key+'*'+cm)==-1){
        		cur+='\n'+val;
        	}else{
        		var regex=new RegExp('\\/\\*'+key+'\\*\\/[\\s\\S]+?\\/\\*\\*\\/');
        		console.log('regex '+regex);
        		cur=cur.replace(regex,val);
    		}
        }else{
        	cur=val;
        }
        if(cssRule.styleSheet){
        	cssRule.styleSheet.cssText=cur;
        }else{
        	if(cssRule.firstChild){
        		cssRule.firstChild.textContent=cur;
        	}else{
        		cssRule.appendChild(document.createTextNode(cur));
        	}
        }
    }-*/;
	@Override
	protected void extend(ServerConnector target) {

	}
	@Override
	public void onUnregister() {
		removeElement(getConnectorId(),CLAZZ);
		super.onUnregister();
	}
	private native void removeElement(String id,String clazz)/*-{
        if($doc.getElementById(clazz+id)) {
        	$doc.getElementsByTagName("head")[0].removeChild($doc.getElementById(clazz+id));
        }
	 }-*/;
}
