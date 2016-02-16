# RingProgress
一个环状的ProgressBar
 
* 可以自定义圆环完成状态和未完成状态的宽度，颜色
* 可以自定义起始的角度
* 可以自定义圆环内文字的文字大小，颜色。


###主要的属性

		<attr name="ring_progress" format="integer"/>
        <attr name="ring_max" format="integer"/>
        <attr name="finished_color" format="color"/>
        <attr name="unfinished_color" format="color"/>
        <attr name="finished_stroke_width" format="dimension"/>
        <attr name="unfinished_stroke_width" format="dimension"/>
        <attr name="inner_text" format="string"/>
        <attr name="inner_text_size" format="dimension"/>
        <attr name="inner_text_color" format="color"/>
        <attr name="starting_degree" format="integer"/>
        <attr name="background_color" format="color"/>


###用法
		<com.brilliantbear.ringprogressbar.RingProgress
        	android:id="@+id/ring_progress"
        	android:layout_width="320dp"
        	android:layout_height="320dp"
        	android:layout_centerInParent="true"
        	app:background_color="@android:color/transparent"
        	app:finished_color="#8800FF00"
        	app:finished_stroke_width="15dp"
        	app:inner_text="RingProgress"
        	app:inner_text_color="@color/colorPrimary"
        	app:inner_text_size="35sp"
        	app:ring_progress="50"
        	app:starting_degree="-90"
        	app:unfinished_color="#880000FF"
        	app:unfinished_stroke_width="8dp"
		/>



