package com.fireworks.kundalini.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.fireworks.kundalini.crud.resource.ItemTypeList;

public class ProductCodeList {

	private static HashMap<String, ItemTypeList> RODUCT_LIST = new HashMap<>();

	public static HashMap<String, ItemTypeList> getProductDetails () {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader("C:\\data\\codelist\\PRODUCT_DETAILS.csv"));
			ItemTypeList itemTypeListe = null;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				if (!"PRODUCT_ID".equals(data[0])) {
					itemTypeListe = new ItemTypeList();
					itemTypeListe.setProductId(data[0]);
					itemTypeListe.setBrand(data[1]);
					itemTypeListe.setItemName(data[2]);
					itemTypeListe.setItemPrice(data[3]);
					itemTypeListe.setItemDesc(data[4]);
					itemTypeListe.setProductImgPath("assets/items/" + data[5]);
					itemTypeListe.setItemActive(data[6]);
					itemTypeListe.setItemStock(data[7]);
					itemTypeListe.setOffer("10");
					RODUCT_LIST.put(data[0], itemTypeListe);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return RODUCT_LIST;
	}

}
