package com.inacap.tag;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/**
 *
 * @author LeoGuitar
 */
public class TagImage extends SimpleTagSupport {

    private Object arreglo;
    private String ancho;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
            byte[] data = (byte[]) arreglo;
            out.println("<img class='materialboxed' width='" + ancho + "' src='data:image/*;base64," + Base64.encode(data) + "' />");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in TagImage tag", ex);
        }
    }

    public void setArreglo(Object arreglo) {
        this.arreglo = arreglo;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }
    
}
