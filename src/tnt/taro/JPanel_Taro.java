package tnt.taro;

import javax.swing.JPanel;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import tnt.GAME;
import tnt.GameFrame;
import tnt.Statics;

import javax.swing.JScrollPane;

public class JPanel_Taro extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public JPanel_Taro() {
		setSize(1200, 750);

		setLayout(null);

		ImageIcon imgTaro = new ImageIcon("image/taro/ta1.png");

		JButton button = new JButton("1", imgTaro);
		button.setBounds(95, 71, 170, 260);
		add(button);
		button.addActionListener((ActionListener) this);

		JButton button_1 = new JButton("2", imgTaro);
		button_1.setBounds(353, 72, 170, 260);
		add(button_1);
		button_1.addActionListener((ActionListener) this);

		JButton button_2 = new JButton("3", imgTaro);
		button_2.setBounds(628, 69, 170, 260);
		add(button_2);
		button_2.addActionListener((ActionListener) this);

		JButton button_3 = new JButton("4", imgTaro);
		button_3.setBounds(905, 69, 170, 260);
		add(button_3);
		button_3.addActionListener((ActionListener) this);

		JButton button_4 = new JButton("5", imgTaro);
		button_4.setBounds(95, 396, 170, 260);
		add(button_4);
		button_4.addActionListener((ActionListener) this);

		JButton button_5 = new JButton("6", imgTaro);
		button_5.setBounds(357, 393, 170, 260);
		add(button_5);
		button_5.addActionListener((ActionListener) this);

		JButton button_6 = new JButton("7", imgTaro);
		button_6.setBounds(632, 394, 162, 260);
		add(button_6);
		button_6.addActionListener((ActionListener) this);

		JButton button_7 = new JButton("8", imgTaro);
		button_7.setBounds(907, 397, 170, 260);
		add(button_7);
		button_7.addActionListener((ActionListener) this);

		JButton btnExit = new JButton(Statics.imgExit);
		btnExit.setPreferredSize(new Dimension(Statics.imgExit.getIconWidth(), Statics.imgExit.getIconHeight()));
		btnExit.setBorderPainted(false); // 외곽선 없애줌
		btnExit.setContentAreaFilled(false);// 내용영역 채우기 않함
		btnExit.setFocusPainted(false); // 선택(focus)되었을 때 생기는 테두리 사용안함
		btnExit.setBounds(1080, 650, Statics.imgExit.getIconWidth(), Statics.imgExit.getIconHeight());
		add(btnExit);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				tManager.gameInit(); // 게임을 초기화 해준다.
				GameFrame.Inst.changePanel(GAME.FIRST);
			}
		});
		
		setBackground(Statics.colorBackGround);
		// setBounds(12, 608, 780, 52);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		JButton a = (JButton) e.getSource();

		if (a.getText().equals("1")) {
			PrintDig dig = new PrintDig();

			dig.setSize(500, 400);
			dig.show();

		} else if (a.getText().equals("2")) {
			PrintDig2 dig2 = new PrintDig2();

			dig2.setSize(500, 400);
			dig2.show();

		} else if (a.getText().equals("3")) {
			PrintDig3 dig3 = new PrintDig3();

			dig3.setSize(500, 400);
			dig3.show();

		} else if (a.getText().equals("4")) {
			PrintDig4 dig4 = new PrintDig4();

			dig4.setSize(500, 400);
			dig4.show();

		} else if (a.getText().equals("5")) {
			PrintDig5 dig5 = new PrintDig5();

			dig5.setSize(500, 400);
			dig5.show();

		} else if (a.getText().equals("6")) {
			PrintDig6 dig6 = new PrintDig6();

			dig6.setSize(500, 400);
			dig6.show();

		} else if (a.getText().equals("7")) {
			PrintDig7 dig7 = new PrintDig7();

			dig7.setSize(500, 400);
			dig7.show();

		} else if (a.getText().equals("8")) {
			PrintDig8 dig8 = new PrintDig8();

			dig8.setSize(500, 400);
			dig8.show();

		} else {

		}
	}

	/*
	 * PrintDig dig = new PrintDig();
	 * 
	 * dig.setSize(500, 400); dig.show();
	 */

}

class PrintDig extends JDialog implements ActionListener {
	// JButton btn = new JButton("뒤로 가기");

	// ImageIcon imgTaro = new ImageIcon("image/ta1.png");

	/*
	 * public printDig() {
	 * 
	 * 
	 * }
	 */

