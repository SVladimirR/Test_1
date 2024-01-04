package example;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class InputData {
    int n;
    int q;
    String s;
    List<Query> queries;


}
