package com.fireworks.kundalini.helper;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fireworks.kundalini.crud.resource.CustomerAddress;
import com.fireworks.kundalini.crud.resource.CustomerOrder;
import com.fireworks.kundalini.crud.resource.OrderList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Helper {

	public Environment env;

	Document document;

	@Autowired
	public Helper(Environment env) {
		this.env = env;
	}

	public void generatePDF(CustomerOrder customerOrder, String filename) {
		this.document = new Document(PageSize.A4);

		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(env.getProperty("pdfPath") + customerOrder.getCustomerMail() + "-" + filename + ".pdf"));
			this.document.open();

			PdfPTable table = new PdfPTable(2);
			table.addCell(createImageCell("kundalini.jpg"));

			Paragraph totalDetails = new Paragraph();
			totalDetails.add(fillCompanyDetails());
			totalDetails.add(fillCustomerDetails(customerOrder));
			table.addCell(totalDetails);
			this.document.add(table);
			fillItemDetails(customerOrder.getOrderDetails().getOrderList());
			document.close();
			writer.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public PdfPCell createImageCell(String path) throws Exception {
		Image img = Image.getInstance(env.getProperty("imagePath") + path);
		return new PdfPCell(img, true);
	}

	private Paragraph fillCompanyDetails() {
		Paragraph companyDetails = new Paragraph();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 16, Font.BOLD, BaseColor.RED);
		Paragraph companyName = new Paragraph("Kundalini Fireworks", font);
		font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
		Phrase mail = new Phrase("Mail Us:", font);
		font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC, BaseColor.BLUE);
		Phrase mailDetails = new Phrase(" LearnItWell2018@gmail.com", font);
		Paragraph mailUs = new Paragraph();
		mailUs.add(mail);
		mailUs.add(mailDetails);

		font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
		Phrase callUs = new Phrase("Call Us:", font);
		font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC, BaseColor.BLUE);
		Phrase mobileDetails = new Phrase("+91 9051915545", font);
		Paragraph callUsDetails = new Paragraph();
		callUsDetails.add(callUs);
		callUsDetails.add(mobileDetails);

		companyDetails.add(companyName);
		companyDetails.add(mailUs);
		companyDetails.add(callUsDetails);

		return companyDetails;

	}

	private Paragraph fillCustomerDetails(CustomerOrder customerOrder) {

		Paragraph customerDetails = new Paragraph();

		Font font = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLDITALIC, BaseColor.BLACK);
		Paragraph customerCopy = new Paragraph("CUSTOMER COPY", font);

		font = FontFactory.getFont(FontFactory.COURIER, 10, Font.NORMAL, BaseColor.BLUE);
		Paragraph customerMail = new Paragraph(customerOrder.getCustomerMail(), font);
		font = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLUE);
		Paragraph customerMobile = new Paragraph(customerOrder.getCustomerMobile(), font);

		customerDetails.add(customerCopy);
		customerDetails.add(customerMail);
		customerDetails.add(customerMobile);

		CustomerAddress customerAddress = customerOrder.getCustomerAddress().get(0);

		font = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.DARK_GRAY);
		Paragraph street = new Paragraph(customerAddress.getStreet(), font);
		customerDetails.add(street);
		Paragraph room = new Paragraph(customerAddress.getRoomorflatno(), font);
		customerDetails.add(room);
		Paragraph pin = new Paragraph(customerAddress.getPincode(), font);
		customerDetails.add(pin);
		Paragraph near = new Paragraph(customerAddress.getNearestLandMark(), font);
		customerDetails.add(near);

		return customerDetails;

	}

	private PdfPCell provideType1Cell(String content) {
		Font font = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, BaseColor.BLACK);
		return new PdfPCell(new Phrase(content, font));
	}

	private PdfPCell provideType2Cell(String content, BaseColor baseColor) {
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBackgroundColor(baseColor);
		return cell;
	}

	private PdfPCell provideHeaderCell(String content, BaseColor baseColor) {
		Font font = FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setBackgroundColor(baseColor);
		return cell;
	}

	private void fillItemDetails(List<OrderList> orderList) throws Exception {
		PdfPTable itemTable;

		Float total = new Float(0);

		itemTable = new PdfPTable(1);
		itemTable.addCell(provideType2Cell("Order Details", BaseColor.BLUE));
		this.document.add(itemTable);

		itemTable = new PdfPTable(6);

		itemTable.setWidths(new float[] { 2, 2, 3, 1, 1, 1 });

		itemTable.addCell(provideHeaderCell("Product Name", BaseColor.ORANGE));
		itemTable.addCell(provideHeaderCell("Product Image", BaseColor.CYAN));
		itemTable.addCell(provideHeaderCell("Product Details", BaseColor.PINK));
		itemTable.addCell(provideHeaderCell("Unit Price", BaseColor.GREEN));
		itemTable.addCell(provideHeaderCell("Product Count", BaseColor.YELLOW));
		itemTable.addCell(provideHeaderCell("Total", BaseColor.RED));

		this.document.add(itemTable);

		for (OrderList order : orderList) {

			itemTable = new PdfPTable(6);

			itemTable.setWidths(new float[] { 2, 2, 3, 1, 1, 1 });

			itemTable.addCell(provideType1Cell(order.getProductId()));
			itemTable.addCell(createImageCell(order.getItemImage()));
			itemTable.addCell(provideType1Cell(order.getItemDesc()));
			itemTable.addCell(provideType1Cell(order.getItemPrice()));
			itemTable.addCell(provideType1Cell(order.getItemCount()));
			total = total + Float.parseFloat(order.getItemPrice()) * Integer.parseInt(order.getItemCount());
			itemTable.addCell(provideType1Cell(
					Float.toString(Float.parseFloat(order.getItemPrice()) * Integer.parseInt(order.getItemCount()))));

			this.document.add(itemTable);

		}

		itemTable = new PdfPTable(2);

		itemTable.setWidths(new float[] { 8, 2 });

		itemTable.addCell(provideType2Cell("Total Amount in Rupees :", BaseColor.RED));
		itemTable.addCell(provideType2Cell(Float.toString(total), BaseColor.BLACK));

		this.document.add(itemTable);

	}

}
