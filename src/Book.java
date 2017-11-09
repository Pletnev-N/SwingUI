import org.omg.CORBA.INTERNAL;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book implements Serializable {
    private String name;
    private String author;
    private int totalCount;
    private GregorianCalendar receivingDate;

    public Book(String name, String author, int totalCount, GregorianCalendar receivingDate) {
        this.name = name;
        this.author = author;
        this.totalCount = totalCount;
        this.receivingDate = receivingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public GregorianCalendar getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(GregorianCalendar receivingDate) {
        this.receivingDate = receivingDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", totalCount=" + totalCount +
                ", receivingDate=" + receivingDate +
                '}';
    }

    public String getStringDate() {
        return Integer.toString(receivingDate.get(Calendar.DAY_OF_MONTH)) + "." +
                Integer.toString(receivingDate.get(Calendar.MONTH) + 1) + "." +
                Integer.toString(receivingDate.get(Calendar.YEAR));
    }
}
