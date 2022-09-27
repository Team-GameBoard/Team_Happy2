package board;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
	private int board_num;
	private String user_id;
	private int game_num;
	private String board_title;
	private String board_context;
	private Timestamp board_created_date;
	private Timestamp board_update_date;
	private int read_num;
	
	public Board(String userId, String boardTitle, String boardContext) {
		this.user_id = userId;
		this.board_title = boardTitle;
		this.board_context = boardContext;
	}
	
	public Board(String userId, String boardTitle, String boardContext, int game_num) {
		this.user_id = userId;
		this.board_title = boardTitle;
		this.board_context = boardContext;
		this.game_num = game_num;
	}

	public Board(int boardNum, String boardTitle, String boardContext) {
		this.board_num = boardNum;
		this.board_title = boardTitle;
		this.board_context = boardContext;
	}
	
}
