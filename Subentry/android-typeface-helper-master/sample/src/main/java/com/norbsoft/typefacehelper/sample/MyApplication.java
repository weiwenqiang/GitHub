package com.norbsoft.typefacehelper.sample;

import android.app.Application;
import android.graphics.Typeface;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

public class MyApplication extends Application {

	/** Multiple custom typefaces support */
	private TypefaceCollection mJuiceTypeface;
	/** Multiple custom typefaces support */
	private TypefaceCollection mArchRivalTypeface;
	/** Multiple custom typefaces support */
	private TypefaceCollection mActionManTypeface;
	/** Multiple custom typefaces support */
	private TypefaceCollection mSystemDefaultTypeface;
	/** Multiple custom typefaces support */
	private TypefaceCollection mUbuntuTypeface;

	@Override public void onCreate() {
		super.onCreate();

		// Load helper with default custom typeface (single custom typeface)
		TypefaceHelper.init(new TypefaceCollection.Builder()
				.set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-R.ttf"))
				.set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-B.ttf"))
				.set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-RI.ttf"))
				.set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-BI.ttf"))
				.create());

		// Multiple custom typefaces support
		mJuiceTypeface = new TypefaceCollection.Builder()
				.set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Regular.ttf"))
				.set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Bold.ttf"))
				.set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Italic.ttf"))
				.set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Juice/JUICE_Bold_Italic.ttf"))
				.create();

		// Multiple custom typefaces support
		mArchRivalTypeface = new TypefaceCollection.Builder()
				.set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival.ttf"))
				.set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival_Bold.ttf"))
				.set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival_Italic.ttf"))
				.set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/arch_rival/SF_Arch_Rival_Bold_Italic.ttf"))
				.create();

		// Multiple custom typefaces support
		mActionManTypeface = new TypefaceCollection.Builder()
				.set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man.ttf"))
				.set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man_Bold.ttf"))
				.set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man_Italic.ttf"))
				.set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/Action-Man/Action_Man_Bold_Italic.ttf"))
				.create();

		// Multiple custom typefaces support
		mUbuntuTypeface = new TypefaceCollection.Builder()
				.set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-R.ttf"))
				.set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-B.ttf"))
				.set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-RI.ttf"))
				.set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-BI.ttf"))
				.create();

		// Multiple custom typefaces support
		mSystemDefaultTypeface = TypefaceCollection.createSystemDefault();
	}

	/** Multiple custom typefaces support */
	public TypefaceCollection getJuiceTypeface() {
		return mJuiceTypeface;
	}

	/** Multiple custom typefaces support */
	public TypefaceCollection getArchRivalTypeface() {
		return mArchRivalTypeface;
	}

	/** Multiple custom typefaces support */
	public TypefaceCollection getActionManTypeface() {
		return mActionManTypeface;
	}

	/** Multiple custom typefaces support */
	public TypefaceCollection getSystemDefaultTypeface() {
		return mSystemDefaultTypeface;
	}

	/** Multiple custom typefaces support */
	public TypefaceCollection getUbuntuTypeface() {
		return mUbuntuTypeface;
	}
}
