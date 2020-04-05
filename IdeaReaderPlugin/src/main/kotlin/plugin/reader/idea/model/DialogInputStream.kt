package plugin.reader.idea.model

import java.io.InputStream
import java.nio.ByteBuffer
import javax.swing.JTextArea

class DialogInputStream(private val textArea: JTextArea) : InputStream() {
    override fun read(): Int {
        val buf = ByteBuffer.wrap(textArea.text.toByteArray())

        if (!buf.hasRemaining()) {
            return -1
        }
        return buf.int
    }
}