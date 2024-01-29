package com.gyunf;

import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.STConnectType;
import com.microsoft.schemas.vml.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;


public class CustomXWPFHeaderFooterPolicy extends XWPFHeaderFooterPolicy {
    private XWPFDocument document;

    public CustomXWPFHeaderFooterPolicy(XWPFDocument document) {
        super(document);
        this.document = document;
    }

    @Override
    public void createWatermark(String watermarkText) {
        XWPFParagraph[] pars = new XWPFParagraph[1];
        int fontSize = 12;
        pars[0] = getWatermarkParagraph(watermarkText, 1,fontSize);
        try {
            createHeader(DEFAULT, pars);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //            pars[0] = getWatermarkParagraph(watermarkText, 1);
//            createHeader(DEFAULT, pars);
//            pars[0] = getWatermarkParagraph(text, 2);
//            createHeader(FIRST, pars);
//            pars[0] = getWatermarkParagraph(text, 3);
//            createHeader(EVEN, pars);
    }


    private XWPFParagraph getWatermarkParagraph(String text, int idx,int fontSize) {
        CTP p = CTP.Factory.newInstance();
        byte[] rsidr = document.getDocument().getBody().getPArray(0).getRsidR();
        byte[] rsidrdefault = document.getDocument().getBody().getPArray(0).getRsidRDefault();
        p.setRsidP(rsidr);
        p.setRsidRDefault(rsidrdefault);
        CTPPr pPr = p.addNewPPr();
        pPr.addNewPStyle().setVal("Header");
        // start watermark paragraph
        CTR r = p.addNewR();
        CTRPr rPr = r.addNewRPr();
        rPr.addNewNoProof();
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
//        for (int i = 0; i < 3; i++) {
            CTShape shape = group.addNewShape();
            shape.setId("PowerPlusWaterMarkObject" + idx);
            shape.setSpid("_x0000_s102" + (4 + idx));
            shape.setType("#_x0000_t136");
            shape.setStyle("rotation:315;position:absolute;margin-left:0;margin-top:0;width:415pt;height:207.5pt;z-index:-251654144;mso-wrap-edited:f;mso-position-horizontal:center;mso-position-horizontal-relative:margin;mso-position-vertical:center;mso-position-vertical-relative:margin");
            shape.setWrapcoords("616 5068 390 16297 39 16921 -39 17155 7265 17545 7186 17467 -39 17467 18904 17467 10507 17467 8710 17545 18904 17077 18787 16843 18358 16297 18279 12554 19178 12476 20701 11774 20779 11228 21131 10059 21248 8811 21248 7563 20975 6316 20935 5380 19490 5146 14022 5068 2616 5068");
            shape.setFillcolor("gray");
            shape.setStroked(STTrueFalse.FALSE);
            CTTextPath shapeTextPath = shape.addNewTextpath();
            shapeTextPath.setStyle("font-family:&quot;Cambria&quot;;font-size:1pt");
            shapeTextPath.setString(text);
//        }


        pict.set(group);
        // end watermark paragraph
        return new XWPFParagraph(p, document);
    }
}
