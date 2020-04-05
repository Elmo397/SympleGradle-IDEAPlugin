package plugin.reader.idea.action

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import plugin.reader.idea.ui.FileSelectionDialog

class FileReaderAction: AnAction(
    "FileReader",
    "Runs gradle task, that read file and write content from it to dialog window",
    AllIcons.Actions.ListFiles
) {
    override fun actionPerformed(e: AnActionEvent) {
        FileSelectionDialog(e.project!!).open()
    }
}