/*
        Screen size specific layouts
        Detailed info from google : https://developer.android.com/guide/practices/screens_support.html
        Some metrics:
            *Screen size : actual physical size, measured as the screen's diagonal.
               Categories : small, normal, large, xlarge

            *Screen density - dots per inch (dpi) :quantity of pixels within a physical area of the screen
                Categories : low, medium, high, extra-high, extra-extra-high, and extra-extra-extra-high.
                Screen density info is usually found on each android device user manual of some kind of documentation.

                A set of six generalized densities:

                    ldpi (low) ~120dpi
                    mdpi (medium) ~160dpi
                    hdpi (high) ~240dpi
                    xhdpi (extra-high) ~320dpi
                    xxhdpi (extra-extra-high) ~480dpi
                    xxxhdpi (extra-extra-extra-high) ~640dp

            *Orientation : portrait or landscape


            *Resolution : Total number of pixels on a screen

            *Density Independent Pixel ( dip):A virtual pixel unit that you should use when defining UI layout,
             to express layout dimensions or position in a density-independent way.
             WARNING : dip is DIFFERENT from dpi.Careful here :-)

             Some screen dimensions in dips
                    xlarge screens are at least 960dp x 720dp
                    large screens are at least 640dp x 480dp
                    normal screens are at least 470dp x 320dp
                    small screens are at least 426dp x 320dp

             Transform from px to dip :  dp = px * (dpi / 160)

            *For android 3.2 and up ( Specifiers like small, normal, large and xlarge are deprecated.
               Use [smallestWidth] sw<N>dp (Examples:sw600dp,sw720dp) ,
                   [Available screen width ]	w<N>dp (Examples: w720dp ,w1024dp) , and
                   [Available screen height] 	h<N>dp (Examples: h720dp h1024dp)
               to target a specific screen size.

            *This example uses a combination of the following layout files and aliases to make our
            * app work on regular phones ,7 inch tablets and 10 inch tablets
               Layout file structure:
                        res/layout/main.xml
                        res/layout-land/main.xml
                        res/layout/main_tablet.xml
                        res/values-large-land/layout.xml
                        res/values-sw600dp-land/layout.xml
                        res/values-xlarge/layout.xml
                        res/values-sw720dp/layout.xml


 */

package com.blikoon.app16;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
