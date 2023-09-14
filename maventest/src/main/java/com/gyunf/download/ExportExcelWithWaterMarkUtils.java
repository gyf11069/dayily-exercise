package com.gyunf.download;

import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExportExcelWithWaterMarkUtils {
    /**
     * 该方法将生成一个带有"列1"、"列2"、"列3"三列表头并且数据行为"数据1"、"数据2"、"数据3"和"数据4"、"数据5"、"数据6"并且带有文字水印"This is a watermark"的Excel文件。
     * @param filePath
     * @param dataList
     * @param watermarkText
     */
    // 导出Excel，带文字水印
    public void exportExcelWithWatermark1(String filePath, List<Object[]> dataList, String watermarkText) {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            // 创建Excel工作簿对象和工作表对象
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            // 添加水印
            Drawing drawing = sheet.createDrawingPatriarch();
            CreationHelper helper = workbook.getCreationHelper();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
//            TextShape shape = drawing.createTextbox(anchor);
//            shape.setText(watermarkText);
//            shape.setInteriorLineStyle(LineStyle.NONE);
//            shape.setLineStyle(LineStyle.NONE);
//            shape.setNoFill(true);
//            shape.setWordWrap(true);
//            shape.setVerticalAlignment(VerticalAlignment.CENTER);
//            shape.setHorizontalAlignment(HorizontalAlignment.CENTER);
//            shape.setTextDirection(TextDirection.HORIZONTAL);
//            shape.setMarginLeft(0);
//            shape.setMarginRight(0);
//            shape.setMarginTop(0);
//            shape.setMarginBottom(0);
//            shape.setAnchor(anchor);

            // 添加表头行
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("列1");
            headerRow.createCell(1).setCellValue("列2");
            headerRow.createCell(2).setCellValue("列3");

            // 添加数据行
            for (int i = 0; i < dataList.size(); i++) {
                Object[] data = dataList.get(i);
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(data[0].toString());
                dataRow.createCell(1).setCellValue(data[1].toString());
                dataRow.createCell(2).setCellValue(data[2].toString());
            }

            // 自适应列宽
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // 写入Excel到文件流
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    调用方法：

//    List<Object[]> dataList = new ArrayList<>();
//dataList.add(new Object[]{"数据1", "数据2", "数据3"});
//dataList.add(new Object[]{"数据4", "数据5", "数据6"});
//
//    exportExcelWithWatermark("D:\\test.xlsx", dataList, "This is a watermark");

    /**
     * 图片水印
     * 这个示例使用了POI的XSSFWorkbook和XSSFDrawing类来实现水印效果。其中，通过createDrawingPatriarch()方法获取到一个Drawing对象，然后通过createPicture(XSSFClientAnchor anchor, int pictureIndex)方法来创建一个新的图片，anchor对象的构造函数通过设置偏移量和图片大小实现了图片在Excel中的位置和大小。
     * 通过header.setOddHeader("&G")方法设置了页眉，并将水印图片放置在页头中。
     *
     * 改进：我们使用了图片的字节数组来创建水印，然后调用XSSFDrawing的createPicture方法来创建水印图片，最后添加到Excel的Header中。我们还通过IOUtils.toByteArray方法从水印图片文件流中获取字节数组。
     * @param filePath
     * @param dataList
     * @param watermarkText
     */
    public void exportExcelWithWatermarkForImage(String filePath, List<Object[]> dataList, String watermarkText) {
    try (OutputStream outputStream = new FileOutputStream(filePath)) {
        // 创建Excel工作簿对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // 添加水印
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFHeaderFooter header = (XSSFHeaderFooter) sheet.getHeader();
//        String headerImgId = WorkbookUtil.createImage(
//                new FileInputStream(watermarkImgPath), Workbook.PICTURE_TYPE_JPEG);
//        drawing.createPicture(new XSSFClientAnchor(), workbook.addPicture(
//                        new FileInputStream(watermarkImgPath), Workbook.PICTURE_TYPE_JPEG))
//                .resize();
//        header.setOddHeader("&G");
//        byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(watermarkImagePath));
//        int picIndex = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG);
//        XSSFPicture picture = drawing.createPicture(new XSSFClientAnchor(), picIndex);
//        picture.resize();
//        header.getOddHeader().setCenter(watermarkText);

        // 添加表头行
        XSSFRow headerRow = sheet.createRow(0);
        XSSFCell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("列1");
        XSSFCell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("列2");
        XSSFCell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("列3");

        // 添加数据行
        for (int i = 0; i < dataList.size(); i++) {
            Object[] data = dataList.get(i);
            XSSFRow dataRow = sheet.createRow(i + 1);
            XSSFCell dataCell1 = dataRow.createCell(0);
            dataCell1.setCellValue(data[0].toString());
            XSSFCell dataCell2 = dataRow.createCell(1);
            dataCell2.setCellValue(data[1].toString());
            XSSFCell dataCell3 = dataRow.createCell(2);
            dataCell3.setCellValue(data[2].toString());
        }

        // 自适应列宽
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        workbook.write(outputStream);
    } catch (IOException e) {
        e.printStackTrace();
    }
}






}
