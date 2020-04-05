package plugin.reader.idea.model

import com.intellij.openapi.project.Project
import org.gradle.tooling.GradleConnector
import java.io.File
import javax.swing.JTextArea

fun callTask(project: Project, textArea: JTextArea, filePath: String) {
    val connector = GradleConnector.newConnector()
    connector.forProjectDirectory(File(project.basePath!!))

    val connection = connector.connect()
    val build = connection.newBuild()

    build.forTasks("readerPlugin")
    build.addArguments(filePath)
    build.setStandardOutput(DialogOutputStream(textArea))
    build.run()
}