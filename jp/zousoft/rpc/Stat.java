/**
**	Stat
**
**	èÛë‘âºëzÉNÉâÉX
*/
package jp.zousoft.rpc;

import jp.zousoft.calc.ValueContainer;
import jp.zousoft.calc.Params;
import jp.zousoft.calc.Keys;

public abstract class Stat
{
	public Stat(ValueContainer cValue, Works cWork)
	{
		mValue = cValue;
		mWork  = cWork;
	}

	protected ValueContainer	mValue;
	protected Works		mWork;
	protected Stat		mNext;

	public abstract Stat next(Keys.Type cType, int cValue);

	public void setStat(Stat cStat)
	{
		mNext = cStat;
	}
}
