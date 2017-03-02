# Ubufundi
Source code for Android Recipies Book 5Th Edition

##App1.1 : Working with styles and themes

* Define your styles in the /res/values/styles.xml file of your project.
* Reuse them on UI components as shown in the res/layout/activity_main.xml file(Example style = "@style/FormCheckBox")
* Themes are like styles in that you define them in /res/values/styles.xm 
* Themes are used either on Activity context or Application context.

* Direct link to app : https://github.com/blikoon/Ubufundi/tree/master/App1.1
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.1/app/src/main/res/layout/activity_main.xml
				

##App1.2 : Show your activity fullscreen when necessary

* Sometimes you need to hide the actionbar and other system views so the user focuses on your content.
* This app shows off different approaches to that.

* Direct link to app :https://github.com/blikoon/Ubufundi/tree/master/App1.2
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.2/app/src/main/java/com/blikoon/app12/MainActivity.java

##App1.3.1 : Create Layouts in XML and add Views at run time(in Java)

* Quick Code:
```java
	LinearLayout layout = (LinearLayout)getLayoutInflater()
           .inflate(R.layout.activity_main, null);
    //Add a new button.Here you can also add complex ui structures
    Button reset = new Button(this);
    reset.setText("Reset Form");
    layout.addView(reset,
                new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
```
						
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.3.1/app/src/main/java/com/blikoon/app131/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.3.1/app/src/main/res/layout/activity_main.xml
				
##App1.3.2 : Creting Completely custom Views
				
* Key overides:
```java			
	@Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
	//Compute the correct dimenstions based on measurement constraints
	//and call setMeasuredDimension with them.Details in the custom view class.
	...
	//MUST call this method with the measured values!
        setMeasuredDimension(width, height);
    }
    
    @Override
    protected void onSizeChanged(int w, int h,
                                 int oldw, int oldh) {
		//Reset your drawn shapes if the size of the view changes.
        ...
    }
	
    @Override
    protected void onDraw(Canvas canvas) {
	//Draw your shapes in here
	
    }
```
	
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.3.2/app/src/main/java/com/blikoon/app132/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.3.2/app/src/main/java/com/blikoon/app132/CustomView.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.3.2/app/src/main/res/layout/activity_main.xml
				
				
				
##App1.4.1 : Animating Views

* we use a ViewPropertyAnimator to animate a view object.
* Call View.animate() and get a ViewPropertyAnimator object
* On it, you can call functions to animate properties like alpha ,translation, rotation...
* Quick code :
```java
	public void onClick(View v) {
        if (viewToAnimate.getAlpha() > 0f) {
            //If the view is visible, slide it out to the right
            viewToAnimate.animate().alpha(0f).translationX(1000f);
        } else {
            //If the view is hidden, do a fade-in in place
            //Property Animations actually modify the view, so
            // we have to reset the view's location first
            viewToAnimate.setTranslationX(0f);
            viewToAnimate.animate().alpha(1f);
        }
    }
```	
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.4.1/app/src/main/java/com/blikoon/app141/MainActivity.java

##App1.4.2 : Use ObjectAnimator to animate a View

* ObjectAnimator is more robust in that it not only animates Views but Also other objects.It also allows you to have updates on the progress of the animation
* This example gives a basic template you can use as a starting point for more complex animations.
* More in depth details here : https://developer.android.com/guide/topics/graphics/prop-animation.html#property-vs-view
* Quick Code:
```java
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeadsImage = BitmapFactory.decodeResource(getResources(), R.drawable.heads);
        mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.tails);
        mFlipImage = (ImageView)findViewById(R.id.flip_image);
        mFlipImage.setImageBitmap(mHeadsImage);
        mIsHeads = true;

        mFlipper = ObjectAnimator.ofFloat(mFlipImage, "rotationY", 0f, 360f);

        mFlipper.setDuration(500);
        mFlipper.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction() >= 0.25f && mIsHeads) {
                    mFlipImage.setImageBitmap(mTailsImage);
                    mIsHeads = false;
                }
                if (animation.getAnimatedFraction() >= 0.75f && !mIsHeads) {
                    mFlipImage.setImageBitmap(mHeadsImage);
                    mIsHeads = true;
                }
            }
        });
    }
```	
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.4.2/app/src/main/java/com/blikoon/app142/MainActivity.java

##App1.4.3 : Use AnimatorSet to chain animations

* Chaining animations in Java Code is verbose so we do that in xml
* Put your animation xml files in the res/animator folder
* Inflate your animation xml file
* Set a target to your animation like this: mFlipper.setTarget(mFlipImage);// In App1.4.2 , ObjectAnimator.ofFloat() did this for us implicitly
* Add an update listener to the correct ObjectAnimator
         //The animations are probably stored in some kind of container and you
         //retrieve them in the order they are stored in inside the flip.xml
         //animation file.
