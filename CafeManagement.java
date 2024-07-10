import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class CafeManagement extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel countLabel; // Label to display customer count
    private int customerCount = 0; // Initial customer count set to 0
    private Queue<String> customerQueue = new LinkedList<>(); // Queue for storing customers
    private Queue<String> counter1Queue = new LinkedList<>(); // Queue for Counter 1
    private Queue<String> counter2Queue = new LinkedList<>(); // Queue for Counter 2
    private Queue<String> counter3Queue = new LinkedList<>(); // Queue for Counter 3
    private StringBuilder counter1Receipts = new StringBuilder(); // Receipts for Counter 1
    private StringBuilder counter2Receipts = new StringBuilder(); // Receipts for Counter 2
    private StringBuilder counter3Receipts = new StringBuilder(); // Receipts for Counter 3
    private DefaultListModel<String> counter1ListModel = new DefaultListModel<>(); // List model for Counter 1
    private DefaultListModel<String> counter2ListModel = new DefaultListModel<>(); // List model for Counter 2
    private DefaultListModel<String> counter3ListModel = new DefaultListModel<>(); // List model for Counter 3

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CafeManagement frame = new CafeManagement(); // Create frame
                    frame.setVisible(true); // Make frame visible
                } catch (Exception e) {
                    e.printStackTrace(); // Print stack trace if exception occurs
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CafeManagement() {
        setResizable(false); // Disable resizing
        setTitle("Cafe Management "); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        setBounds(100, 100, 1226, 680); // Set window position and size
        contentPane = new JPanel(); // Create content pane
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set border for content pane
        setContentPane(contentPane); // Set content pane
        contentPane.setLayout(null); // Use null layout

        countLabel = new JLabel("Count : 0"); // Initialize customer count label
        countLabel.setBounds(575, 10, 100, 13); // Set position and size of count label
        contentPane.add(countLabel); // Add count label to content pane

        JButton showDataButton = new JButton("Show Data"); // Button to show data
        showDataButton.setBounds(398, 31, 105, 25); // Set position and size of show data button
        showDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ShowDataFrame(customerQueue).setVisible(true); // Show data frame on button click
            }
        });
        contentPane.add(showDataButton); // Add show data button to content pane

        JButton addNewCusButton = new JButton("Add New Customer"); // Button to add new customer
        addNewCusButton.setBounds(537, 31, 150, 25); // Set position and size of add new customer button
        addNewCusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewCustomer(); // Add new customer on button click
            }
        });
        contentPane.add(addNewCusButton); // Add add new customer button to content pane

        JButton nextQueueButton = new JButton("Next Queue"); // Button for next queue
        nextQueueButton.setBounds(696, 31, 121, 25); // Set position and size of next queue button
        nextQueueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextQueue(); // Process next queue on button click
            }
        });
        contentPane.add(nextQueueButton); // Add next queue button to content pane

        // Create counter buttons for Counter 1
        createCounterButtons(84, 568, counter1Queue, "Counter 1", counter1Receipts);
        // Create counter buttons for Counter 2
        createCounterButtons(486, 568, counter2Queue, "Counter 2", counter2Receipts);
        // Create counter buttons for Counter 3
        createCounterButtons(910, 568, counter3Queue, "Counter 3", counter3Receipts);

        JScrollPane scrollPane = new JScrollPane(); // Scroll pane for Counter 1 list
        scrollPane.setBounds(10, 118, 386, 427); // Set position and size of scroll pane
        contentPane.add(scrollPane); // Add scroll pane to content pane
        
        JList<String> counter1List = new JList<>(counter1ListModel); // List for Counter 1
        scrollPane.setViewportView(counter1List); // Set view for scroll pane

        JScrollPane scrollPane1 = new JScrollPane(); // Scroll pane for Counter 2 list
        scrollPane1.setBounds(422, 118, 372, 427); // Set position and size of scroll pane
        contentPane.add(scrollPane1); // Add scroll pane to content pane

        JList<String> counter2List = new JList<>(counter2ListModel); // List for Counter 2
        scrollPane1.setViewportView(counter2List); // Set view for scroll pane

        JScrollPane scrollPane2 = new JScrollPane(); // Scroll pane for Counter 3 list
        scrollPane2.setBounds(819, 118, 372, 427); // Set position and size of scroll pane
        contentPane.add(scrollPane2); // Add scroll pane to content pane

        JList<String> counter3List = new JList<>(counter3ListModel); // List for Counter 3
        scrollPane2.setViewportView(counter3List); // Set view for scroll pane

        JButton recordButton = new JButton("Record"); // Button to show records
        recordButton.setBounds(0, 603, 1212, 40); // Set position and size of record button
        recordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RecordFrame(counter1Receipts, counter2Receipts, counter3Receipts).setVisible(true); // Show record frame on button click
            }
        });
        contentPane.add(recordButton); // Add record button to content pane

        JLabel counter1Label = new JLabel("Counter 1"); // Label for Counter 1
        counter1Label.setBounds(172, 95, 100, 13); // Set position and size of Counter 1 label
        contentPane.add(counter1Label); // Add Counter 1 label to content pane

        JLabel counter2Label = new JLabel("Counter 2"); // Label for Counter 2
        counter2Label.setBounds(587, 95, 100, 13); // Set position and size of Counter 2 label
        contentPane.add(counter2Label); // Add Counter 2 label to content pane

        JLabel counter3Label = new JLabel("Counter 3"); // Label for Counter 3
        counter3Label.setBounds(997, 95, 100, 13); // Set position and size of Counter 3 label
        contentPane.add(counter3Label); // Add Counter 3 label to content pane
    }

    private void createCounterButtons(int x, int y, Queue<String> queue, String counterName, StringBuilder receipts) {
        // Create payment button for counters
        JButton paymentButton = new JButton("Payment");
        paymentButton.setBounds(x, y, 85, 25); // Set position and size of payment button
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processPayment(queue, counterName, receipts); // Process payment on button click
                updateListModels(); // Update list models after payment
            }
        });
        contentPane.add(paymentButton); // Add payment button to content pane

        // Create receipt button for counters
        JButton receiptButton = new JButton("Receipt");
        receiptButton.setBounds(x + 171, y, 85, 25); // Set position and size of receipt button
        receiptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReceipt(receipts, counterName); // Show receipt on button click
            }
        });
        contentPane.add(receiptButton); // Add receipt button to content pane
    }

    private void addNewCustomer() {
        // Method to add new customers from file
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\aqeel\\Downloads\\customerList.txt"))) {
            String line;
            while ((line = br.readLine()) != null && customerQueue.size() < 100) {
                customerQueue.add(line); // Add customer to queue
            }
            customerCount = customerQueue.size(); // Update customer count
            countLabel.setText("Count : " + customerCount); // Update count label
            JOptionPane.showMessageDialog(this, "100 New Customers added successfully"); // Show success message
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if exception occurs
        }
    }

    private void nextQueue() {
        // Method to process next queue
        while (!customerQueue.isEmpty() && (counter1Queue.size() < 5 || counter2Queue.size() < 5 || counter3Queue.size() < 5)) {
            String customer = customerQueue.poll(); // Get next customer from queue
            if (customer != null) {
                // Add debugging to verify customer format
                System.out.println("Customer Data: " + customer);
                try {
                    int quantity = Integer.parseInt(customer.split(",")[6].trim()); // Get quantity from customer data
                    System.out.println("Customer Quantity: " + quantity);

                    if (quantity <= 5 && counter1Queue.size() < 5) {
                        counter1Queue.add(customer); // Add to Counter 1 if conditions met
                        System.out.println("Added to Counter 1: " + customer);
                    } else if (quantity <= 5 && counter2Queue.size() < 5) {
                        counter2Queue.add(customer); // Add to Counter 2 if conditions met
                        System.out.println("Added to Counter 2: " + customer);
                    } else if (quantity > 5 && counter3Queue.size() < 5) {
                        counter3Queue.add(customer); // Add to Counter 3 if conditions met
                        System.out.println("Added to Counter 3: " + customer);
                    } else {
                        customerQueue.add(customer); // Add back to customer queue if no counter available
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing quantity for customer: " + customer);
                }
            }
        }
        updateListModels(); // Update list models
        updateQueueCounts(); // Update queue counts
    }

    private void processPayment(Queue<String> queue, String counterName, StringBuilder receipts) {
        // Method to process payment for customer at counter
        if (!queue.isEmpty()) {
            String customer = queue.poll(); // Get next customer from queue
            // Process the payment for the customer
            receipts.append("Processed payment for ").append(customer.split(",")[1]).append(" at ").append(counterName).append("\n");
            JOptionPane.showMessageDialog(this, "Processed payment for " + customer.split(",")[1] + " at " + counterName);
        } else {
            JOptionPane.showMessageDialog(this, counterName + " is empty."); // Show message if counter is empty
        }
    }

    private void showReceipt(StringBuilder receipts, String counterName) {
        // Method to show receipts for counter
        if (receipts.length() > 0) {
            JOptionPane.showMessageDialog(this, receipts.toString()); // Show receipts if available
        } else {
            JOptionPane.showMessageDialog(this, counterName + " has no receipts."); // Show message if no receipts
        }
    }

    private void updateQueueCounts() {
        // Method to update queue counts
        countLabel.setText("Count : " + customerQueue.size()); // Update count label with customer queue size
    }

    private void updateListModels() {
        // Method to update list models for counters
        counter1ListModel.clear(); // Clear Counter 1 list model
        counter2ListModel.clear(); // Clear Counter 2 list model
        counter3ListModel.clear(); // Clear Counter 3 list model

        for (String customer : counter1Queue) {
            counter1ListModel.addElement(customer); // Add customers to Counter 1 list model
        }

        for (String customer : counter2Queue) {
            counter2ListModel.addElement(customer); // Add customers to Counter 2 list model
        }

        for (String customer : counter3Queue) {
            counter3ListModel.addElement(customer); // Add customers to Counter 3 list model
        }

        // Debugging to ensure counters are updated correctly
        System.out.println("Counter 1 Queue: " + counter1Queue);
        System.out.println("Counter 2 Queue: " + counter2Queue);
        System.out.println("Counter 3 Queue: " + counter3Queue);
    }
}
