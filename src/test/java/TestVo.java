import java.util.List;

/**
 * Created on 2019/1/16
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class TestVo {
    @StringChk(isNull = false, defaultValue = "dev", cntaintValue = "dev,cnt,ans", errMag = "字串內容錯誤")
    private String name;
    @ListChk(isEmpty = false)
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
