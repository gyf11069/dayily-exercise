package com.gyunf;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 现在有一个使用3.15版本的POI下载带水印的word文档的开发需求，要求如下，下载的内容是一个目录树，每个目录下挂载了数据表，数据表的内容有表的描述信息，表的字段结构，要求把表的描述作为段落文本输出，表的字段使用表格的形式写入word，并且如果目录是用一级标题样式，目录下的表名称是二级标题，并且可以在上述基础上如何实现自由设置标题和文本的字体大小，并且要设置自动生成连续的页码，可以按照上述需求描述在网络上为我找十篇精选的博客吗
 */
public class WordDocumentGenerator {

    public static void main(String[] args) {
        String outputPath = "/Users/gyunf/Documents/ideaProjects/dayily-exercise/maventest/src/main/resources/worddown/带页码的文档.docx";

        try {
            createDocument(outputPath);
            System.out.println("Word 文档已创建成功。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDocument(String outputPath) throws IOException {
        XWPFDocument document = new XWPFDocument();

        // 添加文本内容
        addCustomText(document, "这是文档的第一页。");

        // 创建分节符号（section break）以分隔页面
        XWPFParagraph sectionBreak = document.createParagraph();
        sectionBreak.setPageBreak(true);

        // 创建页脚
        createFooter(document);

        // 添加更多文本内容（这里可以是多页内容）
        addCustomText(document, "这是文档的第二页。");

        // 保存生成的文档
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            document.write(out);
        }
    }

    private static void addCustomText(XWPFDocument document, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
    }

    private static void createFooter(XWPFDocument document) throws IOException {
        // 创建页脚
// 使用XWPFHeaderFooterPolicy来创建页脚
        XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
        if (headerFooterPolicy == null) {
            headerFooterPolicy = document.createHeaderFooterPolicy();
        }
        XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);

        // 添加页码字段到页脚
        XWPFParagraph paragraph = footer.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setText("页脚");
        // 设置页码的格式（可选）
//        run.setText(" / ");
//        CTFldChar fldChar = run.getCTR().addNewFldChar();
//        fldChar.setFldCharType(STFldCharType.BEGIN);
//
//        run = paragraph.createRun();
//        run.getCTR().addNewInstrText().setStringValue("PAGE");
//
//        fldChar = run.getCTR().addNewFldChar();
//        fldChar.setFldCharType(STFldCharType.SEPARATE);
//
//        run = paragraph.createRun();
//        fldChar = run.getCTR().addNewFldChar();
//        fldChar.setFldCharType(STFldCharType.END);

        // 设置页码的字体大小（可选）
        setFontSize(run, 12); // 字体大小为12，可以根据需要更改
    }

    private static void setFontSize(XWPFRun run, int fontSize) {
        CTFonts fonts = run.getCTR().addNewRPr().addNewRFonts();
        fonts.setAscii("Arial");
        fonts.setHAnsi("Arial");
        CTHpsMeasure fontSizeObj = run.getCTR().addNewRPr().addNewSz();
        fontSizeObj.setVal(BigInteger.valueOf(fontSize * 2));
    }
}

