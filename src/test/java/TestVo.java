import java.util.List;

/**
 * Created on 2019/1/16
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class TestVo {
    @MyAnnotation(isNull = false, bindValue = "123")
    private String name;
    @MyAnnotation(isNull = false)
    private List<Integer> nos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getNos() {
        return nos;
    }

    public void setNos(List<Integer> nos) {
        this.nos = nos;
    }
}
