package plugin.reader.idea.model

import java.io.OutputStream
import javax.swing.JTextArea

class DialogOutputStream(private val textArea: JTextArea) : OutputStream() {
    override fun write(inputByte: Int) {
        textArea.append(inputByte.toChar().toString())
        textArea.caretPosition = textArea.document.length
    }
}