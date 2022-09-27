package comment;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
	private int commentNum;
	private int boardNum;
	private String userId;
	private String commentBoard;
	private Timestamp commentCreatedDate;
}
