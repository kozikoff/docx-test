package com.qabonsai.docxtest.matchers;

import com.qabonsai.docxtest.DOCX;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.hamcrest.Description;

import java.io.IOException;

public class ContainsText extends DOCXMatcher {
    private final String[] substring;
    private int searchCount;

    public ContainsText(String[] substring) {
        this.substring = new String[substring.length];
        for (int i = 0; i < substring.length; i++) {
            this.substring[i] = reduceSpaces(substring[i]);
        }
    }

    @Override
    protected boolean matchesSafely(DOCX item) {
        try {
            return switch (item.getFileType()) {
                case DOCX -> searchInDocx(item, substring);
                case DOC -> searchInDoc(item, substring);
                default -> false;
            };
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read DOCX file " + item.name, e);
        }
    }

    private boolean searchInDocx(DOCX word, String[] textsToSearch) throws IOException {
        for (int i = 0; i < textsToSearch.length; i++) {
            searchCount = i;
            String searchText = textsToSearch[i];
            boolean found = word.docx.getParagraphs().stream()
                    .anyMatch(paragraph -> paragraph.getText().contains(searchText));
            if (!found) return false;
        }
        return true;
    }

    private boolean searchInDoc(DOCX word, String[] textsToSearch) {
        WordExtractor extractor = new WordExtractor(word.doc);
        String content = extractor.getText();
        for (int i = 0; i < textsToSearch.length; i++) {
            searchCount = i;
            String searchText = textsToSearch[i];
            if (!content.contains(searchText)) return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("WORD file contains text ").appendValue(reduceSpaces(substring[searchCount]));
    }
}
