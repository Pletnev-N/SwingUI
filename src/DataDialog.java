import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataDialog extends JDialog {

    private String name;
    private String author;
    private int totalCount;
    private GregorianCalendar receivingDate;

    private JTextField nameTextField;
    private JTextField authorTextField;
    private JSpinner countSpinner;
    private JSpinner yearSpinner;
    private JComboBox<String> monthsComboBox;
    private JSpinner daySpinner;

    private boolean okPressed = false;

    public DataDialog(Frame owner) {
        super(owner, true);
        int width = 300;
        int height = 250;
        setSize(width, height);
        setResizable(false);
        setLocation(owner.getLocation().x + owner.getWidth() / 2 - width / 2, owner.getLocation().y + owner.getHeight() / 2 - height / 2);

        totalCount = 1;
        receivingDate = new GregorianCalendar();

        setLayout(new BorderLayout());
        add(createDataFields(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    public DataDialog(Frame owner, Book book) {
        super(owner, true);
        int width = 300;
        int height = 250;
        setSize(width, height);
        setResizable(false);
        setLocation(owner.getLocation().x + owner.getWidth() / 2 - width / 2, owner.getLocation().y + owner.getHeight() / 2 - height / 2);

        name = book.getName();
        author = book.getAuthor();
        totalCount = book.getTotalCount();
        receivingDate = book.getReceivingDate();

        setLayout(new BorderLayout());
        add(createDataFields(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel createDataFields() {
        JPanel dataPanel = new JPanel(new GridLayout(0, 2, 2, 2));

        dataPanel.add(new JLabel("Name:"));
        dataPanel.add(nameTextField = new JTextField(name));

        dataPanel.add(new JLabel("Author:"));
        dataPanel.add(authorTextField = new JTextField(author));

        SpinnerModel spinnerModel = new SpinnerNumberModel(totalCount, 1, 9999, 1);
        countSpinner = new JSpinner(spinnerModel);
        dataPanel.add(new JLabel("Total Count:"));
        dataPanel.add(countSpinner);

        spinnerModel = new SpinnerNumberModel(receivingDate.get(Calendar.YEAR), 1900, 2100, 1);
        yearSpinner = new JSpinner(spinnerModel);
        dataPanel.add(new JLabel("Year:"));
        dataPanel.add(yearSpinner);

        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthsComboBox = new JComboBox<>(months);
        monthsComboBox.setSelectedIndex(receivingDate.get(Calendar.MONTH));
        dataPanel.add(new JLabel("Month:"));
        dataPanel.add(monthsComboBox);

        spinnerModel = new SpinnerNumberModel(receivingDate.get(Calendar.DAY_OF_MONTH), 1, 31, 1);
        daySpinner = new JSpinner(spinnerModel);
        dataPanel.add(new JLabel("Day:"));
        dataPanel.add(daySpinner);

        return dataPanel;
    }

    private JPanel createButtonPanel() {
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonListener();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    private void okButtonListener() {
        String incorrect = "";
        if (!nameIsCorrect()) incorrect = incorrect + "  Name  ";
        if (!authorIsCorrect()) incorrect = incorrect + "  Author  ";
        if (!dayIsCorrect()) incorrect = incorrect + "  Day  ";
        if (incorrect.isEmpty()) {
            name = nameTextField.getText();
            author = authorTextField.getText();
            totalCount = (Integer) countSpinner.getValue();
            okPressed = true;
            setVisible(false);
        } else {
            incorrect = "Incorrect: " + incorrect;
            MessageDialog dialog = new MessageDialog(this, incorrect);
        }
    }

    private boolean nameIsCorrect() {
        return !nameTextField.getText().isEmpty();
    }

    private boolean authorIsCorrect() {
        return !authorTextField.getText().isEmpty();
    }

    private boolean dayIsCorrect() {
        receivingDate = new GregorianCalendar((Integer) yearSpinner.getValue(), monthsComboBox.getSelectedIndex(), (Integer) daySpinner.getValue());
        return receivingDate.get(Calendar.MONTH) == monthsComboBox.getSelectedIndex();
    }

    public boolean isOkPressed() {
        return okPressed;
    }

    public Book getBook() {
        return new Book(name, author, totalCount, receivingDate);
    }
}
