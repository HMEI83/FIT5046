package com.example.fit5046_ass3;

import androidx.annotation.DrawableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdate;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.annotation.Annotation;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationSourceOptions;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.viewannotation.ViewAnnotationManager;

import java.util.ArrayList;
import java.util.List;

public class EventDetailFragment extends Fragment{

    private EventDetailViewModel eventDetailViewModel;
    private TextView eventDetailName;
    private TextView eventDetailLocation;
    private TextView eventDetailStartTime;
    private TextView eventDetailEndTime;
    private TextView eventDetailDesc;
    private TextView eventDetailCategory;

    private MapView eventDetailMap;

    private float eventDetailLat;
    private float eventDetailLng;

    private Button bookingBtn;

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventDetailViewModel = new ViewModelProvider(this).get(EventDetailViewModel.class);
        // TODO: Use the ViewModel

        eventDetailName = requireActivity().findViewById(R.id.eventDetailName);
        eventDetailLocation = requireActivity().findViewById(R.id.eventDetailLocation);
        eventDetailStartTime = requireActivity().findViewById(R.id.eventDetailStartTime);
        eventDetailEndTime = requireActivity().findViewById(R.id.eventDetailEndTime);
        eventDetailDesc = requireActivity().findViewById(R.id.eventDetailDesc);
        eventDetailCategory = requireActivity().findViewById(R.id.eventDetailCategory);

        String eventPosition = String.valueOf(getArguments().getInt("detailPosition"));

        String eventName = getArguments().getString("detailName");
        String eventDescription = getArguments().getString("detailDescription");
        String eventCategory = getArguments().getString("detailEventCategory");
        String eventStartTime = getArguments().getString("detailEventStartTime");
        String eventEndTime = getArguments().getString("detailEventEndTime");
        String eventAddress = getArguments().getString("detailEventAddress");

        String eventLat = getArguments().getString("detailEventLat");
        String eventLng = getArguments().getString("detailEventLng");


        eventDetailName.setText(eventName);
        eventDetailLocation.setText("Location: " + eventAddress);
        eventDetailStartTime.setText("Start Time: " + eventStartTime);
        eventDetailEndTime.setText("End Time: " + eventEndTime);
        eventDetailDesc.setText(eventDescription);
        eventDetailCategory.setText("Category: " + eventCategory);

        eventDetailLat = Float.parseFloat(eventLat);
        eventDetailLng = Float.parseFloat(eventLng);

        eventDetailMap = (MapView) requireActivity().findViewById(R.id.eventDetailMap);

        final Point point = Point.fromLngLat(eventDetailLng, eventDetailLat);

        CameraOptions cameraPosition = new CameraOptions.Builder().zoom(13.0).center(point).build();
        eventDetailMap.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
        eventDetailMap.getMapboxMap().setCamera(cameraPosition);

        bookingBtn = getActivity().findViewById(R.id.bookingButton);

        bookingBtn.setOnClickListener(new View.OnClickListener() {
            String msg = "The Booking is settled";
            @Override
            public void onClick(View view) {

                MainActivity main = (MainActivity) getActivity();
                String userEmail = main.getUserEmail();

                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.eventsFragment);
                UserEvent userEvent = new UserEvent(userEmail,eventPosition,eventName,eventStartTime,eventEndTime,eventAddress);
                eventDetailViewModel.insertUserEvent(userEvent);

                System.out.println(userEvent.getEventName());


            }
        });


    }


//    private void bitmapFromDrawableRes( Context context, @DrawableRes int resourceId){
//        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId));
//    }
//
//    private Bitmap convertDrawableToBitmap(Drawable sourceDrawable) {
//
//        Drawable.ConstantState constantState = sourceDrawable.getConstantState();
//        Drawable drawable = constantState.newDrawable().mutate();
//        Bitmap bitmap = Bitmap.createBitmap(
//                drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
//                Bitmap.Config.ARGB_8888
//        );
//        Canvas canvas = new Canvas();
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//        return bitmap;
//    }

//    private void addAnnotationToMap() {
//        bitmapFromDrawableRes(
//                requireActivity(),
//                R.drawable.red_marker);
//
//        AnnotationSourceOptions annotationApi = a
//        annotationApi = annotation;
//        PointAnnotationManager pointAnnotationManager = annotationApi.create;
//        PointAnnotationOptions pointAnnotationOptions = PointAnnotationOptions(
//            .withPoint(Point.fromLngLat(18.06, 59.31)),
//            .withIconImage(it)
//        );
//        pointAnnotationManager.create(pointAnnotationOptions);
//    }
//
//
}