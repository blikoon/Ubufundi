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
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.5.1/app/src/main/res/layout/activity_main.xml

##App1.5.2 : Custom Layout Animations

* API Level 11
* Using a custom Layout animation
* Use these to apply custom animations to layout changes
* There are five states of layout changes for which you can
* apply a custom animation on different states:
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
  
##App1.8.1 : Use RecyclerViews to show subViews in Lists.

* Quick Code:
```java
 mRecyclerView = new RecyclerView(this);
        mHorizontalManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mVerticalManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mVerticalGridManager = new GridLayoutManager(this,
                2, /* Number of grid columns */
                LinearLayoutManager.VERTICAL, /* Orient grid vertically */
                false);
        mHorizontalGridManager = new GridLayoutManager(this,
                3, /* Number of grid rows */
                LinearLayoutManager.HORIZONTAL, /* Orient grid horizontally */
                false);
        //Connector line decorations for vertical grid
        mConnectors = new ConnectorDecoration(this);
        //Stagger the vertical grid
        mVerticalGridManager.setSpanSizeLookup(new GridStaggerLookup());
        mAdapter = new SimpleItemAdapter(this);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        //Apply margins decoration to all collections
        mRecyclerView.addItemDecoration(new InsetDecoration(this));
        //Default to vertical layout
        selectLayoutManager(R.id.action_vertical);
        setContentView(mRecyclerView);
```
* Decide how your  items are laid out using LayoutManagers
* Supported LayoutManagers:
  * LinearLayoutManagers
  * GridLayoutManagers
*   Interface between the View and actual data using adapters
*   Adapters have to extend RecyclerView.Adapter
*   Adapters have to implement three key methods:
  * onCreateViewHolder() : Loads the layout file that embodies the looks of a single item in the list
  * onBindViewHolder () : Stuffs the data in the item from the data set in the adapter
  * getItemCount () : used to report the number of items to the RecyclerView

* Notify the view of changes in data by calls to:
  * notifyItemInserted(position)
  * notifyItemRemoved(position)
* Add a subclass of RecyclerView.ViewHolder that implements the logic to manipulate single viewItems
* Control the item span count on each row/column in GridLayoutManager using GridStaggerLookup
* Control the spacing between items using RecyclerView.ItemDecoration
* ItemDecoration can be customized even more to add stunning drawn effects( Look at ConnectorDecoration)
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.8.1/app/src/main/java/com/blikoon/app181/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.8.1/app/src/main/java/com/blikoon/app181/SimpleItemAdapter.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.8.1/app/src/main/java/com/blikoon/app181/GridStaggerLookup.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.8.1/app/src/main/java/com/blikoon/app181/InsetDecoration.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.8.1/app/src/main/java/com/blikoon/app181/ConnectorDecoration.java

  
##App1.9 : Notify user of empty data sets(still RecyclerView)

* Quick Code:
```java
@Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SIMPLE_ITEM:
                holder.textView.setText("Item nr " + (position + 1) + ". Tap to remove.");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int itemPosition = holder.getAdapterPosition();
                        itemCount--;
                        notifyItemRemoved(itemPosition);
                        if(itemCount == 0) {
                            notifyItemInserted(0);
                        }
                    }
                });
                break;
            case EMPTY_ITEM:
                holder.refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notifyItemRemoved(0);
                        itemCount = 10;
                        notifyItemRangeInserted(0, 10);
                    }
                });
                break;
        }
    }
```


* Show a visual representation that the data set is empty
* Show some kind or refresh button to load data into the view
* Override getItemViewType(int position) to let the adapter know which kind of view it is dealing with.
* NOTE : There is not actual data in the adapter of this example.In onBindViewHolder, we are simply tricking the view into thinking it has more items to display using notifyItemRangeInserted(0, 10)
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.9/app/src/main/java/com/blikoon/app19/EmptyStateAdapter.java
  
  
##App1.10 :Show images in ListViews using CardView and RecyclerView