	public PrintDig() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta01.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = " 선택 카드: 달\r\n"
				+ "달은 상대가 본인을 비추어줄 태양을 그리워하고 있음을 나타낸다. 상대는 당신과 헤어지려는 게 아니라 오히려 당신과의 미래를 열고 싶은 열망에 갈등을 하고 있었던 것이다. 당신이 눈치를 못 채고 있을 뿐, 상대의 마음은 한결같이 그를 향해 있다. 이들에겐 은총의 이슬, 지복의 순간이 기다리고 있었다. 상대는 계면쩍은 얼굴로 나를 바라보았다. 나는 이러지 말고 어서 전화를 하라고 타일렀다. 창밖에는 어둠이 내리고 있었다. 도시의 탁한 대기 속에서 달은 보이지 않았지만, 누구나 달이 저 하늘에 빛나고 있음을 잘 알고 있다. 어둠은 그렇게 달빛을 안고 조용히 흘러가고 있었다.";

		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);
		getContentPane().setBackground(Statics.colorBackGround);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}

}

class PrintDig2 extends JDialog implements ActionListener {

	public PrintDig2() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta02.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = "선택 카드: 여자 교황\r\n"
				+ "여자 교황은 상대가 매우 지성적인 면모를 갖춘 인물임을 보여준다. 일정한 교육수준을 넘어 삶을 주도적으로 꾸려갈 정도의 주체성을 지닌 상대라는 것이다. 상대가 훨씬 더 능동적으로 두 사람의 관계를 이끌어나갈 것이라고 말했다. 그것을 낯설게 받아들이지 말고 사랑하는 마음으로 상대를 지켜주라고 부탁했다. 독서나 지적 교류를 통해 둘 사이는 더욱 깊고 두터워질 것이다. 당신이 어떤 만남을 형성해 가고 싶은지 정확히 알지 못하지만, 그날 당신은 책을 사기 위해 서점에 들르겠다며 일찍 자리를 떴다. 상대를 위한 선물은 책이 좋을 것이다.";

		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}

}

class PrintDig3 extends JDialog implements ActionListener {

	public PrintDig3() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta03.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = "선택 카드: 세계\r\n"
				+ "세계 카드는 하나의 그릇, 자신만의 세계, 공간이기도 하다. 세계 카드는 닫힌 공간, 우물 안 개구리에 비유될 수도 있지만 편협한 자아를 의미하기도 한다는 것을 거듭 강조했다. 현재 상황에서는 정의 카드의 올바른 선택이 정말 중요한 때다. 사람마다 자기 그릇의 크기는 다르니 각자 자신의 쓰임새에 맞는 그릇을 알맞게 채워야 한다. 자신의 그릇 크기보다 넘치게 물을 담아 결국 마실 물이 부족해진 것은 누구도 아닌 자신의 탓이다. 이를 통해 인간의 자율의지가 운명을 바꾸는 열쇠가 될 수 있다는 것을 큰 교훈으로 삼고 앞으로 남은 인생에 좋은 밑거름이 되도록 해야 할 것이다.";
		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}
}

class PrintDig4 extends JDialog implements ActionListener {
	public PrintDig4() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta04.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = "선택 카드: 전차\r\n"
				+ "연인을 얻고자 하는 마음과 평생 배필을 향한 마음은 전차를 끄는 두 말의 머리가 각기 다른 방향을 향해 있는 것처럼 ‘두 마리의 토끼를 모두 다 잡을 수 없다’는 진리를 알려준다. \r\n"
				+ "아직도 이것을 깨닫지 못하고 있는 당신의 철없음을 단적으로 보여주는 것이기도 하다.";
		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}
}

class PrintDig5 extends JDialog implements ActionListener {
	public PrintDig5() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta05.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = "선택 카드: 악마\r\n"
				+ "치열한 삶의 생존자로서, 상대에 대한 사랑이 또 다른 형태로 나타난 당신의 모습이다. 이미 오랜 세월 두 사람 사이에는 이런 식의 삶의 패턴이 반복되어 와서 그런지 특별한 건 아니지만 가장 중요한 질문 한 가지를 해왔다. 그것은 바로 당신과의 사랑이 변치 않겠느냐는 것이다. 질문이 끝남과 동시에 상대의 큰 눈에서 굵은 눈물이 흘러내렸다. 내가 해줄 수 있는 답변은 그들이 만약 함께 붙어서 살았다면 이러한 사랑을 느끼기 어려웠을 것이라는 궁색하면서도 통상적인 이야기일 뿐이다. 누군가를 그리워하고 설레는 마음으로 사랑을 기다리는 상대의 모습은 세월을 초월해 감동을 주는 좋은 사례라 생각된다.";
		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}
}

