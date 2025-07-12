package utils;

import java.io.File;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataProvider {
	@DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<LoginData> dataList = mapper.readValue(
            new File(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\testdata.json"), // path to your JSON file
            new TypeReference<List<LoginData>>() {}
        );

        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }
}
