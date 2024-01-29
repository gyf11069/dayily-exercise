package com.gyunf;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.Paragraph;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;


import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import com.microsoft.schemas.office.office.*;
import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.vml.*;

//import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse; // apache ppi 5.0.0
import com.microsoft.schemas.vml.STTrueFalse; // apache poi 3.17 and apache poi 4.1.2

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;

public class CreateWordHeaderWatermarkInExistingDocument {

    private static void setWatermarkInParagraph(XWPFParagraph paragraph, String text) {
        //CTP p = CTP.Factory.newInstance();
        CTP p = paragraph.getCTP();
        XWPFDocument doc = paragraph.getDocument();
        CTBody ctBody = doc.getDocument().getBody();
        byte[] rsidr = null;
        byte[] rsidrdefault = null;
        if (ctBody.sizeOfPArray() == 0) {
            // TODO generate rsidr and rsidrdefault
        } else {
            CTP ctp = ctBody.getPArray(0);
            rsidr = ctp.getRsidR();
            rsidrdefault = ctp.getRsidRDefault();
        }
        p.setRsidP(rsidr);
        p.setRsidRDefault(rsidrdefault);
        CTPPr pPr = p.getPPr(); if (pPr == null) pPr = p.addNewPPr();
        CTString pStyle = pPr.getPStyle(); if (pStyle == null)  pStyle = pPr.addNewPStyle();
        pStyle.setVal("Header");
        // start watermark paragraph
        CTR r = p.addNewR();
        CTRPr rPr = r.addNewRPr();
        rPr.addNewNoProof();
        int idx = 1;
        CTPicture pict = r.addNewPict();
        CTGroup group = CTGroup.Factory.newInstance();
        CTShapetype shapetype = group.addNewShapetype();
        shapetype.setId("_x0000_t136");
        shapetype.setCoordsize("1600,21600");
        shapetype.setSpt(136);
        shapetype.setAdj("10800");
        shapetype.setPath2("m@7,0l@8,0m@5,21600l@6,21600e");
        CTFormulas formulas = shapetype.addNewFormulas();
        formulas.addNewF().setEqn("sum #0 0 10800");
        formulas.addNewF().setEqn("prod #0 2 1");
        formulas.addNewF().setEqn("sum 21600 0 @1");
        formulas.addNewF().setEqn("sum 0 0 @2");
        formulas.addNewF().setEqn("sum 21600 0 @3");
        formulas.addNewF().setEqn("if @0 @3 0");
        formulas.addNewF().setEqn("if @0 21600 @1");
        formulas.addNewF().setEqn("if @0 0 @2");
        formulas.addNewF().setEqn("if @0 @4 21600");
        formulas.addNewF().setEqn("mid @5 @6");
        formulas.addNewF().setEqn("mid @8 @5");
        formulas.addNewF().setEqn("mid @7 @8");
        formulas.addNewF().setEqn("mid @6 @7");
        formulas.addNewF().setEqn("sum @6 0 @5");
        CTPath path = shapetype.addNewPath();
        path.setTextpathok(STTrueFalse.T);
        path.setConnecttype(STConnectType.CUSTOM);
        path.setConnectlocs("@9,0;@10,10800;@11,21600;@12,10800");
        path.setConnectangles("270,180,90,0");
        CTTextPath shapeTypeTextPath = shapetype.addNewTextpath();
        shapeTypeTextPath.setOn(STTrueFalse.T);
        shapeTypeTextPath.setFitshape(STTrueFalse.T);
        CTHandles handles = shapetype.addNewHandles();
        CTH h = handles.addNewH();
        h.setPosition("#0,bottomRight");
        h.setXrange("6629,14971");
        CTLock lock = shapetype.addNewLock();
        lock.setExt(STExt.EDIT);
        CTShape shape = group.addNewShape();
        shape.setId("PowerPlusWaterMarkObject" + idx);
        shape.setSpid("_x0000_s102" + (4 + idx));
        shape.setType("#_x0000_t136");
        shape.setStyle("position:absolute;margin-left:0;margin-top:0;width:415pt;height:207.5pt;z-index:-251654144;mso-wrap-edited:f;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin");
        shape.setWrapcoords("616 5068 390 16297 39 16921 -39 17155 7265 17545 7186 17467 -39 17467 18904 17467 10507 17467 8710 17545 18904 17077 18787 16843 18358 16297 18279 12554 19178 12476 20701 11774 20779 11228 21131 10059 21248 8811 21248 7563 20975 6316 20935 5380 19490 5146 14022 5068 2616 5068");
        shape.setFillcolor("#d8d8d8");
        shape.setStyle(shape.getStyle() + ";rotation:315");
        shape.setStroked(STTrueFalse.FALSE);
        CTTextPath shapeTextPath = shape.addNewTextpath();
        shapeTextPath.setStyle("font-family:&quot;Cambria&quot;;font-size:1pt");
        shapeTextPath.setString(text);
        pict.set(group);
    }

