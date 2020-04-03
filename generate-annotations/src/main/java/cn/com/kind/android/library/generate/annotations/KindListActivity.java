package cn.com.kind.android.library.generate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2020/4/2.
 * @Copyright: 2020 www.kind.com.cn Inc. All rights reserved.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface KindListActivity {
    String busikey();
}
