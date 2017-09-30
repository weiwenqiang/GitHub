# WoWoViewPager

[![Developer](https://img.shields.io/badge/Developer-Nightonke-red.svg)](https://github.com/Nightonke)
[![Demo](https://img.shields.io/badge/Demo-Download-orange.svg)](https://github.com/Nightonke/WoWoViewPager#demo)
[![Download](https://api.bintray.com/packages/nightonke/maven/wowo-viewpager/images/download.svg)](https://bintray.com/nightonke/maven/wowo-viewpager)
[![Lisense](https://img.shields.io/badge/License-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0)

<img src="https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/GuidePage1.gif"> <img src="https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/GuidePage2.gif">

WoWoViewPager combines ViewPager and Animations to provide a simple way to create applications' guide pages. When users are dragging WoWoViewPager, they become the director of the applications. The above gifs show how WoWoViewPager looks like, it supports some simple animations like translation, scale, alpha, background color animations, and moreover, some complicate animations like gif-playing, svg-drawing and path-drawing animations with corresponding custom views.

## Note
1. Here comes the 2.0.0 version! The old versions(1.0.2 or below) are deprecated. The new version provides plenty features for convenient usage and efficiency improvement.
1. Thanks [SCViewPager](https://github.com/sacot41/SCViewPager) for giving me inspiration for codes.
1. Thanks [JazzHands](https://github.com/IFTTT/JazzHands) for giving me inspiration for animations.
1. Thanks [konmik](https://github.com/konmik) for providing a better HSV-animation.

## Gradle & Maven
```
compile 'com.nightonke:wowoviewpager:2.0.0'
```
```
<dependency>
  <groupId>com.nightonke</groupId>
  <artifactId>wowoviewpager</artifactId>
  <version>2.0.0</version>
  <type>pom</type>
</dependency>
```

## Demo
<img src="https://github.com/Nightonke/WoWoViewPager/blob/master/Apk/WoWo%20in%20Fir.png" width="200">  
Or by link:  

[WoWoViewPager V2.0.0 in Github](https://github.com/Nightonke/WoWoViewPager/blob/master/Apk/WoWo%20V2.0.0.apk?raw=true)  

[WoWoViewPager V2.0.0 in Fir](http://fir.im/wowoviewpager)  

## Wiki
Check the [wiki](https://github.com/Nightonke/WoWoViewPager/wiki) to use WoWoViewPager.

### Documentation Chapters
#### [Basic Usage](https://github.com/Nightonke/WoWoViewPager/wiki/Basic-Usage)
#### [Ease](https://github.com/Nightonke/WoWoViewPager/wiki/Ease)
#### [Chameleon](https://github.com/Nightonke/WoWoViewPager/wiki/Chameleon)
#### [Typewriter](https://github.com/Nightonke/WoWoViewPager/wiki/Typewriter)
#### Basic Animations
1. [Position Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Position-Animation)
1. [Position 3D Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Position-3D-Animation)
1. [Translation Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Translation-Animation)
1. [Translation 3D Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Translation-3D-Animation)
1. [Scale Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Scale-Animation)
1. [Alpha Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Alpha-Animation)
1. [Rotation Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Rotation-Animation)
1. [Elevation Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Elevation-Animation)

#### TextView Animations
1. [TextView TextSize Animation](https://github.com/Nightonke/WoWoViewPager/wiki/TextView-TextSize-Animation)
1. [TextView TextColor Animation](https://github.com/Nightonke/WoWoViewPager/wiki/TextView-TextColor-Animation)
1. [TextView Text Animation](https://github.com/Nightonke/WoWoViewPager/wiki/TextView-Text-Animation)

#### Color Animations
1. [Background Color Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Background-Color-Animation)
1. [Shape Color Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Shape-Color-Animation)
1. [State-List Color Animation](https://github.com/Nightonke/WoWoViewPager/wiki/State-List-Color-Animation)
1. [Layer-List Color Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Layer-List-Color-Animation)

#### [Path Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Path-Animation)

#### [WoWoViewPager Attributes](https://github.com/Nightonke/WoWoViewPager/wiki/WoWoViewPager-Attributes)

#### Interface Expansibility
1. [Custom Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Custom-Animation)
1. [Interface Animation](https://github.com/Nightonke/WoWoViewPager/wiki/Interface-Animation)
1. [SVG Animation](https://github.com/Nightonke/WoWoViewPager/wiki/SVG-Animation)
1. [GIF Animation](https://github.com/Nightonke/WoWoViewPager/wiki/GIF-Animation)

#### [Version History](https://github.com/Nightonke/WoWoViewPager/wiki/Version-History)
#### [How WoWoViewPager Works](https://github.com/Nightonke/WoWoViewPager/wiki/How-WoWoViewPager-Works)

## Issues & Feedbacks
Try to tell me the bugs or enhancements about WoWoViewPager, or contact me with Nightonke@outlook.com / 2584541288@qq.com. Before doing that, having a careful read on [read-me](https://github.com/Nightonke/WoWoViewPager), [wiki](https://github.com/Nightonke/WoWoViewPager/wiki) and [issues](https://github.com/Nightonke/WoWoViewPager/issues) is really helpful.

## About Versions
Version 1.0.2 or below use the old api of WoWoViewPager. From version 2.0.0, builder-pattern was used in WoWoViewPager and gif-playing, svg-drawing animations and more features are supported. I strongly suggest to use version 2.0.0 or above. If you still want to use version 1.0.2 or below, try to check the old documents([English](https://github.com/Nightonke/WoWoViewPager/blob/master/DEPRECATED-README-EN.md)|[中文文档](https://github.com/Nightonke/WoWoViewPager/blob/master/DEPRECATED-README-ZH.md)). 

## About Me
[Nightonke](http://huangweiping.me/)
