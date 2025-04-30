import com.android.build.gradle.AppExtension
import com.gyz.myplugin.ASMTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class MethodHookPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        def config = project.getExtensions().create("methodHook", MethodHookExtension);
        def android = project.extensions.getByType(AppExtension)
        def transform = new ASMTransform(project,config)
        android.registerTransform(transform)

        println "MethodHookPlugin apply"
    }
}