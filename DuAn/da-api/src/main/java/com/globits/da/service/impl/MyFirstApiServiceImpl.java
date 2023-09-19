package com.globits.da.service.impl;

import com.globits.da.dto.MyFirstApiDto;
import com.globits.da.service.MyFirstApiService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Phuong Oanh
 */
@Service
public class MyFirstApiServiceImpl implements MyFirstApiService {
    @Override
    public String myApi() {
        return "My First Api Service";
    }

    @Override
    public MyFirstApiDto post(MyFirstApiDto myFirstApiDto) {
        return myFirstApiDto;
    }

    @Override
    public boolean sendFileText(MultipartFile fileText) {
        try {
            File file = new File("E:\\BaiTapL1\\da-api\\src\\main\\resources\\text.txt");
            fileText.transferTo(file);

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendFileExcel(MultipartFile fileExcel) {
        try {
            File file = new File("E:\\BaiTapL1\\da-api\\src\\main\\resources\\TestL1.xlsx");
            fileExcel.transferTo(file);

            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    if(cell.getCellTypeEnum() == CellType.STRING)
                        System.out.print(cell.getStringCellValue()+ "   ");
                    else if (cell.getCellTypeEnum() == CellType.NUMERIC)
                        System.out.print(cell.getNumericCellValue()+ "   ");
                }
                System.out.println();
            }
            fileInputStream.close();
            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
