package com.gyunf.download;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class HSSFWorkbookWithWatermark {
//    public static void main(String[] args) {
//        HSSFWorkbook wb = new HSSFWorkbook();
//        FileOutputStream fileOut = null;
//        try {
//            fileOut = new FileOutputStream("Test.xls");
//            HSSFSheet ws = wb.createSheet("testSheet");
//            HSSFPatriarch dp = ws.createDrawingPatriarch();
//            HSSFClientAnchor anchor = new HSSFClientAnchor
//                    (0, 0, 1023, 255, (short) 2, 4, (short) 13, 26);
//            HSSFTextbox txtbox = dp.createTextbox(anchor);
//            HSSFRichTextString rtxt = new HSSFRichTextString("test");
//            HSSFFont font = wb.createFont();
//            font.setColor((short) 27);
//            font.setBold(false);
//            font.setFontHeightInPoints((short) 192);
//            font.setFontName("Verdana");
//            rtxt.applyFont(font);
//            txtbox.setString(rtxt);
//            txtbox.setLineStyle(HSSFShape.LINESTYLE_NONE);
//            txtbox.setNoFill(true);
//            wb.write(fileOut);
//            fileOut.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        // 从文件加载Excel工作簿

//        /*
//        * 报错：Exception in thread "main" java.io.IOException: Your InputStream was neither an OLE2 stream, nor an OOXML stream
//        * */
//        String inputFile = "/tempFiles/example.xlsx";//如果路径写成："tempFiles/example.xlsx"会报找不到文件的错误
//        InputStream inp = HSSFWorkbookWithWatermark.class.getResourceAsStream(inputFile);
//        Workbook workbook = WorkbookFactory.create(inp);


        Workbook workbook = WorkbookFactory.create(new File("maventest/src/main/resources/tempFiles/example.xlsx"));


        // 将文本添加到水印
        String waterMarkText = "My Watermark";
        XSSFSimpleShape waterMark = createWaterMarkShape(workbook, waterMarkText);

        // 获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);

        // 在工作表上添加水印
        addWaterMark(sheet, waterMark);

        // 将工作簿写入文件
        FileOutputStream out = new FileOutputStream("excel-watermark-example.xlsx");
        workbook.write(out);
        out.close();

        System.out.println("Watermark added successfully.");
    }

    /**
     * 创建包含文本的水印对象
     */
    private static XSSFSimpleShape createWaterMarkShape(Workbook workbook, String text) {
        // 创建形状并设置其样式
        XSSFDrawing drawing = ((XSSFSheet) workbook.getSheetAt(0)).createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor();
        anchor.setCol1(0);
        anchor.setRow1(0);
        anchor.setCol2(0);
        anchor.setRow2(0);
        XSSFSimpleShape shape = drawing.createSimpleShape(anchor);
        shape.setShapeType(ShapeTypes.RECT);
        shape.setFillColor(0,0,0);
        shape.setLineStyleColor(0,0,0);

        // 在形状中添加文本，并设置文本的样式
        XSSFRichTextString richText = new XSSFRichTextString(text);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontHeightInPoints((short) 48);
        font.setColor(IndexedColors.GREY_25_PERCENT.getIndex());
        font.setFontName("Arial");
        richText.applyFont(font);
        shape.setText(richText);

        return shape;
    }

    /**
     * 在工作表上添加水印对象
     */
    private static void addWaterMark(Sheet sheet, XSSFSimpleShape waterMark) {
//        // 获取工作表的页边距
//        PrintSetup printSetup = sheet.getPrintSetup();
//        double margin = 0.5;
//        double xMargin = margin * 0.3937 * 72.0; // 英寸转化为点
//        double yMargin = margin * 0.3937 * 72.0;
//        double width = (printSetup.getPaperSize() == PrintSetup.A4_PAPERSIZE) ? 210.0 : 297.0;
//        double height = (printSetup.getPaperSize() == PrintSetup.A4_PAPERSIZE) ? 297.0 : 420.0;
//
//        // 计算水印的宽度和高度，使其适合于页面大小
//        double waterMarkWidth = width - xMargin * 2.0;
//        double waterMarkHeight = height - yMargin * 2.0;
//        waterMark.setAnchor(new XSSFClientAnchor((int)xMargin, (int)yMargin, (int)waterMarkWidth, (int)waterMarkHeight, 0, 0, 1, 1));
//
//
//        // 将水印添加到Drawing层
//        sheet.createDrawingPatriarch().addShape(waterMark);

        // 获取工作表的绘制图纸
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();

        // 将水印添加到工作表上（重叠在所有单元格上）
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            int firstCol = range.getFirstColumn();
            int lastCol = range.getLastColumn();
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, firstCol, firstRow, lastCol + 1, lastRow + 1);
            anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
            drawing.createSimpleShape(anchor).setShapeType(ShapeTypes.RECT);
        }

    }

}
