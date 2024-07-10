import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.util.Queue;

public class ShowDataFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane; // Panel to hold the content
    private DefaultListModel<String> showDataListModel = new DefaultListModel<>(); // Model to hold list data

    public ShowDataFrame(Queue<String> customerQueue) {
        setTitle("Show Data"); // Set the frame title
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set the default close operation to dispose on close
        setBounds(100, 100, 450, 300); // Set the frame's position and size
        contentPane = new JPanel(); // Create the content panel
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set an empty border for the content panel
        setContentPane(contentPane); // Add the content panel to the frame
        contentPane.setLayout(null); // Set the layout manager to null

        JScrollPane scrollPane = new JScrollPane(); // Create a scroll pane
        scrollPane.setBounds(10, 11, 414, 239); // Set the position and size of the scroll pane
        contentPane.add(scrollPane); // Add the scroll pane to the content panel

        JList<String> showDataList = new JList<>(showDataListModel); // Create a JList using the list model
        scrollPane.setViewportView(showDataList); // Add the list to the scroll pane's viewport

        for (String customer : customerQueue) { // Iterate through the customer queue
            showDataListModel.addElement(customer); // Add each customer to the list model
        }
    }
}
