package plugin.reader.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

class ReaderTask : DefaultTask() {
    @Input
    private var filePath = ""

    @TaskAction
    fun readAction() {
        println("File path is [$filePath]")
        readFile()
    }

    private fun readFile() {
        var content = ""
        var lineCount = 0

        File(filePath).forEachLine {
            val line = it

            if (line.startsWith("#skip")) {
                lineCount = 1 + line.replace("[^0-9]".toRegex(), "").toInt()
            }

            if (lineCount == 0) content += "$line\n" else lineCount--
        }

        println(content)
    }
}