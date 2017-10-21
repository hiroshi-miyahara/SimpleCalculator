/**
**	CPanel
**
**	ä»íPåvéZã@ï\é¶ïîï™
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import jp.zousoft.calc.Calculator;

public abstract class CPanel extends JFrame
{
	public CPanel(String cTitle)
	{
		super(cTitle);

		mCalc = newCalculator();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add("Center", createNums());
		getContentPane().add("West",   createTool());
		getContentPane().add("North",  createFigs());
	}

	protected Calculator	mCalc;

	protected abstract Calculator newCalculator();
	protected abstract JComponent createFigs();
	protected abstract ActionListener newActionListener(String cOp);

	protected JPanel createNums()
	{
		return new JPanel() {{
			setLayout(new GridLayout(4, 4));
			add(createNumBtn(7));
			add(createNumBtn(8));
			add(createNumBtn(9));
			add(createOpBtn("ÅÄ", "/"));
			add(createNumBtn(4));
			add(createNumBtn(5));
			add(createNumBtn(6));
			add(createOpBtn("Å~", "*"));
			add(createNumBtn(1));
			add(createNumBtn(2));
			add(createNumBtn(3));
			add(createOpBtn("Å|", "-"));
			add(createNumBtn(0));
			add(createOpBtn(".", "."));
			add(createOpBtn("Å{", "+"));
			add(createOpBtn("ÅÅ", "="));
		}};
	}

	protected JPanel createTool()
	{
		return new JPanel() {{
			setLayout(new GridLayout(3, 1));
			JButton AC = createOpBtn("AC", "AC");
			AC.setBackground(Color.RED);
			add(AC);
			add(createOpBtn("Çb", "CLR"));
			add(createOpBtn("Å}", "SGN"));
		}};
	}

	private JButton createNumBtn(int cVal)
	{
		return createOpBtn(""+(char)('ÇO'+cVal), ""+cVal);
	}

	private static Font	mFont	= null;

	protected JButton createOpBtn(String cLabel, String cOp)
	{
		return new JButton(cLabel) {{
			addActionListener(newActionListener(cOp));
			if(null == mFont)
			{
				mFont = getFont();
				mFont = new Font(mFont.getName(), mFont.getStyle(), mFont.getSize()*3/2);
			}
			setFont(mFont);
		}};
	}
}
