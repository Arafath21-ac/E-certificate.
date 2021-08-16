package main;

import java.io.FileOutputStream;
//import java.net.ContentHandlerFactory;
import java.time.LocalDate;

import com.itextpdf.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.TextField;

public class CertificateGeneration {

	public static void main(String[] args)throws Exception {
		String destination_location="D:\\HIT_2021\\GenereteCertificate\\src\\Output\\Output_Certificate.pdf";
		String existing_certificate="D:\\HIT_2021\\GenereteCertificate\\src\\main\\Certificate2.pdf";
		String ceertiificate_img="D:\\HIT_2021\\GenereteCertificate\\src\\main\\dow23.jpg";
		LocalDate date=java.time.LocalDate.now();
		System.out.println("Started to create Pdf");
				try {
					PdfReader reader=new PdfReader(existing_certificate);
					PdfStamper stamper=new PdfStamper(reader,new FileOutputStream(destination_location));
					
					Image image= Image.getInstance(ceertiificate_img);
					PdfContentByte content=stamper.getUnderContent(1);
					image.setAbsolutePosition(0, 0);
					
					image.scaleToFit(PageSize.A3.getWidth(),PageSize.A3.getHeight());
					content.addImage(image);
					
					String name="Yasar";
					String Common="is completed the course"
							+"by demonstrating theratically and pratically understanding of";
					String courseName="JAVA";
					String company_name="HIT";
					String signature="Mr Shoaib";
					
					PrintingData(stamper,name, BaseFont.TIMES_BOLD, 34,BaseColor.BLACK,700 ,370,140, 280);
					PrintingData(stamper,Common, BaseFont.TIMES_BOLD, 16,BaseColor.BLACK,680 ,80,200, 320);
					PrintingData(stamper,courseName, BaseFont.TIMES_BOLD, 34,BaseColor.BLACK,650 ,80,200, 275);
					PrintingData(stamper,company_name, BaseFont.TIMES_BOLD, 28,BaseColor.BLACK,645 ,100,200, 160);
					PrintingData(stamper,signature, BaseFont.TIMES_BOLD, 16,BaseColor.BLACK,950 ,100,200, 170);
					PrintingData(stamper,date.toString(), BaseFont.TIMES_BOLD, 16,BaseColor.BLACK,320 ,100,200, 170);
					
					stamper.close();
					reader.close();
				}catch (IOException e) {
					e.printStackTrace();
				} 
					System.out.println("Created");
	}

	private static void PrintingData(PdfStamper Lstamper,
			String data,String fontType,float fontSize,BaseColor fontColor,
			float lx,float ly,float ux,float uy )throws Exception {
		try {
			TextField textField = new TextField(Lstamper.getWriter(),new Rectangle(lx,ly,ux,uy),"newTextField");
			textField.setOptions(TextField.MULTILINE | TextField.READ_ONLY);
			textField.setAlignment(Element.ALIGN_CENTER);
			textField.setTextColor(fontColor);
			BaseFont baseFont= BaseFont.createFont(fontType,BaseFont.WINANSI,BaseFont.EMBEDDED);
			textField.setFont(baseFont);
			textField.setFontSize(fontSize);
			textField.setText(data);
			Lstamper.addAnnotation(textField.getTextField(), 1);
			
		} catch (Exception ex) {
			System.out.println("Exception While Setting Data:"+data);
			ex.printStackTrace();
		}
	}
}
