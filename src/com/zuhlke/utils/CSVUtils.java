package com.zuhlke.utils;

import java.io.FileReader;
import java.io.IOException;

import com.zuhlke.dbconnect.MysqlConnect;

import au.com.bytecode.opencsv.CSVReader;

/*
 * @Author : Pritam Patil
 */
public class CSVUtils {
    
    /**
     * This is the main method to run the main implementation
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    	
    	//database connection 
    	@SuppressWarnings("unused")
		MysqlConnect mySqlConnnect = new MysqlConnect();
    	
        String csvFile = "C:\\Users\\pritam.patil\\Downloads\\zuhlke-coding-challenge\\sales.csv";
        CSVReader reader = null;
        
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                String query = " insert into store_order (ID, ORDER_ID, ORDER_DATE, SHIP_DATE, SHIP_MODE, CUSTOMER_ID, CUSTOMER_NAME, SEGMENT, COUNTRY, CITY, STATE, POSTAL_CODE, REGION, PRODUCT_ID, CATEGORY, SUB_CATEGORY, PRODUCT_NAME, SALES, QUANTITY, DISCOUNT, PROFIT)"
                        + " values ("+line[0]+", '"+line[1]+"', '"+line[2]+"', '"+line[3]+"', '"+line[4]+"', '"+line[5]+"', '"+line[6].replace("'", "")+"', '"+line[7]+"', '"+line[8]+"', '"+line[9]+"', '"+line[10]+"', '"+line[11]+"', '"+line[12]+"', '"+line[13]+"', '"+line[14]+"', '"+line[15]+"', '"+line[16].replace("'", "")+"', "+line[17]+", "+line[18]+", "+line[19]+", "+line[20]+")";
                System.out.println(query);
                int res = MysqlConnect.getDbCon().insert(query);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
