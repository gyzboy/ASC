package com.gyz
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.AppExtension

class MyPlugin2 implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "MyPlugin2 apply this is gyz"
        def android = project.extensions.getByType(AppExtension)
//        def transform = new MyTransform(project)
//        android.registerTransform(transform)
    }
}