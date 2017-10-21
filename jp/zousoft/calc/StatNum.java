/**
**	StatNum
**
**	�Q�ڈȍ~�̐��l���͑҂�
**	��ԑJ�ځF
**		���l�F���l��ǉ�(add)
**		�����_�F�����_��ǉ�(point)
**		�����ϊ��F�����̕ϊ�(sign)
**		���Z�q,���F���Z�����s���A���݂̉��Z�q��ݒ肵���̏�ԂɈړ�
**		AC�F���l���N���A��������Ԃɖ߂�
**		�N���A�F���l���N���A����
*/
package jp.zousoft.calc;

class StatNum extends Stat
{
	public StatNum(ValueContainer cDisp, ValueContainer cMem, Params cParam)
	{
		super(cDisp, cMem, cParam);
		mZero  = null;
	}

	private Stat	mZero;

	public void setStat(Stat cStat)
	{
		if(null == mZero) mZero = cStat;
		else mNext = cStat;
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		switch(cType)
		{
		case Value:
			mDisp.add(cValue);
			break;
		case Point:
			mDisp.point();
			break;
		case Sign:
			mDisp.sign();
			break;
		case Plus:
		case Minus:
		case Times:
		case Div:
		case Equal:
			switch(mParam.getParam())
			{
			case Plus:
				mDisp.setValue(mMem.getValue()+mDisp.getValue());
				break;
			case Minus:
				mDisp.setValue(mMem.getValue()-mDisp.getValue());
				break;
			case Times:
				mDisp.setValue(mMem.getValue()*mDisp.getValue());
				break;
			case Div:
				mDisp.setValue(mMem.getValue()/mDisp.getValue());
				break;
			}
			mParam.setParam(cType);
			return mNext;
		case All:
			mDisp.clear();
			mMem .clear();
			return mZero;
		case Clear:
			mDisp.clear();
			break;
		}

		return this;
	}
}
