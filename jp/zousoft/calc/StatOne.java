/**
**	StatOne
**
**	�������l���͏��
**	��ԑJ�ځF
**		���l�F���l��ǉ�(add)
**		�����_�F�����_��ǉ�(point)
**		���F�Ȃɂ����Ȃ�
**		�����ϊ��F�����̕ϊ�(sign)
**		���Z�q�F���Z�q��ݒ肵���̏�ԂɈړ�
**		�N���A,AC�F���l���N���A��������Ԃɖ߂�
*/
package jp.zousoft.calc;

public class StatOne extends Stat
{
	public StatOne(ValueContainer cDisp, ValueContainer cMem, Params cParam)
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
		switch(cType)
		{
		case Value:
			mDisp.add(cValue);
			break;
		case Point:
			mDisp.point();
			break;
		case Equal:
			break;
		case Sign:
			mDisp.sign();
			break;
		case All:
		case Clear:
			mDisp.clear();
			return mZero;
		default:
			mParam.setParam(cType);
			return mNext;
		}

		return this;
	}
}
