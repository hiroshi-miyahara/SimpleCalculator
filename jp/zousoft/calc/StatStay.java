/**
**	StatStay
**
**	���Z�q���͌�̑҂����
**	��ԑJ�ځF
**		���l�F���l���Z�b�g�����̏�ԂɈړ�
**		�����_�F�����_���Z�b�g�����̏�ԂɈړ�
**		���Z�q�F���Z�q��ݒ肷��
**		�����ϊ��F�����ϊ������s(sign)
**		���F����ݒ肷��
**		AC�F���l���N���A��������Ԃɖ߂�
*/
package jp.zousoft.calc;

class StatStay extends Stat
{
	public StatStay(ValueContainer cDisp, ValueContainer cMem, Params cParam)
	{
		super(cDisp, cMem, cParam);
		mZero = null;
	}

	private Stat	mZero;

	public void setStat(Stat cStat)
	{
		if(null == mZero) mZero = cStat;
		else mNext = cStat;
	}

	public Stat next(Keys.Type cType, int cValue)
	{
		if(Keys.Type.All == cType)
		{
			mDisp.clear();
			mMem .clear();
			return mZero;
		}
		if(mDisp.isError()) return this;

		switch(cType)
		{
		case Value:
			mMem .setValue(mDisp.getValue());
			mDisp.clear();
			mDisp.add(cValue);
			return mNext;
		case Point:
			mMem .setValue(mDisp.getValue());
			mDisp.clear();
			mDisp.point();
			return mNext;
		case Plus:
		case Minus:
		case Times:
		case Div:
		case Equal:
			mParam.setParam(cType);
			break;
		case Sign:
			mDisp.sign();
			break;
		}

		return this;
	}
}
