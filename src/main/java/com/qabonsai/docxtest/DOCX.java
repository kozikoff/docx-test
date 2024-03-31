package com.qabonsai.docxtest;

import com.qabonsai.docxtest.matchers.ContainsText;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.hamcrest.Matcher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import static com.qabonsai.docxtest.IO.readBytes;
import static com.qabonsai.docxtest.IO.readFile;

public class DOCX {
    public final String name;
    //public final XMLSlideShow powerpoint;
    public final XWPFDocument docx;
    public final HWPFDocument doc;

    public FileType getFileType() {
        return fileType;
    }

    public FileType fileType;

    public enum FileType {
        DOCX,
        DOC,
        INVALID
    }

    public DOCX(String name, byte[] content) {
        this.name = name;
        switch (detectDocumentType(content)) {
            case DOCX:
                try (InputStream inputStream = new ByteArrayInputStream(content)) {
                    docx = new XWPFDocument(inputStream);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid DOCX file " + name, e);
                }
                doc = null;
                fileType = FileType.DOCX;
                break;
            case DOC:
                try (InputStream inputStream = new ByteArrayInputStream(content)) {
                    doc = new HWPFDocument(inputStream);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid DOC file " + name, e);
                }
                docx = null;
                fileType = FileType.DOC;
                break;
            case INVALID:
            default:
                throw new IllegalArgumentException("Invalid word file " + name);
        }

    }

    public DOCX(File wordFile) {
        this(wordFile.getAbsolutePath(), readFile(wordFile));
    }

    public DOCX(URL url) throws IOException {
        this(url.toString(), readBytes(url));
    }

    public DOCX(URI uri) throws IOException {
        this(uri.toURL());
    }

    public DOCX(byte[] content) {
        this("", content);
    }

    public DOCX(InputStream inputStream) throws IOException {
        this(readBytes(inputStream));
    }

    public static Matcher<DOCX> containsText(String[] text) {
        return new ContainsText(text);
    }

    private static FileType detectDocumentType(byte[] content) {
        if (content.length > 4 && content[0] == 0x50 && content[1] == 0x4B && content[2] == 0x03 && content[3] == 0x04) {
            return FileType.DOCX;
        }

        if (content.length > 4 &&
                (content[0] == (byte) 0xD0 && content[1] == (byte) 0xCF && content[2] == (byte) 0x11 && content[3] == (byte) 0xE0)) {
            return FileType.DOC;
        }

        else {
            return FileType.INVALID;
        }
    }
}
