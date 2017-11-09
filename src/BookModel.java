import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BookModel extends AbstractTableModel {

    private List<Book> books = new ArrayList<>();

    public BookModel() {
    }

    public void addBook(Book book) {
        books.add(book);
        fireTableDataChanged();
    }

    public void setBook(int index, Book book) {
        books.set(index, book);
        fireTableDataChanged();
    }

    public void deleteBook(int index) {
        books.remove(index);
        fireTableDataChanged();
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    public void clear() {
        books.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return book.getName();
            case 2:
                return book.getAuthor();
            case 3:
                return book.getTotalCount();
            case 4:
                return book.getStringDate();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "№";
            case 1:
                return "Book Name";
            case 2:
                return "Author";
            case 3:
                return "Total Count";
            case 4:
                return "Receiving Date";
        }
        return "";
    }

}
