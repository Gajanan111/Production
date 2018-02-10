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
	
	private File directory=null;
	
	
	public File getDirectory() {
		return directory;
	}


	public void setDirectory(File directory) {
		this.directory = directory;
	}


	public void createUserFolder(final String directoryName) throws IOException {
		final File homeDir = new File(System.getProperty("user.home"));
		directory=new File(homeDir,directoryName);
		if (!directory.exists() && !directory.mkdirs()) {
	        throw new IOException("Unable to create " + directory.getAbsolutePath());
	    }
	}
	
	public File getInvoiceTemplate() {
		URL url = getClass().getResource("/files/InvoiceTemplate_A4.xsl");
		File xsltFile = new File(url.getPath());
		return xsltFile;
	}
	
	public File createPDF() throws IOException {
		final File pdf=new File(directory,"bill.pdf");
		if(!pdf.exists())
			pdf.createNewFile();
		return pdf;
	}
	
	
	public File generateXML(InvoiceTemplateA4 template)
	{
		File input=null;
		try
		{
			input = new File(directory,URLS.INVOICE_XML);
			if(!input.exists())
				input.createNewFile();
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
		   e.printStackTrace();
		}	
		return input;
	}

	public void transformXSLToPDF(InvoiceTemplateA4 template) {
		
		OutputStream out=null;
		try {
			createUserFolder("BillDiaryPDF");
			File xsltFile = getInvoiceTemplate();
			StreamSource xmlSource = new StreamSource(generateXML(template));
			FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
			FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

			out = new java.io.FileOutputStream(createPDF());
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			// FOP
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(xmlSource, res);

		}
		catch (FileNotFoundException e) {
			System.out.println("Error in PDF Generation");
			e.printStackTrace();
		} catch (FOPException e) {
			System.out.println("Error in PDF Generation");
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			System.out.println("Error in PDF Generation");
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out!=null)
					out.close();
			} catch (IOException e) {	
				e.printStackTrace();
			}
		}
	}
}
