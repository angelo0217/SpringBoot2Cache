import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2019/1/17
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListChk {
    boolean isEmpty() default true;
}
