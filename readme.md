# docx-test

MS Word testing library

Make sure your code always generates correct and valid docx and doc files!

## How to use
Just add assertThat from Hamcrest and enjoy ))

```java
import com.qabonsai.docxtest.DOCX;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.qabonsai.docxtest.DOCX.containsText;
import static org.hamcrest.MatcherAssert.assertThat;

public class WordTests {
    @Test
    void canAssertThatDocContainsText() throws IOException {
        DOCX doc = new DOCX(getClass().getClassLoader().getResource("file-sample_100kB.doc"));
        assertThat(doc, containsText(new String[]{"Vestibulum neque massa, scelerisque sit amet ligula eu"}));
    }

    @Test
    void canAssertThatDocxContainsText() throws IOException {
        DOCX docx = new DOCX(getClass().getClassLoader().getResource("file-sample_100kB.docx"));
        assertThat(docx, containsText(new String[]{"Lorem ipsum",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac faucibus odio."}));
    }
}

```

## How to add

Copy jar-file into ```lib``` directory of your project and make import in IntelliJ IDEA (Project structure - Modules - Dependencies). 
If you use **Gradle**, please add the following dependency to build.gradle:

```groovy
dependencies {
    testImplementation files('lib/docx-test.jar')
}
```

# Thanks

Many thanks to myself and my project [pptx-test](https://github.com/kozikoff/pptx-test) for the inspiration ))

# License

docx-test is an open-source project and distributed under [MIT](http://choosealicense.com/licenses/mit/) license
