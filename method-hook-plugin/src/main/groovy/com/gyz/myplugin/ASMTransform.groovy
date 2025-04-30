package com.gyz.myplugin

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import org.gradle.api.Project

class ASMTransform extends Transform {

    Project project
    MethodHookExtension config

    ASMTransform(Project project,MethodHookExtension config) {
        this.project = project
        this.config = config
    }
    // transform 名称
    @Override
    String getName() {
        return this.getClass().getSimpleName()
    }
// 输入源，class文件
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }
// 文件范围，整个工程
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)

    }
}