package plugin.reader.idea.ui

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBScrollPane
import org.jdesktop.swingx.VerticalLayout
import plugin.reader.idea.model.callTask
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*

class FileSelectionDialog(private val project: Project) : JFrame("File reader") {
    private val parentPanel = JPanel(VerticalLayout())
    private val filePathArea = JTextArea(1, 30)

    init {
        defaultCloseOperation = EXIT_ON_CLOSE

        parentPanel.add(createSelectFilePanel())
        parentPanel.add(createShowContentPanel())

        contentPane.add(parentPanel)
    }

    fun open() {
        preferredSize = Dimension(500, 263)
        isResizable = false
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }

    private fun createSelectFilePanel(): JPanel {
        val panel = JPanel(FlowLayout(FlowLayout.CENTER))
        val selectFileBtn = JButton("Select file")

        selectFileBtn.addActionListener {
            val fileChooser = JFileChooser()
            val ret = fileChooser.showDialog(null, "Select file")

            if (ret == JFileChooser.APPROVE_OPTION) {
                val file = fileChooser.selectedFile
                filePathArea.text = file.path
            }
        }

        filePathArea.isEnabled = false
        val scrollPane = JBScrollPane(
            filePathArea,
            JBScrollPane.VERTICAL_SCROLLBAR_NEVER,
            JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        )

        panel.add(scrollPane)
        panel.add(selectFileBtn)

        return panel
    }

    private fun createShowContentPanel(): JPanel {
        val panel = JPanel(VerticalLayout())
        val runBtn = JButton("Run")
        val fileContentArea = JTextArea(10, 40)

        runBtn.addActionListener {
            val filePath = filePathArea.text
            callTask(project, fileContentArea, filePath)
        }

        fileContentArea.isEnabled = false
        val scrollPane = JBScrollPane(
            fileContentArea,
            JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        )

        panel.add(scrollPane)
        panel.add(runBtn)

        return panel
    }
}