* Quick Code:
```java
@Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        String title = context.getString(R.string.title_text);
        holder.titleView.setText(title);
        holder.descriptionView.setText(R.string.ipsum_lorem);
        if (selectedPosition == position) {
            holder.descriptionView.setVisibility(View.VISIBLE);
        } else {
            holder.descriptionView.setVisibility(View.GONE);
        }
        holder.imageView.setImageResource(R.drawable.photo);
        Palette.from(bitmap)
                .generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch swatch = palette.getDarkVibrantSwatch();
                        if (swatch == null) {
                            swatch = palette.getSwatches().get(0);
                        }
                        int titleTextColor = Color.WHITE;
                        if (swatch != null) {
                            titleTextColor = swatch.getTitleTextColor();
                            titleTextColor = ColorUtils.setAlphaComponent(titleTextColor, 255);
                        }
                        holder.titleView.setTextColor(titleTextColor);
                    }
                });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (selectedPosition == position) {
                    selectedPosition = -1;
                    notifyItemChanged(position);
                } else {
                    int oldSelectedPosition = selectedPosition;
                    selectedPosition = position;
                    notifyItemChanged(oldSelectedPosition);
                    notifyItemChanged(selectedPosition);
                }
            }
        });
    }
```   
* Use android.support.v7.widget.CardView and android.support.percent.PercentRelativeLayout to wrap around the layout used by your ViewHolder subclass
* CardView gives you those nice Material rectangles you can stuff your viewItem in
* PercentRelativeLayout allows easy resizing of items for 1:1 or 16:9 ration required by Material design
* Only show item image and title when user is scrolling and display detailed description when the item is explicitly tapped and becomes the "current item".This is taken care of in the onBindViewHolder override.
* The color of the overlayed image on top of the image is taken care of the same way we did in https://github.com/blikoon/Ubufundi#app17--show-text-over-images-in-appropriate-colors
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.10/app/src/main/java/com/blikoon/app110/CardViewAdapter.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.10/app/src/main/res/layout/cardview_item.xml

##App1.11 :Show Section Headers in RecyclerView

* Quick Code :
```java
@Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int steps = 0;
        for (int i = 0; i < sections.size(); i++) {
            Pair<String, String[]> section = sections.get(i);
            if(steps == position) {
                ((HeaderHolder) holder).getView().setText(section.first);
                return;
            }
            steps++;

            for (int j = 0; j < section.second.length; j++) {
                if(steps == position) {
                    ((ItemHolder) holder).getView().setText(section.second[j]);
                    return;
                }
                steps++;
            }
        }
    }
```

* Use the  getItemViewType(int position) override to specify different view types
* Use differently styled ViewHolders in the onBindViewHolder override
* In this example ,we have a header viewType and an item viewType
* The layout for the header viewtype ViewHolder is styled with a blueish background to highlight it
* You can add in more customized headers and item layouts, this example is just making a point.
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.11/app/src/main/java/com/blikoon/app111/SectionsAdapter.java
 

##App1.12 :Create compound Views(Widgets) from standard views

* Quick Code:
```java
public TextImageButton(Context context, AttributeSet attrs, int defaultStyle) {
        //Initialize the parent layout with the system's button style
        // This sets the clickable attributes and button background to match
        // the current theme.
        super(context, attrs, android.R.attr.buttonStyle);
        imageView = new ImageView(context, attrs, defaultStyle);
        textView = new TextView(context, attrs, defaultStyle);
        //create layout parameters
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        //Add the views
        this.addView(imageView, params);
        this.addView(textView, params);

        //If image is present, switch to image mode
        if (imageView.getDrawable() != null) {
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        }

    }
```
* Create a custom class and extend the layout that matches best your goal
* Create chained Constructors as shown below:
```java
         public TextImageButton(Context context) {
             this(context, null);
          }

          public TextImageButton(Context context, AttributeSet attrs) {
              this(context, attrs, 0);
          }

           public TextImageButton(Context context, AttributeSet attrs, int defaultStyle) {...}
```
* The first constructor is used when the view is  created in java code , the rest are used when your view is created in xml.Notice that we pass around the attributes down to the third constructor which does the actual work.If you don't provide these constructors for your view ,it won't work both in code and xml.
* Initialize the parent class of with one of the system styles
```java
          super(context, attrs, android.R.attr.buttonStyle);
```		  
* This allows your view to fit in the general look of the current theme.
* Implement whatever logic you're after in your custom compound view 
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.12/app/src/main/java/com/blikoon/app112/TextImageButton.java
  
  
##App1.13.0 :Animate activity transitions per Activity basis
  
* Quick Code:
```java  
  @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
    }
```
* Animate activity transitions per activity basis
* Call overridePendingTransition right after you call startActivity() or finish() and the animations you pass in the arguments are applied
* Animations are defined in resources : res/anim/
* Animations can also be applied per application.This is done at the theme level.App1.13.1 shows that.
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.0/app/src/main/java/com/blikoon/app113/MainActivity.java 

  
##App1.13.1 :Animate activity for entire application

