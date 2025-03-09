package Zhimin_Zhan_Exercise.Chapter5_TextField;

import com.microsoft.playwright.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VerifyMultilineTextTest {

    @Test
    public void VerifyMultilineTextTest() throws InterruptedException { 
        try (Playwright playwright = Playwright.create()) {
            // ✅ Set Chrome executable path (Update as per your system)
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";  

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setExecutablePath(java.nio.file.Paths.get(chromePath))  // ✅ Use Chrome instead of Chromium
                .setHeadless(false);

            Browser browser = playwright.chromium().launch(options);
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            
            page.navigate("file:///E:/Softwares/Java/JavaPlaywright/src/test/java/Zhimin_Zhan_Exercise/SampleHTMLs/MultilineTextEntry.html");

            String multilineText = "This is line 1\nThis is line 2\nThis is line 3";
            page.fill("#multilineInput", multilineText);
            page.click("#submitBtn");
            
            String displayedText = page.textContent("#output");
            System.out.println(displayedText);
            assertEquals(multilineText, displayedText.trim(), "Output text should match input text");
 
                       
            Thread.sleep(2000);
          
            page.close();
           
        }
    }
}
