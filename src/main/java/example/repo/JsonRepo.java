package example.repo;
import tools.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonRepo<T> {
    private final String filename;
    private final Class<T[]> arrayType;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonRepo(String filename, Class<T[]> arrayType) {
        this.filename = filename;
        this.arrayType = arrayType;
    }

    public List<T> readAll() throws IOException {
        T[] arr = mapper.readValue(new File(filename), arrayType);
        return Arrays.asList(arr);
    }
}