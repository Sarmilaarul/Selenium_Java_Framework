package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;




public class baseTest {
	
	public WebDriver driver;
	
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        launchApp();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

	public WebDriver initializeDriver() throws IOException {
		
//use properties class to invoke the global variables
		Properties property = new Properties();
		String file =
			    System.getProperty("user.dir")
			    + "\\src\\main\\java\\Resources\\globalproperties";
		FileInputStream F = new FileInputStream(file);
		
	   property.load(F);
	   String browserName = property.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			Map<String,Object> prefs = new HashMap<>();
			prefs.put("download.default-directory", "C:\\Users\\Ashok");
			options.setExperimentalOption("prefs", prefs);
			
			 driver = new ChromeDriver(options);
		}
		
		else if(browserName.equalsIgnoreCase("Edge")) {
			
			EdgeOptions options = new EdgeOptions();
			Map<String,Object> prefs = new HashMap<>();
			prefs.put("download.default-directory", "C:\\Users\\Ashok");
			options.setExperimentalOption("prefs", prefs);
			
			 driver = new EdgeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("browser.download.folderList", 2); 
			options.addPreference("browser.download.dir", "C:\\Users\\Ashok"); 
			
			 driver = new FirefoxDriver(options);
            
			
		}
		driver.manage().window().maximize();
		return driver;
	}

//lauchapplication
	public void launchApp() throws IOException {
		
		driver.get("https://automationexercise.com/");

	}


//Take Screenshot
	public String screenshot(String testCaseName) throws IOException {
		
		TakesScreenshot TS= (TakesScreenshot)driver;
		File F = TS.getScreenshotAs(OutputType.FILE);
	
		 File destination = new File(System.getProperty("user.dir")+ "//reports//"+ testCaseName+ ".png");
		FileUtils.copyFile(F, destination);
		  return destination.getAbsolutePath();
	}	

		
	
//Data Extraction form json
	public List<HashMap<String,String>> getjsonData(String filepath) throws IOException{
		//objectmapper from jacksonbid converts jaosn into java class
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(new File(filepath), new TypeReference<List<HashMap<String,String>>>(){});		
		return data;
		
	}

	//Data Extraction from excel:
	public Object[][] excelData(String path) throws IOException {
		
		//String path = "C:\\Users\\Ashok\\eclipse-workspace\\excel.xlsx";
	    FileInputStream FInput = new FileInputStream(path);

	    try (XSSFWorkbook workbook = new XSSFWorkbook(FInput)) {

	        XSSFSheet sheet = workbook.getSheetAt(0);

	        int rowCount = sheet.getPhysicalNumberOfRows();

	        XSSFRow row = sheet.getRow(0);
	        int colCount = row.getLastCellNum();

	        Object[][] data = new Object[rowCount - 1][colCount];

	        for (int i = 0; i < rowCount - 1; i++) {

	            row = sheet.getRow(i + 1);

	            for (int j = 0; j < colCount; j++) {

	                XSSFCell col = row.getCell(j);

	                DataFormatter formatter = new DataFormatter();

	                data[i][j] = formatter.formatCellValue(col);
	            }
	        }

	        return data;
		}
	    
	}
	
	
	
}
