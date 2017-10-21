/**
**	StatZero
**
**	���l���͑҂����
**	��ԑJ�ځF
**		���l�F���l���Z�b�g
**		�����_�F�����_���Z�b�g
**		�����ϊ��F�����̕ϊ�(sign)
**		���F���l��ݒ肷��
**		���Z�q�F���Z�����{�����̏�ԂɈړ�
**		�N���A�F���l�̃N���A
**		AC�F���l���폜�����̏�ԂɈړ�
*/
package jp.zousoft.rpc;

import jp.zousoft.calc.ValueContainer;
import jp.zousoft.calc.Params;
import jp.zousoft.calc.Keys;

public class StatZero extends Stat
{
	public StatZero(ValueContainer cValue, Works cWork)
	{
		super(cValue, cWork);
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		if(Keys.Type.All == cType)
		{
			mWork.doit(cType);
			return this;
		}
		if(mValue.isError()) return this;

		switch(cType)
		{
		case Value:
			mValue.add(cValue);
			break;
 		case Point:
			mValue.point();
			break;
 		case Sign:
			mValue.sign();
			break;
		case Equal:
			mWork.doit(Keys.Type.Equal);
			break;
		case Plus:
		case Minus:
		case Times:
		case Div:
		case All:
			mWork.doit(cType);
			return mNext;
		case Clear:
			mValue.setValue(0);
			break;
		}

		return this;
	}
}
