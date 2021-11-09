package d2l.model;

import lombok.Data;

import java.sql.Date;
import java.util.Comparator;

@Data
public class Comment {

    public static Comparator<Comment> commentComparator = new Comparator<Comment>() {
        @Override
        public int compare(Comment o1, Comment o2) {
            int delta = 0;
            if (o1.getCreationDate().before(o2.getCreationDate())) delta = 1;
            else if (o2.getCreationDate().after(o2.getCreationDate())) delta = -1;
            return delta;
        }
    };

    private int id;
    private String text;
    private String rating;
    private Date creationDate;
    private boolean isDeleted;
    private int idUser;
    private int idProduct;

    public Comment() {}
}
