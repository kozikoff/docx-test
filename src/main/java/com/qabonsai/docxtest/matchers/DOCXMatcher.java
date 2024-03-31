package com.qabonsai.docxtest.matchers;

import com.qabonsai.docxtest.DOCX;
import org.apache.poi.xslf.usermodel.*;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;


abstract class DOCXMatcher extends TypeSafeMatcher<DOCX> implements SelfDescribing {

    protected String reduceSpaces(String text) {
        return text.replaceAll("[\\s\\n\\r\u00a0]+", " ").trim();
    }

    @Override
    protected void describeMismatchSafely(DOCX item, Description mismatchDescription) {
        mismatchDescription.appendText("was \"").appendText(item.name).appendText("\"\n");
        switch (item.getFileType()) {
            case DOCX:
                //mismatchDescription.appendText(textShape.getText()).appendText("\t");
                mismatchDescription.appendText("\n");
                break;
            case DOC:
                //mismatchDescription.appendText(textShape.getText()).appendText("\t");
                mismatchDescription.appendText("\n");
                break;
            case INVALID:
            default:
                mismatchDescription.appendText("Invalid word file");
        }
//        List<XSLFSlide> slides = item.powerpoint.getSlides();
//        for (XSLFSlide slide : slides) {
//            List<XSLFShape> shapes = slide.getShapes();
//            for (XSLFShape shape : shapes) {
//                if (shape instanceof XSLFTextShape) {
//                    XSLFTextShape textShape = (XSLFTextShape) shape;
//                    mismatchDescription.appendText(textShape.getText()).appendText("\t");
//                }
//                if (shape instanceof XSLFTable) {
//                    XSLFTable tableShape = (XSLFTable) shape;
//                    for (XSLFTableRow tableRow : tableShape) {
//                        for (XSLFTableCell tableCell : tableRow) {
//                            mismatchDescription.appendText(tableCell.getText()).appendText("\t");
//                        }
//                    }
//                }
//            }
//            mismatchDescription.appendText("\n");
//        }
    }
}
