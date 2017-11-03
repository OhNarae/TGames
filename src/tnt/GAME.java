package tnt;

public enum GAME {
	FIRST("First", "메뉴판"), TARO("Taro", "타로"), TETRIS("Tetris", "테트리스"), TRICK_PICTURE("Trick Picture", "숨은 그림찾기");

	private String eng;
	private String kor;

	private GAME(String str, String kor) {
		this.eng = str;
		this.kor = kor;
	}

	public String getEng() {
		return eng;
	}

	public String getKor() {
		return kor;
	}
}
