/**
**	Keys
**
**	���̓L�[���
*/
package jp.zousoft.calc;

public final class Keys
{
	public Keys() { }

	// �R�}���h
	public enum Type {
		Value,	// ����
		Point,	// �����_
		Plus,	// �{
		Minus,	// �|
		Times,	// �~
		Div,	// ��
		Equal,	// ��
		All,	// AC
		Clear,	// C
		Sign,	// �}
		Error	// �G���[
	}

	private int	mValue;

	public final Type getType(String cKey)
	{
		if(null == cKey) return Type.Error;

		switch(cKey.length())
		{
		case 0:	return Type.Error;
		case 1:	return key1(cKey.charAt(0));
		default:return keyM(cKey);
		}
	}

	public final int getValue() { return mValue; }

	private static final String	kFigure	= "0123456789";

	// �P��������
	private final Type key1(char C)
	{
		int	aIdx = kFigure.indexOf(C);
		if(-1 != aIdx)
		{
			mValue = aIdx;
			return Type.Value;
		}

		switch(C)
		{
		case '.':	return Type.Point;
		case '+':	return Type.Plus;
		case '-':	return Type.Minus;
		case '*':	return Type.Times;
		case '/':	return Type.Div;
		case '=':	return Type.Equal;
		}

		return Type.Error;
	}

	// ������������(���ʃR�}���h)
	private final Type keyM(String cOp)
	{
		switch(cOp)
		{
		case "AC":	return Type.All;
		case "CLR":	return Type.Clear;
		case "SGN":	return Type.Sign;
		}
		return Type.Error;
	}
}
