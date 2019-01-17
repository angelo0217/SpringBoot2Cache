import com.google.gson.Gson;
import org.junit.Test;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.reflection.Fields;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
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
        chkValue(testVo);
    }

    public void chkValue(Object obj) throws Exception{
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field: fields){
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof StringChk){
                    String methodName = "get"+ Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
                    String value =  (String) obj.getClass().getDeclaredMethod(methodName, null).invoke(obj, null);
                    StringChk stringChk = (StringChk) annotation;
                    if(!stringChk.isNull() && StringUtils.isEmpty(value)){
                        throw new Exception(stringChk.errMag());
                    }
                    if(!StringUtils.isEmpty(stringChk.cntaintValue()) && !stringChk.cntaintValue().contains(value)){
                        throw new Exception(stringChk.errMag()+"，綁定字串錯誤");
                    }
                }

            }
        }
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
                        String methodName = "get"+ Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
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
