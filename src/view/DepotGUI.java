import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Manager;
import utils.FileUtils;
import utils.Log;
import model.Customer;
import model.Parcel;
import java.util.List;

public class DepotGUI extends JFrame {
    private Manager manager;
    private JTextArea customerQueueTextArea;
    private JTextArea parcelListTextArea;
    private JTextArea logTextArea;
    private JTextArea feeTextArea; // To display the calculated fee

    public DepotGUI(Manager manager) {
        this.manager = manager;
        setTitle("Depot Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Customer Queue Panel
        customerQueueTextArea = new JTextArea(15, 20);
        customerQueueTextArea.setEditable(false);
        JPanel customerQueuePanel = new JPanel();
        customerQueuePanel.setLayout(new BorderLayout());
        customerQueuePanel.add(new JLabel("Customer Queue"), BorderLayout.NORTH);
        customerQueuePanel.add(new JScrollPane(customerQueueTextArea), BorderLayout.CENTER);

        // Parcel List Panel
        parcelListTextArea = new JTextArea(15, 20);
        parcelListTextArea.setEditable(false);
        JPanel parcelPanel = new JPanel();
        parcelPanel.setLayout(new BorderLayout());
        parcelPanel.add(new JLabel("Parcels for Collection"), BorderLayout.NORTH);
        parcelPanel.add(new JScrollPane(parcelListTextArea), BorderLayout.CENTER);

        // Log Panel
        logTextArea = new JTextArea(5, 50);
        logTextArea.setEditable(false);
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BorderLayout());
        logPanel.add(new JLabel("Log"), BorderLayout.NORTH);
        logPanel.add(new JScrollPane(logTextArea), BorderLayout.CENTER);

        // Fee Panel (New panel to display calculated fee)
        feeTextArea = new JTextArea(2, 50);
        feeTextArea.setEditable(false);
        JPanel feePanel = new JPanel();
        feePanel.setLayout(new BorderLayout());
        feePanel.add(new JLabel("Fee for Next Customer"), BorderLayout.NORTH);
        feePanel.add(new JScrollPane(feeTextArea), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        JButton processCustomerButton = new JButton("Process Next Customer");
        JButton calculateFeeButton = new JButton("Calculate Fee");

        controlPanel.add(processCustomerButton);
        controlPanel.add(calculateFeeButton);

        processCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processNextCustomer();
            }
        });

        calculateFeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayFee();
            }
        });

        // Add panels to frame
        add(customerQueuePanel, BorderLayout.WEST);
        add(parcelPanel, BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);
        add(logPanel, BorderLayout.CENTER);
        add(feePanel, BorderLayout.NORTH);

        setVisible(true);

        // Read and display data from the files
        displayFileContents();
    }

    private void processNextCustomer() {
        manager.processNextCustomer();
        refreshDisplay();
    }

    private void calculateAndDisplayFee() {
        String fee = manager.calculateAndDisplayFee();
        feeTextArea.setText(fee); // Display the calculated fee
    }

    private void refreshDisplay() {
        customerQueueTextArea.setText(manager.getCustomerQueueDisplay());
        parcelListTextArea.setText(manager.getParcelListDisplay());
        logTextArea.setText(Log.getInstance().getLog());
    }

    private void displayFileContents() {
        List<Customer> customers = FileUtils.readCustomerData("customers.txt");
        List<Parcel> parcels = FileUtils.readParcelData("parcels.txt");

        StringBuilder customerData = new StringBuilder();
        for (Customer customer : customers) {
            customerData.append(customer).append("\n");
        }

        StringBuilder parcelData = new StringBuilder();
        for (Parcel parcel : parcels) {
            parcelData.append(parcel).append("\n");
        }

        customerQueueTextArea.setText(customerData.toString());
        parcelListTextArea.setText(parcelData.toString());
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.initializeData("data/customers.txt", "data/parcels.txt");
        new DepotGUI(manager);
    }
}
