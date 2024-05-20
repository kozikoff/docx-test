package com.qabonsai.docxtest.matchers;

import com.qabonsai.docxtest.DOCX;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

abstract class DOCXMatcher extends TypeSafeMatcher<DOCX> implements SelfDescribing {

    protected String reduceSpaces(String text) {
        return text.replaceAll("[\\s\\n\\r\u00a0]+", " ").trim();
    }

    @Override
    protected void describeMismatchSafely(DOCX item, Description mismatchDescription) {
        mismatchDescription.appendText("was \"").appendText(item.name).appendText("\"\n");
        switch (item.getFileType()) {
            case DOCX:
                for (int i = 0; i < item.docx.getParagraphs().size(); i++) {
                    mismatchDescription.appendText(item.docx.getParagraphs().get(i).getText()).appendText("\n");
                }
                mismatchDescription.appendText("\n");
                break;
            case DOC:
                WordExtractor extractor = new WordExtractor(item.doc);
                String[] paragraphs = extractor.getParagraphText();
                for (String paragraph : paragraphs) {
                    mismatchDescription.appendText(paragraph).appendText("\n");
                }
                break;
            case INVALID:
            default:
                mismatchDescription.appendText("Invalid Word file");
        }
    }
}
