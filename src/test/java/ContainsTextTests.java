import com.qabonsai.docxtest.DOCX;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.qabonsai.docxtest.DOCX.containsText;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContainsTextTests {

    @Test
    void canAssertThatDocContainsText() throws IOException {
        DOCX doc = new DOCX(getClass().getClassLoader().getResource("file-sample_100kB.doc"));
        assertThat(doc, containsText(new String[]{"Maecenas1 mauris lectus, lobortis et purus mattis, blandit dictum tellus.",
                "Mauris id ex erat. Nunc vulputate neque vitae justo facilisis, non condimentum ante sagittis."}));
//        assertThat(pptx, containsText("My pretty chart"));
//        assertThat(pptx, containsText("37.12%"));
//        assertThat(pptx, containsText("Beehive State (official), The Mormon State"));
    }

    @Test
    void canAssertThatDocxContainsText() throws IOException {
        DOCX docx = new DOCX(getClass().getClassLoader().getResource("file-sample_100kB.docx"));
        assertThat(docx, containsText(new String[]{"In non mauris justo. Duis vehicula mi vel mi pretium, a viverra erat efficitur. Cras aliquam est ac eros varius, id iaculis dui auctor. Duis pretium neque ligula, et pulvinar mi placerat et. Nulla nec nunc sit amet nunc posuere vestibulum. Ut id neque eget tortor mattis tristique. Donec ante est, blandit sit amet tristique vel, lacinia pulvinar arcu. Pellentesque scelerisque fermentum erat, id posuere justo pulvinar ut. Cras id eros sed enim aliquam lobortis. Sed lobortis nisl ut eros efficitur tincidunt. Cras justo mi, porttitor quis mattis vel, ultricies ut purus. Ut facilisis et lacus eu cursus.",
                "Fusce vitae vestibulum velit. Pellentesque vulputate lectus quis pellentesque commodo. Aliquam erat volutpat. Vestibulum in egestas velit. Pellentesque fermentum"}));
//        assertThat(pptx, containsText("My pretty chart"));
//        assertThat(pptx, containsText("37.12%"));
//        assertThat(pptx, containsText("Beehive State (official), The Mormon State"));
    }
}
