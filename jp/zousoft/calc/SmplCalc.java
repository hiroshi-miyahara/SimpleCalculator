/**
**	SmplCalc
**
**	�m�[�}���d��̎���
**	0-9	���l
**	.	�����_
**	+-* /=	���Z�q
**	AC	�I�[���N���A
**	CLR	���ݓ��͒��̐��l���N���A
**	SGN	�����ϊ�
*/
package jp.zousoft.calc;

public class SmplCalc implements Calculator
{
	public SmplCalc(int cCol)
	{
		mDisp = new ValueContainer(cCol);
		mMem  = new ValueContainer(cCol);
		Params	aParam = new Params() {
			private Keys.Type mType;
			public void setParam(Keys.Type cType) { mType = cType; }
			public Keys.Type getParam() { return mType; }
		};

		mList = new Stat[4];
		mList[0] = new StatZero(mDisp, mMem, aParam);
		mList[1] = new StatOne (mDisp, mMem, aParam);
		mList[2] = new StatStay(mDisp, mMem, aParam);
		mList[3] = new StatNum (mDisp, mMem, aParam);
		mList[0].setStat(mList[1]);
		mList[1].setStat(mList[0]);
		mList[1].setStat(mList[2]);
		mList[2].setStat(mList[0]);
		mList[2].setStat(mList[3]);
		mList[3].setStat(mList[0]);
		mList[3].setStat(mList[2]);

		mKey  = new Keys();
		mStat = mList[0];
//System.out.println(mStat.getClass().getName());
	}

	private ValueContainer	mDisp;	// �\�����̒l
	private ValueContainer	mMem;	// �v�Z�҂��̒l(�P�O�̒l)

	private Keys	mKey;
	private Stat	mStat;	// ���݂̏��
	private Stat[]	mList;	// �S��ԃ��X�g

	// �L�[����
	public void input(String cText)
	{
		Keys.Type	aType = mKey.getType(cText);
		if(Keys.Type.Error == aType) return;

		mStat = mStat.next(aType, mKey.getValue());
//System.out.println(mStat.getClass().getName());
	}

	// ���݂̏�Ԃ̎��o��(�\��������)
	public String stat()
	{
		return mDisp.getDisp();
	}
}
