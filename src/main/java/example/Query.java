package example;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Query {

    int l;
    int r;
    int k;

    @Override
    public String toString() {
        return "(" + l + ", " + r + ", " + k + ")";
    }
}
