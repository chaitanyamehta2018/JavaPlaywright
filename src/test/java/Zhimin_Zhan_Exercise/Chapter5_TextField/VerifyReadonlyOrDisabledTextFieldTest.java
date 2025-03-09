package Zhimin_Zhan_Exercise.Chapter5_TextField;

import com.microsoft.playwright.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VerifyReadonlyOrDisabledTextFieldTest {

    @Test
    public void VerifyReadonlyOrDisabledTextFieldTest() throws InterruptedException { 
        try (Playwright playwright = Playwright.create()) {
            // ✅ Set Chrome executable path (Update as per your system)
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";  

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setExecutablePath(java.nio.file.Paths.get(chromePath))  // ✅ Use Chrome instead of Chromium
                .setHeadless(false);

            Browser browser = playwright.chromium().launch(options);
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            
            page.navigate("file:///E:/Softwares/Java/JavaPlaywright/src/test/java/Zhimin_Zhan_Exercise/SampleHTMLs/ReadonlyOrDisabledTextField.html");
            
            page.evaluate("document.getElementById('readonlyField').removeAttribute('readonly');");
            page.evaluate("document.getElementById('readonlyField').value = 'Hello';");
            
            page.evaluate("document.getElementById('disabledField').removeAttribute('disabled');");
            page.evaluate("document.getElementById('disabledField').value = 'Folks';");            
            
            
            var readonlyBoxText = page.inputValue("#readonlyField");
            var disabledBoxText = page.inputValue("#disabledField");
            
            assertEquals("Hello", readonlyBoxText, "Text should match for readonly field");
            assertEquals("Folks", disabledBoxText, "Text should match for disabled field");
 
                       
            Thread.sleep(2000);
          
            page.close();
           
        }
    }
}
