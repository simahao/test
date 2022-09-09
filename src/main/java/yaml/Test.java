package yaml;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Test {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ErrorCodeConfig errors = mapper.readValue(new File("./errorCode.yml"), ErrorCodeConfig.class);
        System.out.println(errors);
    }
}