class PrintDig6 extends JDialog implements ActionListener {
	public PrintDig6() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta06.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = "선택 카드: 연인\r\n"
				+ "사랑에 빠진 것이라는 통념적인 해석을 거부하는 듯하다. 세 사람이 모여 무언가 이야기를 주고받는 형상이다. 머리 위에는 광휘에 싸인 큐피드가 한껏 활시위를 당겨 그들을 겨누고 있는데, 사실 큐피드의 장난기 어린 화살은 아직 시위를 떠난 상태가 아니다. 그리고 이 세 사람 중 누가 누구와 연인관계인지도 분명치 않다. 단순히 삼각관계라 넘겨버리기엔 무언가 복잡한 사정이 숨어 있는 것처럼 보인다. 이 맥락에서 어떤 타로카드에서는 불륜의 사랑으로 해석되기도 한다.하지만 남녀 간의 사랑이 아닌 다른 상황에 이 카드를 적용할 때에는 막 일을 시작하려는 단계의 어려움을 나타낸다. 이때에도 가운데 서 있는 인물이 중심이 된다. 토가(치마) 차림을 한 남자는 두 여인 사이에서 무엇인가 난처하고 궁색한 표정을 짓고 있다. 그러나 큐피드의 화살이 그의 심장을 겨누고 있음을 주목해야 한다. 남자는 갈림길에서 하나를 선택해야 하고 그 결과를 전적으로 책임져야 한다.";
		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}
}

class PrintDig7 extends JDialog implements ActionListener {
	public PrintDig7() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta07.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = "선택 카드: 황제\r\n"
				+ "‘황제’라는 사전적인 의미 외에 ‘기둥을 세우다’ ‘지반을 닦다’ 같은 부가적인 의미를 지니며 마르세유 타로카드에서 황제는 부정적인 이미지를 띠고 있다. 강한 남성성과 주도권을 의미하는 반면 사태를 외면하고 상황을 기피하려는 이미지를 나타내기 때문이다. 황제는 수많은 사람을 거느린 사람이기에 책임에 대한 과중한 압박이 있다. 따라서 다른 타로카드에서와 마찬가지로 황제의 표정은 밝지 않다. 외면과 기피의 부정적인 이미지는 정면을 향하지 않고 측면을 향하고 있는 자세에서 유추할 수 있다. 다른 관점으로 보면, 황제는 누군가에 대적하는 모습으로 비치기도 한다.황제의 방패 속에 그려진 독수리는 황제와 마찬가지로 왼쪽을 향해 있다. 왼쪽은 오른쪽과 달리 예로부터 악함이나 부덕함과 연결된다. 이것은 황제를 짓누르는 책임에서 자유로워지고픈 내면의 욕구를 반영한다. 또한 황제가 취한 다리 모양은 점성학에서 사용하는 목성의 약호()와 유사하다. 황제가 목성과 관련 있음을 나타내는 표지다. 항상 위기가 도사리고 있지만, 그에 아랑곳하지 않는 낙천적인 성향을 보여주며, 포괄적으로 배포 크게 기회를 베풂을 의미한다.";
		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}
}

class PrintDig8 extends JDialog implements ActionListener {
	public PrintDig8() {
		getContentPane().setLayout(null);
		JDialog dialog = new JDialog(this, "타로 결과");

		ImageIcon imgTaro = new ImageIcon("image/taro/ta08.jpg");

		JLabel label1 = new JLabel(imgTaro);
		label1.setBounds(20, 5, 200, 350);
		String a = "선택 카드: 정의\r\n"
				+ "어느 쪽을 선택할 것인가에 대한 현명한 해답을 상징하기도 하며 정의’ ‘법’ ‘공평 무사함’이라는 사전적인 의미와 더불어 ‘엄격’ ‘냉정’ ‘낮과 밤의 경계’ 등과 같은 부수적인 의미를 지닌다. 이 카드에서 가장 먼저 눈에 띄는 것은 수직성이다이 카드가 상징하는 바는  명징한 사고와 균형 잡힌 마음의 힘이다. 이것을 단적으로 보여주는 것이 바로 전통적인 상징물인 천칭저울이다. 정확하게 저울질하는 방법을 배워 가능한 한 가장 공정한 판단을 내릴 수 있도록 하는 것이 이 인물에게 요구되는 덕목이다. 이것은 ‘연인’ 카드가 도덕률을 존중하기보다 직관에 따라 느낌과 감성에 충실해 행동을 하는 것과는 대조된다. 본능을 넘어서서 이성에 따라 움직이라는 좀더 성숙한 요구를 담고 있다고 할 수 있다. 목에 두른 황금 목걸이가 이런 변함없는 성실성을 나타내고 머리에 쓴 관에 새겨진 이중의 원은 완전성을 나타낸다. 이 카드는 분쟁과 갈등을 해결하는 판단의 중요성을 강조하고 있다. 따라서 매우 신중한 처신이 필요함을 알 수 있다. 이때 신중한 처신의 핵심은 성실성과 명확함일 것이다. \r\n";

		JTextArea text = new JTextArea(a);
		text.setBounds(250, 30, 180, 300);
		text.setEditable(false);

		text.setLineWrap(true);
		JScrollPane scrollbar = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setBounds(250, 30, 200, 300);

		scrollbar.setVisible(true);

		getContentPane().add(label1);
		getContentPane().add(scrollbar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	}
}