* Quick Code:
```xml
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="android:windowAnimationStyle">
            @style/ActivityAnimation</item>
    </style>

    <style name="ActivityAnimation"
        parent="@android:style/Animation.Activity">
        <item name="android:activityOpenEnterAnimation">
            @anim/activity_open_enter</item>
        <item name="android:activityOpenExitAnimation">
            @anim/activity_open_exit</item>
        <item name="android:activityCloseEnterAnimation">
            @anim/activity_close_enter</item>
        <item name="android:activityCloseExitAnimation">
            @anim/activity_close_exit</item>
    </style>

</resources>
```

* Animations are defined in res/anim
* Add the item:
```xml
   <item name="android:windowAnimationStyle">
            @style/ActivityAnimation</item>
```			
       to your theme
* The ActivityAnimation style contains the animations we defined
* With the theme applied in the manifest ,now every activity transition should be animated.  
* Relevant files:
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.1/app/src/main/res/values/styles.xml

  
##App1.13.2 :Animate Fragment transitions with custom animations


* This example works for fragments from the android support library(v4)
* The details of fragments are explained here : http://www.blikoon.com/tutorials/android-working-with-fragments
* Use it by setting a custom animation to the FragmentTransaction object as shown

```java 
     FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        //Add the animation
        transaction.setCustomAnimations(R.anim.activity_open_enter,
                R.anim.activity_open_exit);

        //end of animation

        transaction
                .replace(R.id.fragment_container, fragment2);
        transaction.commit();
```
* Tried passing the four open and close animations at once in onFragment1Message but the system just ignored.Had to explicitly set the close animations in fragment2
* We can also handle the fragment animation logic inside the fragment class itself so all transitions of its instances are animated. App1.13.3 does just that.
* !!!!!setCustomAnimations() must be called before add(), replace(), or any other action method, or the animation will not run. It is good practice to simply call this method first in the transaction block.
* Relevant files:
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.2/app/src/main/java/com/blikoon/app1132/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.2/app/src/main/java/com/blikoon/app1132/Fragment2.java
  
  
##App1.13.3 : Bundle the fragment transition animation into the fragment class

* Quick Code:
```java 
@Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                if (enter) {
                    return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                } else {
                    return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                if (enter) {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_close_enter);
                } else {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_close_exit);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
            default:
                if (enter) {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_open_enter);
                } else {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_open_exit);
                }
        }
    }
```
* Bundle your animation logic inside the onCreateAnimation() override.
* WARNING : The animations we see here only work with the support library version of fragments.Note that our imports are android.support.v4.app.Fragment;
* Relevant files:
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.3/app/src/main/java/com/blikoon/app1133/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.3/app/src/main/java/com/blikoon/app1133/SupportFragment.java

 
 
##App1.13.4 : Animate Native Fragments transitions( Not fragments from support libs) 

* Use these if you are not using the support libs and are targeting API11 and up
* IMPORTANT : We are using Native fragments : android.app.Fragment;
* The general flow is the same with minor changes to what is done in support fragments
* Animations now live inside objectAnimator objects
```xml
    <set xmlns:android="http://schemas.android.com/apk/res/android" >
    <objectAnimator
        android:valueFrom="90" android:valueTo="0"
        android:valueType="floatType"
        android:propertyName="rotation"
        android:duration="500"/>
    <objectAnimator
        android:valueFrom="0.0" android:valueTo="1.0"
        android:valueType="floatType"
        android:propertyName="alpha"
        android:duration="500"/>
    </set>
```
* We stuff the fragment inside its container as follows:
```java
           Fragment1 fragment =  new Fragment1();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            //Must be called first!
            ft.setCustomAnimations(R.animator.fragment_enter,
                    R.animator.fragment_exit,
                    R.animator.fragment_pop_enter,
                    R.animator.fragment_pop_exit);
            ft.add(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit(); 
```			
* Relevant files:
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.4/app/src/main/java/com/blikoon/app1134/MainActivity.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.4/app/src/main/res/animator/fragment_enter.xml  



##App1.13.5 : Implement the Animation logic inside a native fragment subclass.