    public static void main(String[] args) throws Exception {
        String path = "/Users/gyunf/Documents/ideaProjects/dayily-exercise/maventest/src/main/resources/worddown/水印测试.docx";

        /*此方式可以成功添加水印*/
        // create a Word document
        com.spire.doc.Document document = new com.spire.doc.Document();

        // Add a section
        Section section = document.addSection();

        // Add a heading
        Paragraph heading = section.addParagraph();
        heading.appendText(" ");
//        heading.applyStyle(BuiltinStyle.Title);
        // Save the document
        document.saveToFile(
                path,
                FileFormat.Docx);


        File f = new File(path);
//        XWPFDocument doc= new XWPFDocument();
        XWPFDocument doc= new XWPFDocument(new FileInputStream(path));
//        doc.write(new FileOutputStream(f));

        // the body content
        XWPFParagraph paragraph = doc.createParagraph();

//        /*设置磅数*/
//        CTP ctp = paragraph.getCTP();
//        CTPPr ppr = ctp.isSetPPr() ? ctp.getPPr() : ctp.addNewPPr();
//        CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
//        spacing.setAfter(BigInteger.valueOf(0));
//        spacing.setBefore(BigInteger.valueOf(0));
//        //注意设置行距类型为 EXACT
//        spacing.setLineRule(STLineSpacingRule.EXACT);
//        //1磅数是20
//        spacing.setLine(BigInteger.valueOf(20*25));


        XWPFRun run=paragraph.createRun();
        run.setText("New text in body.");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <100; j++) {
                XWPFParagraph paragraph1 = doc.createParagraph();

//                /*设置磅数*/
//                CTP ctp1 = paragraph1.getCTP();
//                CTPPr ppr1 = ctp1.isSetPPr() ? ctp1.getPPr() : ctp1.addNewPPr();
//                CTSpacing spacing1 = ppr1.isSetSpacing()? ppr1.getSpacing() : ppr1.addNewSpacing();
//                spacing1.setAfter(BigInteger.valueOf(0));
//                spacing1.setBefore(BigInteger.valueOf(0));
//                //注意设置行距类型为 EXACT
//                spacing1.setLineRule(STLineSpacingRule.EXACT);
//                //1磅数是20
//                spacing1.setLine(BigInteger.valueOf(20*25));

                XWPFRun run1 = paragraph1.createRun();
                run1.setText("New text in body.=====" + j);
//                String[][] tableData = {
//                        {"Name", "AgeAge", "Gender"},
//                        {"JohnJohn", "25", "Male"},
//                        {"Jane", "30", "Female"}
//                };
//                String[] tableHeaders = {"Name", "AgeAge", "Gender"};
//                addTable(tableData, tableHeaders,doc);
            }
            // 创建分节符号（section break）以分隔页面
            XWPFParagraph sectionBreak = doc.createParagraph();
            sectionBreak.setPageBreak(true);

        }

        //临时添加设置页眉
//        String headerString = "页眉";
//        CTP ctp = CTP.Factory.newInstance();
//        XWPFParagraph paragraph2 = new XWPFParagraph(ctp, doc);//段落对象
//        ctp.addNewR().addNewT().setStringValue(headerString);//设置页眉参数
//        ctp.addNewR().addNewT().setSpace(SpaceAttribute.Space.PRESERVE);

//        CTSectPr sectPr = doc.getDocument().getBody().isSetSectPr() ? doc.getDocument().getBody().getSectPr() : doc.getDocument().getBody().addNewSectPr();
//        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(doc, sectPr);
//
//
//        XWPFHeader header = policy.getDefaultHeader();
//        if (header == null)
//        {
//            header = policy.createHeader(STHdrFtr.DEFAULT, new XWPFParagraph[] { paragraph2 });
//        } else {
//            header.createParagraph().createRun().setText(headerString);
//        }
//
//        header.setXWPFDocument(doc);



        // get or create the default header(无用)
//        XWPFHeader header = doc.createHeaderFooterPolicy().getDefaultHeader();
        // get or create first paragraph in first header
//        paragraph = header.getParagraphArray(0);
//        if (paragraph == null) paragraph = header.createParagraph();
//        // set watermark to that paragraph
//        setWatermarkInParagraph(paragraph, "Watermark default");

        /*测试二*/
//        XWPFHeaderFooterPolicy policy = new CustomXWPFHeaderFooterPolicy(doc);
//        policy.createWatermark("水印测试");

        /*测试三*/
        XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
        if (headerFooterPolicy == null) headerFooterPolicy = doc.createHeaderFooterPolicy();
        headerFooterPolicy.createWatermark("时测啊身材水印");
        XWPFHeader header = headerFooterPolicy.getDefaultHeader();
        XWPFParagraph paragraph2 = header.getParagraphArray(0);



        XmlObject[] xmlObjects = paragraph2.getCTP().getRArray(0).getPictArray(0).selectChildren(
                new QName("urn:schemas-microsoft-com:vml", "shape"));

        if (xmlObjects.length > 0) {
            com.microsoft.schemas.vml.CTShape ctshape = (com.microsoft.schemas.vml.CTShape) xmlObjects[0];
            ctshape.setFillcolor("#d8d8d8");
            ctshape.setStyle(ctshape.getStyle() + ";rotation:315");
        }

        FileOutputStream out = new FileOutputStream(path);
        doc.write(out);
        out.close();
        doc.close();

    }

    public static void addTable(String[][] data, String[] headers, XWPFDocument document) {
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
}