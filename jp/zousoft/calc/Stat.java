/**
**	Stat
**
**	��ԉ��z�N���X
*/
package jp.zousoft.calc;

public abstract class Stat
{
	public Stat(ValueContainer cDisp, ValueContainer cMem, Params cParam)
	{
		mDisp  = cDisp;
		mMem   = cMem;
		mParam = cParam;
		mNext  = null;
	}

	protected ValueContainer	mDisp;
	protected ValueContainer	mMem;

	protected Params	mParam;
	protected Stat		mNext;

	public abstract Stat next(Keys.Type cType, int cValue);

	public void setStat(Stat cStat)
	{
		mNext = cStat;
	}
}
