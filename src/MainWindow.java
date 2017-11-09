import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private BookModel bookModel;
    private JTable bookTable;

    public MainWindow() {
        super("Books");
        setSize(700, 500);
        setLocation(450, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenu());
        setJMenuBar(menuBar);
        setLayout(new BorderLayout());
        bookModel = new BookModel();
        bookTable = new JTable(bookModel);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.NORTH);
        setVisible(true);
    }

    private JMenu createMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem newTable = new JMenuItem("New");
        newTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTableMenu();
            }
        });

        JMenuItem openTable = new JMenuItem("Open");
        openTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTableMenu();
            }
        });

        JMenuItem saveTable = new JMenuItem("Save");
        saveTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTableMenu();
            }
        });

        fileMenu.add(newTable);
        fileMenu.add(openTable);
        fileMenu.add(saveTable);
        return fileMenu;
    }

    private void newTableMenu() {
        if (!bookModel.isEmpty()) {
            YesNoDialog dialog = new YesNoDialog(this, "Save current table?");
            if (dialog.isAnswerYes()) saveTableMenu();
        }
        bookModel = new BookModel();
        bookTable.setModel(bookModel);
    }

    private void openTableMenu() {
        if (!bookModel.isEmpty()) {
            YesNoDialog dialog = new YesNoDialog(this, "Save current table?");
            if (dialog.isAnswerYes()) saveTableMenu();
        }
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try {
                        FileInputStream fin = new FileInputStream(fileChooser.getSelectedFile());
                        ObjectInputStream objIn = new ObjectInputStream(fin);
                        bookModel.setBooks((ArrayList<Book>) objIn.readObject());
                        objIn.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            worker.execute();
        }
    }

    private void saveTableMenu() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try {
                        FileOutputStream fout = new FileOutputStream(fileChooser.getSelectedFile());
                        ObjectOutputStream objOut = new ObjectOutputStream(fout);
                        objOut.writeObject(bookModel.getBooks());
                        objOut.flush();
                        objOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            worker.execute();
        }
    }

    private JPanel createButtonPanel() {
        int width = 135;
        int height = 20;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton deleteSelectedButton = new JButton("Delete Selected");
        deleteSelectedButton.setPreferredSize(new Dimension(width, height));
        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRows();
            }
        });
        buttonPanel.add(deleteSelectedButton);

        JButton clearTableButton = new JButton("Clear Table");
        clearTableButton.setPreferredSize(new Dimension(width, height));
        clearTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTableData();
            }
        });
        buttonPanel.add(clearTableButton);

        JButton addNewRowButton = new JButton("Add New Row");
        addNewRowButton.setPreferredSize(new Dimension(width, height));
        addNewRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
        buttonPanel.add(addNewRowButton);

        JButton updateRowButton = new JButton("Update Row");
        updateRowButton.setPreferredSize(new Dimension(width, height));
        updateRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRow();
            }
        });
        buttonPanel.add(updateRowButton);

        return buttonPanel;
    }

    private void deleteTableData() {
        if (!bookModel.isEmpty()) {
            YesNoDialog dialog = new YesNoDialog(this, "Delete all data?");
            if (dialog.isAnswerYes()) bookModel.clear();
        }
    }

    private void deleteSelectedRows() {
        if (bookTable.getSelectedRowCount() > 0) {
            YesNoDialog dialog = new YesNoDialog(this, "Delete selected rows?");
            if (dialog.isAnswerYes()) {
                int[] selectedRows = bookTable.getSelectedRows();
                for (int i = 0; i < selectedRows.length; i++)
                    bookModel.deleteBook(selectedRows[i] - i);
            }
        }
    }

    private void addNewRow() {
        DataDialog dialog = new DataDialog(this);
        if (dialog.isOkPressed()) {
            bookModel.addBook(dialog.getBook());
        }
    }

    private void updateRow() {
        if (bookTable.getSelectedRowCount() != 1) {
            MessageDialog dialog = new MessageDialog(this, "You must select one row");
        } else {
            int row = bookTable.getSelectedRow();
            DataDialog dialog = new DataDialog(this, bookModel.getBook(row));
            if (dialog.isOkPressed()) {
                bookModel.setBook(row, dialog.getBook());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

}


