package pl.demo;

import pl.CssPrime;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("CssPrime Add-on Demo")
@SuppressWarnings("serial")
@PreserveOnRefresh
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final CssPrime component = new CssPrime(this);
        component.setStyle("body{background:red;}");
        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        setContent(layout);
        TextArea key = new TextArea("key");
        layout.addComponent(key);
        TextArea area = new TextArea("value");
        layout.addComponent(area);
        Button append = new Button("Append");
        layout.addComponent(append);
        append.addClickListener(e->{
        	component.setStyle(key.getValue(),area.getValue());
        });
        Button remove = new Button("Remove extension");
        layout.addComponent(remove);
        remove.addClickListener(e->{
        	UI.getCurrent().removeExtension(component);
        });
    }
}
