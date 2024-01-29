package com.gyunf;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import java.io.FileOutputStream;
import java.io.IOException;

public class FontSetUtil {


    // 主标题
    public static XWPFParagraph title(XWPFDocument document, String title) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);

        String split = "";
        for (int i = 0; i < 6; i++) {
            split = split + "\r";
        }

        XWPFRun run2 = paragraph.createRun();
        run2.setText(split + title);
        run2.setBold(true);
        run2.setFontSize(35);
        return paragraph;
    }

    // 副标题
    public static XWPFParagraph subtitle(XWPFDocument document, String title) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.RIGHT);

        XWPFRun run = paragraph.createRun();
//        run.setTextPosition(30);
        run.setText(title);
        run.setFontSize(18);
        return paragraph;
    }

    // 一级标题
    public static XWPFParagraph titleone(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("Heading1");
        return paragraph;
    }

    // 二级标题
    public static XWPFParagraph titletwo(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("Heading2");
        return paragraph;
    }

    // 三级标题
    public static XWPFParagraph titlethree(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("Heading3");
        return paragraph;
    }

    // 四级标题
    public static XWPFParagraph title4(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("Heading4");
        return paragraph;
    }

    // 五级标题
    public static XWPFParagraph title5(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("Heading5");
        return paragraph;
    }

    // 正文
    public static XWPFRun content(XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setFontSize(13);
        return run;
    }

    // 表头
    public static XWPFRun tableHead(XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setFontSize(13);
        return run;
    }

    // 表正文
    public static XWPFRun tableContent(XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setFontSize(13);
        return run;
    }

    // 页码
    public static XWPFRun foot(XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setFontSize(10);
        return run;
    }

    // 示例用法
    public static void main(String[] args) {
        try {
            // 创建新的Word文档
            XWPFDocument document = new XWPFDocument();

            // 添加标题和副标题
            title(document, "主标题");
            subtitle(document, "副标题");

            // 添加一级标题
            XWPFParagraph titleOne = titleone(document);
            XWPFRun runOne = content(titleOne);
            runOne.setText("一级标题");

            // 添加正文
            XWPFParagraph content = document.createParagraph();
            XWPFRun runContent = content(content);
            runContent.setText("这是正文内容。");

            // 保存文档
            FileOutputStream out = new FileOutputStream("example.docx");
            document.write(out);
            out.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