* Quick code :
```java
@Override
    public Animator onCreateAnimator(int transit, boolean enter,
                                     int nextAnim) {
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                if (enter) {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            android.R.animator.fade_in);
                } else {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            android.R.animator.fade_out);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                if (enter) {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_pop_enter);
                } else {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_pop_exit);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
            default:
                if (enter) {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_enter);
                } else {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_exit);
                }
        }
    }
```
* Just like App1.13.4 , the animations are in /res/animator and wrapped in ObjectAnimator objects
* The animations are applied by loading them like this :
```java
                return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_exit);
```							
* Relevant files :
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.5/app/src/main/java/com/blikoon/app1135/NativeFragment.java
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.5/app/src/main/java/com/blikoon/app1135/MainActivity.java  

  
##App1.13.6 : Animate fragment transactions in entire application.  

* You do this at the theme level ( res/values/styles.xml):
```xml	
	<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="android:windowAnimationStyle">
            @style/FragmentAnimation</item>
    </style>

    <style name="FragmentAnimation"
        parent="@android:style/Animation.Activity">
    <item name="android:fragmentOpenEnterAnimation">
        @animator/fragment_enter</item>
    <item name="android:fragmentOpenExitAnimation">
        @animator/fragment_exit</item>
    <item name="android:fragmentCloseEnterAnimation">
        @animator/fragment_pop_enter</item>
    <item name="android:fragmentCloseExitAnimation">
        @animator/fragment_pop_exit</item>
    <item name="android:fragmentFadeEnterAnimation">
        @android:animator/fade_in</item>
        <item name="android:fragmentFadeExitAnimation">
            @android:animator/fade_out</item>
    </style>

</resources>
```
* Use fragments as you do it usually
* Note : Adding fragment transitions to the theme will work only for the native implementation(android.app.Fragment). The Support Library cannot look for these attributes in a theme because they did not exist in earlier platform versions.
* If you wanted to customize both the activity and fragment transitions in the theme, you would do it like this :
```xml
<resources>
<style name="AppTheme" parent="android:Theme.Holo.Light">
<item name="android:windowAnimationStyle">
@style/TransitionAnimation</item>
</style>
<style name="TransitionAnimation"
parent="@android:style/Animation.Activity">
<item name="android:activityOpenEnterAnimation">
@anim/activity_open_enter</item>
<item name="android:activityOpenExitAnimation">
@anim/activity_open_exit</item>
<item name="android:activityCloseEnterAnimation">
@anim/activity_close_enter</item>
<item name="android:activityCloseExitAnimation">
@anim/activity_close_exit</item>
<item name="android:fragmentOpenEnterAnimation">
@animator/fragment_enter</item>
<item name="android:fragmentOpenExitAnimation">
@animator/fragment_exit</item>
<item name="android:fragmentCloseEnterAnimation">
@animator/fragment_pop_enter</item>
<item name="android:fragmentCloseExitAnimation">
@animator/fragment_pop_exit</item>
<item name="android:fragmentFadeEnterAnimation">
@android:animator/fade_in</item>
<item name="android:fragmentFadeExitAnimation">
@android:animator/fade_out</item>
</style>
</resources>
```
* Relevant files : 
  * https://github.com/blikoon/Ubufundi/blob/master/App1.13.6/app/src/main/res/values/styles.xml 




##App1.14.0 : Apply visual Transformations to child views of ViewGroup.

* Quick Code :
```java
private void init() {
    // Enable static transformations so each child will
    // have getChildStaticTransformation() called.
        setStaticTransformationsEnabled(true);
    }
    @Override
    protected boolean getChildStaticTransformation(View child,
                                                   Transformation t) {
        // Clear any existing transformation
        t.clear();
        if (getOrientation() == HORIZONTAL) {
        // Scale children based on distance from left edge
            float delta = 1.0f - ((float) child.getLeft() / getWidth());
            t.getMatrix().setScale(delta, delta, child.getWidth() / 2,
                    child.getHeight() / 2);
        } else {
            // Scale children based on distance from top edge
            float delta = 1.0f - ((float) child.getTop() / getHeight());
            t.getMatrix().setScale(delta, delta, child.getWidth() / 2,
                    child.getHeight() / 2);
            //Also apply a fade effect based on its location
            t.setAlpha(delta);
        }
        return true;
    }
```

* It is possible to add transformations such as rotation, scale and alpha to ViewGroups without resorting to animations.
* This is also very convenient in applying transformations from the context of a parent view such as scale or color that changes with position
* The first step in enabling these transformations is calling
```java
  setStaticTranformationsEnabled(true)
```
during the initialization of the ViewGroup class.(Usually in the constructor)
    
* Then implement the
```java    
          getChildStaticTransformation(View child,Transformation t)
```         
override method and in there apply your transformations.
* You have to return true from this method. This way, the system knows it should apply your transformations to the particular child view.
* Relevant files :
  * One
   