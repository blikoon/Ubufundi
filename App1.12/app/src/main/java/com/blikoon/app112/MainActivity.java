/*
        *Create compound Views from standard views
        *Create a custom class and extend the layout that matches best your goal
        *Create chained Constructors as shown below:
        *
         public TextImageButton(Context context) {
             this(context, null);
          }

          public TextImageButton(Context context, AttributeSet attrs) {
              this(context, attrs, 0);
          }

           public TextImageButton(Context context, AttributeSet attrs, int defaultStyle) {...}

         * The first constructor is used when the view is  created in java code , the rest
         are used when your view is created in xml.Notice that we pass around the attributes
         down to the third constructor which does the actual work.If you don't provide these
         constructors for your view ,it won't work both in code and xml.

         * Initialize the parent class of with one of the system styles

          super(context, attrs, android.R.attr.buttonStyle);

         * This allows your view to fit in the general look of the current theme.
         * Implement whatever logic you're after in your custom compound view


 */

package com.blikoon.app112;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
