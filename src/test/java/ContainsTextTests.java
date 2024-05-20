import com.qabonsai.docxtest.DOCX;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.qabonsai.docxtest.DOCX.containsText;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContainsTextTests {

    @Test
    void canAssertThatDocContainsText() throws IOException {
        DOCX doc = new DOCX(getClass().getClassLoader().getResource("file-sample_100kB.doc"));
        assertThat(doc, containsText(new String[]{"Vestibulum neque massa, scelerisque sit amet ligula eu, congue molestie mi. Praesent ut varius sem."}));
    }

    @Test
    void canAssertThatDocxContainsText() throws IOException {
        DOCX docx = new DOCX(getClass().getClassLoader().getResource("file-sample_100kB.docx"));
        assertThat(docx, containsText(new String[]{"Lorem ipsum",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac faucibus odio.",
        "Vestibulum neque massa, scelerisque sit amet ligula eu, congue molestie mi. Praesent ut varius sem. Nullam at porttitor arcu, nec lacinia nisi. Ut ac dolor vitae odio interdum condimentum. Vivamus dapibus sodales ex, vitae malesuada ipsum cursus convallis. Maecenas sed egestas nulla, ac condimentum orci. Mauris diam felis, vulputate ac suscipit et, iaculis non est. Curabitur semper arcu ac ligula semper, nec luctus nisl blandit. Integer lacinia ante ac libero lobortis imperdiet. Nullam mollis convallis ipsum, ac accumsan nunc vehicula vitae. Nulla eget justo in felis tristique fringilla. Morbi sit amet tortor quis risus auctor condimentum. Morbi in ullamcorper elit. Nulla iaculis tellus sit amet mauris tempus fringilla."}));
    }
}
