import com.google.gson.Gson;
import org.junit.Test;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.reflection.Fields;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

/**
 * Created on 2019/1/16
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class TestMain {
    @Test
    public void testReflaction() throws Exception{
        TestVo testVo = new TestVo();
        testVo.setName("654");
        testVo.setNos(new Vector<>());
        testVo.getNos().add(1);
        chkData(testVo);
    }

    public void chkData(Object obj) throws Exception{
        Field[] fields = obj.getClass().getDeclaredFields();

        for(Field field: fields){
            MyAnnotation myAnnotation = field.getDeclaredAnnotation(MyAnnotation.class);
            if(myAnnotation != null){
                System.out.println(myAnnotation.isNull());
                System.out.println(myAnnotation.bindValue());
                if(!StringUtils.isEmpty(myAnnotation.bindValue())){
                    System.out.println(field.getType());
                    if(field.getType().equals(String.class)){
                        String methodName = "get"+field.getName().substring(0,1).toUpperCase() + field.getName().substring(1, field.getName().length());
//                        System.out.println(field.get(obj));
                        System.out.println(methodName);
                        Method privateMethod1 = obj.getClass().getDeclaredMethod(methodName, null);
                        System.out.println(privateMethod1.invoke(obj, null));
                    }
                }
                if(field.getType().equals(List.class)){
                    System.out.println("list : "+field.getName());
                }
            }
        }
    }
}
