package com.gyunf;


//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.usermodel.CharacterRun;
//import org.apache.poi.hwpf.usermodel.Paragraph;
//import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlObject;

import javax.xml.namespace.QName;
import java.io.*;

public class WaterMarkUtils {

    /**
     * word文字水印
     *
     * @param inputPath
     * @param outPath
     * @param markStr
     */
    public static void setWordWaterMark(String inputPath, String outPath, String markStr) throws Exception {
        File inputFile = new File(inputPath);
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        //2003doc 用HWPFDocument  ； 2007doc 用 XWPFDocument
//        ByteArrayInputStream inputStream = new ByteArrayInputStream();
        XWPFDocument doc = null;
        try {
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(0.005d);
            doc = new XWPFDocument(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new Exception("源文件不存在");
        } catch (IOException e) {
            throw new Exception("读取源文件IO异常");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("不支持的文档格式");
        }
//        XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
//
//        if (headerFooterPolicy == null) {
//            headerFooterPolicy = doc.createHeaderFooterPolicy();
//        }


//       方式一： 自定义水印设置
        XWPFHeaderFooterPolicy headerFooterPolicy = new CustomXWPFHeaderFooterPolicy(doc);
//
        if (headerFooterPolicy == null) {
            headerFooterPolicy = doc.createHeaderFooterPolicy();
        }

        //添加水印
        headerFooterPolicy.createWatermark(markStr);


//        方式二：自定义水印,失败
//        addWaterMark(doc,markStr,"D3D3D3FF");


        File file = new File(outPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new Exception("创建输出文件失败");
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(outPath);
            doc.write(os);
        } catch (FileNotFoundException e) {
            throw new Exception("创建输出文件失败");
        } catch (Exception e) {
            throw new Exception("添加文档水印失败");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (doc != null) {
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void addWatermark(String filePath, String watermarkText) throws IOException {
//        FileInputStream fis = new FileInputStream(filePath);
//        HWPFDocument document = new HWPFDocument(fis);
//
//        // 获取文档的所有段落
//        Range range = document.getRange();
//        for (int i = 0; i < range.numParagraphs(); i++) {
//            Paragraph paragraph = range.getParagraph(i);
//
//            // 获取段落的所有runs
//            for (int j = 0; j < paragraph.numCharacterRuns(); j++) {
//                CharacterRun run = paragraph.getCharacterRun(j);
//
//                // 在每个run中添加水印
//                run.setColor(0xCCCCCC);// 设置文字颜色为灰色
//                run.setFontSize(10);  // 设置文字大小
//                run.setBold(true);  // 设置文字加粗
//                  // 设置文字位置
//                run.setVerticalOffset(100);
//
//                // 添加水印文本
//                run.insertBefore(watermarkText);
//            }
//        }
//
//        // 保存文档
//        FileOutputStream fos = new FileOutputStream(filePath);
//        document.write(fos);
//        fos.close();
//
//        document.close();
//        fis.close();
//    }


    //添加水印，并设置水印颜色和旋转位置（待测试）
    private static void addWaterMark(Object obj, String watermark, String color) {
        if (obj instanceof XWPFDocument) {
            XWPFDocument doc = (XWPFDocument) obj;
            // create header-footer
            XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
            if (headerFooterPolicy == null) headerFooterPolicy = doc.createHeaderFooterPolicy();

            // create default Watermark - fill color black and not rotated
            headerFooterPolicy.createWatermark(watermark);

            // get the default header
            // Note: createWatermark also sets FIRST and EVEN headers
            // but this code does not updating those other headers
            XWPFHeader header = headerFooterPolicy.getHeader(XWPFHeaderFooterPolicy.DEFAULT.intValue());
            XWPFParagraph paragraph = header.getParagraphArray(0);

            // get com.microsoft.schemas.vml.CTShape where fill color and rotation is set
            XmlObject[] xmlobjects = paragraph.getCTP().getRArray(0).getPictArray(0).selectChildren(new QName("urn:schemas-microsoft-com:vml", "shape"));
            if (xmlobjects.length > 0) {
                com.microsoft.schemas.vml.CTShape ctshape = (com.microsoft.schemas.vml.CTShape)xmlobjects[0];
                // set fill color
                ctshape.setFillcolor(color);
                // set rotation
                ctshape.setStyle(ctshape.getStyle() + ";rotation:45");
            }
        }
    }

}
