/**
**	StatZero
**
**	�������
**	��ԑJ�ځF
**		���l�F���l���Z�b�g�����̏�ԂɈړ�
**		�����_�F�����_���Z�b�g�����̏�ԂɈړ�
**		���̑��F�������Ȃ�
*/
package jp.zousoft.calc;

public class StatZero extends Stat
{
	public StatZero(ValueContainer cDisp, ValueContainer cMem, Params cParam)
	{
		super(cDisp, cMem, cParam);
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		switch(cType)
		{
		case Value:
			mDisp.add(cValue);
			return mNext;
 		case Point:
			mDisp.point();
			return mNext;
		}

		return this;
	}
}
