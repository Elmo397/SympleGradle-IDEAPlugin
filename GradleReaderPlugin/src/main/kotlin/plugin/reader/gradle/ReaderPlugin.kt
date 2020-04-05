package plugin.reader.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ReaderPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create("readerPlugin", ReaderTask::class.java).run {
            group = "org.test-task"
            description = "Read file and write content from it"
        }
    }
}