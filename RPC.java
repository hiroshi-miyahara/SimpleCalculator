/**
**	RPC
**
**	逆ポーランド計算機
*/

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import jp.zousoft.calc.Calculator;
import jp.zousoft.rpc.RPCalc;

public class RPC extends CPanel
{
	public static void main(String[] args)
	{
		new RPC();
	}

	public RPC()
	{
		super("Reverse Polish Calculator");

		mText = new JTextField [kRow];
		JPanel	aText = new JPanel() {{
			setLayout(new BorderLayout());
			add("North",  mText[0] = createText(true));
			add("Center", new JPanel() {{
				setLayout(new GridLayout(kRow-1, 1));
				for(int i=1 ; i<kRow ; ++i) {
					add(mText[i] = createText(false));
				}
			}} );
		}};
		mText[0].setText(mCalc.stat());
		getContentPane().add("North",  aText);

		setBounds(0, 0, kWidth, kHeight);
		setVisible(true);
	}

	private JTextField[]	mText;

	private final static int	kColumn	= 12;	// とりあえず12桁
	private final static int	kRow	= 5;
	private final static int	kWidth	= 300;
	private final static int	kHeight	= 380;

	private JTextField createText(boolean cFirst)
	{
		return new JTextField() {{
			setHorizontalAlignment(JTextField.RIGHT);
			setEditable(false);
			if(cFirst)
			{
				Font F = getFont();
				setFont(new Font(F.getName(), F.getStyle(), F.getSize()*2));
			} else
			{
				setForeground(Color.DARK_GRAY);
			}
		}};
	}

	protected Calculator newCalculator() { return new RPCalc(kColumn); }

	protected ActionListener newActionListener(String cOp)
	{
		return new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mCalc.input(cOp);
				RPCalc aCalc = (RPCalc)mCalc;
				for(int i=0 ; i<mText.length ; ++i)
				{
					String	aText = (i<aCalc.countStat()) ? aCalc.getStat(i) : "";
					mText[i].setText(aText);
				}
			}
		};
	}
}
