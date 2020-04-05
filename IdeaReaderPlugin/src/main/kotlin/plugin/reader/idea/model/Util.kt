package plugin.reader.idea.model

import com.intellij.openapi.project.Project
import org.gradle.tooling.GradleConnector
import java.io.File
import javax.swing.JTextArea

fun callTask(project: Project, fileContentArea: JTextArea, filePathArea: JTextArea) {
    val connector = GradleConnector.newConnector()
    connector.forProjectDirectory(File(project.basePath!!))

    val connection = connector.connect()
    val build = connection.newBuild()

    build.forTasks("readerPlugin")
    build.setStandardInput(DialogInputStream(filePathArea))
    build.setStandardOutput(DialogOutputStream(fileContentArea))
    build.setStandardError(DialogOutputStream(fileContentArea))
    build.run()
}