```java		 
         ObjectAnimator flipAnimator = (ObjectAnimator) mFlipper.getChildAnimations().get(0);
         flipAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {...}
```
		 
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.4.3/app/src/main/java/com/blikoon/App143/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.4.3/app/src/main/res/animator/flip.xml
				
				
##App1.5.1 : Animate Layout Changes
				
* Adding the property android:animateLayoutChanges="true" to y our layout view makes its layout changes animated.
* This is enough if you don't want fancy animations.If you do, look at App 1.5.2
*Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.5.1/app/src/main/res/layout/activity_main.xml

##App1.5.2 : Custom Layout Animations

* API Level 11
* Using a custom Layout animation
* Use these to apply custom animations to layout changes
* There are five states of layout changes for which you can
*apply a custom animation on different states:
  * APPEARING: An item that is appearing in the container
  * DISAPPEARING: An item that is disappearing from the container
  * CHANGING: An item that is changing because of a layout change, such as a resize, that doesn’t involve views being added or removed
  * CHANGE_APPEARING: An item changing because of another view appearing
  * CHANGE_DISAPPEARING: An item changing because of another view disappearing

* In this example APPEARING, DISAPPEARING and CHANGE_DISAPPEARING are used.

* How to use these:
```java
          #Create a LayoutTransition and attach it to a view(Layout)
                LayoutTransition transition = new LayoutTransition();
                mContainer.setLayoutTransition(transition);

          #Create Animators and apply them to the states you are interested
           in customizing:
                    Animator appearAnim = ObjectAnimator.ofFloat(null,
                "rotationY", 90f, 0f).setDuration(
                transition.getDuration(LayoutTransition.APPEARING));
                transition.setAnimator(LayoutTransition.APPEARING, appearAnim);
```				
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.5.2/app/src/main/java/com/blikoon/app152/MainActivity.java


##App1.6 : Target multiple screen sizes with your app


* Detailed info from google : https://developer.android.com/guide/practices/screens_support.html
* Screen size : actual physical size, measured as the screen's diagonal.
* Screen Size Categories :
  * small
  * normal
  * large
  * xlarge

* Screen density - dots per inch (dpi) :quantity of pixels within a physical area of the screen
* Screen Density Categories :
  * low
  * medium
  * high
  * extra-high
  * extra-extra-high
  * extra-extra-extra-high.
* Screen density info is usually found on each android device user manual of some kind of documentation.
* A set of six generalized densities:
  * ldpi (low) ~120dpi
  * mdpi (medium) ~160dpi
  * hdpi (high) ~240dpi
  * xhdpi (extra-high) ~320dpi
  * xxhdpi (extra-extra-high) ~480dpi
  * xxxhdpi (extra-extra-extra-high) ~640dp
* Orientation : portrait or landscape
* Resolution : Total number of pixels on a screen
* Density Independent Pixel ( dip):A virtual pixel unit that you should use when defining UI layout,to express layout dimensions or position in a density-independent way.WARNING : dip is DIFFERENT from dpi.Careful here :-)
* Some screen dimensions in dips:
  * xlarge screens are at least 960dp x 720dp
  * large screens are at least 640dp x 480dp
  * normal screens are at least 470dp x 320dp
  * small screens are at least 426dp x 320dp
* Transform from px to dip :  __dp = px * (dpi / 160)__
* For android 3.2 and up ( Specifiers like small, normal, large and xlarge are deprecated.
* Use :
  * [smallestWidth] sw--dp (Examples:sw600dp,sw720dp)
  * [Available screen width ]	w--dp (Examples: w720dp ,w1024dp)
  * [Available screen height] 	h--dp (Examples: h720dp h1024dp) to target a specific screen size.
* This example uses a combination of the following layout files and aliases to make our app work on regular phones ,7 inch tablets and 10 inch tablets :
  * res/layout/main.xml
  * res/layout-land/main.xml
  * res/layout/main_tablet.xml
  * res/values-large-land/layout.xml
  * res/values-sw600dp-land/layout.xml
  * res/values-xlarge/layout.xml
  * res/values-sw720dp/layout.xml
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/java/com/blikoon/app16/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/layout/activity_main.xml
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/layout/main.xml
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/layout/main_tablet.xml
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/values-large-land/layout.xml
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/values-sw600dp-land/layout.xml
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/values-sw720dp/layout.xml
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/values-w820dp/dimens.xml
  * https://github.com/blikoon/Ubufundi/blob/master/App1.6/app/src/main/res/values-xlarge/layout.xml
				
##App1.7 : Show text over images in appropriate colors.

* android.graphics.Bitmap and android.support.v7.graphics.Palette classes are used.
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.7/app/src/main/java/com/blikoon/app17/MainActivity.java