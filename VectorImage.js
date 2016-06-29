import { PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

var iface = {
  name: 'VectorImage',
  propTypes: {
    settings: PropTypes.object
  },
};

module.exports = requireNativeComponent('RCTVectorImage', iface,
  {nativeOnly: {
        'testID': true,
        'accessibilityComponentType': true,
        'renderToHardwareTextureAndroid': true,
        'translateY': true,
        'translateX': true,
        'accessibilityLabel': true,
        'accessibilityLiveRegion': true,
        'importantForAccessibility': true,
        'rotation': true,
        'opacity': true,
        'onLayout': true
        }
    });
