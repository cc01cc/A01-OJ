/*
 *        ReadExcel.java  Copyright (c) 2021. cc01cc. All rights reserved.
 *
 *        Licensed under the Apache License, Version 2.0 (the "License");
 *        you may not use this file except in compliance with the License.
 *        You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 *        Unless required by applicable law or agreed to in writing, software
 *        distributed under the License is distributed on an "AS IS" BASIS,
 *        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *        See the License for the specific language governing permissions and
 *        limitations under the License.
 */

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * @author cc01cc
 * 相关读取操作可以参考：https://poi.apache.org/components/spreadsheet/quick-guide.html#CellContents
 */


public class ReadExcel {
    public static void main(String[] args) throws IOException {
        ReadExcel objExcelFile = new ReadExcel();

        Preferences prefs = Preferences.userNodeForPackage(Initial.class);
        String excelPath = prefs.get("excelPath", System.getProperty("user.dir") + "\\src\\main\\resources");
//        String excelPath = System.getProperty("user.dir") + "\\src\\main\\resources";
        String excelName = "测试-题目.xlsx";
        String excelSheet = "单选";

        if(excelSheet.contains("single choice")||excelSheet.equals("单选")){
            objExcelFile.readSingleChoice(excelPath, excelName, excelSheet);
        }
    }

    protected void readSingleChoice(String excelPath, String excelName, String excelSheet) throws IOException {
        File file = new File(excelPath + "\\" + excelName);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(excelSheet);
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        DataFormatter formatter = new DataFormatter();
        String text;
//        System.out.println(rowCount);

        for(int i = 1; i < rowCount + 1; i++){
            Row row = sheet.getRow(i);
            for(Cell cell: row){
                if(cell != null){
                    text = formatter.formatCellValue(cell);
                    System.out.println(text);
                }
            }
        }
    }
}
