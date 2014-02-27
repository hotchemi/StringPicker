StringPicker
============

StringPicker is a library to provides a string picker custom view and dialog.

![screen shot](http://gifzo.net/5dMGZEDfpZ.gif)

## Download

Download from maven repository(GitHub) via gradle.

```groovy
repositories {
    mavenCentral()
    maven { url 'https://raw.github.com/hotchemi/StringPicker/master/repository/' }
}
android {
    dependencies {
        compile 'hotchemi.stringpicker:stringpicker:0.0.1'
    }
}
```

## Sample

Please try to move the [sample module](https://github.com/hotchemi/StringPicker/tree/master/sample/).

## How to use

### StringPicker

```xml
<hotchemi.stringpicker.StringPicker
    android:id="@+id/string_picker"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

```java
StringPicker stringPicker = (StringPicker) view.findViewById(R.id.string_picker);
String[] values = new String[] {"北海道","青森県","岩手県","宮城県","秋田県","山形県"};
// set values
stringPicker.setValues(values);

// get value
stringPicker.getCurrentValue()
```

### StringPickerDialog

implements in your activity.

```java
Bundle bundle = new Bundle();
bundle.putStringArray(getString(R.string.string_picker_dialog_values), getStringArray());
StringPickerDialog dialog = new StringPickerDialog();
dialog.setArguments(bundle);
dialog.show(getSupportFragmentManager(), TAG);
```

callback.

```java
@Override
public void onClick(String value) {
    mTextView.setText(value);
}
```

### Custom dialog

If you want to use your own dialog labels, override string xml resources on your application.

```xml
<resources>
    <string name="string_picker_dialog_values">STRING_PICKER_DIALOG_VALUES</string>
    <string name="string_picker_dialog_title">StringPickerDialog</string>
    <string name="string_picker_dialog_ok">OK</string>
    <string name="string_picker_dialog_cancel">Cancel</string>
</resources>
```

## Requirements

Supports Android 2.2 or greater.

## Deploy

```sh
$ ./gradlew uploadArchives
```

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## ChangeLog

- 2014/02/28 v0.0.1 release.

## License

```
Copyright 2014 Shintaro Katafuchi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```