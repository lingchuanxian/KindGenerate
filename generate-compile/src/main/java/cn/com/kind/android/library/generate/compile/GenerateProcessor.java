package cn.com.kind.android.library.generate.compile;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import cn.com.kind.android.library.generate.annotations.KindListActivity;
import cn.com.kind.android.library.generate.annotations.KindModule;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2020/4/3.
 * @Copyright: 2020 www.kind.com.cn Inc. All rights reserved.
 */
@AutoService(Processor.class)
public class GenerateProcessor extends AbstractProcessor {
    /**
     * Elements中包含用于操作的工具
     */
    private Elements mElementsUtil;
    /**
     * 用于创建新的源文件,class文件以及其他
     */
    private Filer mFiler;
    /**
     * 日志相关的辅助类
     */
    public Messager mMessager;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mElementsUtil = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //添加支持注解的类型
        Set<String> types = new LinkedHashSet<>();
        types.add(KindListActivity.class.getCanonicalName());
        types.add(KindModule.class.getCanonicalName());
        return types;
    }

    /**
     * 返回jdk版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    /**
     * 注解处理器的核心方法，处理具体的注解
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //得到所有包含该注解的element集合

        for (Element element : roundEnv.getElementsAnnotatedWith(KindListActivity.class)) {
            String busikey = element.getAnnotation(KindListActivity.class).busikey();
            //mMessager.printMessage(Diagnostic.Kind.NOTE, busikey, element);
            //可以获取类的信息的element，也是element的子类
            TypeElement classElement = (TypeElement) element.getEnclosingElement();
            //获取包名加类名
           // String fullClassName = classElement.getQualifiedName().toString();
            //mMessager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName(), element);
//            //保存到集合中
//            ClassCreatorFactory factory = mClassCreatorFactoryMap.get(fullClassName);
//            if (factory == null) {
//                factory = new ClassCreatorFactory(mElementUtils, classElement);
//                mClassCreatorFactoryMap.put(fullClassName, factory);
//            }
        }
        //generateKindListActivityManager();
        return false;
    }

    /**
     * 生成KindListActivityManager管理类
     */
    private void generateKindListActivityManager() {
//        MethodSpec methodMain = MethodSpec.methodBuilder("main")//创建main方法
//                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)//定义修饰符为 public static
//                .addJavadoc("@  此类由apt自动生成")//在生成的代码前添加注释
//                .returns(void.class)//定义返回类型
//                .addParameter(String[].class, "args")//定义方法参数
//                .addStatement("$T.out.println($S)", System.class, "helloWorld")//定义方法体
//                .build();
//        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")//创建HelloWorld类
//                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)//定义修饰符为 public final
//                .addMethod(methodMain)//添加方法
//                .addJavadoc("@  此方法由apt自动生成")//定义方法参数
//                .build();
//        JavaFile javaFile = JavaFile.builder("com.tuodao.apt", helloWorld).build();// 生成源   代码
//        try {
//            javaFile.writeTo(mAbstractProcessor.mFiler);//// 在 app module/build/generated/source/apt 生成一份源代码
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
