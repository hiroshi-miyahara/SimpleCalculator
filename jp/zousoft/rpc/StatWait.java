/**
**	StatWait
**
**	���l���͑҂����
**	��ԑJ�ځF
**		���l�F���l���Z�b�g
**		�����_�F�����_���Z�b�g
**		�����ϊ��F�����ϊ������s(sign)
**		���Z�q,���F���Z�q��ݒ肵���̏�ԂɈړ�
**		�N���A�F���l�����������̏�ԂɈړ�
**		AC�F���l�̏���
*/
package jp.zousoft.rpc;

import jp.zousoft.calc.ValueContainer;
import jp.zousoft.calc.Params;
import jp.zousoft.calc.Keys;

class StatWait extends Stat
{
	public StatWait(ValueContainer cValue, Works cWork)
	{
		super(cValue, cWork);
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		if(Keys.Type.All == cType)
		{
			mWork.doit(cType);
			return mNext;
		}
		if(mValue.isError()) return this;

		switch(cType)
		{
		case Value:
			mWork.doit(Keys.Type.Equal);
			mValue.add(cValue);
			return mNext;
		case Point:
			mWork.doit(Keys.Type.Equal);
			mValue.point();
			return mNext;
		case Sign:
			mValue.sign();
			break;
		case Clear:
			mWork.doit(Keys.Type.Equal);
			mValue.setValue(0);
			return mNext;
		case All:
			mWork.doit(Keys.Type.All);
			break;
		case Equal:
			mWork.doit(cType);
			break;
		case Plus:
		case Minus:
		case Times:
		case Div:
			mWork.doit(cType);
			break;
		}

		return this;
	}
}
