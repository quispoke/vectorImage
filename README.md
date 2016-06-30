# vectorImage
vector image support for react native android

# Installation

First, download the library from npm:

```
npm install react-native-vector-image-android --save
```

## Android

1. in `android/app/build.gradle` add:
   ```
   ...
     dependencies {
     ...
    compile project(':react-native-vector-image-android') // <-- Add this!
    }
   ```

2. in `android/settings.gradle` add:
   ```
   ...
   include ':react-native-vector-image-android'
   project(':react-native-vector-image-android').projectDir = file('../node_modules/react-native-vector-image-android/android')
   ```

3. in `android/app/src/main/java/com/{YOUR_APP_NAME}/MainActivity.java` add:

      ```
    ...
    import com.quispoke.vectorImage.ReactVectorImagePackage; // <--- Add this!
    ...

    public class MainActivity extends ReactActivity {
    ...
        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new ReactVectorImagePackage()    // <-- Add this!
            );
        }
   }
   ```
   
# General Usage

```js
var VectorImage = require('react-native-vector-image-android');
```

```jsx
  <VectorImage
      style={{height: side, width: side}}
      settings={{
          height: Math.floor(side * pixelRatio),
          width: Math.floor(side * pixelRatio),
          resourceName: 'ic_local_bar_48px',
          color : [0xFF, 0x00, 0x00, 0xFF]
      }}>
    </VectorImage>
```
