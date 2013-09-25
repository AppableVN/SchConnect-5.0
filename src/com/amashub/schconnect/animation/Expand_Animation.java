package com.amashub.schconnect.animation;

import java.lang.reflect.Method;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Expand_Animation {
	public static final int SPEED_ANIMATION_TRANSITION = 300;
	
	public static Animation expand(final View v, final boolean expand) {
	    try {
	        Method m = v.getClass().getDeclaredMethod("onMeasure", int.class, int.class);
	        m.setAccessible(true);
	        m.invoke(
	            v,
	            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
	            MeasureSpec.makeMeasureSpec(((View)v.getParent()).getMeasuredWidth(), MeasureSpec.AT_MOST)
	        );
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    final int initialHeight = v.getMeasuredHeight();
	    
	    if (expand) {
	    	v.getLayoutParams().height = 0;
	    }
	    else {
	    	v.getLayoutParams().height = initialHeight;
	    }
	    v.setVisibility(View.VISIBLE);
	    
	    Animation a = new Animation() {

	        @Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				// TODO Auto-generated method stub
	        	System.out.println("interpolatedTime:"+ interpolatedTime);
	        	int newHeight = 0;
	            if (expand) {
	            	newHeight = (int) (initialHeight * interpolatedTime);
	            } else {
	            	newHeight = (int) (initialHeight * (1 - interpolatedTime));
	            }
	            v.getLayoutParams().height = newHeight;	            
	            v.requestLayout();
	            
	            if (interpolatedTime == 1){
	            	if(!expand){
	            		v.setVisibility(View.GONE);
	            	}else{
	            		v.setVisibility(View.VISIBLE);
	            	}
	            }
			}

			@Override
	        public boolean willChangeBounds() {
	            return true;
	        }
	    };
	    a.setDuration(SPEED_ANIMATION_TRANSITION);
	    return a;
	}
}
