package com.example.mindscape_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Write_post extends AppCompatActivity {

    private Uri currentUri;
    ImageView imageView, back;
    Button button;
    EditText head,content;
    String hdata;
    String cdata;
    StorageReference mstorage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        imageView = findViewById(R.id.imageSelect);
        head=findViewById(R.id.heading);
        content=findViewById(R.id.content);
        back=findViewById(R.id.BackIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        mstorage=FirebaseStorage.getInstance().getReference();

        button = findViewById(R.id.button1);
        button.setOnClickListener(v -> {
                selectedImage(currentUri);
                head.getText().clear();
                content.getText().clear();
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.camera);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select photo"),1);
        });
    }
    @Override
    public void onActivityResult(
            int requestCode, int resultCode, final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {

            Log.i("Bookdetail", "select image");
        } else {

            // Get photo picker response for single select.
            currentUri = data.getData();
            // Do stuff with the photo/video URI.
            imageView.setImageURI(currentUri);
        }
    }

    private void selectedImage(Uri data)  {

        hdata=head.getText().toString();
        cdata=content.getText().toString();

        StorageReference reference= mstorage.child("Imageupload/"+System.currentTimeMillis()+".png");
        reference.putFile(data)
                .addOnProgressListener(snapshot -> {

                }) .addOnSuccessListener((UploadTask.TaskSnapshot taskSnapshot) -> {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete()) ;
                    Uri url = uriTask.getResult();

                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Blog");
                    BlogModel model= new BlogModel(hdata,cdata,url.toString());
                    myRef.child("detail").push().setValue(model);
                    Toast.makeText(getApplicationContext(),"uploaded successfully",Toast.LENGTH_SHORT).show();

                });
    }
}