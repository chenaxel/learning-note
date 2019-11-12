package com.axel.wsdl;

import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wstool.creator.RequestTemplateCreator;
import com.predic8.wstool.creator.SOARequestCreator;
import groovy.xml.MarkupBuilder;

import java.io.*;
import java.net.URL;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/11/12
 */
public class CreateSoapRequestTemplate {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = new URL("http://cdcdyy.natapp1.cc/wsdl/ICBHRPXlygService").openStream();
		WSDLParser parser = new WSDLParser();
		Definitions wsdl = parser.parse(inputStream);
		StringWriter writer = new StringWriter();
		SOARequestCreator creator = new SOARequestCreator(wsdl, new RequestTemplateCreator(), new MarkupBuilder(writer));
		creator.createRequest("ICBHRPXlygService", "WriteData", "ICBHRPXlygServicebinding");
		System.out.println(writer);
	}
}
