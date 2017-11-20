package tnt.tetris;

import java.util.HashMap;
import java.util.Map;

public class TetrisManager {
	static TetrisManager inst;
	
	JPanel_Tetris_Main tMain;
	
	class UserInfo{
		int num;
		JPanel_TBody tBody;
		int point;
		
		public UserInfo(int num, JPanel_TBody tBody) {
			this.num = num;
			this.tBody = tBody;
			point = 0;
		}
	}
	Map<Integer, UserInfo> tUsers;	
	
	private GAME_STATUS game_status;
	private GAME_MODE game_mode;
	private int level;
	
	TetrisManager(GAME_MODE game_mode, JPanel_Tetris_Main main){
		inst = this;
		
		this.game_mode = game_mode;
		
		this.tMain = main;
		tUsers = new HashMap<>();
	}
	
	int getLevel() {
		return level;
	}
	
	void set(int num, JPanel_TBody body) {
		tUsers.put(num, new UserInfo(num, body));
	}
	
	GAME_STATUS getGameStatus() {
		return game_status;
	}
	
	GAME_MODE getGameMode() {
		return game_mode;
	}
	
	void init() {
		game_status = GAME_STATUS.READY;
		level = 1;
		
		tMain.setMessage("Enter를 치시면 게임이 시작 됩니다.");
		for(UserInfo tUser : tUsers.values()) {
			tUser.tBody.gameReady();
			
			tUser.point = 0;
		}
	}	
		
	private void gameStop() {
		game_status = GAME_STATUS.STOP;
		
		tMain.setMessage("Enter를 치시면 게임이 다시 시작 됩니다.");
		for(UserInfo tUser : tUsers.values()) {
			tUser.tBody.gameStop();
		}
	}
	
	private void gameStart() {
		game_status = GAME_STATUS.RUNNING;
		
		tMain.setMessage("Enter를 치시면 게임이 중단 됩니다.");
		for(UserInfo tUser : tUsers.values()) {
			tUser.tBody.gameStart();
		}
	}
	
	public void levelUp(int tManagerNum) {
		if(level + 1 > TetrisStatics.MAX_LEVEL) {
			game_status = GAME_STATUS.END;
			tMain.setMessage("Enter를 치시면 게임이 새로 시작 됩니다.");
			for(UserInfo tUser : tUsers.values()) {
				if(tUser.num == tManagerNum)
					tUser.tBody.gameEnd("Victory!!");
				else
					tUser.tBody.gameEnd("You Lost!!");
			}
			return;
		}
		level++;
		for(UserInfo tUser : tUsers.values()) {
				tUser.point = 0;
				tUser.tBody.lblLevel.setLevel(level);
				tUser.tBody.panel_game.speedUp();
				if(game_mode == GAME_MODE.DYNAMIC) {
					tUser.tBody.panel_game.blockManager.setWall(level);
				}
		}
	}	
	
	public void IamLoser(int tManageNum) {
		game_status = GAME_STATUS.END;
		tMain.setMessage("Enter를 치시면 게임이 새로 시작 됩니다.");
		for(UserInfo tUser : tUsers.values()) {
			if(tUser.num == tManageNum)
				tUser.tBody.gameEnd("You Lost!!");
			else
				tUser.tBody.gameEnd("You Win!!");
		}
		return;
	}
	
	void Enter() {
		switch (game_status) {
		case READY:
			gameStart();
			break;
		case RUNNING:
			gameStop();
			break;
		case STOP:
			gameStart();
			break;
		case END:
			init();
			gameStart();
			break;
		}
		return;
	}
	
	public void clearBlockRows(int tManagerNum, int blockRows) {
		UserInfo userInfo = tUsers.get(tManagerNum);
		
		userInfo.point += blockRows;
		if(userInfo.point >= TetrisStatics.LEVEL_UP_ROWS_NUM) {
			levelUp(tManagerNum);
		}
	}
}