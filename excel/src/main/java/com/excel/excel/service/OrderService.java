package com.excel.excel.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.excel.model.Order;
import com.excel.excel.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public String readExcelFile(String filePath) throws Exception {
        List<Order> orderList = new ArrayList<>();
    
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = (filePath.endsWith(".xlsx")) ? new XSSFWorkbook(fileInputStream) : null) {
            if (workbook == null) {
                throw new IllegalArgumentException("Invalid file format");
            }
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip the first row header
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell orderNumberCell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                Cell orderAmountCell = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
    
                if (orderNumberCell != null && orderAmountCell != null) {
                    int orderNumber = getCellValueAsInt(orderNumberCell, "order number");
                    double orderAmount = getCellValueAsDouble(orderAmountCell, "order amount");
    
                    Order order = new Order(orderNumber, orderAmount);
                    orderList.add(order);
                }
            }
            orderRepository.saveAll(orderList);
        } catch (Exception e) {
            throw new Exception("Error reading Excel file", e);
        }
    
        return "Excel file processed successfully";
    }

    private int getCellValueAsInt(Cell cell, String fieldName) {
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                return Integer.parseInt(cell.getStringCellValue());
            default:
                throw new IllegalArgumentException("Unsupported cell type for " + fieldName);
        }
    }
    
    private double getCellValueAsDouble(Cell cell, String fieldName) {
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return Double.parseDouble(cell.getStringCellValue());
            default:
                throw new IllegalArgumentException("Unsupported cell type for " + fieldName);
        }
    }

}
