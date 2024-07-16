import React from 'react';
import {
  SafeAreaView,
  ActivityIndicator,
} from 'react-native';
import colors from 'shared/theme/colors';

const LoadingScreen = () => {
  return (
    <SafeAreaView style={{justifyContent: 'center', alignItems: 'center'}}>
      <ActivityIndicator size={'large'}/>
    </SafeAreaView>
  )
}

export default LoadingScreen