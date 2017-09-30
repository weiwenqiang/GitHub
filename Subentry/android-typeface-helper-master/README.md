# Android Typeface Helper

Android lacks proper support for custom typefaces. Most obvious method of defining typeface for UI elements via XML attributes is missing from default framework views. This library makes it a lot easier to set custom typefaces from java code - one time initialization inside Application subclas and then applying custom typeface to all `View`'s via `typeface()` static method call.

![alt text](https://raw.github.com/norbsoft/android-typeface-helper/master/readme_screen.png "custom fonts")

**Sample app** (sources available in `sample` folder):

<a href="https://play.google.com/store/apps/details?id=com.norbsoft.typefacehelper.sample">
  <img alt="Get it on Google Play"
       src="http://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
</a>

## Features
* Apply custom typefaces to whole view hierarchy via single call.
* Super easy initialization in Application subclass via `TypefaceHelper.init()`
* `TypefaceHelper.typeface()` applies custom typeface to all `TextView`'s inside `Activity` or `ViewGroup`
* `TypefaceHelper` can also apply custom typeface to `View`, or create `SpannableString` with custom font from `Strning`/`CharSequence`
* Support for multiple typefaces: `TypefaceHelper` can use default `TypefaceCollection` passed via `init()` or can be parametrized with other `TypefaceCollection`
* Support for textStyles: `Typeface.NORMAL`, `Typeface.BOLD`, `Typeface.ITALIC` and `Typeface.BOLD_ITALIC`
* Support for dynamic typeface changes
* Support for custom typefaces in ActionBar title

## Installation
Via gradle:
```
compile 'com.norbsoft.typefacehelper:library:0.9.0'
```

Alternative download AAR [here](http://search.maven.org/remotecontent?filepath=com/norbsoft/typefacehelper/library/0.9.0/library-0.9.0.aar)

## Usage

* Put your custom true type fonts (TTF) in `assets/fonts` folder
* Pass `TypefaceCollection` to `TypefaceHelper.init()` in your `Application` subclass:
```java
@Override public void onCreate() {
	super.onCreate();
	// Initialize typeface helper
	TypefaceCollection typeface = new TypefaceCollection.Builder()
	        .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/ubuntu/Ubuntu-R.ttf"))
	        .create();
	TypefaceHelper.init(typeface);
}
``` 
* Apply custom typefaces to `Activity`, `ViewGroup` or `View`:
```java
@Override protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_layout);
	// Apply custom typefaces!
	TypefaceHelper.typeface(this);
}   
```         
## Advanced usage
### Static import for `typeface()` method
You can use static import for `typeface()` method:
```java
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface; 
```
and then use it without referencing `TypefaceHelper` class:
```java
View v = inflater.inflate(R.layout.myview);
typeface(v);

```

### Change font in `ActionBar` title
Pass `ActionBar` instance along with `SpannableString` with custom typeface to `ActionBarHelper.setTitle()` helper:
```java
ActionBarHelper.setTitle(
	getSupportActionBar(), 
	typeface(this, R.string.lorem_ipsum_title));
```

### Apply in AdapterView (ListView, Spinner)
Each time `ConvertView` is created inside adapter, it needs to be styled via `typeface()` call
```java
listView.setAdapter(new BaseAdapter() {
    String[] items = { "Item 1", "Item 2", "Item 3" };
    
    @Override public int getCount() {
        return items.length;
    }
    @Override public Object getItem(int position) {
        return items[position];
    }
    @Override public long getItemId(int position) {
        return items[position].hashCode();
    }
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            // Apply custom typeface!
            typeface(convertView);
        }

        ((TextView) convertView).setText(items[position]);
        return convertView;
    }
});
```
### Use multiple typefaces and styles
First, define and initialize `TypefaceCollection` for each typeface you intend to use in your `Application` subclass:
```java
public class MyApplication extends Application {

	private TypefaceCollection mArchRivalTypeface;
	private TypefaceCollection mUbuntuTypeface;

	@Override public void onCreate() {
		super.onCreate();

		// Initialize Arch Rival typeface
		mArchRivalTypeface = new TypefaceCollection.Builder()
			.set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), 
				"fonts/arch_rival/SF_Arch_Rival.ttf"))
			.set(Typeface.BOLD, Typeface.createFromAsset(getAssets(),
				"fonts/arch_rival/SF_Arch_Rival_Bold.ttf"))
			.set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(),
				"fonts/arch_rival/SF_Arch_Rival_Italic.ttf"))
			.set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(),
				"fonts/arch_rival/SF_Arch_Rival_Bold_Italic.ttf"))
			.create();

		// Initialize Ubuntu typeface
		mUbuntuTypeface = new TypefaceCollection.Builder()
			.set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(),
				"fonts/ubuntu/Ubuntu-R.ttf"))
			.set(Typeface.BOLD, Typeface.createFromAsset(getAssets(),
				"fonts/ubuntu/Ubuntu-B.ttf"))
			.set(Typeface.ITALIC, Typeface.createFromAsset(getAssets(), 
				"fonts/ubuntu/Ubuntu-RI.ttf"))
			.set(Typeface.BOLD_ITALIC, Typeface.createFromAsset(getAssets(),
				"fonts/ubuntu/Ubuntu-BI.ttf"))
			.create();

		// Load helper with default custom typeface (single custom typeface)
		TypefaceHelper.init(mUbuntuTypeface);
	}

	/** Getter for Arch Rival typeface */
	public TypefaceCollection getArchRivalTypeface() {
		return mArchRivalTypeface;
	}

	/** Getter for Ubuntu typeface */
	public TypefaceCollection getUbuntuTypeface() {
		return mUbuntuTypeface;
	}
}
```
Then, call `typeface()` and pass custmo `TypefaceCollection`:
```java
typeface(findViewById(R.id.label_title), 
	((MyApplication) getApplication()).getArchRivalTypeface());
	
typeface(findViewById(R.id.label_subtitle), 
	((MyApplication) getApplication()).getUbuntuTypeface());
```

### Change typefaces dynamically
Typeface changes each time `typeface()` is called - it can be used from `onClick()` method:
```java
@Override public void onClick(View v) {
	switch (v.getId()) {
	case R.id.btn_font_ubuntu:
		typeface(findViewById(R.id.label_title),
			((MyApplication) getApplication()).getUbuntuTypeface());
		break;
	case R.id.btn_font_arch_rival:
		typeface(findViewById(R.id.label_title),
			((MyApplication) getApplication()).getArchRivalTypeface());
		break;
	}
}
```

## License

Android Typeface Helper is licensed under [http://www.apache.org/licenses/LICENSE-2.0](Apache License 2.0.)
