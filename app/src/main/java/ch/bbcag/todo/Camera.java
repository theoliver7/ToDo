package ch.bbcag.todo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zjorgm on 26.06.2015.
 */
public class Camera {
    private int TAKE_PICTURE = 1;
    private Uri uriSavedImage;



    public void createCamera(Activity activity){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, TAKE_PICTURE);


        //folder stuff
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
        imagesFolder.mkdirs();

        File image = new File(imagesFolder, "QR_" + timeStamp + ".png");
        uriSavedImage = Uri.fromFile(image);

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
    }
    public Uri getUriSavedImage() {
        return uriSavedImage;
    }

    public void setUriSavedImage(Uri uriSavedImage) {
        this.uriSavedImage = uriSavedImage;
    }
}
