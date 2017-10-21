/**
**	Calc
**
**	ŠÈ’PŒvŽZ‹@
*/

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import jp.zousoft.calc.Calculator;
import jp.zousoft.calc.SmplCalc;

public class Calc extends CPanel
{
	public static void main(String[] args)
	{
		new Calc();
	}

	public Calc()
	{
		super("Simple Calculator");

		mText = new JTextField(mCalc.stat()) {{
			setHorizontalAlignment(JTextField.RIGHT);
			setEditable(false);
			Font F = getFont();
			setFont(new Font(F.getName(), F.getStyle(), F.getSize()*2));
		}};
		getContentPane().add("North",  mText);

		setBounds(0, 0, kWidth, kHeight);
		setVisible(true);
	}

	private JTextField	mText;

	private final static int	kColumn	= 12;	// ‚Æ‚è‚ ‚¦‚¸12Œ…
	private final static int	kWidth	= 300;
	private final static int	kHeight	= 300;

	protected Calculator newCalculator() { return new SmplCalc(kColumn); }

	protected ActionListener newActionListener(String cOp)
	{
		return new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mCalc.input(cOp);
				mText.setText(mCalc.stat());
			}
		};
	}
}
