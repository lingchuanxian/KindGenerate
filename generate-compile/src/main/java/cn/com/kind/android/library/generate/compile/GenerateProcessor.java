package cn.com.kind.android.library.generate.compile;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
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
        handleKindListActivityAnnotation(roundEnv);
        return false;
    }

    /**
     * 处理KindListActivity注解相关
     * @param roundEnv
     */
    private void handleKindListActivityAnnotation(RoundEnvironment roundEnv) {
        //存储类名的集合
        List<ListActivityModel> activityModelList = new ArrayList<>();
        //得到所有包含该注解的element集合
        Set<TypeElement> typeElementSet = (Set<TypeElement>) roundEnv.getElementsAnnotatedWith(KindListActivity.class);
        for (TypeElement element : typeElementSet) {
            String[] busikey = element.getAnnotation(KindListActivity.class).busikey();
            //获取类名
            String className = element.getSimpleName().toString();
            //获取包名加类名
            String fullClassName = element.getQualifiedName().toString();

            activityModelList.add(new ListActivityModel(busikey,className,fullClassName, ClassName.get(element)));
        }
        generateKindListActivityManager(activityModelList);
    }

    /**
     * 生成KindListActivityManager管理类
     */
    private void generateKindListActivityManager(List<ListActivityModel> activityModelList) {
        CodeBlock.Builder build =  CodeBlock.builder();
        for (ListActivityModel activityModel : activityModelList){
           String caseCode = "";
           for (String busikey : activityModel.getBusikey()){
               caseCode += "case \"" + busikey + "\":\n";
           }
            caseCode += "clazz = $T.class;\n";
            build.add(caseCode,activityModel.getClassNameObj());
            build.addStatement("break");
        }
        build.add("default:\n");
        build.addStatement("clazz = null");
        build.addStatement("break");

        MethodSpec methodMain = MethodSpec.methodBuilder("getListActivity")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Class.class)
                .addParameter(String.class, "busikey")
                .addStatement("$T clazz = null",Class.class)
                .beginControlFlow("switch (busikey)")
                .addCode(build.build())
                .endControlFlow()
                .addStatement("return clazz")
                .build();


        TypeSpec kindListActivityManager = TypeSpec.classBuilder("KindListActivityManager")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodMain)
                .addJavadoc("@author ling_cx")
                .addJavadoc("\n@description 列表activity管理类")
                .addJavadoc("\n@date " + new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date()))
                .addJavadoc("\n@Copyright: 2020 www.kind.com.cn Inc. All rights reserved." )
                .build();
        JavaFile javaFile = JavaFile.builder("cn.com.kind.android.library.kindgenerate.result", kindListActivityManager)
                .build();

        try {
            javaFile.writeTo(mFiler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
