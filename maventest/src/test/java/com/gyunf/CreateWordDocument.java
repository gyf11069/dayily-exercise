package com.gyunf;
import com.gyunf.entity.GridData;
import com.gyunf.entity.GridTable;
import com.gyunf.entity.TableforString;
import com.gyunf.entity.TitleData;
//import com.lowagie.text.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldSimpleField;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CreateWordDocument {

    private XWPFDocument document;
    private int currentPage;

    public CreateWordDocument(String filePath) throws IOException {
        document = new XWPFDocument();

        FileOutputStream fos = new FileOutputStream(filePath);
        document.write(fos);
        fos.close();
        currentPage = 1;
    }

    public void addTitle(String title) {
        FontSetUtil.title(document,title);
        newPage();
    }

    public void addTitle(String title,String subTitle) {
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();


        FontSetUtil.title(document,title);
        FontSetUtil.subtitle(document,subTitle);
        newPage();
    }

    public void addSubtitle(String subtitle) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(subtitle);
        run.setBold(true);
        run.setFontSize(14);
    }

    public void addText(String text, String fontName, int fontSize, boolean bold)
            throws IOException {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily(fontName);
        run.setFontSize(fontSize);
        run.setBold(bold);
    }

    public void addText(String text, XWPFParagraph paragraph)
            throws IOException {
        XWPFRun run = paragraph.createRun();
        run.setText(text);
    }

    public void addTable(String[][] data, String[] headers) {
        XWPFTable table = document.createTable(data.length, headers.length);

        for (int i = 0; i < headers.length; i++) {
            XWPFTableCell headerCell = table.getRow(0).getCell(i);
//            CTTblWidth ctTblWidth = headerCell.getCTTc().addNewTcPr().addNewTcW();
//            ctTblWidth.setW(BigInteger.TEN);
            headerCell.setText(headers[i]);
            headerCell.setColor("8DB3E2FF"); // 设置表头单元格颜色
        }
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                XWPFTableCell cell = table.getRow(i).getCell(j);
                cell.setText(data[i][j]);
            }
        }
    }

    public void addTableforGridTable(GridTable gridTable)
            throws  IOException {
        XWPFParagraph paragraph = document.createParagraph();
        if (gridTable.getRowsList().size() == 0) {
            addText("暂无数据", paragraph);
        } else {

            paragraph.createRun().setText("");

            List<Map<String, Object>> rowslist = gridTable.getRowsList();
            String[] rowsHead = gridTable.getKeys();
            int columnSize = rowsHead.length;
            int lineSize = rowslist.size() + 1;
            XWPFTable table = document.createTable(columnSize + (gridTable.isSerial() ? 1 : 0), lineSize);
            /*设置表格居中*/
            setTableLocation(table,"center");
            table.setWidth(100);
            /*设置列宽*/
//            if (gridTable.isSerial()) {
//                float[] width = gridTable.getWidths();
//                float mywidth[] = new float[width.length + 1];
//                mywidth[0] = 100;
//                for (int i = 0; i < width.length; i++) {
//                    mywidth[i + 1] = width[i];
//                }
//                table.setWidth(mywidth);
//            } else {
//                table.setWidth(gridTable.getWidths());
//            }

            // 表头开始
            // 添加序号
            if (gridTable.isSerial()) {
                XWPFTableCell headerCell = table.getRow(0).getCell(0);
                headerCell.setText("序号");
                headerCell.setColor("8DB3E2FF"); // 设置表头单元格颜色
            }
            // 添加表头
            for (int i = 0; i < columnSize; i++) {
                XWPFTableCell headerCell = table.getRow(0).getCell(i);
                headerCell.setText(gridTable.getNames()[i]);
                headerCell.setColor("8DB3E2FF"); // 设置表头单元格颜色
            }
            // table.endHeaders();// 表头结束
            int serial = 1;// 序号
            // 主体开始
            for (Map<String, Object> map : rowslist) {
                serial++;
                // 添加序号
                if (gridTable.isSerial()) {
                    XWPFTableCell headerCell = table.getRow(serial).getCell(0);
                    headerCell.setText("" + serial);
                }

                XWPFTableCell headerCell;
                if (gridTable.isSerial()) {
                    for (int i = 1; i < columnSize; i++) {
                        if (map.get(gridTable.getKeys()[i]) == null) {
                            headerCell = table.getRow(serial).getCell(i);
                            headerCell.setText("");
                        } else {
                            headerCell = table.getRow(serial).getCell(i);
                            headerCell.setText("" + map.get(gridTable.getKeys()[i]));
                        }
                    }

                }else {
                    for (int i = 0; i < columnSize; i++) {
                        if (map.get(gridTable.getKeys()[i]) == null) {
                            headerCell = table.getRow(serial).getCell(i);
                            headerCell.setText("");
                        } else {
                            headerCell = table.getRow(serial).getCell(i);
                            headerCell.setText("" + map.get(gridTable.getKeys()[i]));
                        }
                    }
                }
            }
        }
    }


    /**
     * 设置表格的宽度
     * @param table
     */
    public void editTableWidth(XWPFTable table){
        CTTblPr tblPr = table.getCTTbl().getTblPr();
//        默认TblW的type属性为STTblWidth.AUTO,即自动伸缩。所以要调整为指定类型：STTblWidth.DXA
        tblPr.getTblW().setType(STTblWidth.DXA);
        // 设置表格的宽度
        tblPr.getTblW().setW(new BigInteger("7000"));
    }

    /**
     * 设置表格行高
     * @param row
     */
    public void editRowHeight(XWPFTableRow row){
        CTTrPr trPr = row.getCtRow().addNewTrPr();
        CTHeight ht = trPr.addNewTrHeight();
//        设置行高
        ht.setVal(BigInteger.valueOf(360));
    }

    /**
     * 设置单元格颜色
     * @param cell
     */
    public void editCellColor(XWPFTableCell cell){
        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTShd ctshd = tcpr.addNewShd();
        ctshd.setColor("auto");
        ctshd.setVal(STShd.CLEAR);
//        设置颜色
        ctshd.setFill("A7BFDE");
    }

    /**
     * 设置单元格内容垂直居中
     * @param cell
     */
    public void editCellCenter(XWPFTableCell cell){
        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTVerticalJc va = tcpr.addNewVAlign();
//        设置垂直居中
        va.setVal(STVerticalJc.CENTER);
    }

    /**
     * 设置单元格的宽度
     * @param cell
     */
    public void editCellWidth(XWPFTableCell cell){
        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTTblWidth cellw = tcpr.addNewTcW();
//        默认type属性为STTblWidth.AUTO,即自动伸缩。所以要调整为指定类型：STTblWidth.DXA
        cellw.setType(STTblWidth.DXA);
        // 设置单元格宽度
        cellw.setW(BigInteger.valueOf(360*5));
    }

    /**
     * 获取所有表格
     * @param docx
     */
    public void getTables(XWPFDocument docx) {
        Iterator<XWPFTable> tables = docx.getTablesIterator();
        while (tables.hasNext()) {
            XWPFTable table = tables.next();
//            获取当前表格所有行
            List<XWPFTableRow> rows = table.getRows();
//            获取当前行的所有列
            List<XWPFTableCell> tableCells = rows.get(0).getTableCells();
        }
    }

    /**
     * 获取所有段落
     * @param docx
     */
    public void getParagraphs(XWPFDocument docx) {
        List<XWPFParagraph> paragraphs = docx.getParagraphs();
//        使用增强for循环会获取不到段落对象
        for (int i = 0; i < paragraphs.size(); i++) {
//            打印当前段落的字符串
            System.out.println(paragraphs.get(i).getText());
        }
    }

    /**
     * 设置单元格水平位置和垂直位置
     *
     * @param xwpfTable
     * @param verticalLoction    单元格中内容垂直上TOP，下BOTTOM，居中CENTER，BOTH两端对齐
     * @param horizontalLocation 单元格中内容水平居中center,left居左，right居右，both两端对齐
     */
    public static void setCellLocation(XWPFTable xwpfTable, String verticalLoction, String horizontalLocation) {
        List<XWPFTableRow> rows = xwpfTable.getRows();
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                CTTc cttc = cell.getCTTc();
                CTP ctp = cttc.getPList().get(0);
                CTPPr ctppr = ctp.getPPr();
                if (ctppr == null) {
                    ctppr = ctp.addNewPPr();
                }
                CTJc ctjc = ctppr.getJc();
                if (ctjc == null) {
                    ctjc = ctppr.addNewJc();
                }
                ctjc.setVal(STJc.Enum.forString(horizontalLocation)); //水平居中
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.valueOf(verticalLoction));//垂直居中
            }
        }
    }

    /**
     * 设置表格位置
     *
     * @param xwpfTable
     * @param location  整个表格居中center,left居左，right居右，both两端对齐
     */
    public static void setTableLocation(XWPFTable xwpfTable, String location) {
        CTTbl cttbl = xwpfTable.getCTTbl();
        CTTblPr tblpr = cttbl.getTblPr() == null ? cttbl.addNewTblPr() : cttbl.getTblPr();
        CTJc cTJc = tblpr.addNewJc();
        cTJc.setVal(STJc.Enum.forString(location));
    }





    public void addFooter() throws IOException {
        XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
        if (headerFooterPolicy == null) {
            headerFooterPolicy = document.createHeaderFooterPolicy();
        }

        XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
        XWPFParagraph paragraph = footer.createParagraph();

        CTP ctp = paragraph.getCTP();
        CTPPr ppr = ctp.getPPr();
        if (ppr == null) {
            ppr = ctp.addNewPPr();
        }

        CTJc jc = ppr.getJc();
        if (jc == null) {
            jc = ppr.addNewJc();
        }
        jc.setVal(STJc.RIGHT); // 页码居右对齐

        XWPFRun run = paragraph.createRun();
        run.setText("页码：");
        run.setFontSize(12);

        CTSimpleField ctSimpleField = ctp.addNewFldSimple();
        ctSimpleField.setInstr("PAGE");
        ctSimpleField.setDirty(STOnOff.TRUE); // 强制刷新页码
    }

    public void save() throws IOException {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.getText(0) != null && run.getText(0).contains("PAGE")) {
                    run.setText(run.getText(0).replace("PAGE", String.valueOf(currentPage)), 0);
                }
            }
        }

        currentPage++;

        FileOutputStream fos = new FileOutputStream("/Users/gyunf/Documents/ideaProjects/dayily-exercise/maventest/src/main/resources/worddown/output_file_path.docx");
        document.write(fos);
        fos.close();
        document.close();
    }

    public void newPage() {
        // 创建分节符号（section break）以分隔页面
        XWPFParagraph sectionBreak = document.createParagraph();
        sectionBreak.setPageBreak(true);
    }

    public void addTitleData(TitleData data) throws
            IOException {

        // 添加标题
        switch (data.getTitleDegree().length) {
            case 1:
                addText(data.getTitleDegreetoString() + " " + data.getTitle(),
                        FontSetUtil.titleone(document));
                break;
            case 2:
                addText(data.getTitleDegreetoString() + " " + data.getTitle(),
                        FontSetUtil.titletwo(document));
                break;
            case 3:
                addText(data.getTitleDegreetoString() + " " + data.getTitle(),
                        FontSetUtil.titlethree(document));
                break;
            case 4:
                addText(data.getTitleDegreetoString() + " " + data.getTitle(),
                        FontSetUtil.title4(document));
                break;
            case 5:
                addText(data.getTitleDegreetoString() + " " + data.getTitle(),
                        FontSetUtil.title5(document));
                break;
        }
        // 添加内容
        // 类型为String gridData gridTable
        List<Object> objects = data.getObjects();
        if (objects.size() > 0) {
            for (Object obj : objects) {
                // String 类型
                if (obj instanceof String) {
                    if (!obj.equals(""))
                        addText("" + obj, document.createParagraph());
                }
                // Table 类型
//                if (obj instanceof Table) {
//                    Table table = (Table) obj;
//                    table.setWidth(100);
//                    addTable(table);
//                }
                // GridData类型
                else if (obj instanceof GridData) {
//                    GridData gridData = (GridData) obj;
//                    addTableforGridData(gridData);
                }// TableforString
                else if (obj instanceof TableforString) {
//                    TableforString tableforString = (TableforString) obj;
//                    addTableforString(tableforString.getStr(),
//                            tableforString.getWidth());
                } else if (obj instanceof String[][]) {
                    String[][] str = (String[][]) obj;
//                    addTableforString(str, null);
                }
                // GridTable类型
                else if (obj instanceof GridTable) {
                    GridTable gridTable = (GridTable) obj;
                    addTableforGridTable(gridTable);
                }
            }
        }
    }


    public static void main(String[] args) {
        try {
            CreateWordDocument doc = new CreateWordDocument("/Users/gyunf/Documents/ideaProjects/dayily-exercise/maventest/src/main/resources/worddown/output_file_path.docx");
//            doc.addTitle("Main Title");
//            doc.addSubtitle("Subtitle");
            doc.addTitle("dd资源目录","\r资源分类：全域数据中心");

            doc.addText("Hello, World!", "Arial", 12, true);

            String[][] tableData = {
                    {"Name", "AgeAge", "Gender"},
                    {"JohnJohn", "25", "Male"},
                    {"Jane", "30", "Female"}
            };
            String[] tableHeaders = {"Name", "AgeAge", "Gender"};
            doc.addTable(tableData, tableHeaders);

            doc.addFooter();
            doc.save();
            System.out.println("Word document created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
