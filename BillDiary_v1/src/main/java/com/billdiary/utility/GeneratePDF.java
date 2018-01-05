package com.billdiary.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Component;

import com.billdiary.model.InvoiceTemplateA4;
import com.billdiary.model.Product;

@Component
public class GeneratePDF {
	
	public void generateXML(InvoiceTemplateA4 template)
	{
		try
		{
			File input = new File(URLS.INVOICE_XML);
			PrintWriter writer = new PrintWriter(input, "UTF-8");
			writer.println("<?xml version='1.0'?><Invoice><companyname>"+template.getCompanyName()+"</companyname>");
			writer.println("<Logo>"+/*template.getLogo().getImage()*/"image"+"</Logo>");
			writer.println("<NameOfClient>"+template.getCustomerName()+"</NameOfClient>");
			writer.println("<date>"+LocalDate.now()+"</date>");
			writer.println("<Address>"+template.getCustomerAddress()+"</Address>");
			writer.println("<phone>"+template.getMobileNo()+"</phone>");
			writer.println("<invoiceNO>"+template.getInvoiceNO()+"</invoiceNO>");
			List<Product> products=template.getProducts();
			if(null!=products) {
				int id=1;
				products.forEach(product->{	
					writer.println("<products><id>"+product.getProductId()+"</id>");
					writer.println("<name>"+product.getName()+"</name>");
					writer.println("<Quantity>"+product.getQuantity()+"</Quantity>");
					writer.println("<amtperquantity>"+product.getRetailPrice()+"</amtperquantity>");
					writer.println("<total>"+product.getTotalPrice()+"</total>");
					writer.println("</products>");
					
				});
			}	
			writer.println("<total>"+template.getTotalAmount()+"</total>");
			writer.println("<discount>"+template.getDiscount()+"</discount>");
			writer.println("<Totalafterdiscount>"+template.getFinalAmount()+"</Totalafterdiscount>");
			writer.println("</Invoice>");			
			writer.close();    
		} catch (IOException e) 
		{
		   // do something
		}	
	}

	public void transformXSLToPDF() {
		URL url = getClass().getResource("/files/InvoiceTemplate_A4.xsl");
		File xsltFile = new File(url.getPath());
		StreamSource xmlSource = new StreamSource(new File(URLS.Invoice_Template_A4_XML_PATH + "//Invoice.xml"));
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		OutputStream out=null;
		try {
			out = new java.io.FileOutputStream(URLS.PDF_GENERATION_PATH + "//Bill.pdf");
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
			
			// FOP
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(xmlSource, res);
			
		} catch (FileNotFoundException e) {
			System.out.println("Error in PDF Generation");
			e.printStackTrace();
		} catch (FOPException e) {
			System.out.println("Error in PDF Generation");
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			System.out.println("Error in PDF Generation");
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
