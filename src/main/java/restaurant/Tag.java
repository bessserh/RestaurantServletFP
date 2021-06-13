package restaurant;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Tag extends SimpleTagSupport {

    String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws IOException {
        getJspContext().getOut().write("<span style='font-size:30px'>" + name + " </span>");
    }
}