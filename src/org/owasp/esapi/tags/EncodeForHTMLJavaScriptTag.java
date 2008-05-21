package org.owasp.esapi.tags;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.IEncoder;

public class EncodeForHTMLJavaScriptTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public EncodeForHTMLJavaScriptTag() {}
	
	
	
	public int doStartTag() {
					
		return EVAL_BODY_TAG;

	}

	
	public int doAfterBody() throws JspTagException {

		
		try {
			
			BodyContent body = getBodyContent();
			
			String content = body.getString();
			JspWriter out = body.getEnclosingWriter();
			
			IEncoder e = ESAPI.encoder();
			
			out.println( e.encodeForJavascript(content) );
			body.clearBody();
			
			return EVAL_PAGE;
			
		} catch (IOException ioe) {
			throw new JspTagException("error in encodeForHTML tag doAfterBody()",ioe);
		}
		
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
