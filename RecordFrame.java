import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.BorderLayout;

public class RecordFrame extends JFrame {

    private static final long serialVersionUID = 1L; // Unique ID for serialization
    private JPanel contentPane; // Panel to hold the content
    private DefaultListModel<String> recordListModel = new DefaultListModel<>(); // Model to hold list data

    public RecordFrame(StringBuilder counter1Receipts, StringBuilder counter2Receipts, StringBuilder counter3Receipts) {
        setTitle("All Receipts"); // Set the frame title
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the default close operation to dispose on close
        setBounds(100, 100, 450, 300); // Set the frame's position and size
        contentPane = new JPanel(); // Create the content panel
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set an empty border for the content panel
        setContentPane(contentPane); // Add the content panel to the frame
        contentPane.setLayout(new BorderLayout(0, 0)); // Set the layout manager to BorderLayout

        JScrollPane scrollPane = new JScrollPane(); // Create a scroll pane
        contentPane.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the content panel

        JList<String> recordList = new JList<>(recordListModel); // Create a JList using the list model
        scrollPane.setViewportView(recordList); // Add the list to the scroll pane's viewport

        // Add each receipt line by line for better scrolling experience
        recordListModel.addElement("Counter 1 Receipts:"); // Add a label for Counter 1 receipts
        for (String line : counter1Receipts.toString().split("\n")) { // Split Counter 1 receipts by new line and iterate through them
            recordListModel.addElement(line); // Add each line to the list model
        }

        recordListModel.addElement("Counter 2 Receipts:"); // Add a label for Counter 2 receipts
        for (String line : counter2Receipts.toString().split("\n")) { // Split Counter 2 receipts by new line and iterate through them
            recordListModel.addElement(line); // Add each line to the list model
        }

        recordListModel.addElement("Counter 3 Receipts:"); // Add a label for Counter 3 receipts
        for (String line : counter3Receipts.toString().split("\n")) { // Split Counter 3 receipts by new line and iterate through them
            recordListModel.addElement(line); // Add each line to the list model
        }
    }
}
